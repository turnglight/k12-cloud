package k12.base.mybatis.lang;

import k12.base.mybatis.annotation.Column;
import k12.base.mybatis.util.MybatisUtil;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kinginblue on 2017/2/6.
 */
public class SimpleDeleteLangDriver extends XMLLanguageDriver {

    private static final Pattern inPattern = Pattern.compile("\\[#\\{(\\w+)\\}\\]");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        Matcher matcher = inPattern.matcher(script);
        if (matcher.find()) {

            StringBuilder sb = new StringBuilder();

            if (!script.contains("DELETE") && !script.contains("delete")) {
                String tableName = MybatisUtil.requireTableName(parameterType);
                sb.append("DELETE FROM ").append(tableName).append(" ");
            }

            final String tpl = "<if test=\"_field != null\"> AND _column=#{_field}</if>";

            boolean whereExist = script.contains("WHERE") || script.contains("where");
            boolean isGenWhere = false;

            if (!whereExist) {
                sb.append("<trim prefix=\"WHERE\" prefixOverrides=\"AND|OR\"> ");
                for (Field field : parameterType.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Column.class)) {
                        sb.append(tpl.replaceAll("_field", field.getName()).replaceAll("_column", MybatisUtil.getColumnNameFromField(field)));
                        isGenWhere = true;
                    }
                }
                sb.append("</trim>");
            }

            if (!whereExist) {
                // safety check
                if (!isGenWhere) throw new IllegalArgumentException("ambitious DELETE operation!");
            }

            script = matcher.replaceAll(sb.toString());
            script = "<script>" + script + "</script>";
        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}

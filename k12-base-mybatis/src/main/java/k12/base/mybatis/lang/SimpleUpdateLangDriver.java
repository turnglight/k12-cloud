package k12.base.mybatis.lang;

import k12.base.mybatis.annotation.Column;
import k12.base.mybatis.annotation.UpdateTimestamp;
import k12.base.mybatis.annotation.Versioning;
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
public class SimpleUpdateLangDriver extends XMLLanguageDriver {

    private final Pattern inPattern = Pattern.compile("\\[#\\{(\\w+)\\}\\]");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        Matcher matcher = inPattern.matcher(script);
        if (matcher.find()) {

            StringBuilder sb = new StringBuilder();

            if (!script.contains("UPDATE") && !script.contains("update")) {
                String tableName = MybatisUtil.requireTableName(parameterType);
                sb.append("UPDATE ").append(tableName).append(" ");
            }

            boolean whereExist = script.contains("WHERE") || script.contains("where");
            String whereGen = null;

            sb.append("<trim prefix=\"SET\" suffixOverrides=\",\">");

            final String tpl = "<if test=\"_field != null\">_column=#{_field},</if>";
            final String timeTpl = "_column=now(),";
            final String versioningTpl = "_column=_column + 1,";
            final String whereTpl = "WHERE _column=#{_field}";

            for (Field field : parameterType.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {

                    // the column name
                    String columnName = MybatisUtil.getColumnNameFromField(field);

                    if (!MybatisUtil.isIdColumn(field)) {

                        // adding common columns
                        sb.append(tpl.replaceAll("_field", field.getName()).replaceAll("_column", columnName));

                    } else if (!whereExist) {
                        // auto gen where id={id} if where absent!
                        whereGen = whereTpl.replaceAll("_field", field.getName()).replaceAll("_column", columnName);
                    }

                    // auto set update time when annotate with @UpdateTimestamp
                    if (field.isAnnotationPresent(UpdateTimestamp.class)) {
                        sb.append(timeTpl.replaceAll("_column", columnName));
                    }

                    // supports versioning
                    if (field.isAnnotationPresent(Versioning.class)) {
                        sb.append(versioningTpl.replaceAll("_column", columnName));
                    }
                }
            }
            sb.append("</trim>");

            if (!whereExist) {
                // safety check
                if (null == whereGen) throw new IllegalArgumentException("ambitious UPDATE operation!");

                // auto append where if where absent!
                sb.append(" ").append(whereGen);
            }

            script = matcher.replaceAll(sb.toString());
            script = "<script>" + script + "</script>";
        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}

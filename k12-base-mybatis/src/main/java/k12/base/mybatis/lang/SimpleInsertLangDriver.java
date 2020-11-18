package k12.base.mybatis.lang;

import k12.base.mybatis.annotation.Column;
import k12.base.mybatis.annotation.CreationTimestamp;
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
public class SimpleInsertLangDriver extends XMLLanguageDriver {

    private final Pattern inPattern = Pattern.compile("\\[#\\{(\\w+)\\}\\]");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        Matcher matcher = inPattern.matcher(script);
        if (matcher.find()) {

            StringBuilder keySb = new StringBuilder();
            StringBuilder valSb = new StringBuilder();

            if (!script.contains("INSERT") && !script.contains("insert")) {
                String tableName = MybatisUtil.requireTableName(parameterType);
                keySb.append("INSERT INTO ").append(tableName).append(" ");
            }

            keySb.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
            valSb.append("<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");

            final String keyTpl = "<if test=\"_field != null\">_column,</if>";
            final String valTpl = "<if test=\"_field != null\">#{_field},</if>";
            final String timeKeyTpl = "_column,";
            // final String timeValTpl = "now(),";
            final String timeValAdaptTpl =
                    "<choose>" +
                            "<when test=\"_field != null\">#{_field},</when>" +
                            "<otherwise>now(),</otherwise>" +
                    "</choose>";
            final String versioningKeyTpl = "_column,";
            final String versioningValTpl = "0,";

            for (Field field : parameterType.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {

                    // the column name
                    String columnName = MybatisUtil.getColumnNameFromField(field);

                    // auto set add time stamp if the column annotated with @CreationTimestamp or @UpdateTimestamp
                    if (field.isAnnotationPresent(CreationTimestamp.class) || field.isAnnotationPresent(UpdateTimestamp.class)) {
                        keySb.append(timeKeyTpl.replaceAll("_column", columnName));
                        // valSb.append(timeValTpl);
                        valSb.append(timeValAdaptTpl.replaceAll("_field", field.getName()).replaceAll("_column", columnName));
                        continue;
                    }

                    // auto set 0 on versioning column
                    if (field.isAnnotationPresent(Versioning.class)) {
                        keySb.append(versioningKeyTpl.replaceAll("_column", columnName));
                        valSb.append(versioningValTpl);
                        continue;
                    }

                    {
                        // adding common columns
                        keySb.append(keyTpl.replaceAll("_field", field.getName()).replaceAll("_column", columnName));
                        valSb.append(valTpl.replaceAll("_field", field.getName()));
                    }

                }
            }

            keySb.append("</trim>");
            valSb.append("</trim>");

            keySb.append(valSb);

            script = matcher.replaceAll(keySb.toString());
            script = "<script>" + script + "</script>";
        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}

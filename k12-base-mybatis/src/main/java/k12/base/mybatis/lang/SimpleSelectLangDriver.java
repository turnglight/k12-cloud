package k12.base.mybatis.lang;

import k12.base.mybatis.annotation.*;
import k12.base.mybatis.data.BetweenPair;
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
public class SimpleSelectLangDriver extends XMLLanguageDriver {

    private final Pattern inPattern = Pattern.compile("\\[#\\{(\\w+)\\}\\]");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        Matcher matcher = inPattern.matcher(script);
        if (matcher.find()) {

            StringBuilder sb = new StringBuilder();

            if (!script.contains("SELECT") && !script.contains("select")) {// 如果没有指定查询字段，则*
                sb.append("SELECT * ");
            }

            if (!script.contains("FROM") && !script.contains("from")) {// 补全 from table name
                String tableName = MybatisUtil.requireTableName(parameterType);
                sb.append(" FROM ").append(tableName).append(" ");
            }

            sb.append("<trim prefix=\"WHERE\" prefixOverrides=\"AND|OR\"> ");

            final String tpl = "<if test=\"_field != null\"> AND _column=#{_field}</if>";
            final String queryLikeTpl = "<if test=\"_field != null\"> AND _column LIKE #{_field}</if>";
            final String queryBetweenTpl = "<if test=\"_field != null\"> AND _column BETWEEN #{_field.start} AND #{_field.end}</if>";
            final String queryInTpl = "<if test=\"_field != null\"> AND _column IN (<foreach collection=\"_field\" item=\"_item\" separator=\",\" >#{_item}</foreach>)</if>";
            final String queryGteTpl = "<if test=\"_field != null\"> AND _column &gt;= #{_field}</if>";
            final String queryLteTpl = "<if test=\"_field != null\"> AND _column &lt;= #{_field}</if>";
            final String queryGtTpl = "<if test=\"_field != null\"> AND _column &gt; #{_field}</if>";
            final String queryLtTpl = "<if test=\"_field != null\"> AND _column &lt; #{_field}</if>";

            for (Field field : parameterType.getDeclaredFields()) {

                if (field.isAnnotationPresent(Column.class)) {
                    // if query like
                    if (field.getAnnotation(Column.class).queryLike()) {
                        sb.append(queryLikeTpl.replaceAll("_field", field.getName()).replaceAll("_column", MybatisUtil.getColumnNameFromField(field)));
                        continue;
                    }
                    sb.append(tpl.replaceAll("_field", field.getName()).replaceAll("_column", MybatisUtil.getColumnNameFromField(field)));
                }

                // if @QueryIn on a Collection type field
                if (field.isAnnotationPresent(QueryIn.class)) {
                    String queryInColumnName = field.getAnnotation(QueryIn.class).column();
                    sb.append(queryInTpl.replaceAll("_field", field.getName()).replaceAll("_column", queryInColumnName));
                }

                // if @QueryLike on a Collection type field
                if (field.isAnnotationPresent(QueryLike.class)) {
                    String queryLikeColumnName = field.getAnnotation(QueryLike.class).column();
                    sb.append(queryLikeTpl.replaceAll("_field", field.getName()).replaceAll("_column", queryLikeColumnName));
                }

                // if @QueryBetween on a BetweenPair type field
                if (field.isAnnotationPresent(QueryBetween.class) && BetweenPair.class.isAssignableFrom(field.getType())) {
                    String queryBetweenColumnName = field.getAnnotation(QueryBetween.class).column();
                    sb.append(queryBetweenTpl.replaceAll("_field", field.getName()).replaceAll("_column", queryBetweenColumnName));
                }

                // if @QueryGte on a field
                if (field.isAnnotationPresent(QueryGte.class)) {
                    String queryGteColumnName = field.getAnnotation(QueryGte.class).column();
                    sb.append(queryGteTpl.replaceAll("_field", field.getName()).replaceAll("_column", queryGteColumnName));
                }

                // if @QueryLte on a field
                if (field.isAnnotationPresent(QueryLte.class)) {
                    String queryLteColumnName = field.getAnnotation(QueryLte.class).column();
                    sb.append(queryLteTpl.replaceAll("_field", field.getName()).replaceAll("_column", queryLteColumnName));
                }

                // if @QueryGt on a field
                if (field.isAnnotationPresent(QueryGt.class)) {
                    String queryGtColumnName = field.getAnnotation(QueryGt.class).column();
                    sb.append(queryGtTpl.replaceAll("_field", field.getName()).replaceAll("_column", queryGtColumnName));
                }

                // if @QueryLt on a field
                if (field.isAnnotationPresent(QueryLt.class)) {
                    String queryLtColumnName = field.getAnnotation(QueryLt.class).column();
                    sb.append(queryLtTpl.replaceAll("_field", field.getName()).replaceAll("_column", queryLtColumnName));
                }
            }

            sb.append("</trim>");

            script = matcher.replaceAll(sb.toString());
            script = "<script>" + script + "</script>";
        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}

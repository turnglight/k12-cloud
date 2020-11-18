package k12.base.mybatis.lang;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kinginblue on 2017/2/6.
 */
public class SimpleSelectInLangDriver extends XMLLanguageDriver {

    private static final Pattern inPattern = Pattern.compile("\\[#\\{(\\w+)\\}\\]");

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        Matcher matcher = inPattern.matcher(script);

        final String tpl = "<foreach collection=\"$1\" item=\"_item\" open=\"(\" " +
                "separator=\",\" close=\")\" >#{_item}</foreach>";

        if (matcher.find()) {
            script = matcher.replaceAll(tpl);
        }
        script = "<script>" + script + "</script>";
        return super.createSqlSource(configuration, script, parameterType);
    }
}

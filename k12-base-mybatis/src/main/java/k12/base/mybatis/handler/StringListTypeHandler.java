package k12.base.mybatis.handler;

import k12.base.mybatis.handler.type.StringList;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static k12.base.commons.jackson.Jackson.asJson;
import static k12.base.commons.jackson.Jackson.asObject;

/**
 * TypeHandler for String List
 */
@MappedTypes(StringList.class)
public class StringListTypeHandler extends BaseTypeHandler<StringList> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StringList parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, asJson(parameter));
    }

    @Override
    public StringList getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.parse(rs.getString(columnName));
    }

    @Override
    public StringList getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.parse(rs.getString(columnIndex));
    }

    @Override
    public StringList getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.parse(cs.getString(columnIndex));
    }

    private StringList parse(String str) {
        if (str == null) return null;
        return asObject(str, StringList.class);
    }

}

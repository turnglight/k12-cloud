package k12.base.mybatis.handler;

import k12.base.mybatis.handler.type.IntegerList;
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
 * TypeHandler for Integer List
 */
@MappedTypes(IntegerList.class)
public class IntegerListTypeHandler extends BaseTypeHandler<IntegerList> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IntegerList parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, asJson(parameter));
    }

    @Override
    public IntegerList getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.parse(rs.getString(columnName));
    }

    @Override
    public IntegerList getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.parse(rs.getString(columnIndex));
    }

    @Override
    public IntegerList getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.parse(cs.getString(columnIndex));
    }

    private IntegerList parse(String str) {
        if (str == null) return null;
        return asObject(str, IntegerList.class);
    }

}

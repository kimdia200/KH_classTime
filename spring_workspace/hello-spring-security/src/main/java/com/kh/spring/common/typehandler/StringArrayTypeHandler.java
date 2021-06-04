package com.kh.spring.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * 사용자정의 TypeHandler
 * String[] <------> varchar2
 * 
 * setter 1 : String[] -> PreparedStatement.setString(String)
 * getter 3 : ResultSet -> String[]
 * 
 *
 */
@MappedTypes(String[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType)
			throws SQLException {
		// {"Java", "C", "Javascript"} -----> "Java,C,Javascript" 
		String x = String.join(",", parameter); 
		ps.setString(i, x);

	}

	@Override
	public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String x = rs.getString(columnName);
		return x != null ? x.split(",") : null;
	}

	@Override
	public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String x = rs.getString(columnIndex);
		return x != null ? x.split(",") : null;
	}

	@Override
	public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String x = cs.getString(columnIndex);
		return x != null ? x.split(",") : null;
	}

}

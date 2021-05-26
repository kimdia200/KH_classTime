package com.kh.spring.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(Boolean.class)
@MappedJdbcTypes(JdbcType.CHAR)
public class BoolTypeHandler extends BaseTypeHandler<Boolean> {
	/**
	 * boolean -----> YN
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter ? "Y" : "N");
	}

	/**
	 *  YN -----> boolean
	 */
	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
//		return rs.getString(columnName).equals("Y"); // NPE
		return "Y".equals(rs.getString(columnName));
	}

	/**
	 *  YN -----> boolean
	 */
	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return "Y".equals(rs.getString(columnIndex));
	}

	/**
	 *  YN -----> boolean
	 */
	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return "Y".equals(cs.getString(columnIndex));
	}
}
package com.kh.mybatis.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Map<String, Object>> selectAllEmp(SqlSession session) {
		return session.selectList("emp.selectAllEmp");
	}

	@Override
	public List<Map<String, Object>> search1(SqlSession session, Map<String, Object> param) {
		System.out.println("dao="+param);
		return session.selectList("emp.search1",param);
	}
	
	@Override
	public List<Map<String, Object>> search2(SqlSession session, Map<String, Object> param) {
		return session.selectList("emp.search2", param);
	}

	@Override
	public List<Map<String, Object>> selectJobList(SqlSession session) {
		return session.selectList("emp.selectJobList");
	}

	@Override
	public List<Map<String, Object>> search3(SqlSession session, Map<String, Object> param) {
		System.out.println("쿼리 : "+session.getConfiguration().getMappedStatement("emp.search3").getBoundSql(param).getSql());
		return session.selectList("emp.search3",param);
	}

	@Override
	public List<Map<String, Object>> selectDeptList(SqlSession session) {
		return session.selectList("emp.selectDeptList");
	}

	@Override
	public Map<String, Object> selectEmpOne(SqlSession session, int empId) {
		System.out.println("쿼리 : "+session.getConfiguration().getMappedStatement("emp.selectEmpOne").getBoundSql(empId).getSql());
		return session.selectOne("emp.selectEmpOne", empId);
	}

	@Override
	public int updateEmp(SqlSession session, Map<String, Object> map) {
		System.out.println("쿼리 : "+session.getConfiguration().getMappedStatement("emp.updateEmp").getBoundSql(map).getSql());
		return session.update("emp.updateEmp", map);
	}
}

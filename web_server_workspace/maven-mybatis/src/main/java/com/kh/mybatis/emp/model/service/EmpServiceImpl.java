package com.kh.mybatis.emp.model.service;

import static com.kh.mybatis.common.MybatisUtils.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.emp.model.dao.EmpDao;
import com.kh.mybatis.emp.model.dao.EmpDaoImpl;

public class EmpServiceImpl implements EmpService {
	
	private EmpDao empDao = new EmpDaoImpl();
	
	
	@Override
	public List<Map<String, Object>> selectAllEmp() {
		List<Map<String, Object>> list = null;
		try {
			SqlSession session = getSqlSession();
			list= empDao.selectAllEmp(session);
			session.close();
		} catch (Exception e) {
			throw e;
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> search1(Map<String, Object> param) {
		List<Map<String, Object>> list = null;
		try {
			SqlSession session = getSqlSession();
			System.out.println("param@controller search1 = "+param);
			list= empDao.search1(session,param);
			session.close();
		} catch (Exception e) {
			throw e;
		}
		return list;
	}


	@Override
	public List<Map<String, Object>> search2(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.search2(session, param);
		session.close();
		return list;
	}


	@Override
	public List<Map<String, Object>> selectJobList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.selectJobList(session);
		session.close();
		return list;
	}


	@Override
	public List<Map<String, Object>> search3(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.search3(session,param);
		session.close();
		return list;
	}


	@Override
	public List<Map<String, Object>> selectDeptList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.selectDeptList(session);
		session.close();
		return list;
	}


	@Override
	public Map<String, Object> selectEmpOne(int empId) {
		SqlSession session = getSqlSession();
		Map<String, Object> list = empDao.selectEmpOne(session, empId);
		session.commit();
		session.close();
		return list;
	}


	@Override
	public int updateEmp(Map<String, Object> map) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result=empDao.updateEmp(session, map);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			session.close();
		}
		return result;
	}
}

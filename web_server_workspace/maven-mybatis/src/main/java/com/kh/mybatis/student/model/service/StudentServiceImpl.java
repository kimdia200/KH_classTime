package com.kh.mybatis.student.model.service;

import static com.kh.mybatis.common.MybatisUtils.getSqlSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.student.model.dao.StudentDao;
import com.kh.mybatis.student.model.dao.StudentDaoImpl;
import com.kh.mybatis.student.model.vo.Student;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public int insertStudent(Student std) {
		int result = 0;
		SqlSession session = getSqlSession();
		try {
			//1. connection 생성
			//mybatis에서는 Connection대신 SqlSession이라는 객체를 사용함
			
			//2. dao업무위임
			result = studentDao.insertStudent(session, std);
			
			//3. transaction 처라 - SqlSession에 대해서 commit | rollback
			session.commit();
			
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			//4. 자원반납 - SqlSession에 대한 자원반납
			session.close();
		}
		return result;
	}

	@Override
	public int insertStudentMap(Map<String, Object> student) {
		int result = 0;
		SqlSession session = getSqlSession();
		try {
			result = studentDao.insertStudentMap(session, student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public int selectStudentCount() {
		int total=0;
		SqlSession session = getSqlSession();
		try {
			total = studentDao.selectStudentCount(session);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return total;
	}

	@Override
	public Student selectOneStudent(int no) {
		Student student=null;
		SqlSession session = getSqlSession();
		try {
			student=studentDao.selectOneStudent(session, no);
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}
		return student;
	}

	@Override
	public Map<String, Object> selectOneStudentMap(int no) {
		Map<String, Object> student = null;
		SqlSession session = getSqlSession();
		try {
			student = studentDao.selectOneStudentMap(session, no);
		} catch (Exception e) {
			throw e;
		}finally {
			session.close();
		}
		return student;
	}

	@Override
	public int updateStudent(Student student) {
		int result = 0;
		SqlSession session = getSqlSession();
		try {
			//1. connection 생성
			//mybatis에서는 Connection대신 SqlSession이라는 객체를 사용함
			
			//2. dao업무위임
			result = studentDao.updateStudent(session, student);
			
			//3. transaction 처라 - SqlSession에 대해서 commit | rollback
			session.commit();
			
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			//4. 자원반납 - SqlSession에 대한 자원반납
			session.close();
		}
		return result;
	}

	@Override
	public int deleteStudent(int no) {
		int result = 0;
		SqlSession session = getSqlSession();
		try {
			//1. connection 생성
			//mybatis에서는 Connection대신 SqlSession이라는 객체를 사용함
			
			//2. dao업무위임
			result = studentDao.deleteStudent(session, no);
			
			//3. transaction 처라 - SqlSession에 대해서 commit | rollback
			session.commit();
			
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			//4. 자원반납 - SqlSession에 대한 자원반납
			session.close();
		}
		return result;
	}

	@Override
	public List<Student> selectStudentList() {
		List<Student> list = null;
		SqlSession session = getSqlSession();
		
		try {
			list = studentDao.selectStudentList(session);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList() {
		List<Map<String, Object>> mapList = null;
		SqlSession session = getSqlSession();
		
		try {
			mapList = studentDao.selectStudentMapList(session);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		
		return mapList;
	}
	
}

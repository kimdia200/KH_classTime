package com.kh.mybatis.student.model.service;

import static com.kh.mybatis.common.MybatisUtils.getSqlSession;

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
		} finally {
			//4. 자원반납 - SqlSession에 대한 자원반납
			session.close();
		}
		return result;
	}
}

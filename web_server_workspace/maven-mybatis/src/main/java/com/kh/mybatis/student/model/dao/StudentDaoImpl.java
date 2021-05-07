package com.kh.mybatis.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.student.model.vo.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int insertStudent(SqlSession session, Student std) {
		//statement = namespace.queryTagId
		//student = namespace, insertStudent = queryTagId
		return session.insert("student.insertStudent",std);
	}

	@Override
	public int insertStudentMap(SqlSession session, Map<String, Object> student) {
		return session.insert("student.insertStudentMap",student);
	}

	@Override
	public int selectStudentCount(SqlSession session) {
		//한행만 되는 쿼리는 selectOne
		return session.selectOne("student.selectStudentCount");
	}

	@Override
	public Student selectOneStudent(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudent",no);
	}

	@Override
	public Map<String, Object> selectOneStudentMap(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudentMap",no);
	}
}

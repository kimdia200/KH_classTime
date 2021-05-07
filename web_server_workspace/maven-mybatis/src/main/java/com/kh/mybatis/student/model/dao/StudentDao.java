package com.kh.mybatis.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.student.model.vo.Student;

public interface StudentDao {

	int insertStudent(SqlSession session, Student std);

	int insertStudentMap(SqlSession session, Map<String, Object> student);

	int selectStudentCount(SqlSession session);

	Student selectOneStudent(SqlSession session, int no);

	Map<String, Object> selectOneStudentMap(SqlSession session, int no);

}

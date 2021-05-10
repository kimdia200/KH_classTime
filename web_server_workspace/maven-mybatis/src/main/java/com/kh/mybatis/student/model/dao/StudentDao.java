package com.kh.mybatis.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.student.model.vo.Student;

public interface StudentDao {

	int insertStudent(SqlSession session, Student std);

	int insertStudentMap(SqlSession session, Map<String, Object> student);

	int selectStudentCount(SqlSession session);

	Student selectOneStudent(SqlSession session, int no);

	Map<String, Object> selectOneStudentMap(SqlSession session, int no);

	int updateStudent(SqlSession session, Student student);

	int deleteStudent(SqlSession session, int no);

	List<Student> selectStudentList(SqlSession session);

	List<Map<String, Object>> selectStudentMapList(SqlSession session);

}

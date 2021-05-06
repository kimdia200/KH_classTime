package com.kh.mybatis.student.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.student.model.vo.Student;

public interface StudentDao {

	int insertStudent(SqlSession session, Student std);

}

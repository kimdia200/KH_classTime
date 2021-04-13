package com.kh.homework.controller;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.homework.model.dao.SmartDao;
import com.kh.homework.model.vo.Smart;

public class SmartService {
	private SmartDao smartDao = new SmartDao();

	public int insertSmart(Smart smart) {
		int result = 0;
		Connection conn = getConnection();
		result = smartDao.insertSmart(conn, smart);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<Smart> selectRecentFive() {
		List<Smart> list = null;
		Connection conn = getConnection();
		list = smartDao.selectRecentFive(conn);
		return list;
	}

}

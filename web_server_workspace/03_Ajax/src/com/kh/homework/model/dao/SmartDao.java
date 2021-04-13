package com.kh.homework.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.homework.model.vo.Smart;


public class SmartDao {

	private Properties prop = new Properties();
	/**
	 * MemberDao객체 생성시(최초1회) member-query.properties의 내용을 읽어서
	 * prop에 저장한다
	 * dao메소드 호출시마다 prop으로 부터 sql문을 받는다
	 * @param conn
	 * @return
	 */
	public SmartDao() {
		String fileName = SmartDao.class.getResource("/homework/smart-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertSmart(Connection conn, Smart smart) {
		PreparedStatement pstmt = null;
		int result = 0;
		//update smartphone set amount=amount+? where pname= ?
		String sql = prop.getProperty("insertSmart");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, smart.getPname());
			pstmt.setInt(2, smart.getAmount());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Smart> selectRecentFive(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectRecentFive");
		List<Smart> list= new ArrayList<Smart>(); 
		try {
			//select * from (select rownum,
			pstmt=conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Smart s = new Smart();
				s.setPname(rset.getString("pname"));
				s.setAmount(rset.getInt("amount"));
				s.setPdate(rset.getDate("pdate"));
				list.add(s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

}

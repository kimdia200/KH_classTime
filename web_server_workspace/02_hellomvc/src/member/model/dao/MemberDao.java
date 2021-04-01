package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDao {
	private Properties prop = new Properties();
	/**
	 * MemberDao객체 생성시(최초1회) member-query.properties의 내용을 읽어서
	 * prop에 저장한다
	 * dao메소드 호출시마다 prop으로 부터 sql문을 받는다
	 * @param conn
	 * @return
	 */
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Member selectOne(Connection conn,String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		String sql = "select * from member where member_id = ?";
		String sql = prop.getProperty("selectOne");
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address, hobby, enrollDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}
	public int insertMember(Connection conn,Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberRole());
			pstmt.setString(5, m.getGender());
			pstmt.setDate(6, m.getBirthday());
			pstmt.setString(7, m.getEmail());
			pstmt.setString(8, m.getPhone());
			pstmt.setString(9, m.getAddress());
			pstmt.setString(10, m.getHobby());
			pstmt.setDate(11, m.getEnrollDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberRole());
			pstmt.setString(4, m.getGender());
			pstmt.setDate(5, m.getBirthday());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			pstmt.setString(10, m.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<Member> selectList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		String sql = "select * from member order by Enroll_Date desc"
		String sql = prop.getProperty("selectList");
		List<Member> list= new ArrayList<Member>();
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	//rownum사용한 paging에서 사용할것
	public List<Member> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		String sql = "select * from member order by Enroll_Date desc"
		String sql = prop.getProperty("selectPageList");
		List<Member> list= new ArrayList<Member>();
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public List<Member> searchMember(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		String sql = "select * from member order by Enroll_Date desc"
		String sql = prop.getProperty("searchMember");
		//아이디를 조회 한다면
		//select * from member where member_id like %a%
		//이름을 조회 한다면
		//select * from member where member_name like %a%
		//성별을 조회 한다면
		//select * from member where gender = a
		
		//select * from member where ? 라고 해주자
		switch(param.get("searchType")) {
		case "memberId" 	: sql += " member_id like '%" + param.get("searchKeyword") + "%'"; break;
		case "memberName" 	: sql += " member_name like '%" + param.get("searchKeyword") + "%'"; break;
		case "gender" 		: sql += " gender = '" + param.get("searchKeyword") + "'"; break;
		}
		
		List<Member> list= new ArrayList<Member>();
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public List<Member> searchMember(Connection conn, Map<String, String> param, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		switch(param.get("searchType")) {
		//m.member_id like '%a%') m where rnum between ? and ?
		case "memberId" 	: sql = prop.getProperty("selectPageFinderList_id"); 
							  param.put("searchKeyword","%"+param.get("searchKeyword")+"%");break;
		case "memberName" 	: sql = prop.getProperty("selectPageFinderList_name");
							  param.put("searchKeyword","%"+param.get("searchKeyword")+"%"); break;
		case "gender" 		: sql = prop.getProperty("selectPageFinderList_gender"); break;
		}
		
		List<Member> list= new ArrayList<Member>();
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, param.get("searchKeyword"));
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String memberRole = rset.getString("member_role");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, memberRole, gender, birthday, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberCount");

		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}


	public int selectFinderCount(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents =0;
		String sql = prop.getProperty("selectFinderCount");
		
		switch(param.get("searchType")) {
		case "memberId" 	: sql += " member_id like '%" + param.get("searchKeyword") + "%')"; break;
		case "memberName" 	: sql += " member_name like '%" + param.get("searchKeyword") + "%')"; break;
		case "gender" 		: sql += " gender = '" + param.get("searchKeyword") + "')"; break;
		}
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}


	
}

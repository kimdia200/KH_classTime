package member.model.dao;

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
import java.util.Properties;

import member.model.exception.MemberException;
import member.model.vo.Member;
/**
 * Service를 이용하여 DAO를 사용할때
 * DAO에서 해야 할것
 * DAO
 * 3. PreparedStatement 객체 생성(미완성 쿼리)
 * 	3.1 ? 값대입
 * 4. 실행 DML(executeUpdate)-> 정수형 리턴, DQL(executeQuery)-> resultSet으로 리턴
 * 	4.1 DQL인 경우 ResultSet -> JAVA객체로 옮겨담기
 * 5. 자원반납(생성순서 역순)
 * @author family
 *
 */
import static common.JDBCTemplate.*;
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
		String fileName = "resources/member-query.properties";
		try {
			prop.load(new FileReader(fileName));
//			System.out.println(prop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//1번. 전체 회원 조회
	public List<Member> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null; //DQL이기 때문
//		String sql = "select * from member order by enroll_date desc";
		String sql = prop.getProperty("selectAll");
		List<Member> list = null;
		
		//DAO
		try {
			//3. PreparedStatement 객체 생성(미완성 쿼리)
			//3.1 ? 값대입 //현재 사용하는 SQL문은 미완성쿼리가 아니므로 따로 작업 하지 않음
			pstmt = conn.prepareStatement(sql);
			//4. 실행 DML(executeUpdate)-> 정수형 리턴, DQL(executeQuery)-> resultSet으로 리턴
			rset = pstmt.executeQuery();
			
			//4.1 DQL인 경우 ResultSet -> JAVA객체로 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				Member member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			//예외를 전환 : RuntimeException, 의미가 분명한 커스텀 예외 객체를 던질것
			//unchecked이기 때문에 따로 throws를 써주지 않아도 자동으로 됨(try~catch안해줬으니까)
			throw new MemberException("회원 전체 조회", e);
		}finally {
			//5. 자원반납(생성순서 역순)
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	//2번. 아이디로 회원한명 조회
	public Member selectOne(Connection conn, String memberId) {
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
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new MemberException("아이디 조회",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}
	
	//3번 이름조회
	public List<Member> selectName(Connection conn, String memberName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null; // DQL이기 때문
//		String sql = "select * from member where member_name = ?";
		String sql = prop.getProperty("selectName");
		List<Member> list = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while (rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				Member member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby,
						enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new MemberException("이름조회",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	
	//4번. 회원 가입
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, default)";
		String sql = prop.getProperty("insertMember");
		try {
			//3. PreparedStatement 객체 생성(미완성 쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			//4. 실행 DML(executeUpdate)-> 정수형 리턴, DQL(executeQuery)-> resultSet으로 리턴
			result = pstmt.executeUpdate();
			//4.1 DQL인 경우 ResultSet -> JAVA객체로 옮겨담기
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new MemberException("회원가입",e);
		}finally {
			//5. 자원반납(생성순서 역순)
			close(pstmt);
		}
		return result;
	}
	
	//5번. 정보수정
//	public int updateMember(Connection conn, Member member) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String sql = "update member set password = ?, member_name = ?, gender = ?, age = ?, email = ?, phone = ?, address = ?, hobby = ? where member_id = ?";
//		try {
//			//3. PreparedStatement 객체 생성(미완성 쿼리)
//			pstmt = conn.prepareStatement(sql);
//			//3.1 ? 값대입
//			pstmt.setString(1, member.getPassword());
//			pstmt.setString(2, member.getMemberName());
//			pstmt.setString(3, member.getGender());
//			pstmt.setInt(4, member.getAge());
//			pstmt.setString(5, member.getEmail());
//			pstmt.setString(6, member.getPhone());
//			pstmt.setString(7, member.getAddress());
//			pstmt.setString(8, member.getHobby());
//			pstmt.setString(9, member.getMemberId());
//			//4. 실행 DML(executeUpdate)-> 정수형 리턴, DQL(executeQuery)-> resultSet으로 리턴
//			result = pstmt.executeUpdate();
//			//4.1 DQL인 경우 ResultSet -> JAVA객체로 옮겨담기
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			//5. 자원반납(생성순서 역순)
//			close(pstmt);
//		}
//		return result;
//	}
	
	//비밀번호수정
	public int updatePassword(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "update member set password = ? where member_id = ?";
		String sql = prop.getProperty("updatePassword");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new MemberException("비밀번호 수정",e);
		}finally {
			close(pstmt);
		}
		return result;
	}
	//이메일수정
	public int updateEmail(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "update member set email = ? where member_id = ?";
		String sql = prop.getProperty("updateEmail");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new MemberException("이메일 수정",e);
		}finally {
			close(pstmt);
		}
		return result;
	}
	//전화번호 수정
	public int updatePhone(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "update member set phone = ? where member_id = ?";
		String sql = prop.getProperty("updatePhone");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPhone());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new MemberException("전화번호 수정",e);
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int updateAddress(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "update member set address = ? where member_id = ?";
		String sql = prop.getProperty("updateAddress");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getAddress());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new MemberException("주소 수정",e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	//6번. 회원탈퇴
	public int deleteMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "delete member where member_id = ? and password = ?";
		String sql = prop.getProperty("deleteMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new MemberException("회원탈퇴",e);
		}
		return result;
	}
	
	//7번. 탈퇴회원조회.
	public List<Member> selectDelAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null; //DQL이기 때문
		String sql = prop.getProperty("selectDelAll");
		List<Member> list = null;
		
		//DAO
		try {
			//3. PreparedStatement 객체 생성(미완성 쿼리)
			//3.1 ? 값대입 //현재 사용하는 SQL문은 미완성쿼리가 아니므로 따로 작업 하지 않음
			pstmt = conn.prepareStatement(sql);
			//4. 실행 DML(executeUpdate)-> 정수형 리턴, DQL(executeQuery)-> resultSet으로 리턴
			rset = pstmt.executeQuery();
			
			//4.1 DQL인 경우 ResultSet -> JAVA객체로 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("del_date");
				Member member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			//예외를 전환 : RuntimeException, 의미가 분명한 커스텀 예외 객체를 던질것
			//unchecked이기 때문에 따로 throws를 써주지 않아도 자동으로 됨(try~catch안해줬으니까)
			throw new MemberException("탈퇴 회원 전체 조회", e);
		}finally {
			//5. 자원반납(생성순서 역순)
			close(rset);
			close(pstmt);
		}
		return list;
	}

	
}

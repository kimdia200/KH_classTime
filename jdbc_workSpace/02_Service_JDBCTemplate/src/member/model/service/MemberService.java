package member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;
/**
 * 기존 DAO만 사용했을때 과정
 * 1. DriverClass등록(최초1회)
 * 2. Connection객체 생성(URL,User,Password)
 * 	2.1. 자동커밋  false설정
 * 3. PreparedStatement 객체 생성(미완성 쿼리)
 * 	3.1 ? 값대입
 * 4. 실행 DML(executeUpdate)-> 정수형 리턴, DQL(executeQuery)-> resultSet으로 리턴
 * 	4.1 DQL인 경우 ResultSet -> JAVA객체로 옮겨담기
 * 5. 트랜잭션처리(DML) commit/rollback
 * 6. 자원반납(생성순서 역순)
 * 	6.1 (rset - pstmt - conn)
 * 
 * 실제로는 3,4번만 DB에 접근하는 내용이기 때문에
 * 3,4번만 DAO에게 맡기고 나머지는 Service에서 처리 하겠다!
 * 
 * 분리후 작업과정
 * Service
 * 1. DriverClass등록(최초1회)
 * 2. Connection객체 생성(URL,User,Password)
 * 	2.1. 자동커밋  false설정
 * --------DAO요청----------
 * 6. 트랜잭션처리(DML) commit/rollback
 * 7. 자원반납(생성순서 역순)
 * 	7.1 (rset - pstmt - conn)
 * 
 * DAO
 * 3. PreparedStatement 객체 생성(미완성 쿼리)
 * 	3.1 ? 값대입
 * 4. 실행 DML(executeUpdate)-> 정수형 리턴, DQL(executeQuery)-> resultSet으로 리턴
 * 	4.1 DQL인 경우 ResultSet -> JAVA객체로 옮겨담기
 * 5. 자원반납(생성순서 역순)
 * 
 * @author 윾수
 *
 */

import static common.JDBCTemplate.*; 
//static import하면 클래스.스태틱메서드 이름이 아닌
//바로 스태틱메서드 이름 사용가능
public class MemberService {

	private MemberDao memberDao = new MemberDao();

	// 1번. 전체 회원 조회 ,수정후(Service 사용)
	public List<Member> selectAll() {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectAll(conn);
		close(conn);
		return list;
	}

	// 1번. 수정전
	public List<Member> selectAll2() {
		String driverClass = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1522:xe";
		String user = "student";
		String password = "student";
		Connection conn = null;
		List<Member> list = null;

		// 분리후 작업과정
		// Service
		try {
			// 1. DriverClass등록(최초1회)
			Class.forName(driverClass);

			// 2. Connection객체 생성(URL,User,Password)
			conn = DriverManager.getConnection(url, user, password);

			// 2.1. 자동커밋 false설정
			conn.setAutoCommit(false);

			// --------DAO요청----------
			list = memberDao.selectAll(conn);

			// 6. 트랜잭션처리(DML) commit/rollback

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7. 자원반납(생성순서 역순)
			// 7.1 (rset - pstmt - conn)
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 2번. 아이디로 회원 한명 조회
	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.selectOne(conn, memberId);
		close(conn);
		return member;
	}
	
	//3번. 이름으로 회원 조회
	public List<Member> selectName(String memberName) {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectName(conn, memberName);
		close(conn);
		return list;
	}
	// 4번. 회원가입
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.insertMember(conn, member);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//5번. 정보수정
//	public int updateMember(Member member) {
//		Connection conn = getConnection();
//		int result = memberDao.updateMember(conn, member);
//		if (result > 0)
//			commit(conn);
//		else
//			rollback(conn);
//		close(conn);
//		return result;
//	}
	//비밀번호 수정
	public int updatePassword(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updatePassword(conn, member);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	//이베일수정
	public int updateEmail(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateEmail(conn, member);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	//전화번호수정
	public int updatePhone(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updatePhone(conn, member);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	//주소수정
	public int updateAddress(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateAddress(conn, member);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//6번. 회원탈퇴
	public int deleteMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.deleteMember(conn, member);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
}

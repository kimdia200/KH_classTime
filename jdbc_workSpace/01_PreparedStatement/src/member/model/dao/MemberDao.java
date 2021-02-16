package member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

/**
 * 
 * DAO Data Access Object DB에 접근하는 클래스
 * 
 * 처리순서는 고정되어있다. 
 * 1. Driver클래스 등록(최초 1회만 하면됨) -- 최초1회만 하면되니까 생성자에 넣어주면 될듯
 * 2. Connection 객체 생성(db서버의 URL, user, passWord) 
 * 3. 자동커밋 여부 : true(기본값) = 자동커밋 / false = 수동커밋 
 * 우리는 false로 바꿔서 app에서 직접 트랜잭션 제어를 할것임 
 * 4. PreparedStatement 객체생성(미완성 쿼리) 및 값 대입 
 * 5. Statement객체 실행. DB에 쿼리 요청 
 * 6. 응답처리 : DML=int리턴, DQL=ResultSet리턴 -> 자바객체로 전환 과정 필요
 * 7. DML인경우 트랜잭션 처리 
 * 8. 자원반납(생성의 역순)
 * 
 * @author 윾수
 * 
 */
public class MemberDao {
	// driverCalss, url, user, password는 다른 메서드에서도 사용가능하게 필드선언
	String driverClass = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1522:xe";
	String user = "student";
	String password = "student";

	// DB에 insert시켜주는 메서드
	public int insertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, default)";

		// 강사님께서는 try with resource는 추천하지 않으신다고 하셨음
		try {
			// 1. Driver클래스 등록(최초 1회만 하면됨)
			Class.forName(driverClass);

			// 2. Connection 객체 생성(db서버의 URL, user, passWord)
			// DBDriver타입 @아이피주소or도메인:포트번호:DB이름
			conn = DriverManager.getConnection(url, user, password);

			// 3. 자동커밋 여부 : true(기본값) = 자동커밋 / false = 수동커밋
			// 우리는 false로 바꿔서 app에서 직접 트랜잭션 제어를 할것임
			conn.setAutoCommit(false);

			// 4. PreparedStatement 객체생성(미완성 쿼리) 및 값 대입
			// sql문안에 종료에 세미콜론(;)붙이지 않음
			pstmt = conn.prepareStatement(sql);
			// ?에 대한 값을 채워 넣어줌
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());

			// 5. Statement 객체 실행. DB에 쿼리 요청
			// 6. 응답처리 : DML=int리턴, DQL=ResultSet리턴 -> 자바객체로 전환 과정 필요
			// 5~6번 동시처리
			// DML = executeUpdate, DQL = executeQuery
			result = pstmt.executeUpdate();

			// 7. DML인경우 트랜잭션 처리
			if (result > 0)
				conn.commit();
			else
				conn.rollback();

		} catch (ClassNotFoundException e) {
			// ojdbc6.jar를 프로젝트와 연동실패
			e.printStackTrace();
		} catch (SQLException e) {
			// DB접속에 실패했을때, SQLException = SQL관련 최상위 에러
			e.printStackTrace();
		}
		// 8. 자원반납(생성의 역순)
		finally {
			// 따로 따로 예외처리 해줘야 확실하다
			// 둘중 하나가 에러나면 나머지 하나는 null일수도 있기 때문에
			// 둘다 null이 아닐때만 닫아주는걸로 설정
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Member> selectAll() {
		Connection conn = null;
		String sql = "select * from member order by enroll_date desc";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = null;
		// 1. Driver클래스 등록(최초 1회만 하면됨)
		try {
			Class.forName(driverClass);
			// 2. Connection 객체 생성(db서버의 URL, user, passWord)
			// 3. 자동커밋 여부 : true(기본값) = 자동커밋 / false = 수동커밋
			//DQL이라서 트랜잭션 처리 안해줘도 되니까 connection객체만 만들어줌
			conn = DriverManager.getConnection(url, user, password);
			
			// 4. PreparedStatement 객체생성(미완성 쿼리) 및 값 대입
			pstmt = conn.prepareStatement(sql);
			
			// 5. Statement 객체 실행. DB에 쿼리 요청
			//DQL = executeQuery로 실행
			rset = pstmt.executeQuery();//미리 sql문 넣어놔서 파라미터없는걸로 실행
			
			// 6. 응답처리 : DML=int리턴, DQL=ResultSet리턴 -> 자바객체로 전환 과정 필요
			//다음행 존재여부 리턴. 커서가 다음행에 접근.
			list = new ArrayList<Member>();
			while(rset.next()) {
				//컬럼명은 대소문자를 구분하지 않음, 혹은 컬럼 인덱스로 쓸수도있음
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
			
			// DML = executeUpdate, DQL = executeQuery
			
			// 7. DML인경우 트랜잭션 처리
			//DQL인 경우 트랜잭션처리 따로 안해줘도됨
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 8. 자원반납(생성의 역순)
		finally {
			try {
				rset.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Member selectOne(String memberId) {
		Connection conn = null;
		String sql = "select * from member where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		try {
			Class.forName(driverClass);
			// 2. Connection 객체 생성(db서버의 URL, user, passWord)
			// 3. 자동커밋 여부 : true(기본값) = 자동커밋 / false = 수동커밋
			//DQL이라서 트랜잭션 처리 안해줘도 되니까 connection객체만 만들어줌
			conn = DriverManager.getConnection(url, user, password);
			
			// 4. PreparedStatement 객체생성(미완성 쿼리) 및 값 대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			// 5. Statement 객체 실행. DB에 쿼리 요청
			//DQL = executeQuery로 실행
			rset = pstmt.executeQuery();//미리 sql문 넣어놔서 파라미터없는걸로 실행
			
			// 6. 응답처리 : DML=int리턴, DQL=ResultSet리턴 -> 자바객체로 전환 과정 필요
			//다음행 존재여부 리턴. 커서가 다음행에 접근.
			while(rset.next()) {
				//컬럼명은 대소문자를 구분하지 않음, 혹은 컬럼 인덱스로 쓸수도있음
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
			
			// DML = executeUpdate, DQL = executeQuery
			
			// 7. DML인경우 트랜잭션 처리
			//DQL인 경우 트랜잭션처리 따로 안해줘도됨
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	public Member selectOne(String memberId, String memberPassword) {
		Connection conn = null;
		String sql = "select * from member where member_id = ? and password = ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		try {
			Class.forName(driverClass);
			// 2. Connection 객체 생성(db서버의 URL, user, passWord)
			// 3. 자동커밋 여부 : true(기본값) = 자동커밋 / false = 수동커밋
			//DQL이라서 트랜잭션 처리 안해줘도 되니까 connection객체만 만들어줌
			conn = DriverManager.getConnection(url, user, password);
			
			// 4. PreparedStatement 객체생성(미완성 쿼리) 및 값 대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPassword);
			// 5. Statement 객체 실행. DB에 쿼리 요청
			//DQL = executeQuery로 실행
			rset = pstmt.executeQuery();//미리 sql문 넣어놔서 파라미터없는걸로 실행
			
			// 6. 응답처리 : DML=int리턴, DQL=ResultSet리턴 -> 자바객체로 전환 과정 필요
			//다음행 존재여부 리턴. 커서가 다음행에 접근.
			while(rset.next()) {
				//컬럼명은 대소문자를 구분하지 않음, 혹은 컬럼 인덱스로 쓸수도있음
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
			
			// DML = executeUpdate, DQL = executeQuery
			
			// 7. DML인경우 트랜잭션 처리
			//DQL인 경우 트랜잭션처리 따로 안해줘도됨
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	public Member selectName(String memberName) {
		Connection conn = null;
		String sql = "select * from member where member_name like ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		try {
			Class.forName(driverClass);
			// 2. Connection 객체 생성(db서버의 URL, user, passWord)
			// 3. 자동커밋 여부 : true(기본값) = 자동커밋 / false = 수동커밋
			//DQL이라서 트랜잭션 처리 안해줘도 되니까 connection객체만 만들어줌
			conn = DriverManager.getConnection(url, user, password);
			
			// 4. PreparedStatement 객체생성(미완성 쿼리) 및 값 대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+memberName+"%");
			// 5. Statement 객체 실행. DB에 쿼리 요청
			//DQL = executeQuery로 실행
			rset = pstmt.executeQuery();//미리 sql문 넣어놔서 파라미터없는걸로 실행
			
			// 6. 응답처리 : DML=int리턴, DQL=ResultSet리턴 -> 자바객체로 전환 과정 필요
			//다음행 존재여부 리턴. 커서가 다음행에 접근.
			while(rset.next()) {
				//컬럼명은 대소문자를 구분하지 않음, 혹은 컬럼 인덱스로 쓸수도있음
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
				
				member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
			}
			
			// DML = executeUpdate, DQL = executeQuery
			
			// 7. DML인경우 트랜잭션 처리
			//DQL인 경우 트랜잭션처리 따로 안해줘도됨
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	public int modifyInfo(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update member set password = ?, member_name = ?, gender = ?, age = ?, email = ?, phone = ?, address = ?, hobby = ? where member_id = ?";
//		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, default)";

		// 강사님께서는 try with resource는 추천하지 않으신다고 하셨음
		try {
			// 1. Driver클래스 등록(최초 1회만 하면됨)
			Class.forName(driverClass);

			// 2. Connection 객체 생성(db서버의 URL, user, passWord)
			// DBDriver타입 @아이피주소or도메인:포트번호:DB이름
			conn = DriverManager.getConnection(url, user, password);

			// 3. 자동커밋 여부 : true(기본값) = 자동커밋 / false = 수동커밋
			// 우리는 false로 바꿔서 app에서 직접 트랜잭션 제어를 할것임
			conn.setAutoCommit(false);

			// 4. PreparedStatement 객체생성(미완성 쿼리) 및 값 대입
			// sql문안에 종료에 세미콜론(;)붙이지 않음
			pstmt = conn.prepareStatement(sql);
			// ?에 대한 값을 채워 넣어줌
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getGender());
			pstmt.setInt(4, member.getAge());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getHobby());
			pstmt.setString(9, member.getMemberId());

			// 5. Statement 객체 실행. DB에 쿼리 요청
			// 6. 응답처리 : DML=int리턴, DQL=ResultSet리턴 -> 자바객체로 전환 과정 필요
			// 5~6번 동시처리
			// DML = executeUpdate, DQL = executeQuery
			result = pstmt.executeUpdate();

			// 7. DML인경우 트랜잭션 처리
			if (result > 0)
				conn.commit();
			else
				conn.rollback();

		} catch (ClassNotFoundException e) {
			// ojdbc6.jar를 프로젝트와 연동실패
			e.printStackTrace();
		} catch (SQLException e) {
			// DB접속에 실패했을때, SQLException = SQL관련 최상위 에러
			e.printStackTrace();
		}
		// 8. 자원반납(생성의 역순)
		finally {
			// 따로 따로 예외처리 해줘야 확실하다
			// 둘중 하나가 에러나면 나머지 하나는 null일수도 있기 때문에
			// 둘다 null이 아닐때만 닫아주는걸로 설정
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//회원탈퇴 요청
	public int deleteInfo(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete member where member_id = ?";

		// 강사님께서는 try with resource는 추천하지 않으신다고 하셨음
		try {
			// 1. Driver클래스 등록(최초 1회만 하면됨)
			Class.forName(driverClass);

			// 2. Connection 객체 생성(db서버의 URL, user, passWord)
			// DBDriver타입 @아이피주소or도메인:포트번호:DB이름
			conn = DriverManager.getConnection(url, user, password);

			// 3. 자동커밋 여부 : true(기본값) = 자동커밋 / false = 수동커밋
			// 우리는 false로 바꿔서 app에서 직접 트랜잭션 제어를 할것임
			conn.setAutoCommit(false);

			// 4. PreparedStatement 객체생성(미완성 쿼리) 및 값 대입
			// sql문안에 종료에 세미콜론(;)붙이지 않음
			pstmt = conn.prepareStatement(sql);
			// ?에 대한 값을 채워 넣어줌
			pstmt.setString(1, member.getMemberId());

			// 5. Statement 객체 실행. DB에 쿼리 요청
			// 6. 응답처리 : DML=int리턴, DQL=ResultSet리턴 -> 자바객체로 전환 과정 필요
			// 5~6번 동시처리
			// DML = executeUpdate, DQL = executeQuery
			result = pstmt.executeUpdate();

			// 7. DML인경우 트랜잭션 처리
			if (result > 0)
				conn.commit();
			else
				conn.rollback();

		} catch (ClassNotFoundException e) {
			// ojdbc6.jar를 프로젝트와 연동실패
			e.printStackTrace();
		} catch (SQLException e) {
			// DB접속에 실패했을때, SQLException = SQL관련 최상위 에러
			e.printStackTrace();
		}
		// 8. 자원반납(생성의 역순)
		finally {
			// 따로 따로 예외처리 해줘야 확실하다
			// 둘중 하나가 에러나면 나머지 하나는 null일수도 있기 때문에
			// 둘다 null이 아닐때만 닫아주는걸로 설정
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}

package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service, Dao 클래스의 공통부문을 static메소드로 제공
 * 예외처리를 공통 부분에서 작성 하므로, 사용(호출)하는 쪽의 코드를 간결히 할 수 있다.
 * @author family
 *
 */
public class JDBCTemplate {
	private static String driverClass = "oracle.jdbc.OracleDriver";
//	private static String url = "jdbc:oracle:thin:@khmclass.iptime.org:1521:xe";
	private static String url = "jdbc:oracle:thin:@localhost:1522:xe";
	private static String user = "student";
	private static String password = "student";
	
	//스태틱 초기화블럭 = 프로그램이 실행될때 딱 1회 실행됨
	static{
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			//1. DriverClass등록(최초1회)
			//2. Connection객체 생성(URL,User,Password)
			conn = DriverManager.getConnection(url, user, password);
			
			//	2.1. 자동커밋  false설정
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn) {
		//7. 자원반납(conn)
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rset) {
		try {
			if(rset!=null)
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection conn) {
		try {
			if(conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

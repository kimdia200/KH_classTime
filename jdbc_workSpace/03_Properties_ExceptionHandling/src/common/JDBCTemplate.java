package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Service, Dao 클래스의 공통부문을 static메소드로 제공
 * 예외처리를 공통 부분에서 작성 하므로, 사용(호출)하는 쪽의 코드를 간결히 할 수 있다.
 * @author family
 *
 */
public class JDBCTemplate {
	private static String driverClass;
//	private static String url = "jdbc:oracle:thin:@khmclass.iptime.org:1521:xe";
	private static String url;
	private static String user;
	private static String password;
	
	//스태틱 초기화블럭 = 프로그램이 실행될때 딱 1회 실행됨
	static{
		//data-source.properties의 내용을 읽어서 변수에 대입
		Properties prop = new Properties();
		String fileName = "resources/data-source.properties";
		try {
			prop.load(new FileReader(fileName));
//			System.out.println(prop);
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
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

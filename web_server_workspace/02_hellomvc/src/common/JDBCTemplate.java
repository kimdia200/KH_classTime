package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Service, Dao 클래스의 공통부문을 static메소드로 제공
 * 예외처리를 공통 부분에서 작성 하므로, 사용(호출)하는 쪽의 코드를 간결히 할 수 있다.
 * @author family
 *
 */
public class JDBCTemplate {
	private static String driverClass;
//	private static String url = "jdbc:oracle:thin:@khmclass.iptime.org:1521:xe";
	public static String url;
	private static String user;
	private static String password;
	
	//스태틱 초기화블럭 = 프로그램이 실행될때 딱 1회 실행됨
	static{
		//data-source.properties의 내용을 읽어서 변수에 대입
		Properties prop = new Properties();
//		JDBC때는 아래와 같은 경로로 읽어왔지만 SourceFolder로 만들면 읽어올 수 없다.
//		String fileName = "resources/data-source.properties";
		
//		웹프로젝트의 SourceFolder는 바로 build path에 넣어주기 때문에 자세한 경로는 네비게이터에서만 확인 가능하다.
//		class.getResource 하면 현재 절대 경로(루트)가 호출되고 return은 URL 객체로 리턴하여 getPath라는 절대경로를 String으로 리턴 해주는 메서드를 추가사용
		String fileName = JDBCTemplate.class.getResource("/data-source.properties").getPath();
//		System.out.println("fileName@JDBCTemplate = "+fileName);
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
	
//	public static Connection getConnection() {
//		Connection conn = null;
//		try {
//			//1. DriverClass등록(최초1회)
//			//2. Connection객체 생성(URL,User,Password)
//			conn = DriverManager.getConnection(url, user, password);
//			
//			//	2.1. 자동커밋  false설정
//			conn.setAutoCommit(false);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return conn;
//	}
	
	/**
	 * DBCP 이용버전
	 * 
	 * Resource등록 - JNDI를 통한 참조
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		//javax.naming
		try {
			Context ctx = new InitialContext();
			/**
			 * JNDI구조
			 * java:/comp/env/ + jdbc/myoracle
			 */
			DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException e) {
			e.printStackTrace();
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

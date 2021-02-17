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

import product.model.exception.ProductException;

public class Template {
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;
	
	//초기화 블럭 작업 = static필드값 채워줌, DriverClass등록(최초1회)
	static {
		Properties prop = new Properties();
		String fileName = "resources/data-source.properties";
		try {
			prop.load(new FileReader(fileName));
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			Class.forName(driverClass);
		} catch (FileNotFoundException e) {
			//파일 못찾았을대
			throw new ProductException("파일을 찾을수 없음 in Template",e);
		} catch (IOException e) {
			//입출력 오류
			throw new ProductException("입출력 오류 in Template",e);
		} catch (ClassNotFoundException e) {
			//클래스 이름설정 실패
			throw new ProductException("DriverClass등록 실패 in Template",e);
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new ProductException("Connection 생성 실패 in Template",e);
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new ProductException("Connection 종료 실패 in Template",e);
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			rset.close();
		} catch (SQLException e) {
			throw new ProductException("rset close실패 in Template",e);
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			throw new ProductException("rset close실패 in Template",e);
		}
	}
	
}

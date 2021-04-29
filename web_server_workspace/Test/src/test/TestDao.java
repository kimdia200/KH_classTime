package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TestDao {
	private static String driverClass = "oracle.jdbc.OracleDriver";
	public static String url= "jdbc:oracle:thin:@192.168.10.3:1521:xe";
	private static String user = "kh";
	private static String password = "kh";

	static {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Test> selectList() {
		List<Test> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from test order by seq";
		Connection conn = getConnection();
		
		try {
			pstmt=conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Test>();
			
			while(rset.next()) {
				Test t = new Test();
				t.setSeq(rset.getInt("seq"));
				t.setWriter(rset.getString("writer"));
				t.setTitle(rset.getString("title"));
				t.setContent(rset.getString("content"));
				t.setRegdate(rset.getDate("regdate"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
			close(conn);
		}
		return list;
	}
	

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public static void close(Connection conn) {
		// 7. 자원반납(conn)
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset) {
		try {
			if (rset != null)
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

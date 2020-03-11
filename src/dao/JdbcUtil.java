package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//디비 접속, 디비 종료 ,tx(commit, rollback)
public class JdbcUtil {
	public static Scanner sc = new Scanner(System.in);//스캐너
	static {// 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
	}// static End 드라이버 로딩 완

	public static Connection getConnection() {// 클래스 메소드
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "icia", "1111");
			System.out.println("Connection Success!");
		} catch (SQLException se) {
			System.out.println("Connection fail T.T");
			se.printStackTrace();
		}
		return con;
	}// getConnection End

	public static void close(Connection con, ResultSet rs, PreparedStatement stmt) {// 받아 온걸 닫아줌
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
			System.out.println("Connection close");
		} catch (SQLException e) {
			System.out.println("close 에러");
			e.printStackTrace();
		}
	}// close End

	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("commit Success");
		} catch (SQLException e) {
			System.out.println("commit Err");
			e.printStackTrace();
		}
	}// commit End

	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback Success");
		} catch (SQLException e) {
			System.out.println("rollback Err");
			e.printStackTrace();
		}
	}// rollback End
}// class End

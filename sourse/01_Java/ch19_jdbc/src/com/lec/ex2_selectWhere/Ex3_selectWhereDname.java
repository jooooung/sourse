package com.lec.ex2_selectWhere;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//부서이름을 입력받아 부서 정보출력
import java.util.Scanner;

public class Ex3_selectWhereDname {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		Scanner scanner = new Scanner(System.in);
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;
		System.out.print("원하는 부서 이름은 ? ");
		String dname = scanner.next();
		String sql = "SELECT * FROM DEPT WHERE DNAME=UPPER('"+dname+"')";
		sql = String.format("SELECT * FROM DEPT WHERE DNAME=UPPER('%s')", dname);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "scott", "tiger");
			stmt = conn.createStatement();
			rs	 = stmt.executeQuery(sql);	// select문 전송 시 
			if(rs.next()) {
				System.out.println("부서번호 : " + rs.getInt("deptno"));
				System.out.println("부서명 : " + dname);
				System.out.println("근무지 : " + rs.getString("loc"));
			} else {
				System.out.println("해당 부서 이름이 존재하지 않습니다");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}	
}

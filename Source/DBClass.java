package Project;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBClass {
	private String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	private String userid = "root";
	private String pwd = "root";

	public DBClass() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 연결 실패");
			e.printStackTrace();
		}

		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비...");
			SejongCampingGUI.con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e1) {
			System.out.println("데이터베이스 연결 실패");
			e1.printStackTrace();
		}
	}

	public DBClass(int n) {
		try {
			if (SejongCampingGUI.con != null) {
				SejongCampingGUI.con.close();
				System.out.println("데이터 베이스 연결 종료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

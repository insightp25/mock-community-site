package haro.mockcommunitysite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockCommunitySiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockCommunitySiteApplication.class, args);



//		Connection con = null;
//
//		String server = "jdbc:mysql://127.0.0.1/mocksns"; // 서버 주소
//		String user_name = "root"; //  접속자 id
//		String password = "l$de11@0$ql"; // 접속자 pw
//
//		// JDBC 드라이버 로드
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
//			e.printStackTrace();
//		}
//
//		// 접속
//		try {
//			con = DriverManager.getConnection(server + "?useSSL=false&allowPublicKeyRetrieval=true", user_name, password);
//			System.out.println("연결 완료!");
//		} catch(SQLException e) {
//			System.err.println("연결 오류" + e.getMessage());
//			e.printStackTrace();
//		}
//
//		// 접속 종료
//		try {
//			if(con != null)
//				con.close();
//		} catch (SQLException e) {}
	}

}

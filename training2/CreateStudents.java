import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class CreateStudents {
	public static void main(String[] args) {
		System.out.println("登録する生徒の名前を入力してください。");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String studentName = reader.readLine();
		System.out.println("登録する生徒のクラスを入力してください。(A,B,Cのいずれか)");
		String studentClass = reader.readLine();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String url = "jdbc:postgresql:///123.45.67.89:5432/postgres";
		String user = "postgres";
		String password = "password";

		try {
			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection(url, user, password);

			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			String sql = "SELECT 1";
			rset = stmt.executeQuery(sql);

			while(rset.next()) {
				String col = rset.getString(1);
				System.out.println(col);
			}

			sql = "INSERT INTO jdbc_text VALUES (1, 'AAA')";
			stmt.executeUpdate(sql);
			conn.commit();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("登録が完了しました。");
	}

}

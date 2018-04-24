package training2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	public Connection getConnection() {
		Connection connection = null;

		//接続文字列
		String url = "jdbc:postgresql://localhost:5433/postgres";
		String user = "postgres";
		String password = "postgres";

		try {
			Class.forName("org.postgresql.Driver");//ClassNotFoundException投げる

			//PostgreSQLへ接続
			connection = DriverManager.getConnection(url, user, password);

			//自動コミットOFF
			connection.setAutoCommit(false);

		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}

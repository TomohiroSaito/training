package training2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import training2.studentmodel.student.Student;

public class StudentDataAccess {

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

	public int insertStudent(Student student) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//クラス名から、クラスIDをデータベースより取得
		String studentName = student.getStudentName().getName();
		int classId = selectClassId(student);

		try {
			connection = getConnection();

			Date date = new Date(Calendar.getInstance().getTimeInMillis());

			//INSERT文の実行
			String sql = "INSERT INTO Student (class, name, created_at, updated_at) VALUES(?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, classId);
			preparedStatement.setString(2, studentName);
			preparedStatement.setDate(3, date);
			preparedStatement.setDate(4, date);
			result = preparedStatement.executeUpdate();
			connection.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int selectClassId(Student student) {
		int classId = 0;
		//SELECT文が実行されたことの確認
		boolean hasError = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String className = student.getParty().getPartyName().getName();

		try {
			connection = getConnection();

			//SELECT文の実行
			String sql = "SELECT class_id FROM Class WHERE class_name=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, className);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				classId = resultSet.getInt("class_id");
				hasError = true;
			}
			checkSelectError(hasError);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(resultSet != null) resultSet.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return classId;
	}

	private void checkSelectError(boolean hasError) {
		if(!hasError) {
			System.out.println("SELECT文は実行されませんでした。");
		}
	}

}

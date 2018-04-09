package training2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import training2.studentmodel.party.Party;
import training2.studentmodel.party.PartyId;
import training2.studentmodel.party.PartyName;
import training2.studentmodel.student.Student;
import training2.studentmodel.student.StudentName;
import training2.studentmodel.student.StudentNumber;

public class StudentDataAccess {

	public int insertStudent(Student student) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		DBManager dbManager = new DBManager();

		//クラス名から、クラスIDをデータベースより取得
		String studentName = student.getStudentName().getName();
		PartyId partyId = selectClassId(student.getParty().getPartyName());

		try {
			connection = dbManager.getConnection();

			Date date = new Date(Calendar.getInstance().getTimeInMillis());

			//INSERT文の実行
			String sql = "INSERT INTO Student (class, name, created_at, updated_at) VALUES(?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, partyId.getId());
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

	public PartyId selectClassId(PartyName partyName) {
		int classId = 0;
		//SELECT文が実行されたことの確認
		boolean hasError = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		DBManager dbManager = new DBManager();

		try {
			connection = dbManager.getConnection();

			//SELECT文の実行
			String sql = "SELECT class_id FROM Class WHERE class_name=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, partyName.getName());
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
		return new PartyId(classId);
	}

	public ArrayList<Student> selectClassList(PartyId partyId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		DBManager dbManager = new DBManager();

		ArrayList<Student> students = new ArrayList<Student>();

		try {
			connection = dbManager.getConnection();


			//SELECT文の実行
			String sql = "SELECT Student.number,Class.class_name,Student.name FROM Student INNER JOIN Class ON Student.class = Class.class_id WHERE Student.class=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, partyId.getId());
			rs = preparedStatement.executeQuery();
			int number = 0;
			String className = null;
			String name = null;
			while(rs.next()) {
				number = rs.getInt("number");
				StudentNumber studentNumber = new StudentNumber(number);
				className = rs.getString("class_name");
				PartyName partyName = new PartyName(className);
				name = rs.getString("name");
				StudentName studentName = new StudentName(name);
				students.add(new Student(studentNumber, new Party(partyName), studentName));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(rs != null) rs.close();
				if(connection != null) connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return students;
	}

	private void checkSelectError(boolean hasError) {
		if(!hasError) {
			System.out.println("SELECT文は実行されませんでした。");
		}
	}

}

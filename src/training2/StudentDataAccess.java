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
import training2.studentmodel.student.PersonalRecord;
import training2.studentmodel.student.Student;
import training2.studentmodel.student.StudentName;
import training2.studentmodel.student.StudentNumber;
import training2.studentmodel.subject.SubjectId;
import training2.studentmodel.subject.SubjectName;

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

	public void insertRecords(int number, ArrayList<PersonalRecord> personalRecords) {
		boolean resultError = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		DBManager dbManager = new DBManager();

		try {

			connection = dbManager.getConnection();

			Date date = new Date(Calendar.getInstance().getTimeInMillis());

			for(PersonalRecord personalRecord : personalRecords) {
				String sql = "INSERT INTO Record (number, subject_id, record, created_at, updated_at) VALUES(?, ?, ?, ?, ?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, number);
				preparedStatement.setInt(2, personalRecord.getSubject().getSubjectId().getId());
				preparedStatement.setInt(3, personalRecord.getRecord().getRecord());
				preparedStatement.setDate(4, date);
				preparedStatement.setDate(5, date);
				int update = preparedStatement.executeUpdate();
				if(update == -1 && !resultError) {
					resultError = true;
				}
			}
			checkInsertError(resultError);
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
	}

	public SubjectId selectSubjectId(SubjectName subjectName) {
		int subjectId = 0;
		boolean hasError = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		DBManager dbManager = new DBManager();

		try {
			connection = dbManager.getConnection();

			//SELECT文の実行
			String sql = "SELECT subject_id FROM Subject WHERE subject_name=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subjectName.getName());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				subjectId = resultSet.getInt("subject_id");
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
		return new SubjectId(subjectId);
	}

	public boolean existStudent(int number) {
		boolean exist = false;
		int numberCount = 0;
		//SELECT文が実行されたことの確認
		boolean hasError = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		DBManager dbManager = new DBManager();

		try {
			connection = dbManager.getConnection();

			//SELECT文の実行
			String sql = "SELECT count(*) FROM Student WHERE number=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				numberCount = resultSet.getInt("count");
				hasError = true;
			}
			checkSelectError(hasError);
			exist = checkExist(numberCount);
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
		return exist;
	}

	private void checkSelectError(boolean hasError) {
		if(!hasError) {
			System.out.println("SELECT文は実行されませんでした。");
		}
	}

	private void checkInsertError(boolean hasError) {
		if(hasError) {
			System.out.println("どこかの文に誤りがありました。");
			System.exit(-1);
		}
	}

	private boolean checkExist(int numberCount) {
		boolean exist = false;
		if(1 <= numberCount) {
			exist = true;
		}
		return exist;
	}

}

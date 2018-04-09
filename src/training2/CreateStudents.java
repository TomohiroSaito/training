package training2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import training2.studentmodel.party.Party;
import training2.studentmodel.party.PartyId;
import training2.studentmodel.party.PartyName;
import training2.studentmodel.student.PersonalRecord;
import training2.studentmodel.student.Record;
import training2.studentmodel.student.Student;
import training2.studentmodel.student.StudentName;
import training2.studentmodel.student.StudentNumber;
import training2.studentmodel.subject.Subject;
import training2.studentmodel.subject.SubjectId;
import training2.studentmodel.subject.SubjectName;

public class CreateStudents {

	public static void main(String[] args) {
		outMessage(1);
		Student student = getStudent();
		String studentName = inputName();
		putStudentName(student, studentName);
		outMessage(2);
		String studentClass = inputClass();
		putStudentClass(student, studentClass);
		insertStudent(student);
		outMessage(3);
	}

	static Student getStudent() {
		Student student = new Student();
		StudentNumber studentNumber = new StudentNumber();
		student.setStudentNumber(studentNumber);
		StudentName studentName = new StudentName();
		student.setStudentName(studentName);
		Party party = getParty();
		student.setParty(party);
		ArrayList<PersonalRecord> personalRecordList = getParsonalRecordList();
		student.setPersonalRecordList(personalRecordList);
		return student;
	}

	static Party getParty() {
		Party party = new Party();
		PartyId partyId = new PartyId();
		PartyName partyName = new PartyName();
		party.setPartyId(partyId);
		party.setPartyName(partyName);
		return party;
	}

	static ArrayList<PersonalRecord> getParsonalRecordList() {
		ArrayList<PersonalRecord> personalRecordList = new ArrayList<PersonalRecord>();
		for(int i=0; i<5; ++i) {
			PersonalRecord personalRecord = getPersonalRecord();
			personalRecordList.add(personalRecord);
		}
		return personalRecordList;
	}

	static PersonalRecord getPersonalRecord() {
		PersonalRecord personalRecord = new PersonalRecord();
		Record record = new Record();
		personalRecord.setRecord(record);
		Subject subject = new Subject();
		SubjectName subjectName = new SubjectName();
		SubjectId subjectId = new SubjectId();
		subject.setSubjectName(subjectName);
		subject.setSubjectId(subjectId);
		return personalRecord;
	}

	static Student putStudentClass(Student student, String target) {
		student.getParty().getPartyName().setName(target);
		return student;
	}

	static Student putStudentName(Student student, String target) {
		student.getStudentName().setName(target);
		return student;
	}

	static Student putClassId(Student student, int classId) {
		student.getParty().getPartyId().setId(classId);
		return student;
	}

	static void outMessage(int number) {
		switch(number) {
			case 1:
				System.out.println("登録する生徒の名前を入力してください。");
				break;
			case 2:
				System.out.println("登録する生徒のクラスを入力してください。(A,B,Cのいずれか)");
				break;
			case 3:
				System.out.println("登録が完了しました。");
				break;
		}
	}

	static String inputName() {
		String studentName = input();
		nameByteError(studentName);
		return studentName;
	}

	static String inputClass() {
		String studentClass = input();
		classError(studentClass);
		return studentClass;
	}

	static String input() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputString = null;
		try {
			inputString = reader.readLine();
		} catch(IOException e) {
			System.out.println("入力時にエラーが起こりました。");
			System.exit(-1);
		}
		return inputString;
	}

	static void nameByteError(String name) {
		int count = name.getBytes(Charset.forName("UTF-8")).length;
		if(!(count <= 20)) {
			System.out.println("名前が登録できない長さです。");
			System.exit(-1);
		}

	}

	static void classError(String target) {
		if(!target.matches("A|B|C")) {
			System.out.println("クラスはA,B,Cの中から選んでください。");
			System.exit(-1);
		}

	}

	static void insertStudent(Student student) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//クラス名から、クラスIDをデータベースより取得
		String studentName = student.getStudentName().getName();
		Student resultStudent = selectClassId(student);

		int classId = resultStudent.getParty().getPartyId().getId();

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
			insertError(result);
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

	//executeUpdateメソッドの戻り値が0だったら、エラーメッセージを表示
	static void insertError(int result) {
		if(result == 0) {
			System.out.println("何らかの原因で登録できませんでした。");
			System.exit(-1);
		}
	}

	static Student selectClassId(Student student) {
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
				int classId = resultSet.getInt("class_id");
				putClassId(student, classId);
			}
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
		return student;
	}

	static Connection getConnection() {
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

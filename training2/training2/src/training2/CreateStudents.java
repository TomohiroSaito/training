package training2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class CreateStudents {

	public static void main(String[] args) throws IOException {
		outMessage(1);
		String studentName = inputName();
		outMessage(2);
		int classId = inputClass();
		insertStudent(classId, studentName);
		outMessage(3);
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

	static int inputClass() {
		String studentClass = input();
		return classFormat(studentClass);
	}

	static int classFormat(String target) {
		classError(target);
		return getClassId(target);
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

	static int getClassId(String target) {
		int classId = 0;
		switch(target) {
		case "A" :
			classId = 1;
			break;
		case "B" :
			classId = 2;
			break;
		case "C" :
			classId = 3;
			break;
		}
		return classId;
	}

	static void insertStudent(int classId, String studentName) {
		int result = 0;
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		//接続文字列
		String url = "jdbc:postgresql://localhost:5433/postgres";
		String user = "postgres";
		String password = "postgres";

		try {
			Class.forName("org.postgresql.Driver");//ClassNotFoundException投げる

			//PostgreSQLへ接続
			conn = DriverManager.getConnection(url, user, password);

			//自動コミットOFF
			conn.setAutoCommit(false);

			Date date = new Date(Calendar.getInstance().getTimeInMillis());

			//INSERT文の実行
			String sql = "INSERT INTO Student (class, name, created_at, updated_at) VALUES(?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, classId);
			preparedStatement.setString(2, studentName);
			preparedStatement.setDate(3, date);
			preparedStatement.setDate(4, date);
			result = preparedStatement.executeUpdate();
			conn.commit();
			insertError(result);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		insertError(result);
	}

	static void insertError(int result) {
		if(result == 0) {
			System.out.println("何らかの原因で登録できませんでした。");
			System.exit(-1);
		}
	}

}

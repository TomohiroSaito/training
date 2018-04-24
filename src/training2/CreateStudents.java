package training2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import training2.studentmodel.party.Party;
import training2.studentmodel.party.PartyName;
import training2.studentmodel.student.Student;
import training2.studentmodel.student.StudentName;

public class CreateStudents {

	public static void main(String[] args) {
		outMessage(1);
		String studentName = inputName();
		outMessage(2);
		String studentClass = inputClass();
		Student student = new Student(new StudentName(studentName), new Party(new PartyName(studentClass)));
		StudentDataAccess studentDataAccess = new StudentDataAccess();
		studentDataAccess.insertStudent(student);
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

}

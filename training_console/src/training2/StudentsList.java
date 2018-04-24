package training2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import training2.studentmodel.party.PartyId;
import training2.studentmodel.party.PartyName;
import training2.studentmodel.student.Student;

public class StudentsList {

	public static void main(String[] args) {
		outMessage();
		String className = inputClass();
		PartyName partyName = new PartyName(className);
		StudentDataAccess studentDataAccess = new StudentDataAccess();
		PartyId partyId = studentDataAccess.selectClassId(partyName);
		ArrayList<Student> students = studentDataAccess.selectClassList(partyId);
		outClassList(students);
	}

	static void outMessage() {
		System.out.println("確認するクラスを入力してください。(A,B,Cのいずれか)");
	}

	static void outClassList(ArrayList<Student> students) {
		for(Student student : students) {
			int number = student.getStudentNumber().getNumber();
			String className = student.getParty().getPartyName().getName();
			String studentName = student.getStudentName().getName();
			String message = String.format("生徒番号:%d\tクラス名:%s\t生徒名:%s", number, className, studentName);
			System.out.println(message);
		}
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

	static void classError(String target) {
		if(!target.matches("A|B|C")) {
			System.out.println("クラスはA,B,Cの中から選んでください。");
			System.exit(-1);
		}

	}


}

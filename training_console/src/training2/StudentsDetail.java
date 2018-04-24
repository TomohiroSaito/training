package training2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import training2.studentmodel.student.PersonalRecord;
import training2.studentmodel.student.Student;
import training2.studentmodel.student.StudentNumber;

public class StudentsDetail {
	static final String INPUT_NUMBER = "情報を確認する生徒の生徒番号を入力してください。";

	public static void main(String[] args) throws IOException {
		StudentNumber studentNumber = inputExistStudent();
		StudentDataAccess studentDataAccess = new StudentDataAccess();
		Student student = studentDataAccess.selectStudentDetail(studentNumber);
		String message = createStudentDetailMessage(student);
		outStudentDetail(message);
	}

	static StudentNumber inputExistStudent() {
		boolean exist = false;
		StudentNumber studentNumber = null;
		StudentDataAccess studentDataAccess = new StudentDataAccess();
		do {
			int number = repeatInputNumber();
			studentNumber = new StudentNumber(number);
			exist = studentDataAccess.existStudent(studentNumber);
			checkExistStudent(exist);
		} while(!exist);

		return studentNumber;
	}

	static int repeatInputNumber() {
		int number = 0;
		do {
			outMessage(INPUT_NUMBER);
			number = inputNumber();
		} while(number == -1);
		return number;
	}

	static void outStudentDetail(String message) {
		System.out.println(message);
	}

	static String createStudentDetailMessage(Student student) {
		StringBuilder tempMessage = new StringBuilder("");
		int sumRecord = 0;
		String apenndMessage = String.format("生徒番号:\t%d\nクラス:\t\t%s\n名前:\t\t%s\n", student.getStudentNumber().getNumber(), student.getParty().getPartyName().getName(), student.getStudentName().getName());
		tempMessage.append(apenndMessage);
		if(student.getPersonalRecordList().size() == 0) {
			tempMessage.append("成績は未設定です。\n");
		} else {
			for(PersonalRecord personalRecord : student.getPersonalRecordList()) {
				apenndMessage = String.format("%s:\t\t%d点\n", personalRecord.getSubject().getSubjectName().getName(), personalRecord.getRecord().getRecord());
				tempMessage.append(apenndMessage);
				sumRecord += personalRecord.getRecord().getRecord();
			}
			apenndMessage = String.format("成績の合計:\t%d点\n", sumRecord);
			tempMessage.append(apenndMessage);
		}
		return new String(tempMessage);
	}

	static void outMessage(String message) {
		System.out.println(message);
	}

	static int inputNumber() {
		String stringNumber = input();
		if(numberError(stringNumber)) {
			return -1;
		}
		return numberFormat(stringNumber);
	}

	static boolean numberError(String target) {
		boolean hasError = false;
		if(!target.matches("[0-9]*")) {
			System.out.println("数字を入力してください。");
			hasError = true;
		}
		return hasError;
	}

	static int numberFormat(String target) {
		int number = 0;
		try {
			number = Integer.parseInt(target);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return number;
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
	static void checkExistStudent(boolean exist) {
		if(!exist) {
			System.out.println("存在しない生徒番号です。");
		}
	}

}

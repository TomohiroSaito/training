package training2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import training2.studentmodel.student.PersonalRecord;
import training2.studentmodel.student.Record;
import training2.studentmodel.student.StudentNumber;
import training2.studentmodel.subject.Subject;
import training2.studentmodel.subject.SubjectId;
import training2.studentmodel.subject.SubjectName;

public class RegisteStudentsRecode {
	static final String INPUT_NUMBER_MESSAGE = "成績を登録する生徒の生徒番号を入力してください。";
	static final String CONFIRM_MESSAGE = "登録内容に間違いはありませんか？\n%s\n(yes/no)";
	static final String COMPLETE_REGISTER_MESSAGE = "登録が完了しました。";

	public static void main(String[] args) throws IOException {
		outMessage(INPUT_NUMBER_MESSAGE);
		int number = inputNumber();
		StudentNumber studentNumber = new StudentNumber(number);
		StudentDataAccess studentDataAccess = new StudentDataAccess();
		boolean exist = studentDataAccess.existStudent(studentNumber);
		checkExistStudent(exist);
		ArrayList<PersonalRecord> personalRecords = inputRecords();
		confirmRegisterInformation(studentNumber, personalRecords);
		studentDataAccess.insertRecords(studentNumber, personalRecords);
		outMessage(COMPLETE_REGISTER_MESSAGE);
	}

	static void outMessage(String message) {
		System.out.println(message);
	}

	static String outRecordsMessage(int number) {
		String subjectName = null;
		switch(number) {
			case 0:
				System.out.println("英語の成績を入力してください。");
				subjectName = "英語";
				break;
			case 1:
				System.out.println("数学の成績を入力してください。");
				subjectName = "数学";
				break;
			case 2:
				System.out.println("国語の成績を入力してください。");
				subjectName = "国語";
				break;
			case 3:
				System.out.println("社会の成績を入力してください。");
				subjectName = "社会";
				break;
			case 4:
				System.out.println("理科の成績を入力してください。");
				subjectName = "理科";
				break;
		}
		return subjectName;
	}

	static boolean judge() {
		String select = input();
		confirmError(select);
		return judgeConfirmPattern(select);
	}

	static void confirmError(String select) {
		if(!select.matches("yes|no")) {
			System.out.println("yesかnoを入力してください。");
			System.exit(-1);
		}
	}

	static boolean judgeConfirmPattern(String select) {
		boolean judge = false;
		if(select.matches("yes")) {
			judge = true;
		}
		return judge;
	}

	static String createConfirmRecordsMessage(ArrayList<PersonalRecord> personalRecords) {
		StringBuilder message = new StringBuilder("");
		for(int i = 0; i < 5; ++i) {
			PersonalRecord personalRecord = personalRecords.get(i);
			String subjectName = personalRecord.getSubject().getSubjectName().getName();
			int record = personalRecord.getRecord().getRecord();
			String appendMessage = String.format("%s:%d点\t", subjectName, record);
			message.append(appendMessage);
		}
		String createMessage = String.format(CONFIRM_MESSAGE, message);
		return createMessage;
	}

	static void confirmRegisterInformation(StudentNumber studentNumber, ArrayList<PersonalRecord> personalRecords) {
		String message = createConfirmRecordsMessage(personalRecords);
		System.out.println(message);
		if(!judge()) {
			System.out.println("最初からやり直してください。");
			System.exit(-1);
		}
	}

	static int inputNumber() {
		String stringNumber = input();
		numberError(stringNumber);
		return numberFormat(stringNumber);
	}

	static void numberError(String target) {
		if(!target.matches("[0-9]*")) {
			System.out.println("数字を入力してください。");
			System.exit(-1);
		}
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

	static ArrayList<PersonalRecord> inputRecords() {
		ArrayList<PersonalRecord> personalRecords = new ArrayList<PersonalRecord>();
		for(int i = 0; i < 5; ++i) {
			String tempSubjectName = outRecordsMessage(i);
			StudentDataAccess studentDataAccess = new StudentDataAccess();
			int tempRecord = inputRecord();
			SubjectName subjectName = new SubjectName(tempSubjectName);
			SubjectId subjectId = studentDataAccess.selectSubjectId(subjectName);
			Subject subject = new Subject(subjectName, subjectId);
			Record record = new Record(tempRecord);
			PersonalRecord personalRecord = new PersonalRecord(subject, record);
			personalRecords.add(personalRecord);
		}
		return personalRecords;
	}

	static int inputRecord() {
		int record = inputNumber();
		recordRangeCheck(record);
		return record;
	}

	static void recordRangeCheck(int target) {
		if(target < 1 || 100 < target) {
			System.out.println("成績は1~100の値です。");
			System.exit(-1);
		}
	}

	static void checkExistStudent(boolean exist) {
		if(!exist) {
			System.out.println("存在しない生徒番号です。");
			System.exit(-1);
		}
	}

}

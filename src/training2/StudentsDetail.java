package training2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import training2.studentmodel.student.StudentNumber;

public class StudentsDetail {
	static final String INPUT_NUMBER = "情報を確認する生徒の生徒番号を入力してください。";

	public static void main(String[] args) throws IOException {
		boolean exist = false;
		do {
			outMessage(INPUT_NUMBER);
			int number = inputNumber();
			StudentDataAccess studentDataAccess = new StudentDataAccess();
			StudentNumber studentNumber = new StudentNumber(number);
			exist = studentDataAccess.existStudent(studentNumber);
			checkExistStudent(exist);
		} while(!exist);
	}

	static void outMessage(String message) {
		System.out.println(message);
	}


/*	static String outRecordsMessage(int number) {
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

	static String createConfirmRecordsMessage(int number, int record) {
		String message = null;
		switch(number) {
			case 0:
				message = String.format("英語:%d点\t", record);
				break;
			case 1:
				message = String.format("数学:%d点\t", record);
				break;
			case 2:
				message = String.format("国語:%d点\t", record);
				break;
			case 3:
				message = String.format("社会:%d点\t", record);
				break;
			case 4:
				message = String.format("理科:%d点\t", record);
				break;
		}
		return message;
	}

	static void confirmRegisterInformation(int number, ArrayList<PersonalRecord> personalRecords) {
		StringBuilder message = new StringBuilder(String.format("生徒番号:%d\n", number));
		for(int i = 0; i < 5; ++i) {
			PersonalRecord personalRecord = personalRecords.get(i);
			message.append(createConfirmRecordsMessage(i, personalRecord.getRecord().getRecord()));
		}
		System.out.println(message);
		outMessage(4);
		if(!judge()) {
			System.out.println("最初からやり直してください。");
		}
	}*/

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

/*	static ArrayList<PersonalRecord> inputRecords() {
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
	}*/

	static void checkExistStudent(boolean exist) {
		if(!exist) {
			System.out.println("存在しない生徒番号です。");
		}
	}

}

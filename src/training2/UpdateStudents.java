package training2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import training2.studentmodel.party.Party;
import training2.studentmodel.party.PartyName;
import training2.studentmodel.student.PersonalRecord;
import training2.studentmodel.student.Record;
import training2.studentmodel.student.Student;
import training2.studentmodel.student.StudentNumber;
import training2.studentmodel.subject.Subject;
import training2.studentmodel.subject.SubjectId;
import training2.studentmodel.subject.SubjectName;

public class UpdateStudents {
	static final String INPUT_NUMBER_MESSAGE = "情報を修正する生徒の生徒番号を入力してください。";
	static final String INPUT_CLASS_MESSAGE = "修正後の生徒のクラスを入力してください。(A,B,Cのいずれか)";
	static final String CONFIRM_MESSAGE = "修正内容に間違いはありませんか？\n%s\n(yes/no)";
	static final String COMMIT_MESSAGE = "情報を修正しました。";
	static final String RETRY_MESSAGE = "最初からやり直してください。";
	static final String NOT_RESULT_MESSAGE = "存在しない生徒番号です。";

	public static void main(String[] args) throws IOException {
		boolean existStudent = false;
		boolean existRecord = false;

		//数字を入力するまで生徒番号の入力を促す
		do {
			outMessage(INPUT_NUMBER_MESSAGE);
			int number = inputNumber();
			if(number == -1) {
				continue;
			}

			StudentNumber studentNumber = new StudentNumber(number);

			StudentDataAccess studentDataAccess = new StudentDataAccess();

			//入力した生徒番号の生徒・成績がDBに存在していることを確認
			existStudent = studentDataAccess.existStudent(studentNumber);
			existRecord = studentDataAccess.existRecord(studentNumber);

			//生徒・成績が存在していれば、生徒・成績を入力してもらい、確認後DBに登録
			if(existStudent && existRecord) {
				Student student = inputUpdateStudentAndRecord(studentNumber);
				String message = createUpdateStudentAndRecordMessage(student);
				int resultJudge = 0;
				do {
					outMessage(message);
					resultJudge = judge();
				} while(resultJudge == -1);
				if(resultJudge == 0) {
					continue;
				}

				studentDataAccess.updateStudent(student);
				studentDataAccess.updateRecord(student);
				outMessage(COMMIT_MESSAGE);
				break;

			//生徒のみ存在していれば、生徒を入力してもらい、確認後DBに登録
			} else if(existStudent){
				Student student = inputUpdateStudentOnly(studentNumber);
				String message = createUpdateStudentOnlyMessage(student);
				int resultJudge = 0;
				do {
					outMessage(message);
					resultJudge = judge();
				} while(resultJudge == -1);
				if(resultJudge == 0) {
					continue;
				}

				studentDataAccess.updateStudent(student);
				outMessage(COMMIT_MESSAGE);
				break;


			//生徒が存在していなければ、エラーメッセージを出力して再度入力を促す
			} else {
				outMessage(NOT_RESULT_MESSAGE);
				outMessage(RETRY_MESSAGE);
				continue;
			}
		} while(true);
	}

	static String createUpdateStudentAndRecordMessage(Student student) {
		String firstMessage = String.format("生徒番号:\t%d\nクラス:\t%s\n", student.getStudentNumber().getNumber(), student.getParty().getPartyName().getName());
		String message = createConfirmRecordsMessage(student.getPersonalRecordList());
		StringBuilder buildMessage = new StringBuilder(firstMessage);
		buildMessage.append(message);
		message = String.format(CONFIRM_MESSAGE, buildMessage);
		return message;
	}

	static String createUpdateStudentOnlyMessage(Student student) {
		String message = String.format("生徒番号:\t%d\nクラス:\t%s\n成績は未設定です\n", student.getStudentNumber().getNumber(), student.getParty().getPartyName().getName());
		message = String.format(CONFIRM_MESSAGE, message);
		return message;
	}

	static Student inputUpdateStudentAndRecord(StudentNumber studentNumber) {
		outMessage(INPUT_CLASS_MESSAGE);
		PartyName partyName = new PartyName(inputClass());
		ArrayList<PersonalRecord> personalRecords = new ArrayList<PersonalRecord>();
		personalRecords = inputRecords();
		Student student = new Student(studentNumber, new Party(partyName), personalRecords);
		return student;
	}

	static String inputClass() {
		String studentClass = null;
		boolean hasError = false;
		do {
			studentClass = input();
			hasError = classError(studentClass);
			if(hasError) {
				System.out.println("もう一度入力してください。");
			}
		} while(hasError);
		return studentClass;
	}

	static Student inputUpdateStudentOnly(StudentNumber studentNumber) {
		outMessage(INPUT_CLASS_MESSAGE);
		PartyName partyName = new PartyName(inputClass());
		Student student = new Student(studentNumber, new Party(partyName));
		return student;
	}

	static boolean classError(String target) {
		boolean hasError = false;
		if(!target.matches("A|B|C")) {
			System.out.println("クラスはA,B,Cの中から選んでください。");
			hasError = true;
		}
		return hasError;
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

	static int judge() {
		int judge = 0;
		String select = input();
		if(!confirmError(select)) {
			judge = -1;
		} else {
			judge = judgeConfirmPattern(select);
		}
		return judge;
	}

	static boolean confirmError(String select) {
		boolean match = true;
		if(!select.matches("yes|no")) {
			System.out.println("yesかnoを入力してください。");
			match = false;
		}
		return match;
	}

	static int judgeConfirmPattern(String select) {
		int judge = 0;
		if(select.matches("yes")) {
			judge = 1;
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
		return new String(message);
	}

	static int inputNumber() {
		int number = 0;
		String stringNumber = input();
		if(!numberError(stringNumber)) {
			number = -1;
		} else {
			number = numberFormat(stringNumber);
		}
		return number;
	}

	static boolean numberError(String target) {
		boolean hasNumber = true;
		if(!target.matches("[0-9]*")) {
			System.out.println("数字を入力してください。");
			hasNumber = false;
		}
		return hasNumber;
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
			String tempSubjectName = null;
			StudentDataAccess studentDataAccess = new StudentDataAccess();
			int tempRecord = 0;
			do {
				tempSubjectName = outRecordsMessage(i);
				tempRecord = inputRecord();
			} while(tempRecord == -1);
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
		if(recordRangeCheck(record)) {
			record = -1;
		}
		return record;
	}

	static boolean recordRangeCheck(int target) {
		boolean hasError = false;
		if(target < 1 || 100 < target) {
			System.out.println("成績は1~100の値です。");
			hasError = true;
		}
		return hasError;
	}

	static void checkExistStudent(boolean exist) {
		if(!exist) {
			System.out.println("存在しない生徒番号です。");
			System.exit(-1);
		}
	}

}

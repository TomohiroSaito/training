package pack;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDataAccess;
import studentmodel.party.Party;
import studentmodel.party.PartyName;
import studentmodel.student.PersonalRecord;
import studentmodel.student.Record;
import studentmodel.student.Student;
import studentmodel.student.StudentName;
import studentmodel.student.StudentNumber;
import studentmodel.subject.Subject;
import studentmodel.subject.SubjectId;
import studentmodel.subject.SubjectName;

/**
 * Servlet implementation class CreateStudentsLogic
 */
public class CreateStudentsLogic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String RECORD_NAME_1 = "英語";
	private static final String RECORD_NAME_2 = "数学";
	private static final String RECORD_NAME_3 = "国語";
	private static final String RECORD_NAME_4 = "社会";
	private static final String RECORD_NAME_5 = "理科";

	private static final String NAME_BYTE_ERROR_MESSAGE = "名前が登録できない長さです。";
	private static final String REQUIRED_FIELDS_ERROR_MESSAGE = "※の項目は必須項目です。";
	private static final String RECORD_ALL_INPUT_ERROR_MESSAGE = "成績は5つすべて入力してください。";
	private static final String CLASS_SELECT_ERROR_MESSAGE = "クラスはA,B,Cの中から選んでください。";
	private static final String RECORD_NUMBER_ERROR_MESSAGE = "成績は数字を入力してください。";
	private static final String RECORD_RANGE_ERROR_MESSAGE = "成績は1~100の値で入力してください。";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateStudentsLogic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//入力項目の取得
		String name = (String)request.getParameter("name");
		String party = (String)request.getParameter("party");
		String english = (String)request.getParameter("english");
		String math = (String)request.getParameter("math");
		String japanese = (String)request.getParameter("japanese");
		String social = (String)request.getParameter("social");
		String science = (String)request.getParameter("science");

		//必須項目が入力されていることの確認
		if(name == null || name.equals("") || party == null || party.equals("")) {
			String message = REQUIRED_FIELDS_ERROR_MESSAGE;
			request.setAttribute("message", message);
			gotoPage(request, response, "/student_register.jsp");
			return;
		}

		//成績が5つすべて入力されていなければ、生徒情報のみの登録
		if((english == "" || english == null) && (math == "" || math == null) && (japanese == "" || japanese == null) && (social == "" || social == null) && (science == "" || science == null)) {

			//名前が正しいバイト数であることの確認
			if(nameByteError(request, response, name)) {
				return;
			}

			//クラス名がA,B,Cであることの確認
			if(classError(request, response, party)) {
				return;
			}

			//生徒情報の登録
			studentOnryRegister(new StudentName(name), new Party(new PartyName(party)));

			gotoPage(request, response, "/result.jsp");
			return;
		}
		//成績が5つすべて入力されていることの確認
		if(english == "" || english == null || math == "" || math == null || japanese == "" || japanese == null || social == "" || social == null || science == "" || science == null) {
			String message = RECORD_ALL_INPUT_ERROR_MESSAGE;
			request.setAttribute("message", message);
			gotoPage(request, response, "/student_register.jsp");
			return;
		}

		//成績が5つすべて入力されていれば、生徒情報と成績情報の登録
		if(!(english == "" || english == null || math == "" || math == null || japanese == "" || japanese == null || social == "" || social == null || science == "" || science == null)) {

			//名前が正しいバイト数であることの確認
			if(nameByteError(request, response, name)) {
				return;
			}

			//クラス名がA,B,Cであることの確認
			if(classError(request, response, party)) {
				return;
			}

			//生徒情報の登録
			studentOnryRegister(new StudentName(name), new Party(new PartyName(party)));

			//成績情報の登録
			//成績が数字であり範囲内であることの確認、PersonalRecordListの作成
			ArrayList<PersonalRecord> personalRecordList = new ArrayList<PersonalRecord>();
			int record = formatRecord(request, response, english);
			if(record == -1) {
				return;
			}
			SubjectName subjectName = new SubjectName(RECORD_NAME_1);
			setRecordList(personalRecordList, new Record(record), subjectName);
			record = formatRecord(request, response, math);
			if(record == -1) {
				return;
			}
			subjectName = new SubjectName(RECORD_NAME_2);
			setRecordList(personalRecordList, new Record(record), subjectName);
			record = formatRecord(request, response, japanese);
			if(record == -1) {
				return;
			}
			subjectName = new SubjectName(RECORD_NAME_3);
			setRecordList(personalRecordList, new Record(record), subjectName);
			record = formatRecord(request, response, social);
			if(record == -1) {
				return;
			}
			subjectName = new SubjectName(RECORD_NAME_4);
			setRecordList(personalRecordList, new Record(record), subjectName);
			record = formatRecord(request, response, science);
			if(record == -1) {
				return;
			}
			subjectName = new SubjectName(RECORD_NAME_5);
			setRecordList(personalRecordList, new Record(record), subjectName);

			//DBへ登録
			StudentDataAccess studentDataAccess = new StudentDataAccess();
			StudentNumber studentNumber = studentDataAccess.getMaxStudentNumber();
			studentDataAccess.insertRecords(studentNumber, personalRecordList);

			gotoPage(request, response, "/result.jsp");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	private boolean nameByteError(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException {
		boolean hasError = false;
		int count = name.getBytes(Charset.forName("UTF-8")).length;
		if(!(count <= 20)) {
			String message = NAME_BYTE_ERROR_MESSAGE;
			request.setAttribute("message", message);
			gotoPage(request, response, "/student_register.jsp");
			hasError = true;
		}
		return hasError;
	}

	private boolean classError(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
		boolean hasError = false;
		if(!target.matches("A|B|C")) {
			String message = CLASS_SELECT_ERROR_MESSAGE;
			request.setAttribute("message", message);
			gotoPage(request, response, "/student_register.jsp");
			hasError = true;
		}
		return hasError;
	}

	private void studentOnryRegister(StudentName studentName, Party party) {
		StudentDataAccess studentDataAccess = new StudentDataAccess();
		Student student = new Student(studentName, party);
		studentDataAccess.insertStudent(student);

	}

	private boolean recordNumberError(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
		boolean hasError = false;
		if(!target.matches("[0-9]*")) {
			String message = RECORD_NUMBER_ERROR_MESSAGE;
			request.setAttribute("message", message);
			gotoPage(request, response, "student_register.jsp");
			hasError = true;
		}
		return hasError;
	}

	private boolean recordRangeCheck(HttpServletRequest request, HttpServletResponse response, int target) throws ServletException, IOException {
		boolean hasError = false;
		if(target < 1 || 100 < target) {
			String message = RECORD_RANGE_ERROR_MESSAGE;
			request.setAttribute("message", message);
			gotoPage(request, response, "student_register.jsp");
			hasError = true;
		}
		return hasError;
	}

	private int formatRecord(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
		if(recordNumberError(request, response, target)) {
			return -1;
		}
		int record = numberFormat(request, response, target);
		if(recordRangeCheck(request, response, record)) {
			return -1;
		}
		return record;
	}

	static int numberFormat(HttpServletRequest request, HttpServletResponse response, String target) {
		int number = 0;
		try {
			number = Integer.parseInt(target);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return number;
	}

	private void setRecordList(ArrayList<PersonalRecord> personalRecordList, Record record, SubjectName subjectName) {
		StudentDataAccess studentDataAccess = new StudentDataAccess();
		SubjectId subjectId = studentDataAccess.selectSubjectId(subjectName);
		Subject subject = new Subject(subjectName, subjectId);
		PersonalRecord personalRecord = new PersonalRecord(subject, record);
		personalRecordList.add(personalRecord);
	}


}

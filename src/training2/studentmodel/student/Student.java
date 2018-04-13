package training2.studentmodel.student;
import java.util.ArrayList;

import training2.studentmodel.party.Party;

public class Student {
	private StudentNumber studentNumber;
	private StudentName studentName;
	private Party party;
	private ArrayList<PersonalRecord> personalRecordList;

	public Student() {
		this.studentNumber = new StudentNumber();
		this.studentName = new StudentName();
		this.party = new Party();
		this.personalRecordList = new ArrayList<PersonalRecord>();
	}

	public Student(StudentName studentName, Party party) {
		this.studentName = studentName;
		this.party = party;
		this.studentNumber = new StudentNumber();
		this.personalRecordList = new ArrayList<PersonalRecord>();
	}

	public Student(StudentNumber studentNumber, Party party, StudentName studentName) {
		this.studentNumber = studentNumber;
		this.party = party;
		this.studentName = studentName;
		this.personalRecordList = new ArrayList<PersonalRecord>();
	}

	public Student(StudentNumber studentNumber, StudentName studentName, Party party,
			ArrayList<PersonalRecord> personalRecords) {
		this.studentNumber = studentNumber;
		this.party = party;
		this.studentName = studentName;
		this.personalRecordList = personalRecords;

	}

	public Student(StudentNumber studentNumber, Party party, ArrayList<PersonalRecord> personalRecords) {
		this.studentNumber = studentNumber;
		this.studentName = new StudentName();
		this.party = party;
		this.personalRecordList = personalRecords;
	}

	public StudentNumber getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(StudentNumber studentNumber) {
		this.studentNumber = studentNumber;
	}

	public StudentName getStudentName() {
		return studentName;
	}

	public void setStudentName(StudentName studentName) {
		this.studentName = studentName;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public ArrayList<PersonalRecord> getPersonalRecordList() {
		return personalRecordList;
	}

	public void setPersonalRecordList(ArrayList<PersonalRecord> personalRecordList) {
		this.personalRecordList = personalRecordList;
	}

}

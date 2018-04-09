package training2.studentmodel.student;
import java.util.ArrayList;

import training2.studentmodel.party.Party;

public class Student {
	private StudentNumber studentNumber;
	private StudentName studentName;
	private Party party;
	private ArrayList<PersonalRecord> personalRecordList;

	public Student() {
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

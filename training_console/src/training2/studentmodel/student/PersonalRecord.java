package training2.studentmodel.student;
import training2.studentmodel.subject.Subject;

public class PersonalRecord {
	private Record record;
	private Subject subject;


	public PersonalRecord() {
		this.record = new Record();
		this.subject = new Subject();
	}

	public PersonalRecord(Subject subject, Record record) {
		this.subject = subject;
		this.record = record;
	}

	public PersonalRecord(Record record) {
		this.record = record;
		this.subject = new Subject();
	}


	public Record getRecord() {
		return record;
	}


	public void setRecord(Record record) {
		this.record = record;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}

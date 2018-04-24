package studentmodel.subject;

public class Subject {
	private SubjectName subjectName;
	private SubjectId subjectId;

	public Subject() {
		this.subjectName = new SubjectName();
		this.subjectId = new SubjectId();
	}

	public Subject(SubjectName subjectName, SubjectId subjectId) {
		this.subjectName = subjectName;
		this.subjectId = subjectId;
	}

	public SubjectName getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(SubjectName subjectName) {
		this.subjectName = subjectName;
	}

	public SubjectId getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(SubjectId subjectId) {
		this.subjectId = subjectId;
	}

}

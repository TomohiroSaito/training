package training2.studentmodel.student;
import training2.studentmodel.party.Party;

public class PersonalRecord {
	private Record record;
	private Party party;


	public PersonalRecord() {
	}


	public Record getRecord() {
		return record;
	}


	public void setRecord(Record record) {
		this.record = record;
	}


	public Party getParty() {
		return party;
	}


	public void setParty(Party party) {
		this.party = party;
	}

}

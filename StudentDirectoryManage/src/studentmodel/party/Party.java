package studentmodel.party;

public class Party {
	PartyId partyId;
	PartyName partyName;

	public Party() {
		this.partyId = new PartyId();
		this.partyName = new PartyName();
	}

	public Party(PartyName partyName) {
		this.partyName = partyName;
		this.partyId = new PartyId();
	}

	public PartyId getPartyId() {
		return partyId;
	}

	public void setPartyId(PartyId partyId) {
		this.partyId = partyId;
	}

	public PartyName getPartyName() {
		return partyName;
	}

	public void setPartyName(PartyName partyName) {
		this.partyName = partyName;
	}

}

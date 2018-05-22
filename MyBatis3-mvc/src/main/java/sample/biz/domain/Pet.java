package sample.biz.domain;

import java.util.Date;

public class Pet {
	private String petId;
	private String petName;
	private String ownerName;
	private String price;
	private Date birthDate;
	public String getPetId() {
		return petId;
	}
	public void setPetId(String petId) {
		this.petId = petId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}

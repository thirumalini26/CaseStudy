package com.entity;

import java.util.Date;

public class Suspect {
	private int suspectId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String contactInfo;

    public Suspect() {}

    public Suspect(int suspectId, String firstName, String lastName, Date dateOfBirth, String gender, String contactInfo) {
        this.suspectId = suspectId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInfo = contactInfo;
    }

	public int getSuspectId() {
		return suspectId;
	}

	public void setSuspectId(int suspectId) {
		this.suspectId = suspectId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
    

}

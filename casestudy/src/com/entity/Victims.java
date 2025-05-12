package com.entity;

import java.util.Date;

public class Victims {
	 

	 private int victimId;
	    private String firstName;
	    private String lastName;
	    private Date dateOfBirth;
	    private String gender;
	    private String contactInfo;

	    public Victims() {}

	    public Victims(int victimId, String firstName, String lastName, Date dateOfBirth, String gender, String contactInfo) {
	        this.victimId = victimId;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.dateOfBirth = dateOfBirth;
	        this.gender = gender;
	        this.contactInfo = contactInfo;
	    }
	    public int getVictimId() {
			return victimId;
		}

		public void setVictimId(int victimId) {
			this.victimId = victimId;
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

package com.entity;

import java.util.Date;

public class Incident {
	  private int incidentId;
	    private String incidentType;
	    private Date incidentDate;
	    private double locationLatitude;
	    private double locationLongitude;
	    private String description;
	    private String status;
	    private Integer victimId;
	    private Integer suspectId;
	    public int getIncidentId() {
			return incidentId;
		}

		public void setIncidentId(int incidentId) {
			this.incidentId = incidentId;
		}

		public String getIncidentType() {
			return incidentType;
		}

		public void setIncidentType(String incidentType) {
			this.incidentType = incidentType;
		}

		public Date getIncidentDate() {
			return incidentDate;
		}

		public void setIncidentDate(Date incidentDate) {
			this.incidentDate = incidentDate;
		}

		public double getLocationLatitude() {
			return locationLatitude;
		}

		public void setLocationLatitude(double locationLatitude) {
			this.locationLatitude = locationLatitude;
		}

		public double getLocationLongitude() {
			return locationLongitude;
		}

		public void setLocationLongitude(double locationLongitude) {
			this.locationLongitude = locationLongitude;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Integer getVictimId() {
			return victimId;
		}

		public void setVictimId(Integer victimId) {
			this.victimId = victimId;
		}

		public Integer getSuspectId() {
			return suspectId;
		}

		public void setSuspectId(Integer suspectId) {
			this.suspectId = suspectId;
		}

		public Integer getOfficerId() {
			return officerId;
		}

		public void setOfficerId(Integer officerId) {
			this.officerId = officerId;
		}

		private Integer officerId;

	    public Incident() {}

	    public Incident(int incidentId, String incidentType, Date incidentDate, double locationLatitude,
	                    double locationLongitude, String description, String status,
	                    Integer victimId, Integer suspectId, Integer officerId) {
	        this.incidentId = incidentId;
	        this.incidentType = incidentType;
	        this.incidentDate = incidentDate;
	        this.locationLatitude = locationLatitude;
	        this.locationLongitude = locationLongitude;
	        this.description = description;
	        this.status = status;
	        this.victimId = victimId;
	        this.suspectId = suspectId;
	        this.officerId = officerId;
	    }

}

package com.dao;
import com.entity.Incident;
import com.entity.Reports;

import java.util.Date;
import java.util.List;
public interface ICrimeAnalysisService {
	
	

	    boolean createIncident(Incident incident);

	    //   Update the status of an incident based on ID.
	     
	    boolean updateIncidentStatus(int incidentId, String newStatus) throws com.exception.IncidentNumberNotFoundException;
	    boolean addVictim(com.entity.Victims victim);
	    boolean addSuspect(com.entity.Suspect suspect);
	    boolean addEvidence(com.entity.Evidence evidence);


	    List<Incident> getIncidentsInDateRange(Date start, Date end);

	   
	    List<Incident> searchIncidents(String incidentType);

	    List<Incident> getAllIncidents();

	    Reports generateIncidentReport(int incidentId) throws com.exception.IncidentNumberNotFoundException;
	}



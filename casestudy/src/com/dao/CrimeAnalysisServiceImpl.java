package com.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Evidence;
import com.entity.Incident;
import com.entity.Reports;
import com.entity.Suspect;
import com.entity.Victims;
import com.exception.IncidentNumberNotFoundException;
import com.util.DBConnUtil;


public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

    private static Connection connection;

    public CrimeAnalysisServiceImpl() {
        connection = DBConnUtil.getConnection();
    }

    @Override
    public boolean createIncident(Incident incident) {
        String sql = "INSERT INTO incidents (incidentid, incidenttype, incidentdate, locationlatitude, locationlongitude, description, status, victimid, suspectid, officerid) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, incident.getIncidentId());
            ps.setString(2, incident.getIncidentType());
            ps.setDate(3, new java.sql.Date(incident.getIncidentDate().getTime()));
            ps.setDouble(4, incident.getLocationLatitude());
            ps.setDouble(5, incident.getLocationLongitude());
            ps.setString(6, incident.getDescription());
            ps.setString(7, incident.getStatus());
            ps.setInt(8, incident.getVictimId());
            ps.setInt(9, incident.getSuspectId());
            ps.setInt(10, incident.getOfficerId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateIncidentStatus(int incidentId, String newStatus) throws IncidentNumberNotFoundException {
        String sql = "UPDATE incidents SET status = ? WHERE incidentid = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, incidentId);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new IncidentNumberNotFoundException("Incident ID not found: " + incidentId);
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Incident> getIncidentsInDateRange(Date start, Date end) {
        List<Incident> list = new ArrayList<>();
        String sql = "SELECT * FROM incidents WHERE incidentdate BETWEEN ? AND ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(start.getTime()));
            ps.setDate(2, new java.sql.Date(end.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Incident incident = extractIncident(rs);
                list.add(incident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Incident> searchIncidents(String incidentType) {
        List<Incident> list = new ArrayList<>();
        String sql = "SELECT * FROM incidents WHERE LOWER(incidenttype) = LOWER(?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, incidentType);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Incident incident = extractIncident(rs);
                list.add(incident);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Reports generateIncidentReport(int incidentId) throws IncidentNumberNotFoundException {
        String check = "SELECT * FROM incidents WHERE incidentid = ?";
        String insert = "INSERT INTO reports (reportid, incidentid, reportingofficer, reportdate, reportdetails, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(check)) {
            ps.setInt(1, incidentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int officerId = rs.getInt("officerid");
                String type = rs.getString("incidenttype");
                String desc = rs.getString("description");

                int reportId = new java.util.Random().nextInt(1000) + 100;

                Reports report = new Reports(reportId, incidentId, officerId, new Date(),
                        "Auto report: " + type + " - " + desc, "Draft");

                try (PreparedStatement ins = connection.prepareStatement(insert)) {
                    ins.setInt(1, report.getReportId());
                    ins.setInt(2, report.getIncidentId());
                    ins.setInt(3, report.getReportingOfficer());
                    ins.setDate(4, new java.sql.Date(report.getReportDate().getTime()));
                    ins.setString(5, report.getReportDetails());
                    ins.setString(6, report.getStatus());

                    ins.executeUpdate();
                }

                return report;
            } else {
                throw new IncidentNumberNotFoundException("Incident ID not found: " + incidentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to convert ResultSet to Incident object
    private Incident extractIncident(ResultSet rs) throws SQLException {
        return new Incident(
                rs.getInt("incidentid"),
                rs.getString("incidenttype"),
                rs.getDate("incidentdate"),
                rs.getDouble("locationlatitude"),
                rs.getDouble("locationlongitude"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("victimid"),
                rs.getInt("suspectid"),
                rs.getInt("officerid")
        );
    }
    @Override
    public List<Incident> getAllIncidents() {
        List<Incident> list = new ArrayList<>();
        String sql = "SELECT * FROM incidents";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(extractIncident(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public boolean addVictim(Victims victim) {
        String sql = "INSERT INTO victims (victimid, firstname, lastname, dateofbirth, gender, contactinfo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, victim.getVictimId());
            ps.setString(2, victim.getFirstName());
            ps.setString(3, victim.getLastName());
            ps.setDate(4, new java.sql.Date(victim.getDateOfBirth().getTime()));
            ps.setString(5, victim.getGender());
            ps.setString(6, victim.getContactInfo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addSuspect(Suspect suspect) {
        String sql = "INSERT INTO suspects (suspectid, firstname, lastname, dateofbirth, gender, contactinfo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, suspect.getSuspectId());
            ps.setString(2, suspect.getFirstName());
            ps.setString(3, suspect.getLastName());
            ps.setDate(4, new java.sql.Date(suspect.getDateOfBirth().getTime()));
            ps.setString(5, suspect.getGender());
            ps.setString(6, suspect.getContactInfo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean addEvidence(Evidence evidence) {
        String sql = "INSERT INTO evidence (evidenceid, description, locationfound, incidentid) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, evidence.getEvidenceId());
            ps.setString(2, evidence.getDescription());
            ps.setString(3, evidence.getLocationFound());
            ps.setInt(4, evidence.getIncidentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
}


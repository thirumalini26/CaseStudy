package com.main;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.dao.CrimeAnalysisServiceImpl;
import com.entity.Incident;
import com.entity.Reports;
import com.entity.Suspect;
import com.entity.Victims;
import com.exception.IncidentNumberNotFoundException;

public class MainModule {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CrimeAnalysisServiceImpl service = new CrimeAnalysisServiceImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\n==== Crime Analysis & Reporting System ====");
            System.out.println("1. Create Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. Get Incidents in Date Range");
            System.out.println("4. Search Incidents by Type");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. View All Incident");
            System.out.println("7. Add new Victim");
            System.out.println("8. Add new Suspect");
            System.out.println("9. Add New Evidence");
            System.out.println("10.Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Incident ID: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Type: ");
                        String type = sc.nextLine();
                        System.out.print("Date (yyyy-MM-dd): ");
                        Date date = sdf.parse(sc.nextLine());
                        System.out.print("Latitude: ");
                        double lat = sc.nextDouble();
                        System.out.print("Longitude: ");
                        double lng = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Status: ");
                        String status = sc.nextLine();
                        System.out.print("Victim ID: ");
                        int vid = sc.nextInt();
                        System.out.print("Suspect ID: ");
                        int sid = sc.nextInt();
                        System.out.print("Officer ID: ");
                        int oid = sc.nextInt();

                        Incident inc = new Incident(id, type, date, lat, lng, desc, status, vid, sid, oid);
                        boolean success = service.createIncident(inc);
                        System.out.println(success ? "Incident created successfully!" : "Failed to create incident.");
                        break;

                    case 2:
                        System.out.print("Incident ID to update: ");
                        int updateId = sc.nextInt(); sc.nextLine();
                        System.out.print("New Status: ");
                        String newStatus = sc.nextLine();
                        service.updateIncidentStatus(updateId, newStatus);
                        System.out.println("Status updated.");
                        break;

                    case 3:
                        System.out.print("Start Date (yyyy-MM-dd): ");
                        Date start = sdf.parse(sc.nextLine());
                        System.out.print("End Date (yyyy-MM-dd): ");
                        Date end = sdf.parse(sc.nextLine());
                        List<Incident> rangeList = service.getIncidentsInDateRange(start, end);
                        for (Incident i : rangeList) {
                            System.out.println(i.getIncidentId() + " | " + i.getIncidentType() + " | " + i.getStatus());
                        }
                        break;

                    case 4:
                        System.out.print("Incident Type: ");
                        String searchType = sc.nextLine();
                        List<Incident> typeList = service.searchIncidents(searchType);
                        for (Incident i : typeList) {
                            System.out.println(i.getIncidentId() + " | " + i.getIncidentType() + " | " + i.getStatus());
                        }
                        break;

                    case 5:
                        System.out.print("Incident ID to generate report for: ");
                        int reportId = sc.nextInt();
                        Reports r = service.generateIncidentReport(reportId);
                        System.out.println("Report ID: " + r.getReportId());
                        System.out.println("Details: " + r.getReportDetails());
                        break;

                    
                        
                    case 6:
                        List<Incident> allIncidents = service.getAllIncidents();
                        for (Incident i : allIncidents) {
                            System.out.println(i.getIncidentId() + " | " + i.getIncidentType() + " | " + i.getStatus());
                        }
                        break;
                        
                    case 7: // Add New Victim
                        System.out.print("Victim ID: ");
                        int victimid = sc.nextInt(); sc.nextLine();
                        System.out.print("First Name: ");
                        String vfname = sc.nextLine();
                        System.out.print("Last Name: ");
                        String vlname = sc.nextLine();
                        System.out.print("Date of Birth (yyyy-MM-dd): ");
                        Date vdob = sdf.parse(sc.nextLine());
                        System.out.print("Gender: ");
                        String vgender = sc.nextLine();
                        System.out.print("Contact Info: ");
                        String vcontact = sc.nextLine();
                        Victims victim = new Victims(victimid, vfname, vlname, vdob, vgender, vcontact);
                        boolean vstatus = service.addVictim(victim);
                        System.out.println(vstatus ? "Victim added successfully!" : "Failed to add victim.");
                        break;

                    case 8: // Add New Suspect
                        System.out.print("Suspect ID: ");
                        int suspectid = sc.nextInt(); sc.nextLine();
                        System.out.print("First Name: ");
                        String sfname = sc.nextLine();
                        System.out.print("Last Name: ");
                        String slname = sc.nextLine();
                        System.out.print("Date of Birth (yyyy-MM-dd): ");
                        Date sdob = sdf.parse(sc.nextLine());
                        System.out.print("Gender: ");
                        String sgender = sc.nextLine();
                        System.out.print("Contact Info: ");
                        String scontact = sc.nextLine();
                        Suspect suspect = new Suspect(suspectid, sfname, slname, sdob, sgender, scontact);
                        boolean sstatus = service.addSuspect(suspect);
                        System.out.println(sstatus ? "Suspect added successfully!" : "Failed to add suspect.");
                        break;
                        
                    case 9:
                        System.out.print("Evidence ID: ");
                        int evidenceid = sc.nextInt(); sc.nextLine();
                        System.out.print("Description: ");
                        String description = sc.nextLine();
                        System.out.print("Location Found: ");
                        String locationfound = sc.nextLine();
                        System.out.print("Incident ID: ");
                        int incidentid = sc.nextInt();

                        com.entity.Evidence evidence = new com.entity.Evidence(evidenceid, description, locationfound, incidentid);
                        boolean estatus = service.addEvidence(evidence);
                        System.out.println(estatus ? "Evidence added successfully!" : "Failed to add evidence.");
                        break;

                        
                    case 10:
                        System.out.println("Exiting system...");
                        sc.close();
                        return;



                    default:
                        System.out.println("Invalid option.");
                }

            } catch (IncidentNumberNotFoundException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}


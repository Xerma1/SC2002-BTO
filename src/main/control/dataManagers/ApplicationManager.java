package main.control.dataManagers;

import main.control.viewFilters.IViewFilter;
import main.control.viewFilters.ViewFilterFactory;
import main.control.TimeManager;
import main.entity.Application.ApplicationStatus;
import main.entity.Applicant;
import main.entity.Officer;
import main.entity.Project;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ApplicationManager {
    private static final String APPL_CSV_PATH = "data/processed/bto_applications.csv";
    private static final String OFF_RSG_PATH = "data/processed/officer_registrations.csv";
    private static final String PROJ_PATH = "data/processed/bto_projects.csv";

    private static List<String[]> fetchAll() {
        List<String[]> rows = null;
        try {
            rows = DataManager.readCSV(APPL_CSV_PATH); // Use utility method
        } catch (IOException e) {
            System.err.println("Error reading file: " + APPL_CSV_PATH);
            e.printStackTrace();
        }
        return rows;
    }

    public static List<String[]> getFetchAll() {
        return ApplicationManager.fetchAll();
    }



    public static boolean applyBTO(Applicant applicant, Scanner scanner) { // Writes an application to bto_applications.csv
        // Reading files
        List<String[]> applications = ApplicationManager.getFetchAll();
        List<String[]> projects = ProjectManager.getFetchAll();
        if (projects == null) {
            System.out.println("No projects available.");
            return false;
        }
        List<String[]> officerRsgs;
        try {
            officerRsgs = DataManager.readCSV(OFF_RSG_PATH);
        } catch (IOException e) {
            System.out.println("An error occurred while reading officer registrations: " + e.getMessage());
            return false;
        }

        // Gets valid projects based on filter
        IViewFilter viewInterface = ViewFilterFactory.getViewFilter(applicant.filterType);
        List<String[]> validProjects = viewInterface.getFilter();

        // Asking for project
        String projName = ProjectManager.askProjName(scanner);

        // Checking if project is valid
        boolean foundValidProject = false;
        String[] validProject = null;
        for (String[] values : validProjects) {
            if (projName.equalsIgnoreCase(values[0].trim())) { // Project name found
                foundValidProject = true;
                validProject = values;
                projName = values[0].trim(); // Normalize project name to Title Case
                break;
            }
        }
        if (!foundValidProject) { // Project name not found
            System.out.println("Project is not available.");
            return false;
        }

        // Asking for room type
        String roomType = ProjectManager.askRoomType(applicant, scanner);
        int roomIndex = roomType.equals("2-room") ? 3 : 6;
    
        // Check room availability
        if ("0".equals(validProject[roomIndex].trim())) {
            System.out.println("No vacancies.");
            return false;
        }

        // Checking if application is active
        if (!TimeManager.isValidDate(validProject[8].trim(), validProject[9].trim())) {
            System.out.println("Project not active.");
            return false;
        }

        // Checking if user has already applied for project
        for (String[] values : applications) {
            if (values.length <= 1) { // To guard against empty lines
                continue;
            }
            if (values[1].trim().equals(applicant.getUserID())) {
                System.out.println("You have already applied for this project.");
                return false;
            }
        }

        // Additional Officer logic
        if (applicant instanceof Officer) {
            for (String[] project : projects) {
                if (project.length > 12) { // Ensure the project array has enough elements
                    String projectName = project[0].trim();
                    String[] registeredOfficers = project[12].split(",");
            
                    boolean isSameProject = projectName.equals(projName);
                    boolean isApplicantOfficer = Arrays.stream(registeredOfficers)
                                                    .map(String::trim)
                                                    .anyMatch(officer -> officer.equals(applicant.getName()));
            
                    if (isApplicantOfficer) {
                        if (isSameProject) {
                            System.out.println("You are registered as an officer for this project.");
                            return false;
                        }
                    }

                    if (isApplicantOfficer && TimeManager.isValidDate(project[8].trim(), project[9].trim())) {
                        System.out.println("You are already registered as an officer for another active project.");
                        return false;
                    }
                }
            }
        }
            // Check if officer is registering to be an officer for a project
            // TODO: ^

        // Writing application back to CSV file
        String[] newApplication = {
            applicant.getName(),
            applicant.getUserID(),
            applicant.getAge() + "",
            applicant.getMarried() ? "Married" : "Single",
            projName,
            roomType,
            validProject[roomIndex+1], // Price
            validProject[8], // Opening date
            validProject[9], // Closing date
            ApplicationStatus.PENDING.name(),
            validProject[10], // Manager
            validProject[12] // Officers
        };
        DataManager.appendToCSV(APPL_CSV_PATH, newApplication);
        
        return true;
    }

    public static void viewApplication(Applicant applicant) {
        String filePath = APPL_CSV_PATH; // Path to the CSV file

        List<String[]> applications;
        try {
            applications = DataManager.readCSV(filePath);
            for (String[] values : applications) {
                if (values[1].trim().equals(applicant.getUserID())) {
                    // TODO: Fix formatting
                    System.out.printf("%-15s %-15s %-10s %-15s %-15s %-10s%n",
                    "Project Name", "Room Type", "Price", "Opening date", "Closing date", "Status");
                    System.out.printf("%-15s %-15s %-10s %-15s %-15s %-10s%n",
                        values[4], values[5], values[6], values[7], values[8], values[9]); 
                    return;
                }
            }
            System.out.println("No applications found for this user.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading applications: " + e.getMessage());
        }
    }
}




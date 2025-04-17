package main.control.dataManagers;

import main.control.viewFilters.IviewFilter;
import main.control.viewFilters.ViewFilterFactory;
import main.control.TimeManager;
import main.entity.Application.ApplicationStatus;
import main.entity.Applicant;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ApplicationManager {
    private static final String APPL_CSV_PATH = "data/processed/bto_applications.csv";

    
    public static boolean applyBTO(Applicant applicant, Scanner scanner) { // Writes an application to bto_applications.csv
        String filePath = APPL_CSV_PATH; // Path to the CSV file

        IviewFilter viewInterface = ViewFilterFactory.getViewFilter(applicant.filterType);
        List<String[]> validProjects = viewInterface.getFilter(); // Gets valid projects based on filter

        // Asking for project
        String projName = ProjectManager.askProjName(scanner);

        // Checking if project is valid
        boolean foundValidProject = false;
        String[] validProject = null;
        for (String[] values : validProjects) {
            if (projName.equalsIgnoreCase(values[0].trim())) { // Project name found
                foundValidProject = true;
                validProject = values;
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
        List<String[]> applications;
        try {
            applications = DataManager.readCSV(filePath);
            for (String[] values : applications) {
                if (values.length <= 1) { // To guard against empty lines
                    continue;
                }
                if (values[1].trim().equals(applicant.getUserID())) {
                    System.out.println("You have already applied for this project.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading applications: " + e.getMessage());
            return false;
        }

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
            ApplicationStatus.PENDING.name()
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
                    // NOTE: if user types in project name it is directly put in the application not Title Case
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




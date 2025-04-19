package main.control.dataManagers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import main.control.viewFilters.IViewFilter;
import main.control.viewFilters.ViewFilterFactory;
import main.entity.Applicant;
import main.entity.Enquiry;
import main.entity.Officer;
import main.entity.Project;

public class EnquiryManager {
    private static final String ENQ_CSV_PATH = "data/processed/enquiries.csv";
    private static final int COL_USER_NAME = 0;
    private static final int COL_USER_ID = 1;
    private static final int COL_PROJECT_NAME = 2;
    private static final int COL_QUESTION = 3;
    private static final int COL_ANSWER = 4;


    // Used by applicants to submit enquiries
    public static boolean createEnquiry(Applicant applicant, Scanner scanner) {
        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();
        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        IViewFilter viewInterface = ViewFilterFactory.getViewFilter(applicant.getFilterType());
        List<Project> validProjects = viewInterface.getValidProjects();

        boolean projectFound = false;
        for (Project project : validProjects) { // If applicant is making an enquiry for a project that is available to them
            if (project.getProjectName().equalsIgnoreCase(projectName)) {
                projectFound = true;
                projectName = project.getProjectName(); // Normalising to Title Case
            }
        }
        if (!projectFound) { 
            System.out.println("Project not available."); // No projects avaiable to applicant
            return false;
        }

        if (question.equals("\n")) {
            System.out.println("No question entered."); // No question entered
            return false;
        }

        Enquiry enquiry = new Enquiry(applicant.getName(), applicant.getUserID(), projectName, question);
        writeEnquiry(enquiry);
        return true; // Successful enquiry
    }

    // Write new enquiry to csv file
    public static void writeEnquiry(Enquiry enquiry) {
        String answer = "\"nil\"";

        String[] newEnquiry = {
            enquiry.getUserName(),
            enquiry.getUserID(),
            enquiry.getProjectName(),
            "\"" + enquiry.getQuestion() + "\"", // Formatting for csv
            answer
        };

        DataManager.appendToCSV(ENQ_CSV_PATH, newEnquiry);
    }

    // Used by officers to reply to enquiries
    public static boolean replyEnquiry(Officer officer, Enquiry enquiry, Scanner scanner) {
        List<Project> handling = officer.getHandling();
        boolean foundEnquiry = false;
        for (Project project : handling) {
            if (project.getProjectName().equalsIgnoreCase(enquiry.getProjectName())) { // If enquiry comes from a project being handled
                foundEnquiry = true;
            }
        }
        if (!foundEnquiry) {
            System.out.println("No such enquiry found.");
            return false;
        }
        
        if (!enquiry.getAnswer().equalsIgnoreCase("nil")) { // If enquiry already has an answer
            System.out.println("Enquiry already has reply. Edit if you want to change the reply.");
            return false;
        }
        
        System.out.print("Reply: "); // Asking for reply
        String answer = scanner.nextLine();
        enquiry.setAnswer(answer);
        return true;
    }

    // Used by officers to view enquiries for projects
    public static void viewEnquiries(Officer officer) {
        List<String[]> enquiries = null;
        try {
            enquiries = DataManager.readCSV(ENQ_CSV_PATH);
        } catch (IOException e) {
            System.err.println("Error reading file: " + ENQ_CSV_PATH);
            e.printStackTrace();
        }

        if (enquiries == null || enquiries.isEmpty()) { // No enquiries in file
            System.out.println("No enquiries.");
            return;
        }

        List<Project> projects = officer.getHandling();
        if (projects == null || projects.isEmpty()) { // No projects
            System.out.println("No enquiries.");
            return;
        }

        boolean foundEnquiry = false;
        for (Project project : projects) {
            for (String[] enquiry : enquiries) { // n^2 gg
                if (enquiry[COL_PROJECT_NAME].equalsIgnoreCase(project.getProjectName())) { // Finding enquiries by project name
                    System.out.printf("%-10s %-15s %-10s%n",
                    "Username", "User ID", "Project Name");
                    System.out.println("=".repeat(140));
                    System.out.printf("%-10s %-15s %-10s%n",
                        enquiry[COL_USER_NAME],
                        enquiry[COL_USER_ID],
                        enquiry[COL_PROJECT_NAME]);
                    System.out.println("Question:  " + enquiry[COL_QUESTION]);
                    System.out.println("Answer:    "+ enquiry[COL_ANSWER]);
                    System.out.println("=".repeat(140));
                    System.out.println();
                    foundEnquiry = true;
                }
            }
        }
        if (!foundEnquiry) {
            System.out.print("No enquiries.");
        }

        System.out.println();
    }

    // Used by officers to delete enquiries
    public static void deleteEnquiry(Enquiry enquiryObject) {
        List<String[]> enquiries = null;
        try {
            enquiries = DataManager.readCSV(ENQ_CSV_PATH); // Use utility method
        } catch (IOException e) {
            System.err.println("Error reading file: " + ENQ_CSV_PATH);
            e.printStackTrace();
        }

        for (String[] enquiry : enquiries) {
            if (identifier matcher) {

            }
        }

    }

    // Used by officers to edit enquiries
    public static void editEnquiry() {};

}

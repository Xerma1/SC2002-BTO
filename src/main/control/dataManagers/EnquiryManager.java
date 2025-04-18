package main.control.dataManagers;

import java.util.List;
import java.util.Scanner;

import main.control.viewFilters.IViewFilter;
import main.control.viewFilters.ViewFilterFactory;
import main.entity.Applicant;
import main.entity.Enquiry;
import main.entity.Project;

public class EnquiryManager {
    private static final String ENQ_CSV_PATH = "data/processed/enquiries.csv";

    // Used by applicants to submit enquiries
    public static boolean askEnquiry(Applicant applicant, Scanner scanner) {
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
            System.out.println("Project not available.");
            return false;
        }

        if (question.equals("\n")) {
            System.out.println("No question entered.");
            return false;
        }

        Enquiry enquiry = new Enquiry(applicant.getUserID(), projectName, question);
        writeNewEnquiry(enquiry);
        return true; // Successful enquiry
    }

    // Write new enquiry to csv file
    public static void writeNewEnquiry(Enquiry enquiry) {
        String answer = "\"nil\"";

        String[] newEnquiry = {
            enquiry.getUserID(),
            enquiry.getProjectName(),
            "\"" + enquiry.getQuestion() + "\"", // Formatting for csv
            answer
        };

        DataManager.appendToCSV(ENQ_CSV_PATH, newEnquiry);
    }

}

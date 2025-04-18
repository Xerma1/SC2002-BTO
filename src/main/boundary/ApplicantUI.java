package main.boundary;

import main.control.dataManagers.UserManager;
import main.control.dataManagers.ApplicationManager;
import main.control.viewFilters.IViewFilter;
import main.control.viewFilters.ViewFilterFactory;
import main.entity.Applicant;
import main.entity.User;
import main.entity.Project;

import java.util.Scanner;

public class ApplicantUI implements IusergroupUI {

     private static final String applicantMenu = """
                
                1.  Change password
                2.  View list of BTO projects
                3.  Apply for BTO project
                4.  View details of applied BTO project and application status
                5.  Request booking of flat
                6.  View receipt of booked flat
                7.  Request withdrawl from BTO application/booking
                8.  Submit enquiry
                9.  View/edit/delete enquiries
                10. Exit

                """;
    @Override
    public void runMenu(Scanner scanner, User user) {

        // Create instance of applicant class
        Applicant applicant = (Applicant) UserManager.createUser(user);
        String username = applicant.getName();

        // Switch statement to process each option
        int choice;
        do {
            // Print UI
            System.out.println("<< Logged in as applicant: " + username + " >>");
            System.out.println(applicantMenu);
            System.out.print("Input: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    applicant.changePassword(scanner);
                    System.out.println("Press 'enter' to continue...");
                    scanner.nextLine();
                }
                case 2 -> {
                    IViewFilter viewInterface = ViewFilterFactory.getViewFilter(applicant.filterType);
                    System.out.println("Showing all active projects available to you: ");
                    System.out.println();
                    viewInterface.view();
                    System.out.println("Press 'enter' to continue...");
                    scanner.nextLine();
                }
                case 3 -> {                   
                    if (ApplicationManager.applyBTO(applicant, scanner)) {
                        System.out.println("Applied successfully!");
                    } else {
                        System.out.println("Failed to apply.");
                    }
                    System.out.println("Press 'enter' to continue...");
                    scanner.nextLine();
                    
                }
                case 4 -> {
                    System.out.println("Viewing application details: ");
                    ApplicationManager.viewApplication(applicant);
                    System.out.println("Press 'enter' to continue...");
                    scanner.nextLine();
                }
                
                case 10 -> System.out.println("Exiting....");
                default -> System.out.print("default");
            }
        } while (choice != 10);
        
    }

}

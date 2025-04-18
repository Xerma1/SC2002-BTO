package main.boundary;

import main.control.dataManagers.ApplicationManager;
import main.control.dataManagers.UserManager;
import main.control.viewFilters.*;
import main.entity.Officer;
import main.entity.User;

import java.util.Scanner;

public class OfficerUI implements IusergroupUI {

    private static final String officerMenu = """
                
                1. Change password
                2. View list of BTO projects

                                < Officer >

                3. Register to join BTO project as an officer
                4. View status of officer registration
                5. View details of handling projects
                6. View enquiries of handling projects
                7. Reply to enquiries 
                8. Book flat for client

                                < Applicant >

                9.  Apply for BTO project as an applicant
                10. View details of applied BTO project and application status
                11. Request booking of flat
                12. View receipt of booked flat
                13. Request withdrawl from BTO application/booking
                14. Submit enquiry 
                15. View/edit/delete enquiries 
                16. Exit
                
                """;

    @Override
    public void runMenu(Scanner scanner, User user) {

        // Create instance of officer class
        Officer officer = (Officer) UserManager.createUser(user);
        String username = officer.getName();
       
        // Switch statement to process each option
        int choice;
        do{
            // Print UI
            System.out.println("<< Logged in as officer: " + username + " >>");
            System.out.println(officerMenu);
            System.out.print("Input: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> {
                    officer.changePassword(scanner);
                    System.out.println("Press 'enter' to continue...");
                    scanner.nextLine();
                }
                case 2 -> {
                    IViewFilter viewInterface = ViewFilterFactory.getViewFilter(officer.filterType);
                    System.out.println("Showing all active projects: ");
                    System.out.println();
                    viewInterface.view();
                    System.out.println("Press 'enter' to continue...");
                    scanner.nextLine();
                }
                case 9 -> {
                    if (ApplicationManager.applyBTO(officer, scanner)) {
                        System.out.println("Applied successfully!");
                    } else {
                        System.out.println("Failed to apply.");
                    }
                    System.out.println("Press 'enter' to continue...");
                    scanner.nextLine();
                }
                case 10 -> {
                    ApplicationManager.viewApplication(officer);
                    System.out.println("Press 'enter' to continue...");
                    scanner.nextLine();
                }
                case 16 -> System.out.println("Exiting....");
                default -> System.out.print("default");
            }
        } while (choice != 16);
        
    }

}

package main.boundary;

import java.util.Scanner;
import main.control.dataManager;
import main.entity.Manager;
import main.entity.User;

public class managerUI implements IusergroupUI {

    private static final String managerMenu = """
                
                1.  Change password
                2.  View list of BTO projects
                3.  Create/edit/delete BTO project listing
                4.  Toggle visiblity of project
                5.  View pending and approved HDB officer registrations
                6.  Approve/reject HDB officer registrations
                7.  Approve/reject applicant BTO applications
                8.  Approve/reject applicant withdrawl requests
                9.  Generate report of applicants with respective flat bookings
                10. View all enquiries of all projects
                11. View/reply to enquiries regarding own handling projects
                12. Exit

                """;

    @Override
    public void runMenu(Scanner scanner, String username, String userID) {
         // Create instance of manager class
            User userdata = dataManager.getFetch(userID);
            String name = userdata.getName();
            String ID = userdata.getUserID();
            int age = userdata.getAge();
            boolean married = userdata.getMarried();
        
            Manager manager = new Manager(name, ID, age, married);
       
        // Switch statement to process each option
        int choice;
        do{
            // Print UI
            System.out.println("<< Logged in as manager: " + username + " >>");
            System.out.println(managerMenu);
            System.out.print("Input: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> {
                    manager.changePassword();
                }
                case 12 -> System.out.println("Exiting....");
                default -> System.out.print("default");
            }
        }while (choice != 12);
        
    }

}

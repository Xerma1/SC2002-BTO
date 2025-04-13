package main.boundary;

import java.util.Scanner;
import main.control.dataManager;
import main.entity.manager;

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
    public void printUI(Scanner scanner, String username, String userID) {
         // Create instance of manager class
            String[] userdata = dataManager.search(userID);
            String name = userdata[0];
            String ID = userdata[1];
            int age = Integer.parseInt(userdata[2]);
            boolean married = false;
            if (userdata[3] == "Married"){
                married = true;
            }
        
            manager Manager = new manager(name, ID, age, married);
       
        // Switch statement to process each option
        int choice;
        do{
            // Print UI
            System.out.println("<< Viewing as applicant: " + username + " >>");
            System.out.println(managerMenu);
            System.out.print("Input: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> {
                    Manager.changePassword();
                }
                case 12 -> System.out.println("Exiting....");
                default -> System.out.print("default");
            }
        }while (choice != 12);
        
    }

}

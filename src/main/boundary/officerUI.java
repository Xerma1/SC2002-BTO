package main.boundary;

import java.util.Scanner;
import main.control.dataManager;
import main.entity.officer;

public class officerUI implements IusergroupUI {

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
        
            officer Officer = new officer(name, ID, age, married);
       
        // Switch statement to process each option
        int choice;
        do{
            // Print UI
            System.out.println("<< Viewing as applicant: " + username + " >>");
            System.out.println(officerMenu);
            System.out.print("Input: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> {
                    Officer.changePassword();
                }
                case 16 -> System.out.println("Exiting....");
                default -> System.out.print("default");
            }
        }while (choice != 16);
        
    }

}

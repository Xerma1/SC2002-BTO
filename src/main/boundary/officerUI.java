package main.boundary;

import java.util.Scanner;

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
                
                Input: """;

    @Override
    public void printUI(Scanner scanner, String username) {

        //TODO: create instance of officer class
            //
            //


        // Print UI
        System.out.println("<< Viewing as officer: " + username + " >>");
        System.out.println(officerMenu);

        int choice = scanner.nextInt();
        scanner.nextLine();

        //Input switch statment 



        
    }

}

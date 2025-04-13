package main.boundary;

import java.util.Scanner;

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
                11. View/reply to enquiries regaring own handling projects
                12. Exit

                Input: """;

    @Override
    public void printUI(Scanner scanner, String username) {
         //TODO: create instance of manager class
            //
            //
            

        // Print UI
        System.out.println("<< Viewing as manager: " + username + " >>");
        System.out.println(managerMenu);

        int choice = scanner.nextInt();
        scanner.nextLine();

        //Input switch statment

    }

}

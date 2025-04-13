package main.boundary;

import java.util.Scanner;

public class applicantUI implements IusergroupUI {

     private static final String applicantMenu = """
                
                1.  Change password
                2.  View list of BTO projects
                3.  Apply for BTO project
                4.  View details of applied BTO project and application status
                5.  Book flat
                6.  View receipt of booked flat
                7.  Request withdrawl from BTO application/booking
                8.  Submit enquiry
                9.  View/edit/delete enquiries
                10. Exit

                Input: """;
    @Override
    public void printUI(Scanner scanner, String username) {
        //TODO: create instance of applicant class
            //
            //
       
        // Print UI
        System.out.println("<< Viewing as applicant: " + username + " >>");
        System.out.println(applicantMenu);

        int choice = scanner.nextInt();
        scanner.nextLine();

        //Input switch statment 
    }

}

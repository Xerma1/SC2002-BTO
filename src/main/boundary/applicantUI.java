package main.boundary;

import java.util.Scanner;

import main.control.dataManager;
import main.entity.applicant;
import main.entity.user;

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
    public void printUI(Scanner scanner, String username, String userID) {

        //TODO: create instance of applicant class
            String[] userdata = dataManager.search(userID);
            String name = userdata[0];
            String ID = userdata[1];
            int age = Integer.parseInt(userdata[2]);
            boolean married = false;
            if (userdata[3] == "Married"){
                married = true;
            }
        
            applicant Applicant = new applicant(name, ID, age, married);
       
        // Switch statement to process each option
        int choice;
        do{
            // Print UI
            System.out.println("<< Viewing as applicant: " + username + " >>");
            System.out.println(applicantMenu);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> {
                    Applicant.changePassword();
                }
                case 10 -> System.out.println("Exiting....");
                default -> System.out.print("default");
            }
        }while (choice != 10);
        
    }

}

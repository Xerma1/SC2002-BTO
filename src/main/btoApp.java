package main;

import main.control.InvalidLoginException;
import main.control.loginManager;
import java.util.Scanner;
import main.boundary.*;

public class btoApp {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String usergroup = "";

        // UI
        System.out.println("Welcome to BTO application manager!");
        System.out.println(" ");
        System.out.println("Who are you logging in as?");
        System.out.println("1. Applicant");
        System.out.println("2. Officer");
        System.out.println("3. Manager");
        System.out.println(" ");
        System.out.print("Input: ");

        // Prompt user for usergroup
        while (usergroup.isEmpty()) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                usergroup = switch (choice){
                    case 1 -> "applicant";
                    case 2 -> "officer";
                    case 3 -> "manager";
                    default -> {
                        System.out.println("Please input only a number representing your choice");
                        yield ""; //Exit the switch with an empty string after printing error message
                    }   
                };
            } catch (Exception e){
                System.out.print("Invalid input. Please enter valid number. Input: ");
                scanner.nextLine(); // Clear the invalid input
            }
        }  

        // Prompt for username and password
        boolean isLoggedIn = false;
        do {
            try {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                isLoggedIn = loginManager.login(username, password, usergroup);
                
                } catch (InvalidLoginException e) {
                    System.out.println(e.getMessage()); // Print the custom exception message
                    }
            } while (isLoggedIn == false);

        System.out.println("Login successful!");
        

        // Display UI based on the usergroup
        switch (usergroup) {
            case "applicant" -> applicantUI.printUI(scanner);
            case "officer" -> officerUI.printUI(scanner);
            case "manager" -> managerUI.printUI(scanner);
            default -> System.out.println("Invalid user group.");
            }

        scanner.close();
        }
}

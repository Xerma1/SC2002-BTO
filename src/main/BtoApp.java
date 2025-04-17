package main;

import main.control.InvalidLoginException;
import main.control.LoginManager;
import main.control.UsergroupUIFactory;
import main.control.dataManagers.UserManager;
import main.entity.User;
import main.boundary.*;

import java.util.Scanner;


public class BtoApp {
    
    private static final String UI = """
            Welcome to BTO application manager!

            Who are you logging in as?
            1. Applicant
            2. Officer
            3. Manager
            
            """;
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String usergroup = "";

        // Print UI
        System.out.println(UI);
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
                        System.out.print("Invalid input. Try again. Input: ");
                        yield ""; // Exit the switch with an empty string after printing error message
                    }   
                };
            } catch (Exception e){
                System.out.print("Invalid input. Please enter valid number. Input: ");
            }
        }  

        // Prompt for username and password
        boolean isLoggedIn = false;
        String userID = "";
        do {
            try {
                System.out.print("Enter user ID (type 'return' to go back to previous page): ");
                userID = scanner.nextLine();

                // Handle program restart if user types in "return"
                if (userID.equalsIgnoreCase("return")) {
                    usergroup = ""; // Reset the user group to trigger re-selection
                    break; // Exit the login loop to go back to user group selection
                }

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                isLoggedIn = LoginManager.login(userID, password, usergroup);
                
                } catch (InvalidLoginException e) {
                    System.out.println(e.getMessage()); // Print the custom exception message
                    }
            } while (isLoggedIn == false);
        
        if (usergroup.isEmpty()) {
            // Restart the user group selection loop if "return" was entered
            main(args);
            return;
        }

        System.out.println("Login successful!");
        System.out.println("");
        
        // Get the username associated with the userID to be passed into userUI.printUI()
        User userdata = UserManager.getFetch(userID);
        String username = userdata.getName();
        
        // Run menu based on the usergroup using dependency injection
        IusergroupUI userUI = UsergroupUIFactory.getUI(usergroup);
        userUI.runMenu(scanner, username, userID);

        scanner.close();
        }
}

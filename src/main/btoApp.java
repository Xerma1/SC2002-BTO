package main;

import main.control.InvalidLoginException;
import main.control.dataSearch;
import main.control.loginManager;
import main.control.usergroupUIFactory;

import java.util.Scanner;
import main.boundary.*;

public class btoApp {
    
    private static final String UI = """
            Welcome to BTO application manager!

            Who are you logging in as?
            1. Applicant
            2. Officer
            3. Manager

            Input: """;
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String usergroup = "";

        // Print UI
        System.out.print(UI);

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
        String userID = "";
        do {
            try {
                System.out.print("Enter user ID: ");
                userID = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                isLoggedIn = loginManager.login(userID, password, usergroup);
                
                } catch (InvalidLoginException e) {
                    System.out.println(e.getMessage()); // Print the custom exception message
                    }
            } while (isLoggedIn == false);

        System.out.println("Login successful!");
        System.out.println("");
        
        //Get the username associated with the userID
        String [] data = dataSearch.search(userID, usergroup);
        String username = data[0];
        
        // Display UI based on the usergroup using dependency injection
        IusergroupUI userUI = usergroupUIFactory.getUI(usergroup);
        userUI.printUI(scanner, username);

        scanner.close();
        }
}

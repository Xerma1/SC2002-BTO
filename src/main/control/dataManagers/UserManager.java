package main.control.dataManagers;

import java.io.IOException;
import java.util.List;

import main.entity.User;

public class UserManager extends DataManager {
    // Constants for file paths and column indices
    private static final String USERS_CSV_PATH = "data/processed/users.csv";
    private static final int COL_NAME = 0;
    private static final int COL_USER_ID = 1;
    private static final int COL_AGE = 2;
    private static final int COL_MARTIAL_STATUS = 3;
    private static final int COL_PASSWORD = 4;

    // Private method to fetch sensitive user data. 
    private static User fetch(String userID) {
        String filePath = USERS_CSV_PATH; // Path to the CSV file

        try {
            List<String[]> rows = readCSV(filePath); // Use utility method
            for (String[] values : rows) {
                String storedUserID = values[COL_USER_ID].trim(); // Second column: userID
                if (storedUserID.equals(userID)) {
                    Boolean married = false;
                    if (values[COL_MARTIAL_STATUS].trim().equalsIgnoreCase("Married")) married = true;
                    // Create and return a User object
                    return new User(
                        values[COL_NAME].trim(), // Name
                        values[COL_USER_ID].trim(), // userID
                        Integer.parseInt(values[COL_AGE].trim()), // Age
                        married // Marital Status    
                    );
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        System.out.println("User not found in users.csv.");
        return null;
    }

    // Public method to allow other classes to call search()
    public static User getFetch(String userID){
        return UserManager.fetch(userID);
    }

    // Private method to change password
    private static void changePassword(String userID, String newPassword) {
        String filePath = USERS_CSV_PATH; // Path to the CSV file
        List<String[]> rows;

        try {
            rows = readCSV(filePath); // Use utility method
            for (String[] values : rows) {
                if (values[COL_USER_ID].trim().equals(userID)) { // Match userID
                    if (values[COL_PASSWORD].trim().equals(newPassword)) { // Check if new password is the same as old password
                        System.out.println("New password cannot be the same as the old password.");
                        return;
                    } else {
                        values[COL_PASSWORD] = newPassword; // Update the password
                        
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
            return;
        }

        // Write updated rows back to the file
        try {
            writeCSV(filePath, rows);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }

        System.out.println("Password updated successfully!");
        
    }

    // Public method that calls changePassword
    public static void getChangePassword(String userID, String newPassword){
        UserManager.changePassword(userID, newPassword);
    }
}


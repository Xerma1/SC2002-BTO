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
    private static User _fetch(String userID) {
        try {
            List<String[]> users = readCSV(USERS_CSV_PATH);
            for (String[] user : users) {
                if (user[COL_USER_ID].equals(userID)) { // If find user using userID
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

    // Private method to write password to file
    private static void _writePassword(String userID, String newPassword) {
        List<String[]> users;
        try {
            users = readCSV(USERS_CSV_PATH); // Use utility method
        } catch (IOException e) {
            System.err.println("Error reading file: " + USERS_CSV_PATH);
            e.printStackTrace();
            return;
        }

        for (String[] user : users) {
            if (user[COL_USER_ID].trim().equals(userID)) { // Match userID
                    user[COL_PASSWORD] = newPassword; // Update the password
                }
        }

        // Write updated rows back to the file
        // TODO: update single line instead of rewriting the whole file
        try {
            writeCSV(USERS_CSV_PATH, users);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + USERS_CSV_PATH);
            e.printStackTrace();
        }
    }

    // Public method that calls writePassword
    public static void writePassword(String userID, String newPassword) {
        UserManager._writePassword(userID, newPassword);
    }
}


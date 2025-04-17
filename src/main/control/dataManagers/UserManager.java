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
    private static final int COL_ACCESS_LEVEL = 5; // Access level column index

    // Private method to fetch sensitive user data
    private static User _fetch(String userID) {
        List<String[]> users = null;
        try {
            users = readCSV(USERS_CSV_PATH);
        } catch (IOException e) {
            System.err.println("Error reading file: " + USERS_CSV_PATH);
            e.printStackTrace();
        }

        for (String[] user : users) {
            if (user[COL_USER_ID].equals(userID)) { // Find user using userID   
                String name = user[COL_NAME];
                int age = Integer.parseInt(user[COL_AGE]);
                boolean married = Boolean.parseBoolean(user[COL_MARTIAL_STATUS]); 
                String password = user[COL_PASSWORD]; 
                String accessLevel = user[COL_ACCESS_LEVEL];              
                return new User(name, userID, age, married, password, accessLevel); // Return User object
            }
        }
        return null; // Return null if user not found
    }

    // Public method to fetch User object
    public static User fetch(String userID){
        return UserManager._fetch(userID);
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


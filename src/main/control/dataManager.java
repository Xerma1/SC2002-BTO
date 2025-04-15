package main.control;

import java.io.BufferedReader; //Used to read text from a file line by line
import java.io.BufferedWriter;
import java.io.FileReader; //Used to open and read the file
import java.io.FileWriter;
import java.io.IOException; //Handles exceptions that may occur during file operations
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import main.entity.User;
/*
 * This class's role is to handle on file read/write methods that the app uses
 * Current methods: utility read/write CSV, search(), getSearch(), changePassword(), 
 */

public class dataManager {
    // Constants for file paths and column indices
    private static final String USERS_CSV_PATH = "data/processed/users.csv";
    private static final int COL_NAME = 0;
    private static final int COL_USER_ID = 1;
    private static final int COL_AGE = 2;
    private static final int COL_MARTIAL_STATUS = 3;
    private static final int COL_PASSWORD = 4;

    // Utility method to read CSV file. Return unmodifiable list of defensive copies
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(",").clone()); // Defensive copy
            }
        }
        return Collections.unmodifiableList(rows); // Immutable outer list
    }

    // Utility method to write into CSV file
    public static void writeCSV(String filePath, List<String[]> rows) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } 
    }

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
        return dataManager.fetch(userID);
    }

    // Private method to change password
    private static void changePassword(String userID, String newPassword) {
        String filePath = USERS_CSV_PATH; // Path to the CSV file
        List<String[]> rows;

        try {
            rows = readCSV(filePath); // Use utility method
            for (String[] values : rows) {
                if (values[COL_USER_ID].trim().equals(userID)) { // Match userID
                    values[COL_PASSWORD] = newPassword; // Update the password
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

    //Public method that calls changePassword
    public static void getChangePassword(String userID, String newPassword){
        dataManager.changePassword(userID, newPassword);
    }

}


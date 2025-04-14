package main.control;

import java.io.BufferedReader; //Used to read text from a file line by line
import java.io.BufferedWriter;
import java.io.FileReader; //Used to open and read the file
import java.io.FileWriter;
import java.io.IOException; //Handles exceptions that may occur during file operations
import java.util.ArrayList;
import java.util.List;
/*
 * This class's role is to handle on file read/write methods that the app uses
 * Current methods: utility read/write CSV, search(), changePassword(), 
 */

public class dataManager {

    // Utility method to read CSV file
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(",")); // Split each line into an array of strings
            }
        }
        return rows;
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

    // Method to fetch user data
    public static String[] search(String userID) {
        String filePath = "data/processed/users.csv"; // Path to the CSV file

        try {
            List<String[]> rows = readCSV(filePath); // Use utility method
            for (String[] values : rows) {
                String storedUserID = values[1].trim(); // Second column: userID
                if (storedUserID.equals(userID)) {
                    return values; // Found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        System.out.println("User not found in users.csv.");
        return null;
    }

    // Method to change password
    public static void changePassword(String userID, String newPassword) {
        String filePath = "data/processed/users.csv"; // Path to the CSV file
        List<String[]> rows;

        try {
            rows = readCSV(filePath); // Use utility method
            for (String[] values : rows) {
                if (values[1].trim().equals(userID)) { // Match userID
                    values[4] = newPassword; // Update the password
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

}


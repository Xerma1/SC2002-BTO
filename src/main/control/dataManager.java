package main.control;

import java.io.BufferedReader; //Used to read text from a file line by line
import java.io.BufferedWriter;
import java.io.FileReader; //Used to open and read the file
import java.io.FileWriter;
import java.io.IOException; //Handles exceptions that may occur during file operations
import java.util.ArrayList;
import java.util.List;


public class dataManager {

    public static String[] search (String userID) { // Searches data to find a matching userID and return an array containing all personal data
        String filePath = "data/processed/users.csv"; // Path to the CSV file based on usergroup parameter

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { 
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Splits current line into an array of strings eg."admin,password" -> [admin, password] 
                String storedUserID = values[1].trim(); // Second column: userID, trimmed to remove leading and trailing whitespace

                // Check if userID matches
                if (storedUserID.equals(userID)) {
                    return values; // Found
                }
            }
        } catch (IOException e) { //catches any IOException that might occur while reading the file
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        // User not found, print error message and return empty array
        System.out.println("User not found in users.csv.");
        return null;
        
    }
    
    public static void changePassword(String userID, String newPassword){
        String filePath = "data/processed/users.csv"; // Path to the CSV file
        List<String[]> rows = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            // Read all rows into memory
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].trim().equals(userID)) { // Match userID 
                    userFound = true;
                    // Update the password (assuming it's in the fifth column)
                    values[4] = newPassword;
                }
                rows.add(values); // Add the row (updated or not) to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
            return;
        }
        
        if (!userFound) {
            System.out.println("User not found.");
            return;
        }

        // Write updated rows back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }

        System.out.println("Password updated successfully.");
        System.out.println("");
        
    }
    
    
}


package main.control;

import java.io.BufferedReader; //Used to read text from a file line by line
import java.io.FileReader; //Used to open and read the file
import java.io.IOException; //Handles exceptions that may occur during file operations

public class dataSearch {
    public static String[] search (String userID, String usergroup) { // Searches data to find a matching userID and return an array containing all personal data
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
        System.out.println("User not found in " + usergroup + ".csv.");
        return null;
        
    }
    

}

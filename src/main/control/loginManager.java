package main.control;

import java.io.BufferedReader; //Used to read text from a file line by line
import java.io.FileReader; //Used to open and read the file
import java.io.IOException; //Handles exceptions that may occur during file operations

public class loginManager {
    
    // Login function that checks whether the given username and password matches
    public static boolean login(String userID, String password, String usergroup) throws InvalidLoginException {
        String filePath = "data/processed/users.csv"; // Path to the users CSV file (the one being updated)

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { 
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Splits current line into an array of strings eg."admin,password" -> [admin, password] 
                String storedUserID = values[1].trim(); // Second column: userID, trimmed to remove leading and trailing whitespace
                String storedPassword = values[4].trim(); // Fifth column: password
                String storedUsergroup = values[5].trim(); //Sixth colum: User group

                 // Check if userID matches
                if (storedUserID.equals(userID)) {
                    // Check if password matches
                    if (storedPassword.equals(password)) {
                        // Check if user group matches
                        if (storedUsergroup.equals(usergroup)) {
                            return true; // Login successful
                        } else {
                            throw new InvalidLoginException("Error: " + storedUsergroup + " trying to log in as " + usergroup + ". Please return to user group selection.");
                        }
                    } else {
                        throw new InvalidLoginException("Error: Invalid password.");
                    }
                }
            }
        } catch (IOException e) { //catches any IOException that might occur while reading the file
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        // Login failed due to wrong userID
        throw new InvalidLoginException("Error: Invalid userID");
        
    }
}

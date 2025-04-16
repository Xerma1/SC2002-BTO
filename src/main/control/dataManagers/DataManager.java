package main.control.dataManagers;

import java.io.BufferedReader; // Used to read text from a file line by line
import java.io.BufferedWriter;
import java.io.FileReader; // Used to open and read the file
import java.io.FileWriter;
import java.io.IOException; // Handles exceptions that may occur during file operations
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * This class's role is to handle on file read/write methods that the app uses
 * Current methods: utility read/write CSV, search(), getSearch(), changePassword(), 
 */

public class DataManager {
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

    

}


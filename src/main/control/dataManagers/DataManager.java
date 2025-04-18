package main.control.dataManagers;

import java.io.BufferedReader; // Used to read text from a file line by line
import java.io.BufferedWriter;
import java.io.FileReader; // Used to open and read the file
import java.io.FileWriter;
import java.io.IOException; // Handles exceptions that may occur during file operations
import java.io.RandomAccessFile;
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

    // Utility method to append one line to CSV
    public static void appendToCSV(String filePath, String[] dataRow) {
        if (dataRow == null || dataRow.length == 0) {
            System.out.println("Error: Cannot append an empty or null row to the CSV file.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            // Move to the end of the file
            long fileLength = raf.length();
            if (fileLength > 0) {
                raf.seek(fileLength - 1);
                // Check if the last character is a newline
                if (raf.readByte() != '\n') {
                    raf.writeBytes("\n"); // Add a newline if it doesn't exist
                }
            }

            // Append the new row
            String csvLine = String.join(",", dataRow);
            raf.writeBytes(csvLine + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

}


package main.control.viewFilters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import main.control.DataManager;

public class viewProjectsAll implements IviewFilter {

    public void view(){

        String filePath = "data/processed/projectList.csv";

        List<String[]> rows = null;
        try {
            rows = DataManager.readCSV(filePath); // Use utility method
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

         // Print header
         String[] headers = rows.get(0); // Assuming the first row contains headers
         System.out.printf("%-15s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-15s %-15s %-10s%n",
                 headers[0], headers[1], headers[2], "Amount", "Price", headers[5], "Amount", "Price", "Opening date", "Closing date", headers[10]);
         System.out.println("=".repeat(140));
 
         // Print remaining rows
         for (int i = 1; i < rows.size(); i++) { // Skip the header row
             String[] project = rows.get(i);
             System.out.printf("%-15s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-15s %-15s %-10s%n",
                     project[0], project[1], project[2], project[3], project[4], project[5], project[6], project[7], project[8], project[9], project[10]);
         }
         System.out.println();
    }

}

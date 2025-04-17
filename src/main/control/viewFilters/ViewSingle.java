package main.control.viewFilters;

import main.control.dataManagers.ProjectManager;

import java.util.ArrayList;
import java.util.List;

public class ViewSingle implements IviewFilter {

    public void view() {
        List<String[]> rows = ProjectManager.getFetchAll();

        // Print header
        String[] headers = rows.get(0); // Assuming the first row contains headers
        System.out.printf("%-15s %-15s %-10s %-10s %-10s %-15s %-15s %-10s%n",
                headers[0], headers[1], headers[2], "Amount", "Price", "Opening date", "Closing date", headers[10]);
        System.out.println("=".repeat(140));
 
        // Print remaining rows
        for (int i = 1; i < rows.size(); i++) { // Skip the header row
            String[] project = rows.get(i);
            if (!project[3].trim().equals("0")) { // If there is at least one 2 room flat
                System.out.printf("%-15s %-15s %-10s %-10s %-10s %-15s %-15s %-10s%n",
                    project[0], project[1], project[2], project[3], project[4], project[8], project[9], project[10]);
            }
        }
        System.out.println();
    }

    public List<String[]> getFilter() {
        List<String[]> rows = ProjectManager.getFetchAll();

        List<String[]> validProjects = new ArrayList<>();
        for (String[] values : rows) {
            if (!(values[3].trim().equals("0"))) {
                validProjects.add(values);
            }
        }
        return validProjects;
    }
        
}



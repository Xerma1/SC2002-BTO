package main.control.viewFilters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import main.control.dataManager;

public class viewProjectsAll implements IviewFilter {

    public void view(){

        System.out.println("Showing all active projects available: ");
        System.out.println();
        String filePath = "data/processed/projectList.csv";

        List<String[]> rows = null;
        try {
            rows = dataManager.readCSV(filePath); // Use utility method
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        for (String[] project : rows){
            String[] first10Elements = Arrays.copyOfRange(project, 0, Math.min(10, project.length));
            System.out.println(String.join(", ", first10Elements));;
        }
        System.out.println();
    }

}

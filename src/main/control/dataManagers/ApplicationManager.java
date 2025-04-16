package main.control.dataManagers;

import java.io.IOException;
import java.util.List;

import main.control.viewFilters.IviewFilter;
import main.control.viewFilters.ViewFilterFactory;
import main.control.TimeManager;
import main.entity.User;
import main.entity.Applicant;

public class ApplicationManager {
    private static final String APPL_CSV_PATH = "data/processed/bto_applications.csv";

    
    public void apply(User user, String project) { // Writes an application to bto_applications.csv
        String filePath = APPL_CSV_PATH; // Path to the CSV file
        List<String[]> rows;

        if (user instanceof Applicant) { // Applicant application
            Applicant applicant = (Applicant) user; // Downcasting to access filterType
            IviewFilter viewInterface = ViewFilterFactory.getViewFilter(applicant.filterType);
            List<String[]> validProjects = viewInterface.getFilter(); // Gets valid projects based on filter
            for (String[] values : validProjects) {
                if (project.equals(values[0])) { // If project name exists in valid projects
                    if (TimeManager.isValidDate(values[8], values[9])) { // If current time is within application date range
                        // check if there are 0 flats for room type
                        // makes application row
                    }
                }
            }
        }
        // check if application already exists
        // write application to file
    }

}

package main.control.dataManagers;

import java.io.IOException;
import java.util.List;

import main.entity.Project;

public class ProjectManager extends DataManager {
    // Constants for file paths and column indices
    private static final String PROJ_CSV_PATH = "data/processed/projectList.csv";
    private static final int COL_NAME = 0;
    private static final int COL_NEIGHBORHOOD = 1;
    private static final int COL_2_ROOM = 2;
    private static final int COL_2_ROOM_NO = 3;
    private static final int COL_2_ROOM_PRICE = 4;
    private static final int COL_3_ROOM = 5;
    private static final int COL_3_ROOM_NO = 6;
    private static final int COL_3_ROOM_PRICE = 7;
    private static final int COL_OPEN_DATE= 8;
    private static final int COL_CLOSE_DATE = 9;

    // Private method to fetch sensitive project data. 
    private static List<String[]> fetch() {
        List<String[]> rows = null;
        try {
            rows = DataManager.readCSV(PROJ_CSV_PATH); // Use utility method
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + PROJ_CSV_PATH);
            e.printStackTrace();
        }

        return rows;
    }

    public static List<String[]> getFetch(){
        return ProjectManager.fetch();
    }
}

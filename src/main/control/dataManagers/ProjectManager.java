package main.control.dataManagers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import main.entity.Applicant;

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
    private static List<String[]> fetchAll() {
        List<String[]> rows = null;
        try {
            rows = DataManager.readCSV(PROJ_CSV_PATH); // Use utility method
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + PROJ_CSV_PATH);
            e.printStackTrace();
        }

        return rows;
    }
    
    public static List<String[]> getFetchAll(){
        return ProjectManager.fetchAll();
    }

    public static String askProjName(Scanner scanner) {
        System.out.print("Which project would you like to apply for?: ");
        return scanner.nextLine().trim();
    }
    
    public static String askRoomType(Applicant applicant, Scanner scanner) {
        if (!applicant.getMarried()) {
            System.out.println("Applying for 2-room..."); // Singles default
            return "2-room";
        }

        while (true) {
            System.out.print("2-room or 3-room? (Enter 2 or 3): ");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine(); // Clear newline
                if (input == 2) return "2-room";
                if (input == 3) return "3-room";
            } else {
                scanner.nextLine(); // Clear invalid input
            }
            System.out.println("Invalid room type. Please enter 2 or 3.");
        }
    }


}

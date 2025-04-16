package main.control;

import java.time.LocalDate; // LocalDate class to work with dates
import java.time.LocalDateTime; // LocalDateTime class to work with dates and time
import java.time.format.DateTimeFormatter; // Formats date and time
import java.time.format.DateTimeParseException; // Handles exceptions that may occur during date parsing

public class timeManager {
    public static String timeNow() {
        LocalDateTime date = LocalDateTime.now(); // Get current date and time
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Defining format of date and time
        String formattedDate = date.format(format); // Applying format to date
        return formattedDate;
    }

    public static boolean isValidDate(String openDate, String closeDate) {
        // Checks if the current date now is within openDate and closeDate
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Defining format of date and time
        try {
            LocalDate open = LocalDate.parse(openDate, format); // Parsing openDate
            LocalDate close = LocalDate.parse(closeDate, format); // Parsing closeDate

            return !(date.isBefore(open) || date.isAfter(close)); // Returns true if within date range inclusive

        } catch (DateTimeParseException e) { // Handles exceptions that may occur during date parsing
            System.out.println("Invalid date format. Please use 'dd/MM/yyyy'.");
            return false;
        }
    }

}

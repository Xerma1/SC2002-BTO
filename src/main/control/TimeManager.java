package main.control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // Handles date and time formatting
import java.time.format.DateTimeParseException; // Handles exceptions that may occur during date parsing

// Uses local timezone for simplicity
public class TimeManager {
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
            LocalDate open = LocalDate.parse(openDate, format); // Changing openDate from String to LocalDate
            LocalDate close = LocalDate.parse(closeDate, format); // Changing endDate from String to LocalDate

            return !(date.isBefore(open) || date.isAfter(close)); // Returns true if current date is within range inclusive

        } catch (DateTimeParseException e) { // Handles exceptions that may occur during date parsing
            System.out.println("Invalid date format. Please use 'dd/MM/yyyy'.");
            return false;
        }
    }

}

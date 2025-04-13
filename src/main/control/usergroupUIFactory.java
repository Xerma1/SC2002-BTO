package main.control;

import main.boundary.*;

public class usergroupUIFactory {
    public static IusergroupUI getUI(String usergroup) {
        return switch (usergroup) {
            case "applicant" -> new applicantUI();
            case "officer" -> new officerUI();
            case "manager" -> new managerUI();
            default -> throw new IllegalArgumentException("Invalid usergroup: " + usergroup);
        };
    }
}

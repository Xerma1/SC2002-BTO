package main.entity;

import main.boundary.ApplicantUI;
import main.boundary.ManagerUI;
import main.boundary.OfficerUI;

public class Application {
    private User user;
    private String projName;

    private ApplicationType type;
    public enum ApplicationType {
        APPLICANT,
        OFFICER,
        MANAGER;
    }

    private ApplicationStatus status;
    public enum ApplicationStatus {
        PENDING,
        SUCCESSFUL,
        UNSUCCESSFUL,
        BOOKED;
    }

    //constructor
    Application(User user, String projName, ApplicationType type) {
        this.user = user;
        this.projName = projName;
        this.type = type;
        this.status = ApplicationStatus.PENDING;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}


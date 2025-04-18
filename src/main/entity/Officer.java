package main.entity;

import java.util.ArrayList;
import java.util.List;

import main.control.dataManagers.ProjectManager;

public class Officer extends Applicant {
    private boolean hasActiveProj;

    //constructor
    public Officer(String name, String userID, int age, boolean married, String password, String accessLevel) {
        super(name, userID, age, married, password, accessLevel);
    }

    public List<Project> handling() {
        List<Project> projects = ProjectManager.getFetchAll();

        if (projects == null || projects.isEmpty()) {
            return new ArrayList<>(); // Return an empty list instead of null
        }

        List<Project> handlingProjects = new ArrayList<>();
        for (Project project : projects) {
            if (ProjectManager.isRegistered(this, project)) {
                handlingProjects.add(project);
            }
        }
        return handlingProjects;
    }
    
}

package main.entity;

public class Officer extends Applicant {
    private boolean hasActiveProj;

    //constructor
    public Officer(String name, String userID, int age, boolean married, String password, String accessLevel) {
        super(name, userID, age, married, password, accessLevel);
    }
    
}

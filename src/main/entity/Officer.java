package main.entity;

public class Officer extends User {
    private boolean hasActiveProj;

    //constructor
    public Officer(String name, String userID, int age, boolean married) {
        super(name, userID, age, married);
        
    }
}

package main.entity;

public class applicant extends user {
    //private applicantApplication application;
    public String filterType;

    //constructor
    public applicant(String name, String userID, int age, boolean married) {
        super(name, userID, age, married);
        if (married == true) filterType = "all";
        else filterType = "single";
    }

    public boolean getMarried(){
        return super.getMarried();
    }
}

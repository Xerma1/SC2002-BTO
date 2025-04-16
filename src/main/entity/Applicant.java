package main.entity;

public class Applicant extends User {
    //private applicantApplication application;
    public String filterType;
    private static final String FILTER_ALL = "all";
    private static final String FILTER_SINGLE = "single";

    //constructor
    public Applicant(String name, String userID, int age, boolean married) {
        super(name, userID, age, married);
        this.filterType = married ? FILTER_ALL : FILTER_SINGLE;
        
    }

    public boolean isMarried(){
        return super.getMarried();
    }
}

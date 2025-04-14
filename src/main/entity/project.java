package main.entity;

import java.util.Map;

public class project {
    private String projectName;
    private String neighbourhood;
    private Map<String, Integer> flatTypes; // Map of flat types and their respective counts
    private String openDate;
    private int closeDate;
    private String manager;
    //private String officer;
    //private EnquiryList enquiries;
    private boolean visibility; // True if project is open for application, false otherwise 

    //constructor
    public project() {
    }

}


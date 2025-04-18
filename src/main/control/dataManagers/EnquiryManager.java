package main.control.dataManagers;

import java.util.ArrayList;
import java.util.List;

import main.entity.Enquiry;

public class EnquiryManager {
    public static List<Enquiry> makeEnquiries(String[] enquiries) {
        if (enquiries == null) {
            return new ArrayList<>(); // Return an empty list instead of null
        }
        
        List<Enquiry> enquiriesObjects = new ArrayList<>();
        for (String enquiriesString : enquiries)  {
            Enquiry enquiryObject  = new Enquiry(enquiriesString);
            enquiriesObjects.add(enquiryObject);
        }
        return enquiriesObjects;
    }
}

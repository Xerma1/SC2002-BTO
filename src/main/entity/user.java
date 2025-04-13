package main.entity;

import main.control.dataManager;
import java.util.Scanner;

public abstract class user {
    private String name;
    private String userID;
    private int age;
    private boolean married;
    private static final Scanner scanner = new Scanner(System.in);

    //constructor
    public user() {
    }

    public user(String name, String userID, int age, boolean married) {
        this.name = name;
        this.userID = userID;
        this.age = age;
        this.married = married;
    }

    public void changePassword(){
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        dataManager.changePassword(userID, newPassword);
        
    }

    //public void setFilter(ViewFilter f){}


}

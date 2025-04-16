package main.entity;

import main.control.DataManager;

import java.util.Scanner;

public class User {
    private String name;
    private String userID;
    private int age;
    private boolean married;
    private static final Scanner scanner = new Scanner(System.in);

    //constructor
    public User(String name, String userID, int age, boolean married) {
        this.name = name;
        this.userID = userID;
        this.age = age;
        this.married = married;
    }

    public void changePassword(){
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        DataManager.getChangePassword(userID, newPassword);
        System.out.println("Press 'enter' to continue...");
        scanner.nextLine();
        
    }

    public String getName(){
        return name;
    }

    public String getUserID(){
        return userID;
    }

    public int getAge(){
        return age;
    }

    public boolean getMarried(){
        return married;
    }
    //public void setFilter(ViewFilter f){}


}

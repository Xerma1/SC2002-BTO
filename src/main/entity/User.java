package main.entity;

import java.util.Scanner;

import main.control.dataManagers.UserManager;

public class User {
    private String name;
    private String userID;
    private int age;
    private boolean married;
    private String password = "password";

    //constructor
    public User(String name, String userID, int age, boolean married) {
        this.name = name;
        this.userID = userID;
        this.age = age;
        this.married = married;
    }

    public void changePassword(Scanner scanner) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        if (newPassword == password) {
            System.out.println("New password cannot be the same as the old password.");
            return;
        }
        this.password = newPassword.trim();
        System.out.println("Password changed successfully.");    
        UserManager.writePassword(userID, newPassword);
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

}

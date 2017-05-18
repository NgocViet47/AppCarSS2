package com.example.mypc.appcarss2.object;

/**
 * Created by MyPC on 4/26/2017.
 */

public class User {
    private String accountID;
    private String userID;
    private String password;
    private String fullName;
    private String email;
    private String adress;
    private int cityID;
    private int phoneNumber;

    public User() {

    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(String accountID, String userID, String password, String fullName, String email, String adress, int cityID, int phoneNumber) {
        this.accountID = accountID;
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.adress = adress;
        this.cityID = cityID;
        this.phoneNumber = phoneNumber;
    }
}

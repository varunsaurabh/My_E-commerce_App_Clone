package com.example.project2.Model;

public class User {
    private String FullName,MobileNo,Email,Password;

    public User() {
    }

    public User(String fullName, String mobileNo, String email, String password) {
        FullName = fullName;
        MobileNo = mobileNo;
        Email = email;
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

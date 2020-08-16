package com.example.project2.Model;

public class MyAddressesModel {
    private String fullName,Address,MobileNo;
    private Boolean selected;

    public MyAddressesModel(String fullName, String address, String mobileNo, Boolean selected) {
        this.fullName = fullName;
        Address = address;
        MobileNo = mobileNo;
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getMobileNo() {
        return MobileNo;
    }
    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
}

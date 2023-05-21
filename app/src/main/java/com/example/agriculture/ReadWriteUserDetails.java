package com.example.agriculture;

public class ReadWriteUserDetails {
    public String fullName, phone, address;

    //Constructor
    public ReadWriteUserDetails(){};

    public  ReadWriteUserDetails(String textFullName, String textPhone, String textAddress){
        this.fullName = textFullName;
        this.phone = textPhone;
        this.address = textAddress;
    }
}

package com.example.agriculture.model;

public class User {
    private String Name, Email, Phone, Address, Id, DataImage;

    public String getName() {
        return Name;
    }

    public String getDataImage() {
        return DataImage;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getId(){
        return Id;
    }
    public User(String id, String name, String email, String phone, String address, String dataImage) {
        Id = id;
        Name = name;
        Email = email;
        Phone = phone;
        Address = address;
        DataImage = dataImage;
    }

    public User(){

    }
}

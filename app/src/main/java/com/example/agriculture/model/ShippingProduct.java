package com.example.agriculture.model;

import java.util.ArrayList;

public class ShippingProduct {
    private String name, location, categoryPayment;
    private ArrayList<String> productID;

    public String getName() {
        return name;
    }

    public String getCategoryPayment() {
        return categoryPayment;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<String> getProductID() {
        return productID;
    }

    public ShippingProduct(String name, String location, ArrayList<String> productID, String categoryPayment) {
        this.name = name;
        this.location = location;
        this.productID = productID;
        this.categoryPayment = categoryPayment;
    }

    public ShippingProduct(){

    }
}

package com.example.agriculture.model;

public class CartProduct {
    private Integer productQuantity, productPrice;
    private String productItemName, unitItem, dataImage, currentDate, key, userID;

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public String getKey() {
        return key;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public String getProductItemName() {
        return productItemName;
    }

    public String getUnitItem() {
        return unitItem;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getUserID() {
        return userID;
    }

    public CartProduct(Integer productQuantity, Integer productPrice, String productItemName, String unitItem, String dataImage, String currentDate, String key, String userID) {
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productItemName = productItemName;
        this.unitItem = unitItem;
        this.dataImage = dataImage;
        this.currentDate = currentDate;
        this.key = key;
        this.userID = userID;
    }

    public CartProduct(){

    }

}

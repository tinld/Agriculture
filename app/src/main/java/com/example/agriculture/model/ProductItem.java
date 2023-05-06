package com.example.agriculture.model;

public class ProductItem {
    Integer getProductIteQty, productItemPrice;
    String locationItem, productItemName, unitItem;

    public Integer getGetProductIteQty() {
        return getProductIteQty;
    }

    public Integer getProductItemPrice() {
        return productItemPrice;
    }

    public String getLocationItem() {
        return locationItem;
    }

    public String getProductItemName() {
        return productItemName;
    }

    public String getUnitItem() {
        return unitItem;
    }

    public void setGetProductIteQty(Integer getProductIteQty) {
        this.getProductIteQty = getProductIteQty;
    }

    public void setProductItemPrice(Integer productItemPrice) {
        this.productItemPrice = productItemPrice;
    }

    public void setLocationItem(String locationItem) {
        this.locationItem = locationItem;
    }

    public void setProductItemName(String productItemName) {
        this.productItemName = productItemName;
    }

    public void setUnitItem(String unitItem) {
        this.unitItem = unitItem;
    }

    //    public ProductItem(Integer getProductIteQty, Integer productItemPrice, String locationItem, String productItemName, String unitItem) {
//        this.getProductIteQty = getProductIteQty;
//        this.productItemPrice = productItemPrice;
//        this.locationItem = locationItem;
//        this.productItemName = productItemName;
//        this.unitItem = unitItem;
//    }
}

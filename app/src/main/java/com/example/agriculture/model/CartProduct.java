package com.example.agriculture.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CartProduct implements Parcelable {
    private Integer productQuantity, productPrice;
    private String productItemName, unitItem, dataImage, currentDate, key, userID, status, productKey;

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public String getKey() {
        return key;
    }

    public String getProductKey() {
        return productKey;
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

    public String getStatus() {
        return status;
    }

    public CartProduct(Integer productQuantity, Integer productPrice, String productItemName, String unitItem, String dataImage, String currentDate, String key, String userID, String status, String productKey) {
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productItemName = productItemName;
        this.unitItem = unitItem;
        this.dataImage = dataImage;
        this.currentDate = currentDate;
        this.status = status;
        this.key = key;
        this.userID = userID;
        this.productKey = productKey;
    }

    public CartProduct(){

    }
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        // TODO Auto-generated method stub
        dest.writeInt(productPrice);
        dest.writeInt(productQuantity);
        dest.writeString(productItemName);
        dest.writeString(unitItem);
        dest.writeString(dataImage);
        dest.writeString(currentDate);
        dest.writeString(status);
        dest.writeString(key);
        dest.writeString(userID);
        dest.writeString(productKey);
    }

    public CartProduct(Parcel in) {
        productPrice = in.readInt();
        productQuantity = in.readInt();
        productItemName = in.readString();
        unitItem = in.readString();
        dataImage = in.readString();
        currentDate = in.readString();
        status = in.readString();
        key = in.readString();
        userID = in.readString();
        productKey = in.readString();
    }

    public static final Parcelable.Creator<CartProduct> CREATOR = new Parcelable.Creator<CartProduct>() {
        public CartProduct createFromParcel(Parcel in) {
            return new CartProduct(in);
        }

        public CartProduct[] newArray(int size) {
            return new CartProduct[size];
        }
    };
}

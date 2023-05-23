package com.example.agriculture.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CartProduct implements Parcelable {
    private Integer productQuantity, productPrice;
    private String productItemName, unitItem, dataImage, currentDate, key, userID, Status, productKey;

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
        return Status;
    }

    public CartProduct(Integer productQuantity, Integer productPrice, String productItemName, String unitItem, String dataImage, String currentDate, String key, String userID, String status, String productKey) {
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productItemName = productItemName;
        this.unitItem = unitItem;
        this.dataImage = dataImage;
        this.currentDate = currentDate;
        this.Status = status;
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
        dest.writeString(key);
        dest.writeString(userID);
        dest.writeString(Status);
        dest.writeString(productKey);
    }

    public CartProduct(Parcel in) {
        productQuantity = in.readInt();
        productPrice = in.readInt();
        productItemName = in.readString();
        unitItem = in.readString();
        dataImage = in.readString();
        currentDate = in.readString();
        key = in.readString();
        userID = in.readString();
        productKey = in.readString();
        Status = in.readString();
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

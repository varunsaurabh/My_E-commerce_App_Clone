package com.example.project2.Model;

public class MyWishlistModel {

    private int itemImage;
    private String itemTitle,itemPrice,itemCutPrice,itemCOD;
    public MyWishlistModel(int itemImage, String itemTitle, String itemPrice, String itemCutPrice, String itemCOD) {
        this.itemImage = itemImage;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemCutPrice = itemCutPrice;
        this.itemCOD = itemCOD;
    }
    public int getItemImage() {
        return itemImage;
    }
    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
    public String getItemTitle() {
        return itemTitle;
    }
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
    public String getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
    public String getItemCutPrice() {
        return itemCutPrice;
    }
    public void setItemCutPrice(String itemCutPrice) {
        this.itemCutPrice = itemCutPrice;
    }
    public String getItemCOD() {
        return itemCOD;
    }
    public void setItemCOD(String itemCOD) {
        this.itemCOD = itemCOD;
    }
}

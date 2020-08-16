package com.example.project2.Model;

public class HorizontalProductModel {

    private String productImg,productID;
    private  String productTitle,productDes,productPrice;

    public HorizontalProductModel() {
    }

    public HorizontalProductModel(String productID, String productImg, String productTitle, String productDes, String productPrice) {
        this.productID = productID;
        this.productImg = productImg;
        this.productTitle = productTitle;
        this.productDes = productDes;
        this.productPrice = productPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}

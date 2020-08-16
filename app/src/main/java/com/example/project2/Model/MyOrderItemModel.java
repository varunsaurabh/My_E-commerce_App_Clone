package com.example.project2.Model;

public class MyOrderItemModel {

    private int productImage;
    private String productTitle,productDeliveryDate;
    public MyOrderItemModel(int productImage, String productTitle, String productDeliveryDate) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productDeliveryDate = productDeliveryDate;
    }
    public int getProductImage() {
        return productImage;
    }
    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }
    public String getProductTitle() {
        return productTitle;
    }
    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
    public String getProductDeliveryDate() {
        return productDeliveryDate;
    }
    public void setProductDeliveryDate(String productDeliveryDate) {
        this.productDeliveryDate = productDeliveryDate;
    }
}

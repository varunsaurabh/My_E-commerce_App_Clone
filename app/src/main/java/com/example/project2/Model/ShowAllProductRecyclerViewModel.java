package com.example.project2.Model;

public class ShowAllProductRecyclerViewModel {
    private String productImage;
    private String productTitle,productPrice,productCutPrice,productCODStatus;
    public ShowAllProductRecyclerViewModel(String  productImage, String productTitle, String productPrice, String productCutPrice, String productCODStatus) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productCutPrice = productCutPrice;
        this.productCODStatus = productCODStatus;
    }
    public String getProductImage() {
        return productImage;
    }
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    public String getProductTitle() {
        return productTitle;
    }
    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
    public String getProductCutPrice() {
        return productCutPrice;
    }
    public void setProductCutPrice(String productCutPrice) {
        this.productCutPrice = productCutPrice;
    }
    public String getProductCODStatus() {
        return productCODStatus;
    }
    public void setProductCODStatus(String productCODStatus) {
        this.productCODStatus = productCODStatus;
    }
}

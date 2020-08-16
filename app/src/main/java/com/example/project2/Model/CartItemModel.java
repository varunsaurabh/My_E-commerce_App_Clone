package com.example.project2.Model;

public class CartItemModel {
    public static final int CART_ITEM = 0;
    public static final int CART_TOTAL_AMOUNT = 1;

    private int type;

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    /////////////////////// Cart Item
    private int product_image,product_qty;
    private String product_title,product_price,product_cut_price;

    public CartItemModel(int type, int product_image, int product_qty, String product_title, String product_price, String product_cut_price) {
        this.type = type;
        this.product_image = product_image;
        this.product_qty = product_qty;
        this.product_title = product_title;
        this.product_price = product_price;
        this.product_cut_price = product_cut_price;
    }
    public int getProduct_image() {
        return product_image;
    }
    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }
    public int getProduct_qty() {
        return product_qty;
    }
    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }
    public String getProduct_title() {
        return product_title;
    }
    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }
    public String getProduct_price() {
        return product_price;
    }
    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
    public String getProduct_cut_price() {
        return product_cut_price;
    }
    public void setProduct_cut_price(String product_cut_price) {
        this.product_cut_price = product_cut_price;
    }
    /////////////////////// Cart Item


    /////////////////////// Cart Item Amount
    private String totalItems;
    private String delivery_price,total_item_price,saved_amount,grand_total;
    public CartItemModel(int type, String totalItems, String delivery_price, String total_item_price, String saved_amount, String grand_total) {
        this.type = type;
        this.totalItems = totalItems;
        this.delivery_price = delivery_price;
        this.total_item_price = total_item_price;
        this.saved_amount = saved_amount;
        this.grand_total = grand_total;
    }
    public String getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }
    public String getDelivery_price() {
        return delivery_price;
    }
    public void setDelivery_price(String delivery_price) {
        this.delivery_price = delivery_price;
    }
    public String getTotal_item_price() {
        return total_item_price;
    }
    public void setTotal_item_price(String total_item_price) {
        this.total_item_price = total_item_price;
    }
    public String getSaved_amount() {
        return saved_amount;
    }
    public void setSaved_amount(String saved_amount) {
        this.saved_amount = saved_amount;
    }
    public String getGrand_total() {
        return grand_total;
    }
    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }
    /////////////////////// Cart Item Amount
}

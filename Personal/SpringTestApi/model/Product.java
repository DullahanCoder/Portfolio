package com.company.springrestapi.model;

public class Product {

    private Product() {
        super();
    }

    public Product(Long productID, String  productName, int productPrice){
        super();
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    private Long productID;
    private String productName;
    private int productPrice;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}

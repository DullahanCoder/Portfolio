package com.company.springrestapi.service;

import com.company.springrestapi.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    Product getProduct(Long id);
    void createProduct(Long productID, String productName, int productPrice);
}

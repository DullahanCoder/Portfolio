package com.company.springrestapi.service;

import com.company.springrestapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private List<Product> products = new ArrayList<>();

    public ProductServiceImpl(){

        products.add(new Product(-1L, "Error, Not Found", 0));
        products.add(new Product(1L,"iphone", 1999));
        products.add(new Product(2L, "speaker", 599));
        products.add(new Product(3L, "book", 99));

    }

    public List<Product> getProducts(){
        return products;
    }

    @Override
    public Product getProduct(Long id) {
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()){
            Product product = iterator.next();
            if(product.getProductID().equals(id))
                return product;
        }
        return products.get(0);
    }

    public void createProduct(Long productID, String productName, int productPrice) {
        if(getProduct(productID).getProductID()==-1L)
            products.add(new Product(productID,productName,productPrice));
    }
}

package com.company.springrestapi.controller;

import com.company.springrestapi.model.Product;
import com.company.springrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        return productService.getProduct(id);
    }

    @PostMapping(value = "")
    public Map<String, Object> createProduct(@RequestParam(value = "id") Long id, @RequestParam(value="name") String name, @RequestParam(value = "price") int price){
        productService.createProduct(id,name,price);

        Map<String, Object> map = new HashMap<>();
        map.put("status", "Product added!");
        return map;
    }
}

package com.grace.springbootmall.controller;

import com.grace.springbootmall.dto.ProductRequest;
import com.grace.springbootmall.model.Product;
import com.grace.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        if (product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                               @RequestBody @Valid ProductRequest productRequest){
        Product product = productService.getProductById(productId);
        if (product != null){
            productService.updateProduct(productId,productRequest);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
            productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam String category,
                                                     @RequestParam String search){
            List<Product> productList = productService.getProducts(category, search);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }
}

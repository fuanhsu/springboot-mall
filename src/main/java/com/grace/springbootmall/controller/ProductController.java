package com.grace.springbootmall.controller;

import com.grace.springbootmall.constant.ProductCategory;
import com.grace.springbootmall.dto.ProductRequest;
import com.grace.springbootmall.dto.ProductRequestParams;
import com.grace.springbootmall.model.Product;
import com.grace.springbootmall.service.ProductService;
import com.grace.springbootmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
                                                     //查詢條件
                                                     @RequestParam(required = false) ProductCategory category,
                                                     @RequestParam(required = false) String search,
                                                     //排序
                                                     @RequestParam(defaultValue = "created_date") String orderBy,
                                                     @RequestParam(defaultValue = "desc") String sort,
                                                     //分頁
                                                     @RequestParam(defaultValue = "5") @Max(1000)  @Min(0) Integer limit,
                                                     @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        ProductRequestParams productRequestParams = new ProductRequestParams();
        productRequestParams.setCategory(category);
        productRequestParams.setSearch(search);
        productRequestParams.setOrderBy(orderBy);
        productRequestParams.setSort(sort);
        productRequestParams.setLimit(limit);
        productRequestParams.setOffset(offset);
        List<Product> productList = productService.getProducts(productRequestParams);
        Integer count = productService.countProduct(productRequestParams);

        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(productList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

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

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }



}

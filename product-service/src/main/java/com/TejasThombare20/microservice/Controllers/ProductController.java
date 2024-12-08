package com.TejasThombare20.microservice.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.TejasThombare20.microservice.Services.ProductService;
import com.TejasThombare20.microservice.dto.ProductRequest;
import com.TejasThombare20.microservice.dto.ProductResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public void createProduct(@RequestBody ProductRequest productrequest) {
        productService.createProduct(productrequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return  productService.getAllProducts();
    }
    
    

}

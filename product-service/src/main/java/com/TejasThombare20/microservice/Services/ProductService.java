package com.TejasThombare20.microservice.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TejasThombare20.microservice.Models.Product;
import com.TejasThombare20.microservice.Repository.ProductRepository;
import com.TejasThombare20.microservice.dto.ProductRequest;
import com.TejasThombare20.microservice.dto.ProductResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProductService {

    private final ProductRepository productRepo;

    public void createProduct(ProductRequest productRequest){

        Product product  = Product.builder().name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .build();

        productRepo.save(product);

        log.info("Product {} saved ", product.getId());
    }
    

    public List<ProductResponse> getAllProducts() {
       List <Product> products =  productRepo.findAll();

       return products.stream().map(product -> mapToProductResponse(product)).toList();

    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .build();
    }
}

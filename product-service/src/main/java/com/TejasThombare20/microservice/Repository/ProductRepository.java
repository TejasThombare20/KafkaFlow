package com.TejasThombare20.microservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TejasThombare20.microservice.Models.Product;


@Repository
public interface ProductRepository extends MongoRepository<Product , String>{
    
}

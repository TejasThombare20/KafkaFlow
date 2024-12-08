package com.TejasThombare20.microservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.TejasThombare20.microservice.Repository.ProductRepository;
import com.TejasThombare20.microservice.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc

class MicroserviceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo : 4.4.2");

	@Autowired
	private MockMvc mockMvc;


	@Autowired
	private ObjectMapper objectMapper;

	   
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
			dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Autowired 
	ProductRepository productRepo;

	
	

	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();
		String productRequestString =  objectMapper.writeValueAsString(productRequest);


		mockMvc.perform(MockMvcRequestBuilders.post("/api/product/add")
		.contentType(MediaType.APPLICATION_JSON)
		.content(productRequestString))
		.andExpect(status().isCreated());

		Assertions.assertTrue(productRepo.findAll().size() == 1);   
		
	} 

	private ProductRequest getProductRequest(){

		return ProductRequest.builder()
		.name("iphone16")
		.description("iphone 16 - premium product")
		 .price(BigDecimal.valueOf(85000))
		 .build();
		 
	}






	void contextLoads() {
	}

}
  
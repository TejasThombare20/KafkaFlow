package com.TejasThombare20.microservice.dto;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal  price;
}

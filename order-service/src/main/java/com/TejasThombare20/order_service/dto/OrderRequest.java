package com.TejasThombare20.order_service.dto;

import java.math.BigDecimal;
// import java.util.List;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class OrderRequest {
//     private List<OrderLineItemsDto>orderLineItemDtoList;
// }


public record OrderRequest(Long id, String orderNumber, String skuCode,
                           BigDecimal price, Integer quantity, UserDetails userDetails) {

    public record UserDetails(String email, String firstName, String lastName) {}
}
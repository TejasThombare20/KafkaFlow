package com.TejasThombare20.order_service.Services;

// import java.util.List; 
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.TejasThombare20.order_service.Client.InventoryClient;
import com.TejasThombare20.order_service.Models.Order;
// import com.TejasThombare20.order_service.Models.OrderLineItems;
import com.TejasThombare20.order_service.Repositories.OrderRepository;
// import com.TejasThombare20.order_service.dto.OrderLineItemsDto;
import com.TejasThombare20.order_service.dto.OrderRequest;
import com.TejasThombare20.order_service.event.OrderPlaceEvent;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepo;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String , OrderPlaceEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {

        boolean isProductIsInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        

        if (isProductIsInStock) {

            Order order = new Order();

            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            // List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemDtoList()
            // .stream()
            // .map(this::mapToDTO)
            // .toList();
            // order.setOrderLineItemList(orderLineItems);
            orderRepo.save(order);

            OrderPlaceEvent orderPlaceEvent = new OrderPlaceEvent(order.getOrderNumber() , orderRequest.userDetails().email()); 
            log.info("start sending order place event {} to Kafka topic order-placed",orderPlaceEvent);
            kafkaTemplate.send("order-placed",orderPlaceEvent);
            log.info("end sending order place event {} to Kafka topic order-placed",orderPlaceEvent);



        } else {
            throw new RuntimeException("product with SkuCode :" + orderRequest.skuCode() + "is not in stock");
        }

    }

// private OrderLineItems mapToDTO(OrderLineItemsDto orderLineItemDto) {
//     OrderLineItems orderLineItems = new OrderLineItems();
//     orderLineItems.setPrice(orderLineItemDto.getPrice());
//     orderLineItems.setQuantity(orderLineItemDto.getQuantity());
//     orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
//     return orderLineItems;
// }
}

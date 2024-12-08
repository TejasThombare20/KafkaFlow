package com.TejasThombare20.order_service.Client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Slf4j
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory/{skuCode}/{quantity}")
    @CircuitBreaker(name="inventory" , fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")

    boolean isInStock(@PathVariable String skuCode, @PathVariable Integer quantity);


    default boolean fallbackMethod(String skucode ,  Integer quantity, Throwable throwable){
            log.info("can not get the inventory for skuCode{} , failure reason :{}", skucode, throwable.getMessage());

            return false;
    }
}

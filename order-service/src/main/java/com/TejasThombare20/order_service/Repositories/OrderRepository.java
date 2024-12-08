package com.TejasThombare20.order_service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TejasThombare20.order_service.Models.Order;

@Repository
public interface OrderRepository extends   JpaRepository<Order, Long>{
    
}

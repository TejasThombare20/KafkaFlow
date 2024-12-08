package com.TejasThombare20.inventory_service.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TejasThombare20.inventory_service.Model.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory , Long> {
     // Optional<Inventory>findBySkuCode(String skuCode);

     boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}

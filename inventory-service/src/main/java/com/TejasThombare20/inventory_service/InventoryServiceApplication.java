package com.TejasThombare20.inventory_service;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import com.TejasThombare20.inventory_service.Model.Inventory;
// import com.TejasThombare20.inventory_service.Repositories.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner laodData(InventoryRepository inventoryRepo){
	// 	return args->{
	// 		Inventory inventory = new Inventory();

	// 		inventory.setSkuCode("iPhone17");
	// 		inventory.setQuantitiy(5);
 
	// 		Inventory  inventory2 = new Inventory();
	// 		inventory2.setSkuCode("iPhone17_pale");
	// 		inventory2.setQuantitiy(0);

	// 		inventoryRepo.save(inventory);
	// 		inventoryRepo.save(inventory2);

	// 	};
	// }


}

package com.example.api.facade;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.api.dto.ProductDetailDTO;
import com.example.api.entity.Inventory;
import com.example.api.entity.Price;
import com.example.api.entity.Product;
import com.example.api.service.InventoryService;
import com.example.api.service.PriceService;
import com.example.api.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductSyncFacade
{
	private final ProductService productService;
	private final InventoryService inventoryService;
	private final PriceService priceService;

	public ProductSyncFacade(ProductService productService, InventoryService inventoryService, PriceService priceService)
	{
		this.productService = productService;
		this.inventoryService = inventoryService;
		this.priceService = priceService;
	}

	public ProductDetailDTO getProductDetails(long productId)
	{
		LocalDateTime startTime = LocalDateTime.now();
		log.info("Sync facade for getting product details for the product id {}, start time {}", productId, startTime);

		// fetch product
		Product product = productService.findById(productId);

		// fetch the price
		Price price = priceService.getPriceByProductId(productId);

		// fetch the inventory
		Inventory inventory = inventoryService.getInventoryByProductId(productId);

		log.info("Sync facade for getting product details call completed for product id {}, time taken {} miliseconds",
				productId, Duration.between(startTime, LocalDateTime.now()).toMillis());

		return ProductDetailDTO.builder()
				.id(productId)
				.categoryName(product.getCategory().getName())
				.name(product.getName())
				.description(product.getDescription())
				.availableQuantity(inventory.getAvailableQuantity())
				.price(price.getPrice())
				.status(inventory.getStatus())
				.build();
	}

}

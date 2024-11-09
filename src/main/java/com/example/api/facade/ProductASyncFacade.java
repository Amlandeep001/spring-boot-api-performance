package com.example.api.facade;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

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
public class ProductASyncFacade
{
	private final ProductService productService;
	private final InventoryService inventoryService;
	private final PriceService priceService;

	public ProductASyncFacade(ProductService productService, InventoryService inventoryService, PriceService priceService)
	{
		this.productService = productService;
		this.inventoryService = inventoryService;
		this.priceService = priceService;
	}

	public ProductDetailDTO getProductDetails(long productId)
	{
		LocalDateTime startTime = LocalDateTime.now();
		log.info("Async facade for getting product details for the product id {}, start time {}", productId, startTime);

		// fetch all async
		CompletableFuture<Product> productFuture = getProductById(productId);
		CompletableFuture<Price> priceFuture = getPriceByProductById(productId);
		CompletableFuture<Inventory> inventoryFuture = getInventoryByProductId(productId);

		// wait for all futures to complete
		CompletableFuture.allOf(priceFuture, productFuture, inventoryFuture);

		// combine the result
		Product product = productFuture.join();
		Price price = priceFuture.join();
		Inventory inventory = inventoryFuture.join();

		log.info("Async facade for getting product details call completed for product id {}, time taken {} miliseconds",
				productId, Duration.between(startTime, LocalDateTime.now()).toMillis());

		// build and return
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

	private CompletableFuture<Product> getProductById(long productId)
	{
		return CompletableFuture
				.supplyAsync(() -> productService.findById(productId));
	}

	private CompletableFuture<Price> getPriceByProductById(long productId)
	{
		return CompletableFuture
				.supplyAsync(() -> priceService.getPriceByProductId(productId));
	}

	private CompletableFuture<Inventory> getInventoryByProductId(long productId)
	{
		return CompletableFuture
				.supplyAsync(() -> inventoryService.getInventoryByProductId(productId));
	}
}

package com.example.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.ProductDetailDTO;
import com.example.api.facade.ProductASyncFacade;
import com.example.api.facade.ProductSyncFacade;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController
{
	private final ProductSyncFacade productSyncFacade;
	private final ProductASyncFacade productASyncFacade;

	public ProductController(ProductSyncFacade productSyncFacade, ProductASyncFacade productASyncFacade)
	{
		this.productSyncFacade = productSyncFacade;
		this.productASyncFacade = productASyncFacade;
	}

	@GetMapping("/{id}/sync")
	public ResponseEntity<ProductDetailDTO> getProductSync(@PathVariable Long id)
	{
		log.info("Rest request to get product by id sync: {}", id);
		return ResponseEntity.ok(productSyncFacade.getProductDetails(id));
	}

	@GetMapping("/{id}/async")
	public ResponseEntity<ProductDetailDTO> getProductAsync(@PathVariable Long id)
	{
		log.info("Rest request to get product by id async: {}", id);
		return ResponseEntity.ok(productASyncFacade.getProductDetails(id));
	}

}

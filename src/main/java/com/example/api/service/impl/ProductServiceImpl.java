package com.example.api.service.impl;

import org.springframework.stereotype.Service;

import com.example.api.entity.Product;
import com.example.api.repository.ProductRepository;
import com.example.api.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService
{
	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	@Override
	public Product findById(Long id)
	{
		log.info("Service request to fetch product by id: {}", id);
		addDelay();
		return productRepository.findById(id).orElse(null);
	}

	private void addDelay()
	{
		try
		{
			Thread.sleep(2000);
		}
		catch(InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}
}

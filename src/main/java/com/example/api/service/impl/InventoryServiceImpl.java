package com.example.api.service.impl;

import org.springframework.stereotype.Service;

import com.example.api.entity.Inventory;
import com.example.api.repository.InventoryRepository;
import com.example.api.service.InventoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService
{
	private final InventoryRepository inventoryRepository;

	public InventoryServiceImpl(InventoryRepository inventoryRepository)
	{
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public Inventory getInventoryByProductId(Long productId)
	{
		log.info("Getting inventory for the productId {}", productId);
		addDelay();
		return inventoryRepository.findByProductId(productId);
	}

	private void addDelay()
	{
		try
		{
			// adding 2s delay
			Thread.sleep(2000);
		}
		catch(InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}
}

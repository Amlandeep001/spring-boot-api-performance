package com.example.api.service;

import com.example.api.entity.Inventory;

public interface InventoryService
{
	Inventory getInventoryByProductId(Long productId);
}

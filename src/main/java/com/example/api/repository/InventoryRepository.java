package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>
{
	Inventory findByProductId(Long productId);
}

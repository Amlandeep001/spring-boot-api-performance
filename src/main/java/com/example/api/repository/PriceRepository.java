package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long>
{
	Price findByProductId(Long productId);
}

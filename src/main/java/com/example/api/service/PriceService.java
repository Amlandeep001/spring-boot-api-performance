package com.example.api.service;

import com.example.api.entity.Price;

public interface PriceService
{
	Price getPriceByProductId(Long productId);
}

package com.example.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ProductDetailDTO
{
	Long id;

	String categoryName;

	String name;

	String description;

	Integer availableQuantity;

	Double price;

	String status;
}

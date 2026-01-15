package com.eeit.ajax.backend.model.dto;

import lombok.Data;

@Data
public class ProductResponseDto {

    private Integer productId;
    private String productName;
    private Integer price;
    private String color;

}
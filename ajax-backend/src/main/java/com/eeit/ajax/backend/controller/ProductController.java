package com.eeit.ajax.backend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeit.ajax.backend.model.dto.PageRequestDto;
import com.eeit.ajax.backend.model.dto.ProductResponseDto;
import com.eeit.ajax.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 分頁查詢商品
    @GetMapping
    public Page<ProductResponseDto> getProductsByPagination(@ModelAttribute PageRequestDto dto) {
        return productService.getProductsByPagination(dto);
    }

}
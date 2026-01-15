package com.eeit.ajax.backend.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eeit.ajax.backend.model.dto.PageRequestDto;
import com.eeit.ajax.backend.model.dto.ProductResponseDto;
import com.eeit.ajax.backend.model.entity.Product;
import com.eeit.ajax.backend.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductResponseDto> getProductsByPagination(PageRequestDto dto) {

        // 建立分頁查詢物件
        PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getSize());

        // findAll()，並且傳入分頁物件
        Page<Product> products = productRepository.findAll(pageRequest);

        // 轉換成 DTO
        Page<ProductResponseDto> dtos = products.map(this::toDto);

        return dtos;
    }

    /**
     * 轉換 entity to dto 的方法。
     * 標準做法是獨立到 ProdcutMapper.toDto() 中，
     * 但是，也可以寫在 Servcie 層。
     */
    private ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

}
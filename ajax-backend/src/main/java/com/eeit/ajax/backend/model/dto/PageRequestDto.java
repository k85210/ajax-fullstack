package com.eeit.ajax.backend.model.dto;

import lombok.Data;

@Data
public class PageRequestDto {
    private Integer page;
    private Integer size;
}
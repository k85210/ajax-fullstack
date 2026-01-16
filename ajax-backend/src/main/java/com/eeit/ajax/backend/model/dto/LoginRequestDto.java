package com.eeit.ajax.backend.model.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;

}
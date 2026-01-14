package com.eeit.ajax.backend.model.dto;

import lombok.Data;

@Data
public class MemberCreateDto {
    private Boolean isAdmin;
    private String email;
    private String password;
    private String memberName;
}

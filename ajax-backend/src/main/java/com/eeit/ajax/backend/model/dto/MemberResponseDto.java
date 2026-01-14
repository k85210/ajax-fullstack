package com.eeit.ajax.backend.model.dto;

import jakarta.persistence.Lob;
import lombok.Data;

/**
 * 
 */

@Data
public class MemberResponseDto {
    private Integer memberId;
    private Boolean isAdmin;
    private String email;
    private String memberName;
    private byte[] memberPhoto;

}

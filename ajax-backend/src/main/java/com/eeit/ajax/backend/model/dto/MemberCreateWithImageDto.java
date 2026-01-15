package com.eeit.ajax.backend.model.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberCreateWithImageDto {
    private Boolean isAdmin;
    private String email;
    private String password;
    private String memberName;
    private MultipartFile photo;

    private byte[] photoBytes;

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
        try {
            this.photoBytes = photo.getBytes();
        } catch (IOException e) {
            System.out.println("轉換失敗");
            e.printStackTrace();
        }
    }
}

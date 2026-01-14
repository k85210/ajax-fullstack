package com.eeit.ajax.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping("/api/members")
@CrossOrigin
public class MemberController {
    
    @PostMapping
    public void insertMember(@RequestBody MemberDTO dto) {
        System.out.println(dto);
    }

    @Data
    public static class MemberDTO {
        private Boolean isAdmin;
        private String email;
        private String password;
        private String memberName;
    }
}

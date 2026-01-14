package com.eeit.ajax.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeit.ajax.backend.model.dto.MemberCreateDto;
import com.eeit.ajax.backend.model.dto.MemberResponseDto;
import com.eeit.ajax.backend.model.entity.Member;
import com.eeit.ajax.backend.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@CrossOrigin
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;

    // 新增會員的API(僅新增文字，沒有二進制檔案)
    @PostMapping
    public void insertMember(@RequestBody MemberCreateDto dto) {
        memberService.insertMember(dto);
    }

    @GetMapping("/{id}")
    public MemberResponseDto findMemberById(@PathVariable Integer id) {
        return memberService.findMemberById(id);
    }

    
}

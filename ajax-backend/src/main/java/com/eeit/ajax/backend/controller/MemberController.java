package com.eeit.ajax.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeit.ajax.backend.model.dto.MemberCreateDto;
import com.eeit.ajax.backend.model.dto.MemberCreateWithImageDto;
import com.eeit.ajax.backend.model.dto.MemberResponseDto;
import com.eeit.ajax.backend.model.dto.MemberUpdateDto;
import com.eeit.ajax.backend.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@CrossOrigin // 允許跨域(但這是應急手段。推薦使用 spring secruity)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // @RequestBody 承接 JSON 格式字串
    // @ModelAttribute 承接 Formdata

    /**
     * 新增會員的 API (僅新增文字，沒有二進制檔案)
     */
    @PostMapping
    public MemberResponseDto insertMember(@RequestBody MemberCreateDto dto) {
        return memberService.insertMember(dto);
    }

    @PostMapping("/image")
    public MemberResponseDto insertMemberWithIamge(@ModelAttribute MemberCreateWithImageDto dto) {
        return memberService.inserMemberWithPhoto(dto);
    }

    /**
     * 根據 ID 查詢會員
     */
    @GetMapping("/{id}")
    public MemberResponseDto findMemberById(@PathVariable Integer id) {
        return memberService.findMemberById(id);
    }

    /**
     * 查詢所有會員
     */
    @GetMapping
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }

    /**
     * 刪除會員
     */
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Integer id) {
        memberService.deleteMemberById(id);
    }

    /*
     * 更新會員資訊
     */
    @PutMapping("/{id}")
    public MemberResponseDto updateMemberById(@PathVariable Integer id, @RequestBody MemberUpdateDto dto) {
        return memberService.updateMemberById(id, dto);
    }
}
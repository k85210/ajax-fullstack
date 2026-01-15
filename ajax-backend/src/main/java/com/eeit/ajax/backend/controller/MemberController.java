package com.eeit.ajax.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeit.ajax.backend.model.dto.MemberCreateDto;
import com.eeit.ajax.backend.model.dto.MemberResponseDto;
import com.eeit.ajax.backend.model.dto.MemberUpdateDto;
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
    public MemberResponseDto insertMember(@RequestBody MemberCreateDto dto) {
        return memberService.insertMember(dto);
    }

    @GetMapping("/{id}")
    public MemberResponseDto findMemberById(@PathVariable Integer id) {
        return memberService.findMemberById(id);
    }

    @GetMapping
    public List<MemberResponseDto> findAll() {
        return memberService.findAll(); // 回傳集合
    }
    
    /**
     * 刪除會員
     */
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Integer id) {
        memberService.deleteMemberById(id);
    }

    /**
     * 更新會員
     */
    @PutMapping("{id}")
    public MemberResponseDto updateMember(@PathVariable Integer id, @RequestBody MemberUpdateDto dto) {
        return memberService.updateMemberById(id, dto);
    }
}

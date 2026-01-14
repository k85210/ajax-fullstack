package com.eeit.ajax.backend.service;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.eeit.ajax.backend.model.dto.MemberCreateDto;
import com.eeit.ajax.backend.model.dto.MemberResponseDto;
import com.eeit.ajax.backend.model.entity.Member;
import com.eeit.ajax.backend.repository.CartItemRepository;
import com.eeit.ajax.backend.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;

    public void insertMember(MemberCreateDto dto) {
        // 資料檢查
        if (Strings.isBlank(dto.getMemberName())) {
            throw new RuntimeException("請輸入會員名稱");
        }
        // dto轉換成entity
        Member member = new Member();
        BeanUtils.copyProperties(dto, member); // 運行時期才執行，未來推薦使用 MapStruct

        // 寫入資料庫
        memberRepository.save(member);
    }

        public MemberResponseDto findMemberById(Integer id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("找不到會員 " + id));

        MemberResponseDto dto = new MemberResponseDto();
        BeanUtils.copyProperties(member, dto);

        return dto;
    }


}
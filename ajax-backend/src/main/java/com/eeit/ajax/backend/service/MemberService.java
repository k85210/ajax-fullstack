package com.eeit.ajax.backend.service;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.eeit.ajax.backend.model.dto.MemberCreateDto;
import com.eeit.ajax.backend.model.dto.MemberResponseDto;
import com.eeit.ajax.backend.model.dto.MemberUpdateDto;
import com.eeit.ajax.backend.model.entity.Member;
import com.eeit.ajax.backend.model.mapper.MemberMapper;
import com.eeit.ajax.backend.repository.CartItemRepository;
import com.eeit.ajax.backend.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberMapper memberMapper;


    public MemberResponseDto insertMember(MemberCreateDto dto) {
        // 資料檢查
        if (Strings.isBlank(dto.getMemberName())) {
            throw new RuntimeException("請輸入會員名稱");
        }
        // dto轉換成entity
        Member member = new Member();
        BeanUtils.copyProperties(dto, member); // 運行時期才執行，未來推薦使用 MapStruct

        // 寫入資料庫
        Member saveMember = memberRepository.save(member);
        return memberMapper.toDto(saveMember);
    }

    public MemberResponseDto findMemberById(Integer id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("找不到會員 " + id));

        return memberMapper.toDto(member);
    }

    public List<MemberResponseDto> findAll() {
        
        List<MemberResponseDto> dtos= memberRepository.findAll().stream()
            // .map(m -> memberMapper.toDto(m))
            .map(memberMapper::toDto) // 方法參考
            .toList();
        return dtos;
    }

    // @Transactional
    public void deleteMemberById(Integer id) {
        cartItemRepository.deleteByMemberMemberId(id);
        memberRepository.deleteById(id);
    }

    public MemberResponseDto updateMemberById(Integer id, MemberUpdateDto dto) {
        Member existsMember = memberRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("找不到會員 " + id));

        existsMember.setMemberName(dto.getMemberName());
        existsMember.setEmail(dto.getEmail());

        Member updatMember = memberRepository.save(existsMember);
        return memberMapper.toDto(updatMember);
    }
}
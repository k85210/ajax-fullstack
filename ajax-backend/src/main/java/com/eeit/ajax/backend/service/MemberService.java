package com.eeit.ajax.backend.service;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.eeit.ajax.backend.model.dto.MemberCreateDto;
import com.eeit.ajax.backend.model.dto.MemberCreateWithImageDto;
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
            throw new RuntimeException("請輸入姓名");
        }

        // dto 轉換成 entity
        Member m = new Member();
        // m.setEmail(dto.getEmail());
        // m.setMemberName(dto.getMemberName());
        // m.setPassword(dto.getPassword());
        // m.setIsAdmin(dto.getIsAdmin());
        BeanUtils.copyProperties(dto, m); // 功能很陽春，運行時期才執行，不太推薦使用。 未來會推薦使用 MapStruct

        // 寫入資料庫
        Member member = memberRepository.save(m);
        return memberMapper.toDto(member);
    }

    public MemberResponseDto inserMemberWithPhoto(MemberCreateWithImageDto dto) {
        // dto 轉換成 entity
        Member m = new Member();
        BeanUtils.copyProperties(dto, m); // 僅複製同名屬性
        m.setMemberPhoto(dto.getPhotoBytes());

        // 寫入資料庫
        Member newMember = memberRepository.save(m);
        return memberMapper.toDto(newMember);
    }

    public MemberResponseDto findMemberById(Integer id) {
        Member member = memberRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("查無此人，ID 為: " + id));
        return memberMapper.toDto(member);
    }

    public List<MemberResponseDto> findAll() {
        // List<Member> members = memberRepository.findAll();
        // List<MemberResponseDto> dtos = new ArrayList<>();
        // for (Member m : members) {
        // MemberResponseDto dto = new MemberResponseDto();
        // dto.setEmail(m.getEmail());
        // dto.setIsAdmin(m.getIsAdmin());
        // //... 屬性映射
        // dtos.add(dto);
        // }
        // return dtos;

        List<MemberResponseDto> dtos = memberRepository.findAll().stream()
                // .map(m -> memberMapper.toDto(m))
                .map(memberMapper::toDto) // 方法參考: 只在 callback function 參數，剛好是內部呼叫方法的參數時，可以使用
                .toList();

        return dtos;
    }

    public void deleteMemberById(Integer id) {
        cartItemRepository.deleteByMemberMemberId(id);
        memberRepository.deleteById(id);
    }

    public MemberResponseDto updateMemberById(Integer id, MemberUpdateDto dto) {

        Member existsMember = memberRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("查無此人，ID 為: " + id));

        existsMember.setMemberName(dto.getMemberName());
        existsMember.setEmail(dto.getEmail());

        Member updatedMember = memberRepository.save(existsMember);

        return memberMapper.toDto(updatedMember);
    }

}
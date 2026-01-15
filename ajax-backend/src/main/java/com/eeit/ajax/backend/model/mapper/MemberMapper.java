package com.eeit.ajax.backend.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.eeit.ajax.backend.model.dto.MemberResponseDto;
import com.eeit.ajax.backend.model.entity.Member;

@Component
public class MemberMapper {

    public MemberResponseDto toDto(Member entity) {
        MemberResponseDto dto = new MemberResponseDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Member toEntity(MemberResponseDto dto) {
        Member entity = new Member();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

}

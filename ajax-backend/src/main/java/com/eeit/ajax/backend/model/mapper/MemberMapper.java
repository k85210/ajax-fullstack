package com.eeit.ajax.backend.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.eeit.ajax.backend.model.dto.MemberCreateDto;
import com.eeit.ajax.backend.model.dto.MemberResponseDto;
import com.eeit.ajax.backend.model.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberResponseDto toDto(Member entity);

    @Mapping(target = "memberId", ignore = true)
    @Mapping(target = "memberPhoto", ignore = true)
    Member toEntity(MemberCreateDto dto);

}
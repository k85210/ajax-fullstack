package com.eeit.ajax.backend.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.eeit.ajax.backend.model.dto.LoginRequestDto;
import com.eeit.ajax.backend.model.entity.Member;
import com.eeit.ajax.backend.repository.MemberRepository;
import com.eeit.ajax.backend.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public String login(LoginRequestDto dto) {

        Member member = memberRepository.findByEmail(dto.getEmail());

        if (member == null) {
            throw new RuntimeException("登入失敗，查無此 email: " + dto.getEmail());
        }

        // 能走到這，表示要準備驗證密碼
        if (!Objects.equals(dto.getPassword(), member.getPassword())) {
            throw new RuntimeException("登入失敗，密碼錯誤: " + dto.getPassword());
        }

        // 能走到這，就表示登入成功，準備發行 token
        String token = jwtUtil.generateToken(member.getMemberId(), member.getIsAdmin() ? "admin" : "user");

        return token;
    }
}
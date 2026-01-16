package com.eeit.ajax.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eeit.ajax.backend.model.dto.LoginRequestDto;
import com.eeit.ajax.backend.service.AuthService;
import com.eeit.ajax.backend.utils.JwtUtil;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true") // TODO: 少一段傳統 session 允許跨域
@RequiredArgsConstructor
public class AuthController {
    // === 傳統 session&cookie 區塊 ===
    @Data
    private static class LoggedInUser {
        public Integer id;
        public String username;
        public String role;
    }

    @GetMapping("/session/login")
    public String loginBySession(
            HttpSession session,
            @RequestParam(required = false) String u,
            @RequestParam(required = false) String p) {

        // 先執行登入判斷邏輯
        System.out.println("列印出 登入所需資訊 %s, %s".formatted(u, p));
        System.out.println("未來要呼叫 AuthService.login()");

        // 登入成功後，才設定 session
        LoggedInUser user = new LoggedInUser();
        user.setId(1001);
        user.setRole("admin");
        user.setUsername("Alice");

        session.setAttribute("loggedInMember", user);

        return "登入成功";
    }

    @GetMapping("/session/me")
    public String whoAmIBySession(HttpSession session) {
        LoggedInUser loggedInUser = (LoggedInUser) session.getAttribute("loggedInMember");
        String username = loggedInUser == null ? "未登入" : loggedInUser.getUsername();

        return "你是: " + username;
    }

    // === JWT (json web token) 區塊 ===
    private final AuthService authService;

    @GetMapping("/jwt/login")
    public String loginByJwt(@ModelAttribute LoginRequestDto dto) {
        return authService.login(dto);
    }

    private final JwtUtil jwtUtil;

    @PostMapping("/jwt/me")
    public String whoAmIByJwt(@RequestParam String token) {

        String id = jwtUtil.getMemberId(token);
        String role = jwtUtil.getMemberRole(token);

        return "你是: %s, 你的角色是: %s".formatted(id, role);
    }
}
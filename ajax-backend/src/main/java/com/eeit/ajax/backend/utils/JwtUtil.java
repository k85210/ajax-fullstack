package com.eeit.ajax.backend.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "this_is_my_super_secret_key_and_do_not_share_with_anyone"; // 不可外流的原始密鑰
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 有效期限預設是一天
    private final SecretKey sercetKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // 加密、驗算用的密鑰物件

    /**
     * 產生 JWT
     */
    public String generateToken(String memberId, String memberRole) {
        String role = Strings.isBlank(memberRole) ? "user" : memberRole;

        return Jwts.builder() // 取得 jwt 建構器
                .subject(memberId) // 主題，通常放 user 的唯一識別
                .claim("custom_role", role) // 放入自訂義屬性 custom_role，其屬性值為外部傳入
                .issuedAt(new Date()) // 發行日期，設定為當下時間
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 到期時間，(當下時間 + 有效期限)
                .signWith(sercetKey) // 使用私鑰物件簽名
                .compact(); // 產生 jwt
    }

    /**
     * 多載(overload)方法，方便使用者傳入不同型別的參數
     */
    public String generateToken(Integer memberId, String memberRole) {
        return this.generateToken(memberId.toString(), memberRole);
    }

    /**
     * 解析 JWT
     */
    public Claims parseToken(String token) {
        return Jwts.parser() // 取得 jwt 解析器
                .verifyWith(sercetKey) // 等一下要使用密鑰驗證 token 的有效性
                .build() // 建立一個執行緒安全的 JwtParser
                .parseSignedClaims(token) // 真正執行解析 token，如果有竄改的跡象(驗證失敗)，就會拋出異常
                .getPayload(); // 取得 token 內的所有內容
    }

    /**
     * 方便直接取得 memberId
     */
    public String getMemberId(String token) {
        return parseToken(token).getSubject();
    }

    /**
     * 方便直接取得 memberRole
     */
    public String getMemberRole(String token) {
        return (String) parseToken(token).get("custom_role");
    }
}
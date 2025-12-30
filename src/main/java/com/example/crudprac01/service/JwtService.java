package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.MemberRegisterRequestDto;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public String createJwtToken(Long memberId,String email, String name ) {
        Date now = new Date();
        Date expirationPeriod = new Date(now.getTime() + 1000 * 60);

        String jwtToken = Jwts.builder()
                .issuer("jwt.members")
                .subject(memberId.toString())
                .issuedAt(now)
                .expiration(expirationPeriod)
                .claim("email", email)
                .claim("name", name)
                .signWith(secretKey)
                .compact();

        return jwtToken;
    }
}

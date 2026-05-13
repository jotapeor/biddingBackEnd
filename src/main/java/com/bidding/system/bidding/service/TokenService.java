package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerarToken(UserDTO user) {
        if ((user.getId() == 0 || user.getId() == null) ||
                user.getNome().equals("") ||
                user.getEmail().equals("") ||
                user.getSenha().equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Um ou mais campos faltantes");
        }
        return Jwts.builder()
                .subject(user.getNome())
                .claim("usuario", user)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3000000))
                .signWith(getSignKey())
                .compact();
    }

    public UserDTO extrairClaim(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        UserDTO user = claims.get("usuario", UserDTO.class);

        return user;
    }

}
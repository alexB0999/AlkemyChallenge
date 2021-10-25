package com.project.DisneyApi.security.jwt;

import com.project.DisneyApi.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal)authentication.getPrincipal();
        List<String> roles = usuarioPrincipal.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
            .setSubject(usuarioPrincipal.getUsername())
            .claim("roles", roles)
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + expiration * 1000))
            .signWith(SignatureAlgorithm.HS512, secret.getBytes())
            .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            LOGGER.error("Token mal formado");
        }catch (UnsupportedJwtException e){
            LOGGER.error("Token no soportado");
        }catch (ExpiredJwtException e){
            LOGGER.error("Token expirado");
        }catch (IllegalArgumentException e){
            LOGGER.error("Token vacio");
        }catch (SignatureException e){
            LOGGER.error("fail en la firma");
        }
        return false;
    }

}
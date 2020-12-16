package com.sushildangi.security;

import com.sushildangi.exception.GeneralException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;

@Service
public class JwtProviderImpl implements JwtProvider {

    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "Hello@123".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new GeneralException("Exception occured while loading keystore");
        }

    }

    @Override
    public String generateToken(Authentication authentication) {
        Date now = new Date();
        int jwtExpirationInMs = 60;
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .setIssuedAt(new Date())
                .setIssuer("BlogPost")
                .setExpiration(expiryDate)
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "Hello@123".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new GeneralException("Exception occured while retrieving public key from keystore");
        }
    }

    @Override
    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
            return true;
        } catch (MalformedJwtException | SignatureException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new GeneralException("Exception occured while retrieving public key from keystore");
        }
    }

    @Override
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}

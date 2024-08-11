package com.iot.home.application.shared;

import com.iot.home.application.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Component
public class Utils {
    public static final String SUBJECT_EMAIL_CONFIRMATION="Confirm Your Email to Complete Registration";
    public static final String EMAIL_CONFIRMATION_URL="http://localhost:8088/iot-home/users/email-verification?token=";
    public static final String SUBJECT_EMAIL_CONFIRMATION_FOR_PASSWORD_RESET="Please get token and reset your password";
    private static final Random random= new SecureRandom();
    private static final String ALPHABET="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ITERATORS=10000;
    private final int KEY_LENGTH=256;
    public static String generateUserId(int length){
        return generateRandomString(length);
    }
    public static String generateAddressId(int length){
        return generateRandomString(length);
    }
    public static String generateDeviceId(int length){return generateRandomString(length);}

    private static String generateRandomString(int length){
        StringBuilder sb=new StringBuilder(length);
        for (int i=0;i<length;i++){
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
    public static boolean hasTokenExpired(String token){
        try {
            byte[] secretKeyBytes = SecurityConstants.getTokenSecret().getBytes();
            SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);

            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();

            Claims claims = jwtParser.parseClaimsJws(token).getBody();

            Date tokenExpirationDate = claims.getExpiration();
            Date today = new Date();

            return tokenExpirationDate.before(today);
        } catch (Exception e) {
            // Log exception and consider the token expired if any error occurs
            System.out.println("Error parsing JWT token: " + e.getMessage());
            return true;
        }

    }

    public static String generateEmailVerificationToken(String userId) {
        return generateToken(userId);
    }
    public static String generatePasswordVerificationToken(String userId){
        return generateToken(userId);
    }
    private static String generateToken(String userId){
        byte[] secretKeyBytes= SecurityConstants.getTokenSecret().getBytes();
        SecretKey secretKey= Keys.hmacShaKeyFor(secretKeyBytes);
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(Date.from(now.plusMillis(SecurityConstants.EXPIRATION_TIME)))
                .setIssuedAt(Date.from(now))
                .signWith(secretKey)
                .compact();
    }



}

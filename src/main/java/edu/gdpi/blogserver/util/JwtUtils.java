package edu.gdpi.blogserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

@Slf4j
public class JwtUtils {

    private static final String TOKEN_SPLIT = "Bearer ";

    private static final String KEY = "&()@!$%^$F&##DFHW#%$&@!";

    private static JWTVerifier jwtVerifier;

    static {
        try {
            jwtVerifier = JWT.require(Algorithm.HMAC256(KEY)).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建token
     *
     * @param username
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createToken(String username) throws UnsupportedEncodingException {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.WEEK_OF_MONTH, 1);
        String token = JWT.create()
                .withClaim("username", username)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(KEY));
        return token;
    }

    /**
     * 校验 Token 是否合法
     *
     * @param token
     * @return
     */
    public static boolean valid(String token) {
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 判断 Token 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpires(String token) {
        try {
            DecodedJWT verify = jwtVerifier.verify(token);
        } catch (TokenExpiredException e) {
            return true;
        }
        return false;
    }

    public static String getUsername(String token) {
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify.getClaim("username").asString();
    }

    public static boolean validateToken(String jwt, UserDetails userDetails) {
        return getUsername(jwt).equals(userDetails.getUsername());
    }
}
package com.halfsay.flower.utils;

import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

public class JWTUtils {
    public static final String AUTH_HEADER_KEY = "access_halfsay";
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 10;

    /**
     * 根据用户名，创建jwtToken
     *
     * @param username 用户名
     * @return jwtToken
     */
    public static String createJwtToken(String username) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("user-login")
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("username", username)
                .claim("islogin", true)
                .signWith(SignatureAlgorithm.HS256, Constant.APP_SECRET)
                .compact();
        return jwtToken;
    }

    /**
     * 根据用户id和用户名，创建token
     *
     * @param userid   用户id
     * @param username 用户名
     * @return token
     */
    public static String createJwtToken(String userid, String username) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("user-login")
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("userid", userid)
                .claim("username", username)
                .claim("islogin", true)
                .signWith(SignatureAlgorithm.HS256, Constant.APP_SECRET)
                .compact();
        return jwtToken;
    }

    /**
     * 校验token
     *
     * @param token token
     * @return 校验结果
     */
    public static boolean checkToken(String token) {
        return ckToken(token);
    }

    /**
     * 校验token
     *
     * @param request 请求
     * @return 校验结果
     */
    public static boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_KEY);
        return ckToken(token);
    }

    /**
     * 获得token数据
     *
     * @param token token
     * @return 结果
     */
    public static Claims parseToken(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(Constant.APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims;
    }

    /**
     * 获取token数据
     *
     * @param token token
     * @return 用户名
     */
    public static String getTokenUserName(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(Constant.APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get("username").toString();
    }

    /**
     * 获取token数据
     *
     * @param token token
     * @return 用户id
     */
    public static String getTokenUserId(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(Constant.APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get("userid").toString();
    }

    /**
     * 获取token数据
     *
     * @param token   token
     * @param datakey key
     * @return 结果
     */
    public static String getTokenData(String token, String datakey) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(Constant.APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.get(datakey).toString();
    }

    private static boolean ckToken(String token) {
        if (token == null || token.isEmpty() || "".equals(token.trim())) {
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Constant.APP_SECRET).parseClaimsJws(token);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}

package com.example.demoMybatisPlus.config.jwt;

import com.alibaba.fastjson.JSONObject;
import com.example.demoMybatisPlus.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import io.jsonwebtoken.*;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JWTUtils {

    /**
     * 根据用户签发
     *
     * @param user
     * @return
     */
    @SneakyThrows
    public static final String sign(User user) {
        Preconditions.checkNotNull(user);

        //使用jwt生成token进行加密
        JwtBuilder builder = Jwts.builder();

        //链式调用，设置相关加密信息
        String token = builder
                .setHeaderParam("type", "JWT")
                .setSubject(user.getId().toString()) //设置主题，也就是设置token中携带的数据
                .setIssuedAt(new Date()) //设置token生成时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) //设置token过期时间为1天后
//                .setExpiration(new Date(System.currentTimeMillis() + 10 * 1000)) //设置token过期时间为1天后
                .claim("USER_KEY", JSONObject.toJSONString(user)) //map中可存放用户角色权限信息
                .signWith(SignatureAlgorithm.HS256, "cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=") //设置加密方式和加密密码
                .compact();
        return token;
    }

    /**
     * 根据用户和token签发refreshToken
     *
     * @param user
     * @return
     */
    @SneakyThrows
    public static final String signRefresh(User user, String token) {
        Preconditions.checkNotNull(user);

        Date expareDate = Date.from(LocalDateTime.now().plus(90, ChronoUnit.MINUTES).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setHeaderParam("type", "JWT")
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expareDate)
                .claim("USER_KEY", JSONObject.toJSONString(user))
                .claim("TOKEN_KEY", Integer.toHexString(token.hashCode()))
                .signWith(SignatureAlgorithm.HS256, "cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=")
                .compact();
    }

    /**
     * 根据oken和refreshToken 签发新的token
     *
     * @return
     */
    @SneakyThrows
    public static final String refreshToken(String token, String refreshToken) {
        Jws<Claims> jwt = Jwts
                .parser()
                .setSigningKey("cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=")
                .parseClaimsJws(refreshToken);
        Preconditions.checkArgument(Objects.equals(Integer.toHexString(token.hashCode()), jwt.getBody().get("TOKEN_KEY", String.class)), "不一致的token对");
        User user =JSONObject.parseObject(jwt.getBody().get("USER_KEY", String.class), User.class);
        return sign(user);
    }

    @SneakyThrows
    public static User parse(String token) throws ExpiredJwtException {
        Jws<Claims> jwt = Jwts
                .parser()
                .setSigningKey("cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=")
                .parseClaimsJws(token);
        return JSONObject.parseObject(jwt.getBody().get("USER_KEY", String.class), User.class);
    }
}

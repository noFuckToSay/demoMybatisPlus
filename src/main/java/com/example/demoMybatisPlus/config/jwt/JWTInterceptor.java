package com.example.demoMybatisPlus.config.jwt;

import com.example.demoMybatisPlus.base.AjaxResult;
import com.example.demoMybatisPlus.base.UserContext;
import com.example.demoMybatisPlus.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        log.info("token is :{}",token);
        Map<String,Object> map=new HashMap();
        try{
            User user=JWTUtils.parse(token);
            UserContext.set(user);
            return true;
        }catch (ExpiredJwtException ex){
            log.error(ex.getMessage(),ex);
            map.put("success",false);
            map.put("msg","token信息已过期");
            map.put("code",402);
            String json=new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            map.put("success",false);
            map.put("msg","token信息无效");
            map.put("code",401);
            String json=new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }
    }
}

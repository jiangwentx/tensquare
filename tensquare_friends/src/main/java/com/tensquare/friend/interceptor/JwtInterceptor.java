package com.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-03-03 19:30
 **/
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("经过了拦截器");
        //判断有头请求的token进行验证
        String header = request.getHeader("Authorization");
        if(header!=null&&!"".equals(header)){
            //如果有头信息进行解析
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                //对令牌进行验证
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String)claims.get("roles");
                    if(roles!=null&&roles.equals("admin")){
                        //具体页面的从request取角色然后执行业务逻辑
                        request.setAttribute("claims_admin",claims);
                    }
                    if(roles!=null&&roles.equals("user")){
                        request.setAttribute("claims_user",claims);
                    }
                }catch (Exception e){
                    throw new RuntimeException("令牌不正确");
                }
            }
        }
        return true;
    }
}


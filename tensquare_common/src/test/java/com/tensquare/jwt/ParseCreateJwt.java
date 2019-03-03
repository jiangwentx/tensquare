package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-03-03 18:01
 **/

public class ParseCreateJwt {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("itcast")
               .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqazlk6UiLCJpYXQiOjE1NTE2MDg0MzMsImV4cCI6MTU1MTYwODQ5Mywicm9sZSI6ImFkbWluIn0.oStTeEjiWO00kUBJW8qP4FDsPNlEozKIu2RRXMW8s9A")
                .getBody();
        System.out.println("用户id"+claims.getId());
        System.out.println("用户名"+claims.getSubject());
        System.out.println("登录时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()) );
        System.out.println("过期时间"+claims.getExpiration());
        System.out.println("用户角色"+claims.get("role"));
    }
}


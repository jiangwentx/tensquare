package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-03-22 10:11
 **/
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;
    /*
    * pre在请求前和
    * post在请求后执行
    * */
    @Override
    public String filterType() {
        return "pre";
    }

    /*
    * 多个过滤器执行顺序
    * */
    @Override
    public int filterOrder() {
        return 0;
    }

    /*
    * 当前过滤器是否开启，true打开,false关闭
    * */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /*
    * 过滤器内执行的操作
    * setsendzuulresponse(false)表示不再继续执行
    * */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过后台过滤器了!");
        //得到request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = currentContext.getRequest();

        //这是浏览器自身的分发请求，必须让它通过
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        //如果请求登录的，过滤器也需要放行
        String url=request.getRequestURL().toString();
        if(url.indexOf("/admin/login")>0){
            System.out.println("登陆页面"+url);
            return null;
        }


        //得到头信息
        String header = request.getHeader("Authorization");
        //后台过滤器先来一次验证
        if(header!=null &&!"".equals(header)){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles=(String)claims.get("roles");
                    if(roles.equals("admin")){
                        //把头信息转发并且放行
                        currentContext.addZuulRequestHeader("Authorization",header);
                        return null;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    currentContext.setSendZuulResponse(false);//终止运行
                }
            }
        }
        currentContext.setSendZuulResponse(false);//终止运行
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}


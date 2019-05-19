package com.ximingren.filter;

import com.ximingren.entity.User;
import com.ximingren.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SysUserFilter extends PathMatchingFilter {
    @Autowired
    private UserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("preHandle方法执行,前置增强器，拦截器链执行之前执行");
        return super.preHandle(request, response);
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("postHandle方法执行，后置返回增强，拦截器链执行完成后执行");
        super.postHandle(request, response);
    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        System.out.println("afterCompletion方法执行，后置最终增强");
        super.afterCompletion(request, response, exception);
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        User user  = (User) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("currentUser", user);
        return true;
    }
}

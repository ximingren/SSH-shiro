package com.ximingren.web;

import com.ximingren.entity.Message;
import com.ximingren.entity.User;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    //    TODO 参数是不是可以有多个，可不可以不用model的方法向前端传递信息
    @ResponseBody
    @RequestMapping("/login")
    public Message login(HttpServletRequest request) {
        Message message = new Message();
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误:" + exceptionClassName;
        }
        if (error != null) {
            message.setCode(-1);
            message.setMsg(error);
        } else {
            message.setCode(1);
            message.setMsg("登录成功");
        }
        return message;
    }

//    TODO 注解不起作用
    @RequiresPermissions("create")
    @ResponseBody
    @RequestMapping(value = "/getCurrentUser",method = RequestMethod.GET)
    public Message getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("currentUser");
        return new Message(1,"success",user);
    }
}

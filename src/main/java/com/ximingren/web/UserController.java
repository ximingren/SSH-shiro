package com.ximingren.web;

import com.ximingren.entity.Message;
import com.ximingren.entity.Role;
import com.ximingren.entity.User;
import com.ximingren.service.RoleService;
import com.ximingren.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/list")
    public Message userList() {
        Message message = new Message();
        List<User> userList = userService.findAll();
        message.setCode(1);
        message.setMsg("success");
        message.setData(userList);
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Message create(@RequestBody User user) {
        Message message = new Message();
        Set<Role> roleSet = new HashSet<Role>();
        for (Long roleId : user.getRids()) {
            Role role = roleService.findOne(roleId);
            roleSet.add(role);
        }
        user.setRoles(roleSet);
        userService.createUser(user);
        message.setCode(1);
        message.setMsg("createSuccess");
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/{email}/delete", method = RequestMethod.POST)
    public Message delete(@PathVariable("email") String email) {
        User user = new User();
        user.setEmail(email);
        Message message = new Message();
        userService.deleteUser(user);
        message.setCode(1);
        message.setMsg("deleteSuccess");
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/{email}/changePassword", method = RequestMethod.POST)
    public Message changePassword(@PathVariable("email") String email,String newPassword) {
        User user=userService.findByUsername(email);
        user.setPswd(newPassword);
        userService.updatePassword(user);
        Message message = new Message();
        message.setCode(1);
        message.setMsg("changeSuccess");
        return message;
    }
}

package com.ximingren.web;

import com.ximingren.entity.Message;
import com.ximingren.entity.Role;
import com.ximingren.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/create")
    public Message create(@RequestBody Role role) {
        Message message = new Message();
        roleService.createRole(role);
        message.setMsg("createSuccess");
        message.setCode(1);
        return message;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Message update(@RequestBody Role role) {
        Message message = new Message();
        roleService.updateRole(role);
        message.setMsg("updateSuccess");
        message.setCode(1);
        return message;
    }

    @ResponseBody
    @RequestMapping("/{id}/delete")
    public Message delete(@PathVariable("id") Long id) {
        Message message = new Message();
        roleService.deleteRole(id);
        message.setMsg("deleteSuccess");
        message.setCode(1);
        return message;
    }
}

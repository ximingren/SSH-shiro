package com.ximingren;

import com.ximingren.entity.Role;
import com.ximingren.entity.User;
import com.ximingren.service.RoleService;
import com.ximingren.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-base.xml", "classpath:spring-hibernate.xml", "classpath:spring-shiro.xml"})
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Test
    public void findRolesTest() {
        Set<Role> roles = userService.findRoles("soso");
        Set<String> rolesString = new HashSet<String>();
        for (Role r : roles) {
            rolesString.add(r.getName());
        }
        System.out.println(rolesString);
    }

    @Test
    public void changePasswordTest() {
        User user= userService.findByUsername("8446666@qq.com");
        user.setPswd("123");
        userService.updatePassword(user);
    }

    @Test
    public void findAllTest() {
        for (User u : userService.findAll()) {
            System.out.println(u.toString());
        };
    }

    @Test
    public void createTest() {
        User user = new User();
        user.setNickname("nick");
        user.setPswd("234");
        user.setEmail("email@16.com");
        user.setStatus(new Long(1));
        Set<Long> rids = new HashSet<Long>();
        rids.add(new Long(1));
        user.setRids(rids);
        Set<Role> roles = new HashSet<Role>();
        for (Long rid : rids) {
            roles.add(roleService.findOne(rid));
        }
        user.setRoles(roles);
        userService.createUser(user);
    }

    @Test
    public void deleteTest() {
        User user = new User();
        user.setNickname("nick");
        user.setPswd("234");
        user.setEmail("email@16.com");
        user.setStatus(new Long(1));
        Set<Long> rids = new HashSet<Long>();
        rids.add(new Long(1));
        user.setRids(rids);
        userService.deleteUser(user);
    }
}

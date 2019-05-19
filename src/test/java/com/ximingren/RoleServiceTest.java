package com.ximingren;

import com.ximingren.entity.Permission;
import com.ximingren.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-base.xml", "classpath:spring-hibernate.xml", "classpath:spring-base.xml"})
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void getPermissionsTest() {
        Set<Permission> permissionSet = roleService.findPermissions(new Long(4));
        Set<String> permissions = new HashSet<String>();
        for (Permission permission : permissionSet) {
            permissions.add(permission.getName());
        }
        System.out.println(permissions);
    }
}

package com.ximingren.service.impl;

import com.ximingren.dao.BaseDaoI;
import com.ximingren.entity.Permission;
import com.ximingren.entity.Role;
import com.ximingren.service.PermissionService;
import com.ximingren.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private BaseDaoI<Role> roleDao;

    public Role findOne(Long roleId) {
        return roleDao.findOne(new Role(), roleId);
    }
    public Role createRole(Role role) {
        return null;
    }

    public void deleteRole(Long roleId) {

    }

    public void updateRole(Role role){
        roleDao.update(role);
    }


    public Set<Permission> findPermissions(Long roleId) {
        return roleDao.findOne(new Role(), roleId).getPermissions();
    }
}

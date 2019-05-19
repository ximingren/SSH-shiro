package com.ximingren.service;

import com.ximingren.entity.Permission;
import com.ximingren.entity.Role;

import java.util.Set;

public interface RoleService {
    public Role findOne(Long roleId);
    public Role createRole(Role role);

    public void deleteRole(Long roleId);

    public void updateRole(Role role);
    /*
     * 根据用户名查找其权限
     */
    public Set<Permission> findPermissions(Long roleId);

}

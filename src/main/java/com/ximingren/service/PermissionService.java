package com.ximingren.service;

import com.ximingren.entity.Permission;

public interface PermissionService {
    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);

    public void findPermission(Long roleId);
}

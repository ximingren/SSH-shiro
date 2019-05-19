package com.ximingren.service;

import com.ximingren.entity.Permission;
import com.ximingren.entity.Role;
import com.ximingren.entity.User;

import java.util.List;
import java.util.Set;

//TODO 为什么要用接口
public interface UserService {
    public void createUser(User user);

    public void updatePassword(User user);

    /*
     *添加用户-角色关系
     */
    public void correlationRoles(Long userId, Long... roleIds);

    /*
     *移除用户-角色关系
     */
    public void uncorrelationRoles(Long userId, Long... roleIds);

    /*
     * 根据用户名查找用户
     */
    public User findByUsername(String username);

    /*
     * 根据用户名查找角色
     */
    public Set<Role> findRoles(String username);


    public void updateLoginTime(User user);

    public List<User> findAll();

    public void deleteUser(User user);
}

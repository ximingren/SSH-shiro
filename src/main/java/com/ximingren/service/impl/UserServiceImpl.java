package com.ximingren.service.impl;

import com.ximingren.dao.BaseDaoI;
import com.ximingren.entity.Role;
import com.ximingren.service.UserService;
import com.ximingren.entity.User;
import com.ximingren.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;

import java.util.Date;
import java.util.List;
import java.util.Set;

//TODO autowired是根据什么而进行匹配注入的
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDaoI<User> userDao;

    @Autowired
    private PasswordHelper passwordHelper;

    public void createUser(User user) {
        user.setCreateTime(new Date());
        passwordHelper.encryptPassword(user);
        userDao.create(user);
    }

    public void updatePassword(User user) {
        passwordHelper.encryptPassword(user);
        userDao.update(user);
    }

    public void deleteUser(User user) {
        userDao.executeHql("delete from User where email=?0", user.getEmail());
    }
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    public User findByUsername(String username) {
        return userDao.findByHql("from User where email=?0", username).get(0);
    }

    public Set<Role> findRoles(String username) {
        return userDao.findByHql("from User where email=?0", username).get(0).getRoles();
    }


    public void updateLoginTime(User user) {
        userDao.update(user);
    }

    public List<User> findAll() {
        return userDao.findByHql("from User");
    }
}

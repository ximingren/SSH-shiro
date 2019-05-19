package com.ximingren;

import com.ximingren.dao.BaseDaoI;
import com.ximingren.entity.Role;
import com.ximingren.entity.User;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-hibernate.xml", "classpath:spring-base.xml"})
public class UserDaoTest {
    @Autowired
    private BaseDaoI<User> baseDao;

    @Autowired
    private BaseDaoI<Role> roleDao;
    @Autowired
    private BaseDaoI<User> userDao;

    @Test
    public void rolesToUserTest() {
        User user = userDao.findOne(new User(), new Long(112));
        System.out.println(user.toString());
        for (Role r : user.getRoles()) {
            System.out.println(r.getName());
        }
    }

    @Test
    public void userToRolesTest() {
        Role role = roleDao.findOne(new Role(), new Long(1));
        System.out.println(role);
    }

    @Test
    public void findByUsernameTest() {
        List<User> user = userDao.findByHql("from User where id=?0", "1");
        System.out.println(user.get(0).toString());
    }


}

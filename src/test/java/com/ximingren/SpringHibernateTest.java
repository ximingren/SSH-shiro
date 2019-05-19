package com.ximingren;

import com.ximingren.dao.BaseDaoI;
import com.ximingren.service.UserService;
import com.ximingren.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-hibernate.xml", "classpath:spring-base.xml"})
public class SpringHibernateTest {
    @Autowired
    SessionFactory sessionFactory;


    @Autowired
    UserService authServiceI;

    @Test
    public void springTest() {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, "nickname1");
        System.out.println(user.getPswd());
    }

    @Test
    public void createTest() {
        User user = new User("nickname1233", "email123", "pswd", new Date(), new Date(), new Long(2));
        authServiceI.createUser(user);
    }

    @Test
    public void updateTest() {
        User user = new User("nickname1a", "email", "pswd123", new Date(), new Date(), new Long(2));
        authServiceI.updatePassword(user);
    }

    @Test
    public void findUserByNameTest() {
        User user = authServiceI.findByUsername("nickname1");
        System.out.println(user.getNickname());
    }


}

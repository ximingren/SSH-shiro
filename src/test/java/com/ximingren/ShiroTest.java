package com.ximingren;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-base.xml", "classpath:spring-hibernate.xml", "classpath:spring-shiro.xml"})
public class ShiroTest {
    @Test
    public void loginTest() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("8446666@qq.com", "123");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }
    @Test
    public void loginRetryTest() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("8446666@qq.com", "1234");
        for (int i = 0; i < 7; i++) {
            try {
                subject.login(token);

            } catch (ExcessiveAttemptsException e) {
                System.out.println("超过5次");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue((subject.isAuthenticated()));
    }

    @Test
    public void sessionTest() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("8446666@qq.com", "123");
        subject.login(token);
        Session session = subject.getSession();
        System.out.println(session.getId());
        System.out.println(session.getHost());
        System.out.println(session.getStartTimestamp());
        System.out.println(session.getLastAccessTime());
        System.out.println(session.getAttributeKeys());
        System.out.println(session.getTimeout());
    }

}

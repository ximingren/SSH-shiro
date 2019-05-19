package com.ximingren.util;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener implements SessionListener {
    public void onStart(Session session) {
        System.out.println("创建session"+session.getId());
    }

    public void onStop(Session session) {
        System.out.println("停止session" + session.getId());
    }

    public void onExpiration(Session session) {
        System.out.println("销毁session" + session.getId());
    }
}

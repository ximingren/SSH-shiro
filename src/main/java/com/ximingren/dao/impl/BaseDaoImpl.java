package com.ximingren.dao.impl;

import com.ximingren.dao.BaseDaoI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

// respository,具有将数据库原生操作抛出的异常转为spring的持久层异常
@Repository("basedao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {
    @Autowired
    private SessionFactory sessionFactory;

    //    sessionFactory.getCurrentSession()是基于事务的，才能实现session生命周期的管理
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T findOne(T o, String params) {
        return (T) this.getCurrentSession().get(o.getClass(), params);
    }

    public T findOne(T o, int params) {
        return (T) this.getCurrentSession().get(o.getClass(), params);
    }

    public T findOne(T o, Long params) {
        return (T) this.getCurrentSession().get(o.getClass(), params);
    }

    public Serializable create(T o) {
//        TODO 这个为什么会返回serializable?
        return this.getCurrentSession().save(o);
    }

    public void delete(T o) {
        this.getCurrentSession().delete(o);
    }

    public void update(T o) {
        this.getCurrentSession().update(o);
    }

    public int executeHql(String hql,String... arguments) {
        Query query = this.getCurrentSession().createQuery(hql);
        for (int i = 0; i < arguments.length; i++) {
            String argument = arguments[i];
            query.setParameter(i, argument);
        }
        return query.executeUpdate();
    }
    public List<T> findByHql(String hql) {
        Query query = this.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public List<T> findByHql(String hql, String... arguments) {
        Query query = this.getCurrentSession().createQuery(hql);
        for (int i = 0; i < arguments.length; i++) {
            String argument = arguments[i];
            query.setParameter(i, argument);
        }
        return query.list();
    }

    public List<T> findByHql(String hql, Long... arguments) {
        Query query = this.getCurrentSession().createQuery(hql);
        for (int i = 0; i < arguments.length; i++) {
            Long argument = arguments[i];
            query.setParameter(i, argument);
        }
        return query.list();
    }
}

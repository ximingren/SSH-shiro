package com.ximingren.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDaoI<T> {
    //    TODO 这里不懂为什么要序列化
    public Serializable create(T o);

    public void delete(T o);

    public void update(T o);

    public T findOne(T o, String params);

    public T findOne(T o, int params);

    public T findOne(T o, Long params);

    public List<T> findByHql(String hql);

    //TODO 这里参数设置，能不能用一个函数代替
    public List<T> findByHql(String hql, String... arguments);

    public List<T> findByHql(String hql, Long... arguments);

    public int executeHql(String hql, String... arguments);
}

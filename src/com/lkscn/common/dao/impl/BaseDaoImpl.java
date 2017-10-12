package com.lkscn.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lkscn.common.dao.IBaseDao;


@Repository
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements IBaseDao<T> {

	private Class<T> entityClazz;
	protected SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	public BaseDaoImpl() {
		this.entityClazz = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClazz = (Class<T>) parameterizedType[0];
		}
	}

	public T getById(Serializable id) {
		return (T) getSession().get(entityClazz, id);
	}

	public T loadById(Serializable id) {
		return (T) getSession().load(entityClazz, id);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void deleteById(Serializable id) {
		T entity = loadById(id);
		getSession().delete(entity);
	}

	public List<T> findAll() {
		String hql = " FROM " + entityClazz.getName();
		return getSession().createQuery(hql).list();
	}

	public List<T> get(Serializable[] ids) {
		if (ids == null || ids.length == 0)
			return Collections.EMPTY_LIST;
		String hql = " FROM " + entityClazz.getName() + " WHERE id in (:ids) ";
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public Serializable saveEntity(T entity) {
		return getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public int count(Class<?> clazz) {
		Object o = getSession().createQuery("select count(*) from " + clazz.getSimpleName()).uniqueResult();
		return o == null ? 0 : ((Number) o).intValue();
	}

//	@Resource(name="losJdbcTemplate")
//	private JdbcTemplate jdbcTemp;


	public Query getQuery(String hql) {
		return getSession().createQuery(hql);
	}

	public Criteria getCriteria(Class<?> clazz) {
		return getSession().createCriteria(clazz);
	}

	public Criteria getCriteria() {
		return getSession().createCriteria(this.entityClazz);
	}

	@Override
	public List<?> findByProperty(Class<?> clazz, String propKey, String propVal) {
		Criteria c = getCriteria(clazz);
		c.add(Restrictions.eq(propKey, propVal));
		return c.list();
	}
}

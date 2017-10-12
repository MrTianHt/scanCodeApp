package com.lkscn.common.service.impl;

import java.io.Serializable;
import java.util.List;

import com.lkscn.common.dao.IBaseDao;
import com.lkscn.common.service.IBaseService;

public class BaseServiceImpl<T> implements IBaseService<T> {

	public IBaseDao<T> baseDao;
	
	public IBaseDao<T> getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public void delete(T entity) {
		baseDao.delete(entity);
	}

	public void deleteById(Serializable id) {
		baseDao.deleteById(id);
	}

	public List<T> findAll() {
		return baseDao.findAll();
	}

	public List<T> get(Serializable[] ids) {
		return baseDao.get(ids);
	}

	public T getById(Serializable id) {
		return baseDao.getById(id);
	}

	public T loadById(Serializable id) {
		return baseDao.loadById(id);
	}

	public void save(T entity) {
		baseDao.save(entity);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}
	
	public Serializable saveEntity(T entity){
		return baseDao.saveEntity(entity);
	}

}

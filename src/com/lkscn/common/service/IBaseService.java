package com.lkscn.common.service;

import java.io.Serializable;
import java.util.List;

/**
 * 业务逻辑操作基类
 * @author Mr Tian
 */
public interface IBaseService<T> {	

	/**
	 * 通过ID获取指定实体对象
	 * @param id
	 */
	T getById(Serializable id);
	
	T loadById(Serializable id);
	
	/**
	 * 根据id数组获取对应的实体集合
	 * @param ids
	 */
	List<T> get(Serializable[] ids);
	
	/**
	 * 获取所有实体集合
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 保存实体对象
	 * @param entity
	 */
	void save(T entity);
	
	/**
	 * 更新实体对象
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 删除实体对象
	 * @param entity
	 */
	void delete(T entity);
	
	/**
	 * 根据id删除指定对象
	 * @param id
	 */
	void deleteById(Serializable id);
	
}

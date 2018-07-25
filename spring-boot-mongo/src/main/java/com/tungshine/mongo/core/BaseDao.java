package com.tungshine.mongo.core;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @Author: TungShine
 * @Description:
 * @Date: 2018年7月25日
 * @Modified By:
 */
public interface BaseDao<T, PK extends Serializable> {

	T save(T entity);

	T findById(PK id);

	List<T> findOne(LinkedHashMap<String, Object> linkedHashMap);
}

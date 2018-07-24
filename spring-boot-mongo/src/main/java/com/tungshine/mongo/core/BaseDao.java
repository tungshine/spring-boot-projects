package com.tungshine.mongo.core;

import java.io.Serializable;

public interface BaseDao<T, ID extends Serializable> {

	T save(T entity);

}

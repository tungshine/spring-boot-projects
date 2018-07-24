package com.tungshine.mongo.core;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class BaseDaoImpl<T> implements BaseDao<T, Serializable> {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public T save(T entity) {
		mongoTemplate.save(entity);
		return entity;
	}

}

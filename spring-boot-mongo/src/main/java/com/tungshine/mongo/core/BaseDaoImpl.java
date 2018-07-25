package com.tungshine.mongo.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	protected Class<?> entityClass;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MongoOperations mongoOperations;

	public BaseDaoImpl() {
		this.entityClass = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public T save(T entity) {
		mongoTemplate.save(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(PK id) {
		return (T) mongoTemplate.findById(id, entityClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findOne(LinkedHashMap<String, Object> linkedHashMap) {
		Query query = new Query();
		Criteria[] criteriaArray = new Criteria[linkedHashMap.size()];
		int index = 0;
		for (String key : linkedHashMap.keySet()) {
			criteriaArray[index] = Criteria.where(key).is(linkedHashMap.get(key));
			if (index < linkedHashMap.size() - 1) {
				index++;
			}
		}
		Criteria criteria = new Criteria();
		criteria.andOperator(criteriaArray);
		query.addCriteria(criteria);
		return (List<T>) mongoTemplate.find(query, entityClass);
	}

}

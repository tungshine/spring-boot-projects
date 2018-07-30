package com.tungshine.mongo.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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
    public List<T> pageListByAndOperation(LinkedHashMap<String, Object> linkedHashMap, Integer pageNo, Integer pageSize) {
        Query query = new Query();
        query.skip((pageNo - 1) * pageSize);
        query.limit(pageSize);
        Criteria[] criteriaArray = buildCriteriaArray(linkedHashMap);
        Criteria criteria = new Criteria();
        criteria.andOperator(criteriaArray);
        query.addCriteria(criteria);
        return (List<T>) mongoTemplate.find(query, entityClass);
    }

    @Override
    public Long totalByAndOperation(LinkedHashMap<String, Object> linkedHashMap) {
        Query query = new Query();
        Criteria[] criteriaArray = buildCriteriaArray(linkedHashMap);
        Criteria criteria = new Criteria();
        criteria.andOperator(criteriaArray);
        query.addCriteria(criteria);
        return mongoTemplate.count(query, entityClass);
    }

    @Override
    public List<T> pageListByOrOperation(LinkedHashMap<String, Object> linkedHashMap, Integer pageNo, Integer pageSize) {
        Query query = new Query();
        query.skip((pageNo - 1) * pageSize);
        query.limit(pageSize);
        Criteria[] criteriaArray = buildCriteriaArray(linkedHashMap);
        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaArray);
        query.addCriteria(criteria);
        return (List<T>) mongoTemplate.find(query, entityClass);
    }

    @Override
    public Long totalByOrOperation(LinkedHashMap<String, Object> linkedHashMap) {
        Query query = new Query();
        Criteria[] criteriaArray = buildCriteriaArray(linkedHashMap);
        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaArray);
        query.addCriteria(criteria);
        return mongoTemplate.count(query, entityClass);
    }

    @Override
    public List<T> pageListByVague(Object value, Integer pageNo, Integer pageSize) {
        Criteria[] criteriaArray = buildMatchFieldCriteriaArray(value);
        Query query = new Query();
        query.skip((pageNo - 1) * pageSize);
        query.limit(pageSize);
        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaArray);
        query.addCriteria(criteria);
        return (List<T>) mongoTemplate.find(query, entityClass);
    }

    @Override
    public Long totalByVague(Object value) {
        Query query = new Query();
        Criteria[] criteriaArray = buildMatchFieldCriteriaArray(value);
        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaArray);
        query.addCriteria(criteria);
        return mongoTemplate.count(query, entityClass);
    }

    private Criteria[] buildCriteriaArray(LinkedHashMap<String, Object> linkedHashMap) {
        if (null != linkedHashMap && linkedHashMap.size() > 0) {
            Criteria[] criteriaArray = new Criteria[linkedHashMap.size()];
            int index = 0;
            for (String key : linkedHashMap.keySet()) {
                criteriaArray[index] = Criteria.where(key).is(linkedHashMap.get(key));
                if (index < linkedHashMap.size() - 1) {
                    index++;
                }
            }
            return criteriaArray;
        }
        return null;
    }

    private Criteria[] buildMatchFieldCriteriaArray(Object value) {
        try {
            Pattern pattern = Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE);
            Object instance = entityClass.newInstance();
            Field[] fields = instance.getClass().getDeclaredFields();
            int fieldCount = fields.length;
            Criteria[] criteriaArray = new Criteria[fieldCount];
            int index = 0;
            for (Field field : fields) {
                String fieldName = (null == field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class)) ? field.getName() : field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class).value();
                if (fieldName.equals("serialVersionUID")) {
                    criteriaArray = new Criteria[fieldCount - 1];
                    fieldCount--;
                    continue;
                }
                criteriaArray[index] = Criteria.where(fieldName).regex(pattern);
                if (index < fieldCount - 1) {
                    index++;
                }
            }
            return criteriaArray;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.tungshine.mongo.core;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: TungShine
 * @Description:
 * @Date: 2018年7月25日
 * @Modified By:
 */
public interface BaseDao<T, PK extends Serializable> {

    /**
     * 保存对象
     *
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 查询对象
     *
     * @param id
     * @return
     */
    T findById(PK id);

    /**
     * 分页组合查询数据(and)
     *
     * @param linkedHashMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<T> pageListByAndOperation(LinkedHashMap<String, Object> linkedHashMap, Integer pageNo, Integer pageSize);

    /**
     * 分页组合查询数据(or)
     *
     * @param linkedHashMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<T> pageListByOrOperation(LinkedHashMap<String, Object> linkedHashMap, Integer pageNo, Integer pageSize);

    /**
     * 组合查询总数(and)
     *
     * @param linkedHashMap
     * @return
     */
    Long totalByAndOperation(LinkedHashMap<String, Object> linkedHashMap);

    /**
     * 组合查询总数(or)
     *
     * @param linkedHashMap
     * @return
     */
    Long totalByOrOperation(LinkedHashMap<String, Object> linkedHashMap);
}

package com.simplegis.webservice.persistence.dao;

import java.math.BigInteger;
import java.util.List;

/**
 * Generic data access object interface.
 * @param <T> entity type
 */
public interface GenericDao<T> {

    /**
     * Get all objects.
     *
     * @return list of objects.
     */
    List<T> getAll();

    /**
     * Get object by id.
     *
     * @param id of an object
     * @return city or null
     */
    T getById(BigInteger id);

    /**
     * Update object by id.
     *
     * @param object with new data
     * @return 1 if updated 0 if not
     */
    Integer update(T object);

    /**
     * Add object.
     *
     * @param object to insert into db
     * @return inserted object with generated keys
     */
    T insert(T object);

    /**
     * Batch objects add.
     *
     * @param objects list of objects to insert into db
     * @return list of inserted objects with generated keys
     */
    List<T> batchInsert(List<T> objects);
}

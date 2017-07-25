package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.Phone;

import java.util.List;

/**
 * Phone specific data access object interface extension.
 */
public interface PhoneDao extends GenericDao<Phone> {

    /**
     *
     * @param phone
     * @return
     */
    Integer delete(Phone phone);

    /**
     *
     * @param orgId
     * @return
     */
    List<Phone> getByOrganizationId(Long orgId);
}

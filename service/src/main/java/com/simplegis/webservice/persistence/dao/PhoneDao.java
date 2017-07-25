package com.simplegis.webservice.persistence.dao;

import com.simplegis.webservice.persistence.entity.Phone;

import java.util.List;

/**
 * Phone specific data access object interface extension.
 */
public interface PhoneDao extends GenericDao<Phone> {

    /**
     * DAO method for delete phone.
     *
     * @param phone to delete
     * @return 1 if phone was deleted 0 if not
     */
    Integer delete(Phone phone);

    /**
     * DAO method for getting organization phones.
     *
     * @param orgId to get phones for
     * @return list of organization phones.
     */
    List<Phone> getByOrganizationId(Long orgId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.SysGroup;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author ucha
 */
@Repository
public class SysgroupDao extends AbstractDao<SysGroup> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}

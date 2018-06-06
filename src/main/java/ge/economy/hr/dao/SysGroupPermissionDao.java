/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.SysGroupPermission;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class SysGroupPermissionDao extends AbstractDao<SysGroupPermission> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<SysGroupPermission> getSysGroupPermissionByGroupId(int id) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(SysGroupPermission.class.getSimpleName()).
                append(" e Where e.groupId ='").append(id).append("'");
        TypedQuery<SysGroupPermission> query = entityManager.createQuery(q.toString(), SysGroupPermission.class);
        return query.getResultList();
    }

}

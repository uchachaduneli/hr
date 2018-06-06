/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.dto.ViewPersonalDTO;
import ge.economy.hr.model.ViewPersonal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class ViewPersonalDao extends AbstractDao<ViewPersonal> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<ViewPersonal> getPersonalByParent(int parentId) {
        boolean hasOrder = false;
        StringBuilder q = new StringBuilder();
        String orderBy = " order by";
        q.append("Select e From ").append(ViewPersonal.class.getSimpleName()).
                append(" e Where e.statusId=1 ");
        if (parentId != -1) {
            q.append(" and e.structureId = '").append(parentId).append("'");
            orderBy += " e.structureId =" + parentId;
            hasOrder = true;
        }
        if (hasOrder) {
            orderBy += ", e.statusId = " + ViewPersonalDTO.ACTIVE_STATUS;
        } else {
            orderBy += " e.statusId = " + ViewPersonalDTO.ACTIVE_STATUS;
        }
        TypedQuery<ViewPersonal> query = entityManager.createQuery(q.toString() + orderBy, ViewPersonal.class);
        return query.getResultList();
    }
}

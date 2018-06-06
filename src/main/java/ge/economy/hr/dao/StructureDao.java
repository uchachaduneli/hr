/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.Structure;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class StructureDao extends AbstractDao<Structure> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Structure> getAll(int id) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Structure.class.getSimpleName()).
                append(" e Where e.visible=1 and e.organisationId ='").append(id).append("' order by e.parentId, e.id asc");
        TypedQuery<Structure> query = entityManager.createQuery(q.toString(), Structure.class);
        return query.getResultList();
    }

    public List<Structure> getDepartmentStructure(List<Integer> list) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Structure.class.getSimpleName()).
                append(" e Where e.parentId NOT IN :list order by e.parentId, e.id asc");
        TypedQuery<Structure> query = entityManager.createQuery(q.toString(), Structure.class);
        query.setParameter("list", list);
        return query.getResultList();
    }
}

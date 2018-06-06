/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.AnswerGroup;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class AnswerGroupDao extends AbstractDao<AnswerGroup> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<AnswerGroup> getAnswerGroupByTypeId(int Id) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(AnswerGroup.class.getSimpleName()).
                append(" e Where e.typeId ='").append(Id).append("'");
        TypedQuery<AnswerGroup> query = entityManager.createQuery(q.toString(), AnswerGroup.class);
        return query.getResultList();
    }
}

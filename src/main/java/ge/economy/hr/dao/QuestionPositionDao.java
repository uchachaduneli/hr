/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.QuestionPosition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class QuestionPositionDao extends AbstractDao<QuestionPosition> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<QuestionPosition> getAllOrderedById() {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(QuestionPosition.class.getSimpleName()).
                append(" e order by e.id asc");
        TypedQuery<QuestionPosition> query = entityManager.createQuery(q.toString(), QuestionPosition.class);
        return query.getResultList();
    }

}

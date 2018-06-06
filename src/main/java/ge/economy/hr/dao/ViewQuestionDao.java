/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.ViewQuestion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class ViewQuestionDao extends AbstractDao<ViewQuestion> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<ViewQuestion> getAllOrderedById() {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(ViewQuestion.class.getSimpleName()).
                append(" e order by e.id desc");
        TypedQuery<ViewQuestion> query = entityManager.createQuery(q.toString(), ViewQuestion.class);
        return query.getResultList();
    }

    public List<ViewQuestion> getQuestionByTest(int testId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(ViewQuestion.class.getSimpleName()).
                append(" e Where e.testId ='").append(testId).append("'");
        TypedQuery<ViewQuestion> query = entityManager.createQuery(q.toString(), ViewQuestion.class);
        return query.getResultList();
    }

}

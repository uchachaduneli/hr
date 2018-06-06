/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class QuestionDao extends AbstractDao<Question> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Question> getQuestionByTest(int testId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Question.class.getSimpleName()).
                append(" e Where e.testId ='").append(testId).append("'");
        TypedQuery<Question> query = entityManager.createQuery(q.toString(), Question.class);
        return query.getResultList();
    }
}

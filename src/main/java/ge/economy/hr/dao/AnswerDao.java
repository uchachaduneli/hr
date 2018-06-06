/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.Answer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class AnswerDao extends AbstractDao<Answer> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Answer> getAnswersByAnswerGroupId(int answerGroupId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Answer.class.getSimpleName()).
                append(" e Where e.groupId ='").append(answerGroupId).append("'");
        TypedQuery<Answer> query = entityManager.createQuery(q.toString(), Answer.class);
        return query.getResultList();
    }
}

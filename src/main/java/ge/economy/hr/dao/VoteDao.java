/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.Vote;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author ucha
 */
@Repository
public class VoteDao extends AbstractDao<Vote> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean checkPersonalHasTest(int voterId, int testId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Vote.class.getSimpleName()).
                append(" e Where e.testId ='").append(testId).append("'").
                append(" and e.voterId ='").append(voterId).append("'");
        TypedQuery<Vote> query = entityManager.createQuery(q.toString(), Vote.class);
        return query.getResultList().isEmpty();
    }

    public boolean checkByVoterTestCandidate(int voterId, int testId, int candidateId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Vote.class.getSimpleName()).
                append(" e Where e.testId ='").append(testId).append("'").
                append(" and e.candidateId ='").append(candidateId).append("'").
                append(" and e.voterId ='").append(voterId).append("'");
        TypedQuery<Vote> query = entityManager.createQuery(q.toString(), Vote.class);
        return query.getResultList().isEmpty();
    }

}

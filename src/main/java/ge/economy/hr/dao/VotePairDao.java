/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.VotePair;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class VotePairDao extends AbstractDao<VotePair> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<VotePair> getVotePairByTestId(int id) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(VotePair.class.getSimpleName()).
                append(" e Where e.testId ='").append(id).append("'");
        TypedQuery<VotePair> query = entityManager.createQuery(q.toString(), VotePair.class);
        return query.getResultList();
    }

    public List<VotePair> getVotePairByVoterAndTestId(int voterId, int testId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(VotePair.class.getSimpleName()).
                append(" e Where e.testId ='").append(testId).append("' and e.voterId='").append(voterId).append("'");
        TypedQuery<VotePair> query = entityManager.createQuery(q.toString(), VotePair.class);
        return query.getResultList();
    }

    public VotePair getVotePairByParams(VotePair v) {
        StringBuilder q = new StringBuilder();
        String orderBy = " order by  e.testId =" + v.getTestId() + ", e.transactionId =" + v.getTransactionId() + ", e.voterId =" + v.getVoterId();
        q.append("Select e From ").append(VotePair.class.getSimpleName()).
                append(" e Where e.testId ='").append(v.getTestId()).append("'").
                append(" and e.transactionId ='").append(v.getTransactionId()).append("'").
                append(" and e.voterId = '").append(v.getVoterId()).append("'");
        if (v.getCandidateId() != null) {
            q.append(" and e.candidateId = '").append(v.getCandidateId()).append("'");
            orderBy += ", e.candidateId =" + v.getCandidateId();
        } else if (v.getDepartmentId() != null) {
            q.append(" and e.departmentId = '").append(v.getDepartmentId()).append("'");
            orderBy += ", e.departmentId =" + v.getDepartmentId();
        }
        TypedQuery<VotePair> query = entityManager.createQuery(q.toString() + orderBy, VotePair.class);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }
}

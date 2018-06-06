/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.dto.VoteDTO;
import ge.economy.hr.model.ViewVote;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class ViewVoteDao extends AbstractDao<ViewVote> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<ViewVote> getVoteByPersonal(VoteDTO v) {
        boolean hasOrder = false;
        StringBuilder q = new StringBuilder();
        String orderBy = " order by ";
        q.append("Select e From ").append(ViewVote.class.getSimpleName()).
                append(" e Where 1=1 ");
        if (v.getTestId() != null) {
            q.append(" and e.testId = '").append(v.getTestId()).append("'");
            orderBy += ", e.testId =" + v.getTestId();
            hasOrder = true;
        } else if (v.getDepartmentId() != null) {
            q.append(" and e.departmentId = '").append(v.getDepartmentId()).append("'");
            orderBy += ", e.departmentId =" + v.getDepartmentId();
            hasOrder = true;
        } else if (v.getCandidateId() != null) {
            q.append(" and e.candidateId = '").append(v.getCandidateId()).append("'");
            orderBy += ", e.candidateId =" + v.getCandidateId();
            hasOrder = true;
        }
        TypedQuery<ViewVote> query = entityManager.createQuery(q.toString() + (hasOrder ? orderBy : ""), ViewVote.class);
        return query.getResultList();
    }

}

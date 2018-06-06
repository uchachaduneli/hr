/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.dto.VoteDTO;
import ge.economy.hr.model.ViewSelfScore;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class ViewSelfScoreDao extends AbstractDao<ViewSelfScore> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<ViewSelfScore> getSelfScore(VoteDTO v) {
        boolean hasOrder = false;
        StringBuilder q = new StringBuilder();
        String orderBy = " order by ";
        q.append("Select e From ").append(ViewSelfScore.class.getSimpleName()).
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
        } else if (v.getQuestionTypeId() != null) {
            q.append(" and e.questionTypeId = '").append(v.getQuestionTypeId()).append("'");
            orderBy += ", e.questionTypeId =" + v.getQuestionTypeId();
            hasOrder = true;
        } else if (v.getCandidateStructureId() != null) {
            q.append(" and e.candidateStructureId = '").append(v.getCandidateStructureId()).append("'");
            orderBy += ", e.candidateStructureId =" + v.getCandidateStructureId();
            hasOrder = true;
        }
        TypedQuery<ViewSelfScore> query = entityManager.createQuery(q.toString() + (hasOrder ? (orderBy + " asc ") : ""), ViewSelfScore.class);
        return query.getResultList();
    }
}

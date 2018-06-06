/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.dto.VoteDTO;
import ge.economy.hr.model.ViewTestScore;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class ViewTestScoreDao extends AbstractDao<ViewTestScore> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<ViewTestScore> getTestScoreByPersonal(VoteDTO v) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(ViewTestScore.class.getSimpleName()).
                append(" e Where 1=1 ");
        if (v.getTestId() != null) {
            q.append(" and e.testId = '").append(v.getTestId()).append("'");
        } else if (v.getDepartmentId() != null) {
            q.append(" and e.departmentId = '").append(v.getDepartmentId()).append("'");
        } else if (v.getCandidateId() != null) {
            q.append(" and e.candidateId = '").append(v.getCandidateId()).append("'");
        } else if (v.getQuestionTypeId() != null) {
            q.append(" and e.questionTypeId = '").append(v.getQuestionTypeId()).append("'");
        } else if (v.getCandidateStructureId() != null) {
            q.append(" and e.candidateStructureId = '").append(v.getCandidateStructureId()).append("'");
        }
        if (v.getOrderByClause() != null && v.getOrderByClause() != "") {
            q.append(" order by " + v.getOrderByClause() + " asc");
        }
        TypedQuery<ViewTestScore> query = entityManager.createQuery(q.toString(), ViewTestScore.class);
        return query.getResultList();
    }

}

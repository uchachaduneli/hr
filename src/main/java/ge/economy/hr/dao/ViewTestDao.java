/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.dto.TestDTO;
import ge.economy.hr.model.ViewTest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class ViewTestDao extends AbstractDao<ViewTest> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<ViewTest> getViewTestByOrganizationId(int organisationId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(ViewTest.class.getSimpleName()).
                append(" e Where e.organisationId ='").append(organisationId).append("' order by e.id desc");
        TypedQuery<ViewTest> query = entityManager.createQuery(q.toString(), ViewTest.class);
        return query.getResultList();
    }

    public List<ViewTest> getTestByStatus(int statusId, int organisationId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(ViewTest.class.getSimpleName()).append(" e Where 1=1 ");
        if (statusId != TestDTO.ALL) {
            q.append(" and e.organisationId ='").append(organisationId).append("'");
            q.append(" and e.activeStatusId ='").append(statusId).append("'");
        } else {
            q.append(" and e.organisationId ='").append(organisationId).append("'");
        }
        q.append(" order by e.id desc");
        TypedQuery<ViewTest> query = entityManager.createQuery(q.toString(), ViewTest.class);
        return query.getResultList();
    }
}

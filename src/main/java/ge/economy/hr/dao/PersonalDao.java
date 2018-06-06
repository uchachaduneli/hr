/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.Personal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class PersonalDao extends AbstractDao<Personal> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Personal> getNonManagementPersonal(List<Integer> structures, List<Integer> positions, int status) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Personal.class.getSimpleName()).
                append(" e Where e.statusId='").append(status).append("' and e.positionId NOT IN :positions and e.structureId NOT IN :structures");
        TypedQuery<Personal> query = entityManager.createQuery(q.toString(), Personal.class);
        query.setParameter("positions", positions);
        query.setParameter("structures", structures);
        return query.getResultList();
    }

    public List<Personal> getPersonalByStructureId(int id) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Personal.class.getSimpleName()).
                append(" e Where e.structureId ='").append(id).append("' order by e.positionId asc");
        TypedQuery<Personal> query = entityManager.createQuery(q.toString(), Personal.class);
        return query.getResultList();
    }

    public List<Personal> getInactivePersonal(List<String> mails, int organisationId) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(Personal.class.getSimpleName()).
                append(" e Where e.organisationId='").append(organisationId).append("' and e.mail NOT IN :mails");
        TypedQuery<Personal> query = entityManager.createQuery(q.toString(), Personal.class);
        query.setParameter("mails", mails);
        return query.getResultList();
    }

}

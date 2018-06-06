/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dao;


import ge.economy.hr.model.PersonalDocument;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
@Repository
public class PersonalDocumentDao extends AbstractDao<PersonalDocument> {

    @PersistenceContext(unitName = "hr")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<PersonalDocument> getPersonalDocumentByPersonalId(int id) {
        StringBuilder q = new StringBuilder();
        q.append("Select e From ").append(PersonalDocument.class.getSimpleName()).
                append(" e Where e.personalId ='").append(id).append("'");
        TypedQuery<PersonalDocument> query = entityManager.createQuery(q.toString(), PersonalDocument.class);
        return query.getResultList();
    }

}

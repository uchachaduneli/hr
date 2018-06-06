package ge.economy.hr.dao;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ucha
 */
public abstract class AbstractDao<T> {

    public abstract EntityManager getEntityManager();

    public <T> T create(T object) {
        getEntityManager().persist(object);
        return object;
    }

    public <T> T update(T object) {
        return getEntityManager().merge(object);
    }

    public <T> void delete(T object) {
        getEntityManager().remove(object);
    }

    public <T> T find(Class object, Object identifier) {
        return (T) getEntityManager().find(object, identifier);
    }

    public <T> List<T> getAll(Class<T> clazz) {
        TypedQuery<T> query = getEntityManager().createQuery("Select a from " + clazz.getSimpleName() + " a", clazz);
        return query.getResultList();
    }

    public <T> List<T> getAllByParamValue(Class<T> clazz, List<ParamValuePair> paramValues) {

        StringBuilder q = new StringBuilder();
        q.append("Select tbl From ").append(clazz.getSimpleName()).append("  tbl Where 1 = 1 ");

        for (ParamValuePair pair : paramValues) {
            q.append(" and  tbl.").append(pair.getParamName()).append("  = :").append(pair.getParamName());
        }
        TypedQuery<T> query
                = getEntityManager().createQuery(q.toString(), clazz);

        for (ParamValuePair pair : paramValues) {
            query.setParameter(pair.getParamName(), pair.getParamValue());
        }

        return query.getResultList();
    }

}

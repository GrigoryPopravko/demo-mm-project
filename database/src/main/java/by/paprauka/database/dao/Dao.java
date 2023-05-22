package by.paprauka.database.dao;

import by.paprauka.database.entity.BaseEntity;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class Dao<K extends Serializable, E extends BaseEntity<K>> {

    private Class<E> clazz;

    public List<E> findAll(Session session) {
        var criteria = session.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return session.createQuery(criteria)
                .getResultList();
    }

    Optional<E> findById(Session session, K id) {
        return Optional.ofNullable(session.get(clazz, id));
    }

    Optional<E> create(Session session, E entity) {
        session.persist(entity);
        return Optional.ofNullable(entity);
    }

    Optional<E> update(Session session, E entity) {
        session.merge(entity);
        return Optional.ofNullable(entity);
    }

    boolean delete(Session session, K id) {
        return Optional.ofNullable(session.get(clazz, id))
                .map(entity -> {
                    session.remove(entity);
                    return true;
                })
                .orElse(false);
    }
}

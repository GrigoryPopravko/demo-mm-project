package by.paprauka.database.repository;

import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.AuthorEntity_;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.entity.BookEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryExtensionImpl implements BookRepositoryExtension {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<BookEntity> findByFilter(BookFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> query = cb.createQuery(BookEntity.class);
        Root<BookEntity> bookRoot = query.from(BookEntity.class);
        query.select(bookRoot);
        Join<Object, Object> authors = bookRoot.join(BookEntity_.AUTHORS, JoinType.LEFT);
        query.where(collectPredicates(filter, cb, bookRoot, authors).toArray(Predicate[]::new));
        return entityManager.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }

    private static List<Predicate> collectPredicates(BookFilter filter, CriteriaBuilder cb, Root<BookEntity> bookRoot, Join<Object, Object> authors) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getPagesAmount() != null) {
            predicates.add(cb.le(bookRoot.get(BookEntity_.PAGES), filter.getPagesAmount()));
        }
        if (filter.getGenre() != null) {
            predicates.add(cb.equal(bookRoot.get(BookEntity_.GENRE), filter.getGenre()));
        }
        if (filter.getTitle() != null && !filter.getTitle().isBlank()) {
            predicates.add(cb.like(bookRoot.get(BookEntity_.TITLE), "%" + filter.getTitle() + "%"));
        }
        if (filter.getAuthorName() != null && !filter.getAuthorName().isBlank()) {
            predicates.add(cb.like(authors.get(AuthorEntity_.FULL_NAME), "%" + filter.getAuthorName() + "%"));
        }
        return predicates;
    }
}

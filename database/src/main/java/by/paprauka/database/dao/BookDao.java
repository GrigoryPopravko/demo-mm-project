package by.paprauka.database.dao;

import by.paprauka.database.dto.BookDto;
import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.AuthorEntity_;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.entity.BookEntity_;
import by.paprauka.database.entity.enam.Genre;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaJoin;
import org.hibernate.query.criteria.JpaRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class BookDao {

    private static final BookDao INSTANCE = new BookDao();

    public List<BookEntity> findAll(Session session) {
        JpaCriteriaQuery<BookEntity> query = session.getCriteriaBuilder().createQuery(BookEntity.class);
        JpaRoot<BookEntity> bookRoot = query.from(BookEntity.class);
        query.select(bookRoot);
        return session.createQuery(query).list();
    }

    public List<BookDto> findAllDtos(Session session) {
        // SELECT b.title, a.full_name FROM book b JOIN book_author ba ON b.id = ba.book_id JOIN author a ON ba.author_id = a.id
        JpaCriteriaQuery<BookDto> query = session.getCriteriaBuilder().createQuery(BookDto.class);
        JpaRoot<BookEntity> bookRoot = query.from(BookEntity.class);
        JpaJoin<Object, Object> authors = bookRoot.join(BookEntity_.AUTHORS, JoinType.LEFT);
        query.multiselect(bookRoot.get(BookEntity_.TITLE), authors.get(AuthorEntity_.FULL_NAME));
        return session.createQuery(query).list();
    }

    public List<BookEntity> findAllByGenre(Session session, Genre genre) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<BookEntity> query = cb.createQuery(BookEntity.class);
        JpaRoot<BookEntity> bookRoot = query.from(BookEntity.class);
        query.select(bookRoot);
        query.where(cb.equal(bookRoot.get(BookEntity_.GENRE), genre));
        return session.createQuery(query).list();
    }

    public List<BookEntity> findAllByAuthor(Session session, String authorName) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<BookEntity> query = cb.createQuery(BookEntity.class);
        JpaRoot<BookEntity> bookRoot = query.from(BookEntity.class);
        query.select(bookRoot);
        JpaJoin<Object, Object> authors = bookRoot.join(BookEntity_.AUTHORS);
        query.where(cb.equal(authors.get(AuthorEntity_.FULL_NAME), authorName));
        return session.createQuery(query).list();
    }

    public Optional<BookEntity> findById(Session session, Long id) {
        return Optional.ofNullable(session.get(BookEntity.class, id));
    }


    public List<BookEntity> findByFilter(Session session, BookFilter filter) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<BookEntity> query = cb.createQuery(BookEntity.class);
        JpaRoot<BookEntity> bookRoot = query.from(BookEntity.class);
        query.select(bookRoot);
        JpaJoin<Object, Object> authors = bookRoot.join(BookEntity_.AUTHORS);
        query.where(collectPredicates(filter, cb, bookRoot, authors).toArray(Predicate[]::new));

        return session.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .list();
    }

    public Optional<BookEntity> create(Session session, BookEntity book) {
        session.persist(book);
        return Optional.ofNullable(book);
    }

    public Optional<BookEntity> update(Session session, BookEntity book) {
        session.merge(book);
        return Optional.ofNullable(book);
    }

    public boolean delete(Session session, Long id) {
        BookEntity book = session.get(BookEntity.class, id);
        if (book == null) {
            return false;
        }
        session.remove(book);
        return true;
    }

    private static List<Predicate> collectPredicates(BookFilter filter, HibernateCriteriaBuilder cb, JpaRoot<BookEntity> bookRoot, JpaJoin<Object, Object> authors) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getPagesAmount() != null) {
            predicates.add(cb.le(bookRoot.get(BookEntity_.PAGES), filter.getPagesAmount()));
        }
        if (filter.getGenre() != null) {
            predicates.add(cb.equal(bookRoot.get(BookEntity_.GENRE), filter.getGenre()));
        }
        if (filter.getTitle() != null) {
            predicates.add(cb.like(bookRoot.get(BookEntity_.TITLE), "%" + filter.getTitle() + "%"));
        }
        if (filter.getAuthorName() != null) {
            predicates.add(cb.like(authors.get(AuthorEntity_.FULL_NAME), "%" + filter.getAuthorName() + "%"));
        }
        return predicates;
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }
}

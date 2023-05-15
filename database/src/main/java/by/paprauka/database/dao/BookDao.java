package by.paprauka.database.dao;

import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.BookEntity;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class BookDao {

    private static final BookDao INSTANCE = new BookDao();

    public List<BookEntity> findAll(Session session) {
        return session.createQuery("SELECT book FROM BookEntity book", BookEntity.class).list();
    }

    public Optional<BookEntity> findById(Session session, Long id) {
        return Optional.ofNullable(session.get(BookEntity.class, id));
    }


    public List<BookEntity> findByFilter(Session session, BookFilter filter) {
        return session.createQuery("SELECT book FROM BookEntity book WHERE book.pages < :pages",
                        BookEntity.class)
                .setMaxResults(filter.getLimit())
                .setFirstResult(getOffset(filter.getLimit(), filter.getPage()))
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

    private int getOffset(Integer limit, Integer page) {
        return limit * (page - 1);
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }
}

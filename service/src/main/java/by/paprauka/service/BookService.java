package by.paprauka.service;

import by.paprauka.database.dao.BookDao;
import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.hibernate.HibernateFactory;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BookService {

    private static final BookService INSTANCE = new BookService();
    private final BookDao bookDao = BookDao.getInstance();
    private final HibernateFactory hibernateFactory = HibernateFactory.getInstance();

    public List<BookEntity> getAll() {
        List<BookEntity> books;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            books = bookDao.findAll(session);
            transaction.commit();
        }
        return books;
    }

    public List<BookEntity> getFindByFilter(BookFilter filter) {
        List<BookEntity> books;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            books = bookDao.findByFilter(session, filter);
            transaction.commit();
        }
        return books;
    }

    public BookEntity getById(Long id) {
        BookEntity book;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            book = bookDao.findById(session, id)
                    .orElse(BookEntity.builder()
                            .title("Lukomorie")
                            .build());
            transaction.commit();
        }
        return book;
    }

    public Optional<BookEntity> create(BookEntity book) {
        Optional<BookEntity> newBook;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            newBook = bookDao.create(session, book);
            transaction.commit();
        }
        return newBook;
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}

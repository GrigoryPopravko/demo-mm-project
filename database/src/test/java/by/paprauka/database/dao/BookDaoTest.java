package by.paprauka.database.dao;

import by.paprauka.database.TestDataImporter;
import by.paprauka.database.dto.BookDto;
import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.hibernate.HibernateFactory;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static by.paprauka.database.entity.enam.Genre.CLASSIC;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BookDaoTest {

    private static final BookDao bookDao = BookDao.getInstance();
    private static final HibernateFactory sessionFactory = HibernateFactory.getInstance();

    @BeforeAll
    static void beforeAll() {
        try (var session = sessionFactory.getSession()) {
            var transaction = session.beginTransaction();
            TestDataImporter.importTestData(session);
            transaction.commit();
        }
    }

    @Test
    void whenFindAllInvoked_ThenAllTheBooksAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = bookDao.findAll(session)
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "Madame Bovary", "War and Peace", "The Great Gatsby")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    void whenFindAllDtosInvoked_ThenAllTheBooksDtosAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        BookDto[] actual = bookDao.findAllDtos(session).toArray(BookDto[]::new);

        BookDto[] expected = bookDao.findAll(session)
                .stream()
                .map(book -> new BookDto(book.getTitle(),
                        book.getAuthors().size() > 0
                                ? book.getAuthors().get(0).getFullName()
                                : null))
                .toArray(BookDto[]::new);

        assertArrayEquals(expected, actual);
    }

    @Test
    void whenFindAllByGenreInvoked_ThenAllTheBooksOfGenreAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = bookDao.findAllByGenre(session, CLASSIC)
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "War and Peace", "The Great Gatsby")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    void whenFindAllByAuthorInvoked_ThenAllTheBooksOfAuthorAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        String[] actual = bookDao.findAllByAuthor(session, "Leo Tolstoi")
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "War and Peace")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    void whenFindAllByFilterContainsOnlyAuthorInvoked_ThenAllTheFilteredByAuthorBooksAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        BookFilter filter = BookFilter.builder()
                .authorName("Leo Tolstoi")
                .build();
        String[] actual = bookDao.findByFilter(session, filter)
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "War and Peace")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    void whenFindAllByFilterContainsAuthorAndPagesInvoked_ThenAllTheFilteredByAuthorAndPagesBooksAreReturned() {
        @Cleanup Session session = sessionFactory.getSession();
        BookFilter filter = BookFilter.builder()
                .authorName("Leo Tolstoi")
                .pagesAmount(1000)
                .build();
        String[] actual = bookDao.findByFilter(session, filter)
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }
}
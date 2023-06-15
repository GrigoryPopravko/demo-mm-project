package by.paprauka.database.repository;

import by.paprauka.database.config.DatabaseConfig;
import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.AuthorEntity;
import by.paprauka.database.entity.BookEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.paprauka.database.entity.enam.Genre.CLASSIC;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SqlGroup({
        @Sql(value = "classpath:test-data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:purge-data.sql", executionPhase = AFTER_TEST_METHOD)
})
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllTheBooksAreReturned() {
        String[] actual = bookRepository.findAll()
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "Madame Bovary", "War and Peace", "The Great Gatsby")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(3)
    void whenFindAllByGenreInvoked_ThenAllTheBooksOfGenreAreReturned() {
        Page<BookEntity> page = bookRepository.findAllByGenre(CLASSIC, Pageable.ofSize(5).withPage(0));
        String[] actual = page
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "War and Peace", "The Great Gatsby")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(4)
    @Transactional
    void whenFindAllByAuthorInvoked_ThenAllTheBooksOfAuthorAreReturned() {
        Optional<AuthorEntity> leoTolstoi = authorRepository.findByFullName("Leo Tolstoi");
        List<BookEntity> allByAuthorsContains = bookRepository.findAllByAuthorsContains(leoTolstoi.get());

        String[] actual = bookRepository.findAllByAuthorsContains(leoTolstoi.get())
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "War and Peace")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(5)
    void whenFindAllByFilterContainsOnlyAuthorInvoked_ThenAllTheFilteredByAuthorBooksAreReturned() {

        BookFilter filter = BookFilter.builder()
                .authorName("Leo Tolstoi")
                .build();

        String[] actual = bookRepository
                .findByFilter(filter)
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "War and Peace")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(6)
    void whenFindAllByFilterContainsAuthorAndPagesInvoked_ThenAllTheFilteredByAuthorAndPagesBooksAreReturned() {
        BookFilter filter = BookFilter.builder()
                .authorName("Leo Tolstoi")
                .pagesAmount(1000)
                .build();
        String[] actual = bookRepository.findByFilter(filter)
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(8)
    void whenCreatedInvokedWithBook_ThenBookIsSaved() {
        BookEntity testBook = BookEntity.builder()
                .title("Test")
                .genre(CLASSIC)
                .pages(200)
                .build();


        BookEntity bookEntity = bookRepository.save(testBook);

        List<String> allTitles = bookRepository.findAll().stream()
                .map(BookEntity::getTitle)
                .toList();
        assertTrue(allTitles.contains(testBook.getTitle()));
    }

    @Test
    @Order(9)
    void testFindAllByTitleIsLikeIgnoreCaseAndPagesLessThan() {
        List<String> allTitles = bookRepository.findAllBy("ada", 800).stream()
                .map(BookEntity::getTitle)
                .toList();
        assertTrue(allTitles.contains("Madame Bovary"));
    }

    @Test
    @Order(10)
    void testFindAllByAuthor() {
        List<String> allTitles = bookRepository.findAllBy("Leo Tolstoi").stream()
                .map(BookEntity::getTitle)
                .toList();
        assertTrue(allTitles.containsAll(List.of("War and Peace", "Anna Karenina")));
    }

    @Test
    @Order(10)
    @Transactional
    void testFindSetTitleById() {
        Optional<BookEntity> warAndPeace = bookRepository.findByTitle("War and Peace");
        bookRepository.setTitleById("War & Peace", warAndPeace.get().getId());
        assertTrue(bookRepository.findByTitle("War & Peace").isPresent());
    }
}
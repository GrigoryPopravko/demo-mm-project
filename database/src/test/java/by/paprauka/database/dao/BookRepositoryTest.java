package by.paprauka.database.dao;

import by.paprauka.database.config.DatabaseConfig;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.repository.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static by.paprauka.database.entity.enam.Genre.CLASSIC;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql("classpath:test-data.sql")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;


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
        String[] actual = bookRepository.findAllByGenre(CLASSIC)
                .stream()
                .map(BookEntity::getTitle)
                .toArray(String[]::new);
        String[] expected = List.of("Anna Karenina", "War and Peace", "The Great Gatsby")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

//    @Test
//    @Order(4)
//    void whenFindAllByAuthorInvoked_ThenAllTheBooksOfAuthorAreReturned() {
//
//
//        String[] actual = bookRepository.findAllByAuthorsContains("Leo Tolstoi")
//                .stream()
//                .map(BookEntity::getTitle)
//                .toArray(String[]::new);
//        String[] expected = List.of("Anna Karenina", "War and Peace")
//                .toArray(String[]::new);
//        assertArrayEquals(expected, actual);
//    }

//    @Test
//    @Order(5)
//    void whenFindAllByFilterContainsOnlyAuthorInvoked_ThenAllTheFilteredByAuthorBooksAreReturned() {
//
//        BookFilter filter = BookFilter.builder()
//                .authorName("Leo Tolstoi")
//                .build();
//        String[] actual = bookRepository.findByFilter(filter)
//                .stream()
//                .map(BookEntity::getTitle)
//                .toArray(String[]::new);
//        String[] expected = List.of("Anna Karenina", "War and Peace")
//                .toArray(String[]::new);
//        assertArrayEquals(expected, actual);
//    }

//    @Test
//    @Order(6)
//    void whenFindAllByFilterContainsAuthorAndPagesInvoked_ThenAllTheFilteredByAuthorAndPagesBooksAreReturned() {
//
//        BookFilter filter = BookFilter.builder()
//                .authorName("Leo Tolstoi")
//                .pagesAmount(1000)
//                .build();
//        String[] actual = bookRepository.findByFilter(filter)
//                .stream()
//                .map(BookEntity::getTitle)
//                .toArray(String[]::new);
//        String[] expected = List.of("Anna Karenina")
//                .toArray(String[]::new);
//        assertArrayEquals(expected, actual);
//    }

    @Test
    @Order(7)
    void whenFindById_ThenAllTheFilteredReturnsValidBook() {

        Optional<BookEntity> actual = bookRepository.findById(1L);
        assertTrue(actual.isPresent());
        assertEquals("Anna Karenina", actual.get().getTitle());
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
}
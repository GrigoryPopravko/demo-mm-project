package by.paprauka.database.dao;

import by.paprauka.database.entity.Book;
import by.paprauka.database.entity.enam.Genre;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDaoTest {

    private BookDao bookDao = BookDao.getInstance();

    @Test
    public void getAll() {
        List<Book> result = bookDao.findAll();
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void whenTheBookByIdExistsThenBookIsReturned() {
        Optional<Book> byId = bookDao.findById(1L);
        Assert.assertTrue(byId.isPresent());
    }

    @Test
    public void whenTheBookByIdDoesntExistThenNullIsReturned() {
        Optional<Book> byId = bookDao.findById(100L);
        Assert.assertTrue(byId.isEmpty());
    }

    @Test
    public void whenBookPassedThenBookIsCreated() throws SQLException {
        Book test = bookDao.create(Book.builder()
                .title("Test")
                .pages(100)
                .genre(Genre.CLASSIC)
                .build());

        Assert.assertNotNull(test.getId());
    }
}
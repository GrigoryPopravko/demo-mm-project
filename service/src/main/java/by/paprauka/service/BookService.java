package by.paprauka.service;

import by.paprauka.database.dao.BookDao;
import by.paprauka.database.entity.Book;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BookService {

    private static final BookService INSTANCE = new BookService();
    private final BookDao bookDao = BookDao.getInstance();

    public List<Book> getAll() {
        return bookDao.findAll();
    }

    public Book getById(Long id) {
        return bookDao.findById(id)
                .orElse(Book.builder()
                        .title("Lukomorie")
                        .build());
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}

package by.paprauka.service;

import by.paprauka.database.dao.BookDao;
import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.Book;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BookService {

    private static final BookService INSTANCE = new BookService();
    private final BookDao bookDao = BookDao.getInstance();

    public List<Book> getAll() {
        return bookDao.findAll();
    }

    public List<Book> getFindByFilter(BookFilter filter) {
        return bookDao.findByFilter(filter);
    }

    public Book getById(Long id) {
        return bookDao.findById(id)
                .orElse(Book.builder()
                        .title("Lukomorie")
                        .build());
    }

    public Optional<Book> create(Book book) {
        return bookDao.create(book);
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}

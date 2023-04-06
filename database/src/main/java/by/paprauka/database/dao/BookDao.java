package by.paprauka.database.dao;

import by.paprauka.database.DummyDatabase;
import by.paprauka.database.entity.Book;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class BookDao {

    private static final BookDao INSTANCE = new BookDao();
    private final DummyDatabase db = DummyDatabase.getInstance();

    public List<Book> getAll() {
        return new ArrayList<>(db.getBooks().values());
    }

    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(db.getBooks().get(id));
    }

    public Book create(Book book) {
        return db.getBooks().put(book.getId(), book);
    }

    public Book delete(Long id) {
        return Optional.ofNullable(db.getBooks().remove(id))
                .orElseThrow(RuntimeException::new);
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }
}

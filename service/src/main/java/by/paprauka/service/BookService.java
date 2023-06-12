package by.paprauka.service;

import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

//    public List<BookEntity> getFindByFilter(BookFilter filter) {
//
//            books = bookDao.findByFilter(session, filter);
//        return books;
//    }

    public BookEntity getById(Long id) {
        return bookRepository.findById(id)
                .orElse(BookEntity.builder()
                        .title("Lukomorie")
                        .build());
    }

    public BookEntity create(BookEntity book) {
        return bookRepository.save(book);
    }
}

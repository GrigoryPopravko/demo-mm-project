package by.paprauka.service;

import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    public List<BookEntity> getFindByFilter(BookFilter filter) {
        return bookRepository.findByFilter(filter);
    }

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

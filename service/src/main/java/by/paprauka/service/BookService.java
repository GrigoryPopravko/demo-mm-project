package by.paprauka.service;

import by.paprauka.database.dto.BookCreationDto;
import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.dto.BookReadDto;
import by.paprauka.database.entity.AuthorEntity;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.repository.AuthorRepository;
import by.paprauka.database.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<BookReadDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::toReadDto)
                .toList();
    }

    public List<BookEntity> getFindByFilter(BookFilter filter) {
        return bookRepository.findByFilter(filter);
    }

    public Optional<BookReadDto> getById(Long id) {
        return bookRepository.findById(id)
                .map(this::toReadDto);
    }

    public Long create(BookCreationDto book) {
        BookEntity newBook = BookEntity
                .builder()
                .title(book.title())
                .genre(book.genre())
                .pages(book.pages())
                .build();
        authorRepository.findAllByIdIn(book.authorsIds())
                .forEach(newBook::addAuthor);
        return bookRepository.save(newBook).getId();
    }

    public Optional<BookReadDto> update(Long id, BookCreationDto update) {
        Optional<BookEntity> existedBook = bookRepository.findById(id);
        if (existedBook.isPresent()) {
            BookEntity book = existedBook.get();
            book.setTitle(update.title());
            book.setGenre(update.genre());
            book.setPages(update.pages());
            List<AuthorEntity> authors = authorRepository.findAllByIdIn(update.authorsIds());
            book.setAuthors(authors);
            return Optional.of(toReadDto(bookRepository.save(book)));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        bookRepository.findById(id)
                .ifPresent(bookRepository::delete);
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    private BookReadDto toReadDto(BookEntity book) {
        return new BookReadDto(book.getId(),
                book.getTitle(),
                book.getPages(),
                book.getGenre(),
                book.getAuthors().stream()
                        .map(AuthorEntity::getFullName)
                        .toList());
    }
}

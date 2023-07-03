package by.paprauka.service;

import by.paprauka.database.dto.BookCreationDto;
import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.dto.BookReadDto;
import by.paprauka.database.entity.AuthorEntity;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.entity.enam.Genre;
import by.paprauka.database.repository.AuthorRepository;
import by.paprauka.database.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Cacheable(value = "books", unless = "#result.size() > 100")
    public List<BookEntity> getFindByFilter(BookFilter filter) {
        return bookRepository.findByFilter(filter);
    }

    @Cacheable("book")
    public Optional<BookReadDto> getById(Long id) {
        return bookRepository.findById(id)
                .map(this::toReadDto);
    }

    public Long create(BookCreationDto book) {
        BookEntity newBook = BookEntity
                .builder()
                .title(book.getTitle())
                .genre(book.getGenre())
                .pages(book.getPages())
                .build();
        authorRepository.findAllByIdIn(book.getAuthorsIds())
                .forEach(newBook::addAuthor);
        return bookRepository.save(newBook).getId();
    }

    @CachePut(value = "book", key = "#id")
    public Optional<BookReadDto> update(Long id, BookCreationDto update) {
        Optional<BookEntity> existedBook = bookRepository.findById(id);
        if (existedBook.isPresent()) {
            BookEntity book = existedBook.get();
            book.setTitle(update.getTitle());
            book.setGenre(update.getGenre());
            book.setPages(update.getPages());
            List<AuthorEntity> authors = authorRepository.findAllByIdIn(update.getAuthorsIds());
            book.setAuthors(authors);
            return Optional.of(toReadDto(bookRepository.save(book)));
        }
        return Optional.empty();
    }

    @Caching(evict = {
            @CacheEvict(value = "book", key = "#id"),
            @CacheEvict(value = "books", allEntries = true)
    })
    public void delete(Long id) {
        bookRepository.findById(id)
                .ifPresent(bookRepository::delete);
    }

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Genre> getAllGenres() {
        return List.of(Genre.values());
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

package by.paprauka.web.controller;

import by.paprauka.database.dto.BookCreationDto;
import by.paprauka.database.dto.BookReadDto;
import by.paprauka.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.paprauka.web.util.PagesUtil.API;
import static by.paprauka.web.util.PagesUtil.BOOK;

@RestController
@RequestMapping(API + BOOK)
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookReadDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookReadDto> getBook(@PathVariable Long id) {
        return bookService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Long> createBook(@RequestBody BookCreationDto newBook) {
        return ResponseEntity.ok(bookService.create(newBook));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<BookReadDto> updateBook(@PathVariable Long id, @RequestBody BookCreationDto newBook) {
        return bookService.update(id, newBook)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

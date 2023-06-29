package by.paprauka.web.controller;


import by.paprauka.database.dto.BookCreationDto;
import by.paprauka.database.dto.BookFilter;
import by.paprauka.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static by.paprauka.web.util.PagesUtil.BOOK;

@Controller
@RequestMapping(BOOK)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String getAllBooksPage(Model model, BookFilter bookFilter) {
        model.addAttribute("books", bookService.getFindByFilter(bookFilter));
        return "books";
    }

    @GetMapping(path = "/{id}")
    public String getBookPage(Model model, @PathVariable Long id) {

        return bookService.getById(id).
                map(book -> {
                    model.addAttribute("book", book);
                    return "book";
                })
                .orElse("redirect:/book");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/create")
    public String createBookPage(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("authors", bookService.getAllAuthors());
        return "create-book";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/create")
    public String createBook(BookCreationDto book) {
        return "redirect:/book/" + bookService.create(book);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/{id}/update")
    public String updateBook(@PathVariable Long id, BookCreationDto book) {
        return bookService.update(id, book).map(
                        updatedBook -> "redirect:/book/" + id
                )
                .orElse("redirect:/book/?error=true");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/book";
    }
}

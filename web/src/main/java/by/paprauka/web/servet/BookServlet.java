package by.paprauka.web.servet;

import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.entity.enam.Genre;
import by.paprauka.service.BookService;
import by.paprauka.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("books", bookService.getFindByFilter(new BookFilter(
                    Integer.parseInt(req.getParameter("pages") != null ? req.getParameter("pages") : "1000"),
                    Genre.valueOf(req.getParameter("genre") != null ? req.getParameter("genre") : "CLASSIC"),
                    Integer.parseInt(req.getParameter("limit") != null ? req.getParameter("limit") : "100"),
                    Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1")
            )));
            req.getRequestDispatcher(PagesUtil.BOOKS).forward(req, resp);
        } else {
            redirectToBookPage(req, resp, bookService.getById(Long.parseLong(id)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String pages = req.getParameter("pages");
        String genre = req.getParameter("genre");
        BookEntity bookForCreation = BookEntity.builder()
                .title(title)
                .pages(Integer.parseInt(pages))
                .genre(Genre.valueOf(genre))
                .build();
        bookService.create(bookForCreation)
                .ifPresentOrElse(
                        book -> redirectToBookPage(req, resp, book),
                        () -> onFailedCreation(req, resp)
                );
        super.doPost(req, resp);
    }

    @SneakyThrows
    private static void redirectToBookPage(HttpServletRequest req, HttpServletResponse resp, BookEntity book) {
        req.setAttribute("book", book);
        req.getRequestDispatcher(PagesUtil.BOOK).forward(req, resp);
    }

    @SneakyThrows
    private static void onFailedCreation(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("error", true);
        req.getRequestDispatcher(PagesUtil.BOOK).forward(req, resp);
    }
}

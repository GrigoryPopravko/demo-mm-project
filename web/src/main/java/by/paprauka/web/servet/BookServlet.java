package by.paprauka.web.servet;

import by.paprauka.service.BookService;
import by.paprauka.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("books", bookService.getAll());
            req.getRequestDispatcher(PagesUtil.BOOKS).forward(req, resp);
        } else {
            req.setAttribute("book", bookService.getById(Long.getLong(id)));
            req.getRequestDispatcher(PagesUtil.BOOK).forward(req, resp);
        }
    }
}

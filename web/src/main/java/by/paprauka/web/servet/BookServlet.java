package by.paprauka.web.servet;

import by.paprauka.config.ServiceConfig;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.entity.enam.Genre;
import by.paprauka.service.BookService;
import by.paprauka.web.util.PagesUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ApplicationContext ac = new AnnotationConfigApplicationContext(ServiceConfig.class);

        config.getServletContext().setAttribute("applicationContext", ac);
        bookService = ac.getBean(BookService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
//            req.setAttribute("books", bookService.getFindByFilter(
//                            BookFilter.builder()
//                                    .title(req.getParameter("title"))
//                                    .pagesAmount(Integer.parseInt(req.getParameter("pages")))
//                                    .genre(Genre.valueOf(req.getParameter("genre")))
//                                    .authorName(req.getParameter("authorName"))
//                                    .limit(Integer.parseInt(req.getParameter("limit")))
//                                    .page(Integer.parseInt(req.getParameter("page")))
//                                    .build()
//                    )
//            );
            req.getRequestDispatcher(PagesUtil.BOOKS).forward(req, resp);
        } else {
            redirectToBookPage(req, resp, bookService.findById(Long.parseLong(id)));
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

        redirectToBookPage(req, resp, bookService.create(bookForCreation));
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

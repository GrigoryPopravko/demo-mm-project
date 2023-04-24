package by.paprauka.web.servet;

import by.paprauka.database.entity.User;
import by.paprauka.service.UserService;
import by.paprauka.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<User> saved = userService.save(
                User.builder()
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .name(req.getParameter("name"))
                        .surname(req.getParameter("surname"))
                        .build());
        resp.sendRedirect("/login");
    }
}

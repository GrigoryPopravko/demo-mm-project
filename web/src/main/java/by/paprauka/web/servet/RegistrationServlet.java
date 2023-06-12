package by.paprauka.web.servet;

import by.paprauka.database.entity.UserEntity;
import by.paprauka.service.UserService;
import by.paprauka.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@WebServlet("/registration")
@RequiredArgsConstructor
public class RegistrationServlet extends HttpServlet {

    private final UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserEntity saved = userService.save(
                UserEntity.builder()
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .build());
        resp.sendRedirect("/login");
    }
}

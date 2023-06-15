package by.paprauka.web.servet;

import by.paprauka.database.entity.UserEntity;
import by.paprauka.service.UserService;
import by.paprauka.web.util.PagesUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PagesUtil.REGISTRATION).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute("applicationContext");
        UserService userService = context.getBean(UserService.class);
        UserEntity saved = userService.save(
                UserEntity.builder()
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .build());
        resp.sendRedirect("/login");
    }
}

package by.paprauka.web.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

    private static final String UNIQ = "uniq";
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null && Arrays.stream(req.getCookies()).anyMatch(cookie -> cookie.getName().equals(UNIQ))) {
                writer.write("Hello buddy!!");
            } else {
                resp.addCookie(new Cookie(UNIQ, UUID.randomUUID().toString()));
                writer.write("Hello newbie you are " + counter.incrementAndGet());
            }
        }
    }
}

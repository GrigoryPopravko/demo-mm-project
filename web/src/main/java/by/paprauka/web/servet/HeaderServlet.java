package by.paprauka.web.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            String userAgent = req.getHeader("User-Agent");
            if (userAgent.contains("Chrome")){
                writer.write("Hello Chrome User");
            } else if (userAgent.contains("Firefox")){
                writer.write("Hello Firefox USer");
            } else {
                writer.write("400 You are using an unsafe browser");
            }
        }
    }
}

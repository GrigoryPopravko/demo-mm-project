package by.paprauka.web.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.*;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.*;

@WebServlet("/body")
public class BodyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");

            if (isNull(name) || isNull(surname)) {
                writer.write("400 Client Error");
            } else {
                writer.write("Hello " + name + " " + surname);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader reader = req.getReader();
             PrintWriter writer = resp.getWriter()) {
            List<String> data = reader.lines()
                    .collect(toList());

            if (data.size() < 2) {
                writer.write("400 Client Error");
            } else {
                writer.write("Hello " + data.get(0) + " " + data.get(1));
            }
        }
    }
}

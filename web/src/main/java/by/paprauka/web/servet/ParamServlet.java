package by.paprauka.web.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/param")
public class ParamServlet extends HttpServlet {

    // http://localhost:8080/param?color=red&color=green&type=sedan&lowerPrice=3000
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String singleParam = req.getParameter("type");

        String[] colors = req.getParameterValues("color");

        Map<String, String[]> parameterMap = req.getParameterMap();

        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String parameter = req.getParameter(name);
        }

        super.doGet(req, resp);
    }
}

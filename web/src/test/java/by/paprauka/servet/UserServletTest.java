package by.paprauka.servet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserServletTest {

    @Test
    public void getUser() {
        UserServlet servlet = new UserServlet();
        assertEquals("Hello Mr. Bob", servlet.getUser());
    }
}
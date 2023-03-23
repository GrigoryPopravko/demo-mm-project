package by.paprauka.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    @Test
    public void getUser() {
        UserService userService = UserService.getInstance();
        assertEquals("Hello Mr. Bob", userService.getUser());
    }
}
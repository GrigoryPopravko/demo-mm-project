package by.paprauka.servet;

import by.paprauka.service.UserService;

public class UserServlet {

    private final UserService userService = UserService.getInstance();

    public String getUser() {
        return userService.getUser();
    }
}

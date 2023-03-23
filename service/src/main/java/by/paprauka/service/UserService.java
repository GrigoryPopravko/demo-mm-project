package by.paprauka.service;

import by.paprauka.dao.UserDao;
import by.paprauka.entity.User;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    public String getUser() {
        User dummyUser = userDao.getDummy();
        if (dummyUser.isMale()) {
            return "Hello Mr. ".concat(dummyUser.getName());
        } else {
            return "Hello Mrs. ".concat(dummyUser.getName());
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}

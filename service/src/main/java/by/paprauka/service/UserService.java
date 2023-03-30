package by.paprauka.service;

import by.paprauka.database.dao.UserDao;
import by.paprauka.database.entity.User;

public final class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    public String getUser() {
        User dummyUser = userDao.getDummy();
        if (true) {
            return "Hello Mr. ".concat(dummyUser.getName());
        } else {
            return "Hello Mrs. ".concat(dummyUser.getName());
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}

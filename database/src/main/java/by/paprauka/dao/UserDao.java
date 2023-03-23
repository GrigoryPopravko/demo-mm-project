package by.paprauka.dao;

import by.paprauka.entity.User;

public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    public User getDummy() {
        return new User("Bob", "Smith");
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}

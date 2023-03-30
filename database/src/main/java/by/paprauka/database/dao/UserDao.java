package by.paprauka.database.dao;

import by.paprauka.database.entity.User;

public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
    }

    public User getDummy() {
        return User.builder()
                .name("Bob")
                .surname("Smith")
                .build();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}

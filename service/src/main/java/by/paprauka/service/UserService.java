package by.paprauka.service;

import by.paprauka.database.dao.UserDao;
import by.paprauka.database.entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();



    public static UserService getInstance() {
        return INSTANCE;
    }
}

package by.paprauka.service;

import by.paprauka.database.dao.UserDao;
import by.paprauka.database.entity.User;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    public Optional<User> getBy(String email, String password) {
        return userDao.getByEmailAndPass(email, password);
    }

    public Optional<User> save(User user) {
        return userDao.create(user);
    }


    public static UserService getInstance() {
        return INSTANCE;
    }
}

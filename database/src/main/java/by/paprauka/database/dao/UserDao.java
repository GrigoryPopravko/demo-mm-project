package by.paprauka.database.dao;

import by.paprauka.database.entity.UserEntity;
import org.hibernate.Session;

import java.util.Optional;

public final class UserDao extends Dao<Long, UserEntity> {

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
        super(UserEntity.class);
    }

    public Optional<UserEntity> getByEmailAndPass(Session session, String email, String password) {
        return session
                .createQuery("SELECT user FROM UserEntity user "
                             + "WHERE email = :email AND password = :password", UserEntity.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResultOptional();

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}

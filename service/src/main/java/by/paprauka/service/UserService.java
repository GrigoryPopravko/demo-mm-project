package by.paprauka.service;

import by.paprauka.database.dao.UserDao;
import by.paprauka.database.entity.UserEntity;
import by.paprauka.database.hibernate.HibernateFactory;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();
    private final HibernateFactory hibernateFactory = HibernateFactory.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    public Optional<UserEntity> getBy(String email, String password) {
        Optional<UserEntity> user;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            user = userDao.getByEmailAndPass(session, email, password);
            transaction.commit();
        }
        return user;
    }

    public Optional<UserEntity> save(UserEntity user) {
        Optional<UserEntity> newUser;
        try (Session session = hibernateFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            newUser = userDao.create(session, user);
            transaction.commit();
        }
        return newUser;
    }


    public static UserService getInstance() {
        return INSTANCE;
    }
}

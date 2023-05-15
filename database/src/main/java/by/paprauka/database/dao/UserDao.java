package by.paprauka.database.dao;

import by.paprauka.database.entity.UserEntity;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();


    public Optional<UserEntity> getByEmailAndPass(Session session, String email, String password) {
        return session
                .createQuery("SELECT user FROM UserEntity user "
                             + "WHERE email = :email AND password = :password", UserEntity.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResultOptional();

    }

    public List<UserEntity> findAll(Session session) {
        return session.createQuery("SELECT user FROM UserEntity user", UserEntity.class).list();
    }

    public Optional<UserEntity> findById(Session session, Long id) {
        return Optional.ofNullable(session.get(UserEntity.class, id));
    }

    public Optional<UserEntity> create(Session session, UserEntity user) {
        session.persist(user);
        return Optional.ofNullable(user);
    }

    public Optional<UserEntity> update(Session session, UserEntity user) {
        session.merge(user);
        return Optional.ofNullable(user);
    }

    public boolean delete(Session session, Long id) {
        return Optional.ofNullable(session.get(UserEntity.class, id))
                .map(user -> {
                    session.remove(user);
                    return true;
                })
                .orElse(false);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}

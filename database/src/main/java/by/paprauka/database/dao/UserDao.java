package by.paprauka.database.dao;

import by.paprauka.database.entity.User;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();


    public Optional<User> getByEmailAndPass(String email, String password) {
        return Optional.of(null);
    }

    public List<User> getAll() {
        return new ArrayList<>();
    }

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(null);
    }

    public User create(User user) {
        return User.builder().build();
    }

    public User delete(Long id) {
        return Optional.ofNullable(User.builder().build())
                .orElseThrow(RuntimeException::new);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}

package by.paprauka.database.dao;

import by.paprauka.database.connection.ConnectionPool;
import by.paprauka.database.entity.User;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SELECT_BY_EMAIL_PASS = "SELECT * FROM users WHERE email =? AND password = ?";

    private static final String INSERT = "INSERT INTO users (name, surname, email, password, date) VALUES (?,?,?,?,?)";


    public Optional<User> getByEmailAndPass(String email, String password) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_PASS)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(User.builder()
                    .id(resultSet.getLong("id"))
                    .email(resultSet.getString("email"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .build())
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<User> getAll() {
        return new ArrayList<>();
    }

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(null);
    }

    public Optional<User> create(User user) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDate(5, Date.valueOf(user.getDate().toString()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public User delete(Long id) {
        return Optional.ofNullable(User.builder().build())
                .orElseThrow(RuntimeException::new);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}

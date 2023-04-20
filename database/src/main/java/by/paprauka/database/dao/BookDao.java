package by.paprauka.database.dao;

import by.paprauka.database.connection.ConnectionManager;
import by.paprauka.database.entity.Book;
import by.paprauka.database.entity.enam.Genre;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class BookDao {

    private static final BookDao INSTANCE = new BookDao();
    private static final String SELECT_ALL = "SELECT * FROM book";
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id =?";
    private static final String INSERT = "INSERT INTO book (title, pages, genre) VALUES (?,?,?)";


    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                books.add(Book.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .pages(resultSet.getInt("pages"))
                        .genre(Genre.valueOf(resultSet.getString("genre")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Optional<Book> findById(Long id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(Book.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .pages(resultSet.getInt("pages"))
                    .genre(Genre.valueOf(resultSet.getString("genre")))
                    .build())
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Book create(Book book) throws SQLException {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getPages());
            preparedStatement.setString(3, book.getGenre().name());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getLong("id"));
            }
            return book;
        }
    }


    public static BookDao getInstance() {
        return INSTANCE;
    }
}

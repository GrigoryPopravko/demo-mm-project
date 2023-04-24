package by.paprauka.database.dao;

import by.paprauka.database.connection.ConnectionPool;
import by.paprauka.database.dto.BookFilter;
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
    private static final String DELETE_BY_ID = "DELETE FROM book WHERE id =?";

    private static final String UPDATE = "UPDATE book SET title = ?, pages = ?, genre = ? WHERE id = ?";
    private static final String SELECT_BY = "SELECT * FROM book WHERE pages < ? AND genre = ? LIMIT ? OFFSET ?";


    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
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
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(Book.builder()
                    .id(resultSet.getLong("id"))
                    .pages(resultSet.getInt("pages"))
                    .genre(Genre.valueOf(resultSet.getString("genre")))
                    .build())
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Book> findByFilter(BookFilter filter) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY)) {
            preparedStatement.setInt(1, filter.pages());
            preparedStatement.setString(2, filter.genre().name());
            preparedStatement.setInt(3, filter.limit());
            preparedStatement.setInt(4, filter.limit() * (filter.page() - 1));
            ResultSet resultSet = preparedStatement.executeQuery();
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

    public Optional<Book> create(Book book) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getPages());
            preparedStatement.setString(3, book.getGenre().name());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getLong("id"));
            }
            return Optional.of(book);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Book> update(Book book) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getPages());
            preparedStatement.setString(3, book.getGenre().name());
            preparedStatement.setLong(4, book.getId());
            preparedStatement.executeUpdate();
            return Optional.of(book);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static BookDao getInstance() {
        return INSTANCE;
    }
}

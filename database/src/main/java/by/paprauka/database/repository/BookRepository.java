package by.paprauka.database.repository;

import by.paprauka.database.entity.AuthorEntity;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.entity.enam.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByTitleAndAndGenre(String title, Genre genre);

    List<BookEntity> findAllByGenre(Genre genre);

    List<BookEntity> findAllByAuthorsContains(AuthorEntity author);
}

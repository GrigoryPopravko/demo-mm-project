package by.paprauka.database.repository;

import by.paprauka.database.entity.AuthorEntity;
import by.paprauka.database.entity.BookEntity;
import by.paprauka.database.entity.enam.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>, BookRepositoryExtension {

    Optional<BookEntity> findByTitle(String title);

    Optional<BookEntity> findByTitleAndAndGenre(String title, Genre genre);

    Page<BookEntity> findAllByGenre(Genre genre, Pageable pageable);

    List<BookEntity> findAllByAuthorsContains(AuthorEntity author);

    @Query(value = "SELECT b FROM BookEntity b WHERE b.title LIKE %:title% AND b.pages < :pages")
    List<BookEntity> findAllBy(@Param("title") String title,
                               @Param("pages") Integer pages);

    @Query(value = "SELECT b.* FROM book b " +
            "JOIN book_author ba ON b.id = ba.book_id " +
            "JOIN author a ON a.id = ba.author_id " +
            "WHERE a.full_name = ?", nativeQuery = true)
    List<BookEntity> findAllBy(String authorName);

    @Modifying
    @Query("UPDATE BookEntity b SET b.title = :title WHERE b.id = :id")
    void setTitleById(@Param("title") String title, @Param("id") Long id);
}

package by.paprauka.database.repository;

import by.paprauka.database.dto.BookFilter;
import by.paprauka.database.entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryExtension {

    List<BookEntity> findByFilter(BookFilter filter);
}

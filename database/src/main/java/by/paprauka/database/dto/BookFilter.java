package by.paprauka.database.dto;

import by.paprauka.database.entity.enam.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookFilter {
    private int pages;
    private Genre genre;
    private int limit;
    private int page;
}

package by.paprauka.database.dto;

import by.paprauka.database.entity.enam.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReadDto {
    private Long id;
    private String title;
    private Integer pages;
    private Genre genre;
    private List<String> authors;
}

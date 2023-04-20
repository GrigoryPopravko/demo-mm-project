package by.paprauka.database.entity;

import by.paprauka.database.entity.enam.Genre;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Book {

    private Long id;
    private String title;
    private Integer pages;
    private Genre genre;
    @Builder.Default
    private List<Author> authors = new ArrayList<>();
}

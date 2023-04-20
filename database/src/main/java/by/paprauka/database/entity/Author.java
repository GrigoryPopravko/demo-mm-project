package by.paprauka.database.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Author {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birth;
    private LocalDate death;
    @Builder.Default
    List<Book> books = new ArrayList<>();
}

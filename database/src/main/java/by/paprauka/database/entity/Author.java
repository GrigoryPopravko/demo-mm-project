package by.paprauka.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Author {
    private Long id;
    private String fullName;
    private LocalDate birth;
    private String origin;
    @Builder.Default
    private List<Book> books = new ArrayList<>();
}

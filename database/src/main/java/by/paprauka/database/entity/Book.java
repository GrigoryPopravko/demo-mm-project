package by.paprauka.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class Book {

    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private Double price;
}

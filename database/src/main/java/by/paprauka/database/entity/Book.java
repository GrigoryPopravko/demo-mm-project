package by.paprauka.database.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Book {

    private Long id;
    private String title;
    private Author author;
    private Integer pages;
    private Double price;
    private String description;
}

package by.paprauka.database.entity;

import lombok.Builder;

@Builder
public class Book {

    private String name;
    private String author;
    private Integer pages;
    private Boolean isSolid;
    private Double price;
}

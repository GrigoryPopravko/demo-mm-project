package by.paprauka.database.dto;

import by.paprauka.database.entity.enam.Genre;

import java.util.List;

public record BookCreationDto(String title, Integer pages, Genre genre, List<Long> authorsIds) {
}

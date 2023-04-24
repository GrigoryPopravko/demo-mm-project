package by.paprauka.database.dto;

import by.paprauka.database.entity.enam.Genre;

public record BookFilter(int pages, Genre genre, int limit, int page) {
}

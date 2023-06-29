package by.paprauka.database.dto;

import by.paprauka.database.entity.enam.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreationDto {
    String title;
    Integer pages;
    Genre genre;
    List<Long> authorsIds = new ArrayList<>();
}

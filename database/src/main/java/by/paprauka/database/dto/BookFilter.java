package by.paprauka.database.dto;

import by.paprauka.database.entity.enam.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookFilter {

    private String title;
    private Integer pagesAmount;
    private Genre genre;
    private String authorName;
    private Integer limit;
    private Integer page;

    public Integer getLimit() {
        return limit == null ? 10 : limit;
    }

    public Integer getOffset() {
        return page == null ? 0 : limit * (page - 1);
    }
}

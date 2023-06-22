package by.paprauka.database.entity;

import by.paprauka.database.entity.enam.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "authors")
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class BookEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "pages")
    private Integer pages;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 10, nullable = false)
    private Genre genre;

    @Builder.Default
    @ManyToMany(mappedBy = "books", cascade = CascadeType.REMOVE)
    private List<AuthorEntity> authors = new ArrayList<>();

    public void addAuthor(AuthorEntity author) {
        this.getAuthors().add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(AuthorEntity author) {
        this.getAuthors().remove(author);
        author.getBooks().remove(this);
    }
}

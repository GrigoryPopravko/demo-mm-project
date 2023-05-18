package by.paprauka.database;

import by.paprauka.database.entity.AuthorEntity;
import by.paprauka.database.entity.BookEntity;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import java.time.LocalDate;

import static by.paprauka.database.entity.enam.Genre.CLASSIC;
import static by.paprauka.database.entity.enam.Genre.FICTION;

@UtilityClass
public class TestDataImporter {

    public void importTestData(Session session) {
        AuthorEntity tolstoi = AuthorEntity.builder()
                .fullName("Leo Tolstoi")
                .birth(LocalDate.of(1828, 9, 9))
                .origin("Russian")
                .build();

        session.persist(tolstoi);

        var annaKarenina = BookEntity.builder()
                .title("Anna Karenina")
                .pages(1000)
                .genre(CLASSIC)
                .build();

        var madameBovary = BookEntity.builder()
                .title("Madame Bovary")
                .pages(700)
                .genre(FICTION)
                .build();

        var warAndPeace = BookEntity.builder()
                .title("War and Peace")
                .pages(2000)
                .genre(CLASSIC)
                .build();

        var theGreatGatsby = BookEntity.builder()
                .title("The Great Gatsby")
                .pages(800)
                .genre(CLASSIC)
                .build();

        annaKarenina.addAuthor(tolstoi);
        warAndPeace.addAuthor(tolstoi);

        session.persist(annaKarenina);
        session.persist(madameBovary);
        session.persist(warAndPeace);
        session.persist(theGreatGatsby);
    }
}

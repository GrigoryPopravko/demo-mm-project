package by.paprauka.database;

import by.paprauka.database.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class DummyDatabase {

    private static final DummyDatabase INSTANCE = new DummyDatabase();

    private final Map<Long, Book> books = new HashMap<>() {{
        put(1L, Book.builder()
                .id(1L)
                .title("Crime and Punishment")
                .author("Dostoevskiy")
                .pages(300)
                .price(50.0)
                .build());
        put(2L, Book.builder()
                .id(2L)
                .title("Metrvym ne bolit")
                .author("Bykau")
                .pages(320)
                .price(32.5)
                .build());
    }};

    public static DummyDatabase getInstance() {
        return INSTANCE;
    }

}

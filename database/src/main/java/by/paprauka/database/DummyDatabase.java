package by.paprauka.database;

import by.paprauka.database.entity.Book;
import by.paprauka.database.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class DummyDatabase {

    private static final DummyDatabase INSTANCE = new DummyDatabase();
    public static final int PAGES = 300;
    public static final double PRICE = 50.0;
    public static final int PAGES_1 = 320;
    public static final double PRICE_1 = 32.5;

    private final Map<Long, Book> books = new HashMap<>() {{
        put(1L, Book.builder()
                .id(1L)
                .title("Crime and Punishment")
                .author("Dostoevskiy")
                .pages(PAGES)
                .price(PRICE)
                .description(
                        "Crime and Punishment is a novel by the Russian author Fyodor Dostoevsky. It was first published in the literary journal The Russian Messenger in twelve monthly installments during 1866. It was later published in a single volume."
                )
                .build());
        put(2L, Book.builder()
                .id(2L)
                .title("Metrvym ne bolit")
                .author("Bykau")
                .pages(PAGES_1)
                .price(PRICE_1)
                .description(
                        "Мёртвым не больно (белор. Мёртвым не баліць) — повесть Василя БыковаВпервые повесть опубликовал журнал «Маладосць», осенью 1965 года, а год спустя «Мёртвым не больно» напечатали в «Новом мире». После публикации «Огонек» и другие журналы напечатали разгромные статьи о его труде")
                .build());
    }};

    private final Map<Long, User> users = new HashMap<>() {{
        put(1L, User.builder()
                .id(1L)
                .name("Bob")
                .surname("Smith")
                .email("bobsmith@gmail.com")
                .password("bobsmith1985")
                .gender("MALE")
                .build());
        put(2L, User.builder()
                .id(2L)
                .name("Ann")
                .surname("Black")
                .email("annblack@gmail.com")
                .password("blackann")
                .gender("FEMALE")
                .build());
    }};

    public static DummyDatabase getInstance() {
        return INSTANCE;
    }

}

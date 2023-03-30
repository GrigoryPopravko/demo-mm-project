package by.paprauka.database.dao;

import by.paprauka.database.entity.Book;

public final class BookDao {

    private static final BookDao INSTANCE = new BookDao();
    public static final int PAGES = 400;
    public static final double PRICE = 20.4;

    private BookDao() {
    }

    public Book getDummy() {
        return Book.builder()
                .name("Punishement")
                .author("Dostoyevskii")
                .pages(PAGES)
                .isSolid(true)
                .price(PRICE)
                .build();
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }
}

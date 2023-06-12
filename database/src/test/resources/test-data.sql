INSERT INTO author(full_name)
VALUES ('Leo Tolstoi');

INSERT INTO book (genre, pages, title)
VALUES ('CLASSIC', 1000, 'Anna Karenina'),
       ('FICTION', 700, 'Madame Bovary'),
       ('CLASSIC', 2000, 'War and Peace'),
       ('CLASSIC', 800, 'The Great Gatsby');

INSERT INTO book_author (author_id, book_id)
SELECT a.id, b.id
FROM author a
         JOIN book b ON a.full_name = 'Leo Tolstoi' AND b.title = 'Anna Karenina';

INSERT INTO book_author (author_id, book_id)
SELECT a.id, b.id
FROM author a
         JOIN book b ON a.full_name = 'Leo Tolstoi' AND b.title = 'War and Peace';



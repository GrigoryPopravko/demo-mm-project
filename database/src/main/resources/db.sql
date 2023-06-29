DROP TABLE IF EXISTS passport;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    email      VARCHAR(50) UNIQUE NOT NULL,
    role       VARCHAR(10)        NOT NULL,
    password   VARCHAR(256)       NOT NULL,
    city       VARCHAR(30)        NULL,
    street     VARCHAR(30)        NULL,
    building   VARCHAR(10)        NULL,
    flat       VARCHAR(10)        NULL,
    tel        VARCHAR(10)        NULL,
    address    VARCHAR(50)        NULL,
    created_at DATE               NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE orders
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT NOT NULL REFERENCES users (id),
    price      INT    NOT NULL,
    created_at DATE   NOT NULL
);

CREATE TABLE passport
(
    user_id BIGINT REFERENCES users (id),
    number  VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE book
(
    id     BIGSERIAL PRIMARY KEY,
    title  VARCHAR(20) UNIQUE NOT NULL,
    format VARCHAR(3)         NULL,
    pages  INT                NULL,
    genre  VARCHAR(20)        NULL
);

CREATE TABLE author
(
    id        BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE book_author
(
    book_id   BIGINT REFERENCES book (id),
    author_id BIGINT REFERENCES author (id)
);

INSERT INTO book(title, genre, format, pages)
VALUES ('DEVILS', 'CLASSIC', 'MP3', '200')
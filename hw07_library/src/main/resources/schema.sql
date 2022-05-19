create table books
(
    id    bigserial,
    title varchar(255),
    primary key (id)
);
create table authors
(
    id      bigserial,
    name    varchar(255),
    surname varchar(255),
    primary key (id)
);
create table genres
(
    id   bigserial,
    name varchar(255),
    primary key (id)
);
create table comments
(
    id      bigserial,
    book_id bigint references books (id) on delete cascade,
    text    varchar(255),
    primary key (id)
);
create table book_author
(
    id        bigserial,
    book_id   bigint references books (id) on delete cascade,
    author_id bigint references authors (id) on delete cascade,
    primary key (id)
);
create table book_genre
(
    id       bigserial,
    book_id  bigint references books (id) on delete cascade,
    genre_id bigint references genres (id) on delete cascade,
    primary key (id)
);
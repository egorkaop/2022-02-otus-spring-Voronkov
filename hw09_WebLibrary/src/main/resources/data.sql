insert into books(title) values ('A song of ice and fire'),('it'),('test');
insert into genres (name) values ('Fantasy'),('horror');
insert into authors (name,surname) values ('Martin', 'George Raymond Richard'),('Stephen', 'King');
insert into comments (book_id,text) values (1,'Хорошая книга'),(1,'Супер книга'),(2,'Хорошая книга');
insert into book_author(book_id,author_id) values (1,1),(2,2),(3,1),(3,2);
insert into book_genre(book_id,genre_id) values (1,1),(2,2),(3,1),(3,2);

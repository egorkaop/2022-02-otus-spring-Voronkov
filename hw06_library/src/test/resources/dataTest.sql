insert into books(title) values ('A song of ice and fire');
insert into genres (name) values ('Fantasy');
insert into authors (name,surname) values ('Martin', 'George Raymond Richard');
insert into comments (book_id,text) values (1,'Хорошая книга'),(1,'Супер книга');
insert into book_author(book_id,author_id) values (1,1);
insert into book_genre(book_id,genre_id) values (1,1);
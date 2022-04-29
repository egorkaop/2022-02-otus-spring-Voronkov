insert into genres (name) values ('Fantasy'),('horror');
insert into authors (name,surname) values ('Martin', 'George Raymond Richard');
insert into books (title,author_id,genre_id) values ('A song of ice and fire',1,1);
insert into comments (book_id,text) values (1,'Хорошая книга');
insert into comments (book_id,text) values (1,'Супер книга');
insert into authors (name,surname) values ('Stephen', 'King');
-- insert into genres (name) values ('horror');
insert into books (title,author_id,genre_id) values ('it',2,2);
insert into comments (book_id,text) values (2,'Хорошая книга');

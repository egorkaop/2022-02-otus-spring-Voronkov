insert into books(title) values ('A song of ice and fire'),('it'),('test');
insert into genres (name) values ('Fantasy'),('horror');
insert into authors (name,surname) values ('Martin', 'George Raymond Richard'),('Stephen', 'King');
insert into book_author(book_id,author_id) values (1,1),(2,2),(3,1),(3,2);
insert into book_genre(book_id,genre_id) values (1,1),(2,2),(3,1),(3,2);
insert into users (username,password) values ('admin','$2a$10$jZ.WEqX8kxWTFaf7ggFZ1es725GPnGAa5HUXK9y9hjI8t/VZ1Uezq')
                                            ,('user','$2a$12$s1mq/aglNq5fKrhUoKnAGOVsFJrUt2YYVu1FCO5hQq.ysPD1/epeC');
insert into roles (name) values ('ROLE_ADMIN'),('ROLE_USER');
insert into user_role (user_id,role_id) values (1,1),(2,2);

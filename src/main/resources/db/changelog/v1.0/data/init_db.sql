delete from review;
delete from book;
delete from "user";

INSERT INTO public."user" (name, email, role)
VALUES ('Vasya','test1@mail', 'USER'),
       ('Petya','test2@mail', 'USER'),
       ('Ivan', 'test3@mail', 'ADMIN');


INSERT INTO public."book" (id, title, author, genre, publish_date)
VALUES (1,'Besy','Dostoyevsky', 'classic', '1872-01-01'),
       (2,'Solyaris','Lem', 'fantastic', '1961-01-01'),
       (3,'Taras Bulba', 'Gogol', 'classic', '1834-01-01'),
       (4,'book4', 'author4', 'genre4', '2000-01-01'),
       (5,'book5', 'author5', 'genre5', '2001-01-01');


INSERT INTO public."review" (text, review_date, rating, book_id)
VALUES ('text1','2021-05-06 10:00:00', 9, 1),
       ('text2','2013-05-06 12:00:00', 8, 1),
       ('text3','2010-05-07 12:00:00', 5, 1),
       ('text4','2018-05-12 12:00:00', 3, 2),
       ('text5','2019-06-12 12:00:00', 6, 2);

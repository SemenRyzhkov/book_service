delete from review;
delete from book;
delete from "user";

INSERT INTO "user" (name, password, email, role, status)
VALUES ('Vasya', '$2a$12$6UA18MZQ5H0UplulkiURWupu84peKvdIragYM5OHx2ZakOfC4ufTi','test1@mail', 'USER', 'ACTIVE'),
       ('Petya', '$2a$12$Y3.yAVh...2lf/CIv6IT6ua8r3gUKBynGAoo/7RuzzVe6c/QnBNme','test2@mail', 'USER','BANNED'),
       ('Ivan', '$2a$12$E8tif7sXJcZ5xDUosWLcEew.JOepm6Ea2ZAwYKHv9P1JdGgJLvvOW', 'test3@mail', 'ADMIN', 'ACTIVE');


INSERT INTO "book" (id, title, author, genre, publish_date)
VALUES (1,'Besy','Dostoyevsky', 'classic', '1872-01-01'),
       (2,'Solyaris','Lem', 'fantastic', '1961-01-01'),
       (3,'Taras Bulba', 'Gogol', 'classic', '1834-01-01'),
       (4,'book4', 'author4', 'genre4', '2000-01-01'),
       (5,'book5', 'author5', 'genre5', '2001-01-01');


INSERT INTO "review" (text, review_date, rating, book_id)
VALUES ('text1','2021-05-06 10:00:00', 9, 1),
       ('text2','2013-05-06 12:00:00', 8, 1),
       ('text3','2010-05-07 12:00:00', 5, 1),
       ('text4','2018-05-12 12:00:00', 3, 2),
       ('text5','2019-06-12 12:00:00', 6, 2);

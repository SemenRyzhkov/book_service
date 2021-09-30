delete from "user";


INSERT INTO "user" (name, password, email, role, status)
VALUES ('Vasya', '$2a$12$6UA18MZQ5H0UplulkiURWupu84peKvdIragYM5OHx2ZakOfC4ufTi','test1@mail', 'USER', 'ACTIVE'),
       ('Petya', '$2a$12$Y3.yAVh...2lf/CIv6IT6ua8r3gUKBynGAoo/7RuzzVe6c/QnBNme','test2@mail', 'USER','BANNED'),
       ('Ivan', '$2a$12$E8tif7sXJcZ5xDUosWLcEew.JOepm6Ea2ZAwYKHv9P1JdGgJLvvOW', 'test3@mail', 'ADMIN', 'ACTIVE');
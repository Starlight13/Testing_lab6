drop table if exists users cascade;
create table users(
                      id serial primary key,
                      name varchar(40),
                      email varchar(240),
                      phone_number varchar(13),
                      password varchar(40)
);

insert into users(name, email, phone_number, password) values ('Test1', 'test1@gmail.com', '+380673800171', 'Test1Test');
insert into users(name, email, phone_number, password) values ('Test2', 'test2@gmail.com', '+380673800172', 'Test2Test');
insert into users(name, email, phone_number, password) values ('Test3', 'test3@gmail.com', '+380673800173', 'Test3Test');
insert into users(name, email, phone_number, password) values ('Test4', 'test4@gmail.com', '+380673800174', 'Test4Test');
insert into users(name, email, phone_number, password) values ('Test5', 'test5@gmail.com', '+380673800175', 'Test5Test');
insert into users(name, email, phone_number, password) values ('Test6', 'test6@gmail.com', '+380673800176', 'Test6Test');
insert into users(name, email, phone_number, password) values ('Test7', 'test7@gmail.com', '+380673800177', 'Test7Test');
insert into users(name, email, phone_number, password) values ('Test8', 'test8@gmail.com', '+380673800178', 'Test8Test');
insert into users(name, email, phone_number, password) values ('Test9', 'test9@gmail.com', '+380673800179', 'Test9Test');

drop table if exists songs cascade;
create table songs(
                      id serial primary key,
                      title varchar(40),
                      artist varchar(50),
                      duration_sec int,
                      genre_id int
);

insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 1', 'Artist 1',111, 1);
insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 2', 'Artist 2',222, 1);
insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 3', 'Artist 3',333, 1);
insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 4', 'Artist 4',444, 2);
insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 5', 'Artist 5',555, 2);
insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 6', 'Artist 6',666, 2);
insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 7', 'Artist 7',777, 3);
insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 8', 'Artist 8',888, 3);
insert into songs(title, artist, duration_sec, genre_id) values ('Test Song 9', 'Artist 9',999, 3);

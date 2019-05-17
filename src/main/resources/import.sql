-- this init data file should be only enable for test environment not in production
use management;

insert into `user`(username, password, state) values('test', '$2a$10$QY2s4iOMaoLUYo6TW9lR/OCkeji9.kCx0.SkiTrqeUthIHY50bkGS', 'live');
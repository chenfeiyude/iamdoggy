-- this init data file should be only enable for test environment not in production
use management;

insert into `user`(username, password, state) values('test', '$2a$10$L3BM4HBpHbe1W.u83ifd7uLwnH.oPcvKHyjyFVO10FsfXpuR0h/N6', 'live');
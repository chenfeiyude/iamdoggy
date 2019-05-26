-- this init data file should be only enable for test environment not in production
use management;

insert into `user`(uid, username, password, state) values(123, 'feiyu', '$2a$10$L3BM4HBpHbe1W.u83ifd7uLwnH.oPcvKHyjyFVO10FsfXpuR0h/N6', 'live');

insert into account(uid, credit) values(123, 100000);

insert into command_configure(command, activity, difficulty) values ('wait', 'waiting', 0.5);
insert into command_configure(command, activity, difficulty) values ('sit', 'sitting', 0.7);
insert into command_configure(command, activity, difficulty) values ('go', 'release', 0.8);
insert into command_configure(command, activity, difficulty) values ('stop', 'stop', 0.5);

insert into pet_breed_configure(`type`, breed, rarity, cost) values (0, 'corgi', 0.05, 1000);
insert into pet_breed_configure(`type`, breed, rarity, cost) values (0, 'husky', 0.5, 300);

insert into event_configure(`type`, description, possibility) values (0, 'is illed', 0.05);
insert into event_configure(`type`, description, possibility) values (1, 'is hungried', 0.10);
insert into event_configure(`type`, description, possibility) values (2, 'poo every where', 0.1);
insert into event_configure(`type`, description, possibility) values (3, 'pee every where', 0.1);
insert into event_configure(`type`, description, possibility) values (4, 'fight with other dogs', 0.05);
insert into event_configure(`type`, description, possibility) values (5, 'is sleeping', 0.20);
insert into event_configure(`type`, description, possibility) values (6, 'is playing toys', 0.15);
insert into event_configure(`type`, description, possibility) values (7, 'is relaxing', 0.25);


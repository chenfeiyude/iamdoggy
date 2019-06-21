CREATE USER 'iamdoggy'@'localhost' IDENTIFIED BY 'iamdoggy';

CREATE DATABASE management;
CREATE DATABASE doggy;

GRANT ALL ON management.* TO 'iamdoggy'@'localhost';
GRANT ALL ON doggy.* TO 'iamdoggy'@'localhost';
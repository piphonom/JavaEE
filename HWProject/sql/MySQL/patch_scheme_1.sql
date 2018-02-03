USE Rik;

ALTER TABLE `User` ADD `salt` varchar(45) DEAFULT NULL;
ALTER TABLE `User` ADD `pwdHash` varchar(400) DEFAULT NULL;
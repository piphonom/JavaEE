USE Rik;

ALTER TABLE `User` ADD `salt` varchar(45) DEFAULT NULL;
ALTER TABLE `User` ADD `pwdHash` varchar(400) DEFAULT NULL;
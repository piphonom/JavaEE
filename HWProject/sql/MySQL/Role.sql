USE `Rik`

CREATE TABLE `Role` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE KEY `uk_Role` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
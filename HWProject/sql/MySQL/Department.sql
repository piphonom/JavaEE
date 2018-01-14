USE `Rik`

CREATE TABLE `Department` (
  `idDepartment` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDepartment`),
  UNIQUE KEY `uk_Department` (`name`,`location`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
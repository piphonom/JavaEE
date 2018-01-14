USE `Rik`

CREATE TABLE `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `departmentRef` int(11) DEFAULT NULL,
  `positionRef` int(11) DEFAULT NULL,
  `roleRef` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `uk_Name` (`name`),
  UNIQUE KEY `uk_Email` (`email`),
  KEY `fk_User_name_idx` (`name`),
  KEY `fk_User_email_idx` (`email`),
  CONSTRAINT `fk_department` FOREIGN KEY (`departmentRef`) REFERENCES `Department` (`idDepartment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_position` FOREIGN KEY (`positionRef`) REFERENCES `Position` (`idPosition`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role` FOREIGN KEY (`roleRef`) REFERENCES `Role` (`idRole`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
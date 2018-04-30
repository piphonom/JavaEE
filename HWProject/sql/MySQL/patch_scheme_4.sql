USE `Rik`

ALTER TABLE `User` DROP FOREIGN KEY `fk_role`;
ALTER TABLE `User` DROP COLUMN `roleRef`;

CREATE TABLE `UserRoles` (
  `idUser` int(11) NOT NULL,
  `idRole` int(11) NOT NULL,
  PRIMARY KEY (`idUser`,`idRole`),
  KEY `idRole` (`idRole`),
  CONSTRAINT `idUser_ibfk_1` 
  FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`),
  CONSTRAINT `idRole_ibfk_2` 
  FOREIGN KEY (`idRole`) REFERENCES `Role` (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

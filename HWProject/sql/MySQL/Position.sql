USE `Rik`

CREATE TABLE `Position` (
  `idPosition` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPosition`),
  UNIQUE KEY `uk_Title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
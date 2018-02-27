use Rik;

CREATE TABLE `Statistics` (
  `idStat` int(11) NOT NULL AUTO_INCREMENT,
  `markerName` varchar(50) DEFAULT NULL,
  `pageName` varchar(50) DEFAULT NULL,
  `clientIP` varchar(15) DEFAULT NULL,
  `clientName` varchar(50) DEFAULT NULL,
  `clientTime` varchar(50) DEFAULT NULL,
  `serverTime` varchar(50) DEFAULT NULL,
  `prevIdStat` int(11) DEFAULT NULL,
  `origin` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idStat`),
  UNIQUE KEY `uk_prevIdStat` (`prevIdStat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
use Rik;

CREATE TABLE `InvocationStatistics` (
  `idStat` int(11) NOT NULL AUTO_INCREMENT,
  `methodName` text DEFAULT NULL,
  `invocationTime` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
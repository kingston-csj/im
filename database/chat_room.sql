DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(64) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

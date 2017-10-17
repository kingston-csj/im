/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : chat_room

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-05 21:10:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for friendgroup
-- ----------------------------
DROP TABLE IF EXISTS `friendgroup`;
CREATE TABLE `friendgroup` (
  `id` bigint(255) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_pk` (`userId`),
  CONSTRAINT `user_pk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friendgroup
-- ----------------------------

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` int(11) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `friendid` bigint(20) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `groupid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid_fk` (`userid`),
  KEY `friend_fk` (`friendid`),
  KEY `group_fk` (`groupid`),
  CONSTRAINT `friend_fk` FOREIGN KEY (`friendid`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_fk` FOREIGN KEY (`groupid`) REFERENCES `friendgroup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userid_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` bigint(20) NOT NULL,
  `userName` varchar(128) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` tinyint(255) DEFAULT NULL,
  `signature` varchar(512) DEFAULT NULL,
  `birthday` varchar(32) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

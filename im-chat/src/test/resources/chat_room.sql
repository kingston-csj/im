/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost:3306
 Source Schema         : chat_room

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 19/04/2021 21:20:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
BEGIN;
INSERT INTO `friendgroup` VALUES (1, '我的好友', 1000);
INSERT INTO `friendgroup` VALUES (2, '程序猿展览馆', 1000);
INSERT INTO `friendgroup` VALUES (3, '默认好友', 1001);
COMMIT;

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
BEGIN;
INSERT INTO `friends` VALUES (1, 1000, 1001, '大师兄', 1);
INSERT INTO `friends` VALUES (2, 1000, 1002, '二师兄', 1);
INSERT INTO `friends` VALUES (3, 1000, 1003, '三师兄', 1);
INSERT INTO `friends` VALUES (4, 1000, 1004, '隔壁老王', 2);
INSERT INTO `friends` VALUES (5, 1000, 1005, 'bug砖家', 2);
INSERT INTO `friends` VALUES (6, 1001, 1000, NULL, 3);
COMMIT;

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
BEGIN;
INSERT INTO `user` VALUES (1000, 'kinsn', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '专业写Bug', NULL, NULL);
INSERT INTO `user` VALUES (1001, 'hello', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '我爱Java', NULL, NULL);
INSERT INTO `user` VALUES (1002, 'world', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 0, '我爱JavaFX', NULL, NULL);
INSERT INTO `user` VALUES (1003, '屌丝', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '我不做游戏，我在做一个付费系统', NULL, NULL);
INSERT INTO `user` VALUES (1004, '我心飞扬', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '把你放在 我心、最柔软的 地方', NULL, NULL);
INSERT INTO `user` VALUES (1005, '金刚互撸娃', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '写代码，我很快乐~', NULL, NULL);
INSERT INTO `user` VALUES (2021, '2021', '$apr1$2021$2g52Q2tQTm6ODFxeZA6jL0', 0, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : im

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 13/04/2024 17:54:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friendgroup
-- ----------------------------
DROP TABLE IF EXISTS `friendgroup`;
CREATE TABLE `friendgroup`  (
  `id` bigint(0) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `userId` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_pk`(`userId`) USING BTREE,
  CONSTRAINT `user_pk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friendgroup
-- ----------------------------
INSERT INTO `friendgroup` VALUES (1, '我的好友', 1000);
INSERT INTO `friendgroup` VALUES (2, '程序猿展览馆', 1000);
INSERT INTO `friendgroup` VALUES (3, '默认好友', 1001);

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `id` int(0) NOT NULL,
  `userid` bigint(0) NOT NULL,
  `friendid` bigint(0) NULL DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `groupid` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid_fk`(`userid`) USING BTREE,
  INDEX `friend_fk`(`friendid`) USING BTREE,
  INDEX `group_fk`(`groupid`) USING BTREE,
  CONSTRAINT `friend_fk` FOREIGN KEY (`friendid`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `group_fk` FOREIGN KEY (`groupid`) REFERENCES `friendgroup` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userid_fk` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES (1, 1000, 1001, '大师兄', 1);
INSERT INTO `friends` VALUES (2, 1000, 1002, '二师兄', 1);
INSERT INTO `friends` VALUES (3, 1000, 1003, '三师兄', 1);
INSERT INTO `friends` VALUES (4, 1000, 1004, '隔壁老王', 2);
INSERT INTO `friends` VALUES (5, 1000, 1005, 'bug砖家', 2);
INSERT INTO `friends` VALUES (6, 1001, 1000, NULL, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` bigint(0) NOT NULL,
  `userName` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sex` tinyint(0) NULL DEFAULT NULL,
  `signature` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `birthday` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1000, 'jforgame', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '专业做游戏一百年', NULL, NULL);
INSERT INTO `user` VALUES (1001, 'hello', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '我爱Java', NULL, NULL);
INSERT INTO `user` VALUES (1002, 'world', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 0, '我爱JavaFX', NULL, NULL);
INSERT INTO `user` VALUES (1003, '屌丝', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '我不做游戏，我在做一个付费系统', NULL, NULL);
INSERT INTO `user` VALUES (1004, '我心飞扬', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '把你放在 我心、最柔软的 地方', NULL, NULL);
INSERT INTO `user` VALUES (1005, '金刚互撸娃', '$apr1$1000$2DYl/guO0EHY9CRoXROrE0', 1, '写代码，我很快乐~', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

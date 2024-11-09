/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : localhost:3306
 Source Schema         : im

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 08/11/2024 19:07:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for discussion
-- ----------------------------
DROP TABLE IF EXISTS `discussion`;
CREATE TABLE `discussion`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `creatorId` bigint(20) NULL DEFAULT NULL,
  `createdDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discussion
-- ----------------------------
INSERT INTO `discussion` VALUES (1, 'jforgame讨论组', 1000, '2024-11-09 11:17:48');

-- ----------------------------
-- Table structure for discussionmember
-- ----------------------------
DROP TABLE IF EXISTS `discussionmember`;
CREATE TABLE `discussionmember`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NULL DEFAULT NULL,
  `discussionId` bigint(20) NULL DEFAULT NULL,
  `nickName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `joinDate` datetime NULL DEFAULT NULL,
  `position` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_index`(`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discussionmember
-- ----------------------------
INSERT INTO `discussionmember` VALUES (1, 1000, 1, 'aa', '2024-11-02 11:19:00', 0);
INSERT INTO `discussionmember` VALUES (2, 1001, 1, 'bb', '2024-11-09 11:19:18', 0);
INSERT INTO `discussionmember` VALUES (3, 1002, 1, 'cc', '2024-11-10 11:19:35', 0);

-- ----------------------------
-- Table structure for friendapply
-- ----------------------------
DROP TABLE IF EXISTS `friendapply`;
CREATE TABLE `friendapply`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `fromId` bigint(20) NOT NULL,
  `toId` bigint(20) NULL DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `status` smallint(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1852541339353522178 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friendapply
-- ----------------------------
INSERT INTO `friendapply` VALUES (1851861780182396929, 1002, 1000, '你今晚寂寞吗？', '2024-10-31 00:00:00', 1);
INSERT INTO `friendapply` VALUES (1852173090061299714, 1002, 1001, '', '2024-11-01 00:00:00', 0);
INSERT INTO `friendapply` VALUES (1852541339353522177, 1002, 1004, '', '2024-11-02 00:00:00', 1);

-- ----------------------------
-- Table structure for friendgroup
-- ----------------------------
DROP TABLE IF EXISTS `friendgroup`;
CREATE TABLE `friendgroup`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userId` bigint(20) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_pk`(`userId`) USING BTREE,
  CONSTRAINT `user_pk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friendgroup
-- ----------------------------
INSERT INTO `friendgroup` VALUES (1, '我的好友', 1000, 1);
INSERT INTO `friendgroup` VALUES (2, '程序猿展览馆', 1000, 2);
INSERT INTO `friendgroup` VALUES (3, '默认好友', 1001, 3);

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `id` bigint(11) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `friendId` bigint(20) NULL DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `groupId` bigint(20) UNSIGNED NULL DEFAULT 0,
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid_fk`(`userId`) USING BTREE,
  INDEX `friend_fk`(`friendId`) USING BTREE,
  CONSTRAINT `friend_fk` FOREIGN KEY (`friendId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userid_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES (1, 1000, 1001, '大师兄', 1, NULL);
INSERT INTO `friends` VALUES (3, 1000, 1003, '三师兄', 1, NULL);
INSERT INTO `friends` VALUES (4, 1000, 1004, '隔壁老王', 2, NULL);
INSERT INTO `friends` VALUES (5, 1000, 1005, 'bug砖家', 2, NULL);
INSERT INTO `friends` VALUES (6, 1001, 1000, NULL, 3, NULL);
INSERT INTO `friends` VALUES (1851925201460412418, 1002, 1000, NULL, NULL, '2024-10-31 17:52:29');
INSERT INTO `friends` VALUES (1851925202706120705, 1000, 1002, NULL, NULL, '2024-10-31 17:52:29');
INSERT INTO `friends` VALUES (1852541851499012097, 1002, 1004, NULL, 0, '2024-11-02 10:42:51');
INSERT INTO `friends` VALUES (1852541851499012098, 1004, 1002, NULL, 0, '2024-11-02 10:42:51');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` bigint(20) NOT NULL,
  `userName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` tinyint(4) NULL DEFAULT NULL,
  `signature` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1000, 'jforgame', '$apr1$1000$668/pMBPHtbCefk.Pm17X1', 1, '专业写bug一百年', NULL, NULL, 'http://192.168.0.121:9000/database/picture/df42963d623349e485b3d012fa8032eb.jpg');
INSERT INTO `user` VALUES (1001, 'hello', '$apr1$1000$668/pMBPHtbCefk.Pm17X1', 1, '我爱Java', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1002, 'world', '$apr1$1002$T/ej7k6djN9c7Up..RQYL/', 0, '我爱JavaFX', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1003, '屌丝', '$apr1$1000$668/pMBPHtbCefk.Pm17X1', 1, '我不做游戏，我在做一个付费系统', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1004, '我心飞扬', '$apr1$1004$YjYqjugalWF5GYYsZLSxI0', 1, '把你放在 我心、最柔软的 地方', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1005, '金刚互撸娃', '$apr1$1000$668/pMBPHtbCefk.Pm17X1', 1, '写代码，我很快乐~', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

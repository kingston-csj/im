/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : chat_room

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 17/11/2024 11:26:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for discussion
-- ----------------------------
DROP TABLE IF EXISTS `discussion`;
CREATE TABLE `discussion`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `creatorId` bigint NULL DEFAULT NULL,
  `createdDate` datetime NULL DEFAULT NULL,
  `maxSeq` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discussion
-- ----------------------------
INSERT INTO `discussion` VALUES (1, 'jforgame讨论组', 1000, '2024-11-09 11:17:48', 15);

-- ----------------------------
-- Table structure for discussionmember
-- ----------------------------
DROP TABLE IF EXISTS `discussionmember`;
CREATE TABLE `discussionmember`  (
  `id` bigint NOT NULL,
  `userId` bigint NULL DEFAULT NULL,
  `discussionId` bigint NULL DEFAULT NULL,
  `nickName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `joinDate` datetime NULL DEFAULT NULL,
  `position` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_index`(`userId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fromId` bigint NOT NULL,
  `toId` bigint NULL DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `status` smallint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1855244484024590338 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friendapply
-- ----------------------------
INSERT INTO `friendapply` VALUES (1851861780182396929, 1002, 1000, '你今晚寂寞吗？', '2024-10-31 00:00:00', 1);
INSERT INTO `friendapply` VALUES (1852173090061299714, 1002, 1001, '', '2024-11-01 00:00:00', 0);
INSERT INTO `friendapply` VALUES (1852541339353522177, 1002, 1004, '', '2024-11-02 00:00:00', 1);
INSERT INTO `friendapply` VALUES (1855244250661904385, 1000, 1002, '', '2024-11-09 00:00:00', 0);
INSERT INTO `friendapply` VALUES (1855244484024590337, 1000, 1004, '', '2024-11-09 00:00:00', 0);

-- ----------------------------
-- Table structure for friendgroup
-- ----------------------------
DROP TABLE IF EXISTS `friendgroup`;
CREATE TABLE `friendgroup`  (
  `id` bigint NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `userId` bigint NULL DEFAULT NULL,
  `sort` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_pk`(`userId` ASC) USING BTREE,
  CONSTRAINT `user_pk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

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
  `id` bigint NOT NULL,
  `userId` bigint NOT NULL,
  `friendId` bigint NULL DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `groupId` bigint UNSIGNED NULL DEFAULT 0,
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid_fk`(`userId` ASC) USING BTREE,
  INDEX `friend_fk`(`friendId` ASC) USING BTREE,
  CONSTRAINT `friend_fk` FOREIGN KEY (`friendId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userid_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

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
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint NOT NULL,
  `seq` bigint NULL DEFAULT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `channel` smallint NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `sender` bigint NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  `type` smallint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1857970607049252866, 2, '{\"type\":0,\"content\":\"hello?\"}', 1, '1', 1000, '2024-11-17 10:14:47', 0);
INSERT INTO `message` VALUES (1857975738721464321, 3, '{\"type\":0,\"content\":\"hello?\"}', 1, '1', 1000, '2024-11-17 10:35:10', 0);
INSERT INTO `message` VALUES (1857987047944945666, 4, '{\"type\":0,\"content\":\"hello?\"}', 1, '1', 1000, '2024-11-17 11:20:06', 0);
INSERT INTO `message` VALUES (1857987124579074049, 5, '{\"type\":0,\"content\":\"3\"}', 1, '1', 1000, '2024-11-17 11:20:25', 0);
INSERT INTO `message` VALUES (1857987383661232130, 6, '{\"type\":0,\"content\":\"234\"}', 1, '1', 1000, '2024-11-17 11:21:27', 0);
INSERT INTO `message` VALUES (1857987409942740994, 7, '{\"type\":0,\"content\":\"1\"}', 1, '1', 1000, '2024-11-17 11:21:33', 0);
INSERT INTO `message` VALUES (1857987428431233025, 8, '{\"type\":0,\"content\":\"2\"}', 1, '1', 1000, '2024-11-17 11:21:37', 0);
INSERT INTO `message` VALUES (1857987722154147841, 9, '{\"type\":0,\"content\":\"2?\"}', 1, '1', 1002, '2024-11-17 11:22:47', 0);
INSERT INTO `message` VALUES (1857987761689657345, 10, '{\"type\":0,\"content\":\"1\"}', 1, '1', 1000, '2024-11-17 11:22:57', 0);
INSERT INTO `message` VALUES (1857987794040324098, 11, '{\"type\":0,\"content\":\"2\"}', 1, '1', 1002, '2024-11-17 11:23:04', 0);
INSERT INTO `message` VALUES (1857987814978289666, 12, '{\"type\":0,\"content\":\"3\"}', 1, '1', 1000, '2024-11-17 11:23:09', 0);
INSERT INTO `message` VALUES (1857987852517310466, 13, '{\"type\":0,\"content\":\"4\"}', 1, '1', 1000, '2024-11-17 11:23:18', 0);
INSERT INTO `message` VALUES (1857987873102954497, 14, '{\"type\":0,\"content\":\"5\"}', 1, '1', 1002, '2024-11-17 11:23:23', 0);
INSERT INTO `message` VALUES (1857988046105411586, 15, '{\"type\":0,\"content\":\"4\"}', 1, '1', 1002, '2024-11-17 11:24:05', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` bigint NOT NULL,
  `userName` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `sex` tinyint NULL DEFAULT NULL,
  `signature` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `birthday` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1000, 'jforgame', '$apr1$1000$668/pMBPHtbCefk.Pm17X1', 1, '专业写bug一百年', NULL, NULL, 'http://192.168.0.121:9000/database/picture/df42963d623349e485b3d012fa8032eb.jpg');
INSERT INTO `user` VALUES (1001, 'hello', '$apr1$1001$RY0r9WJwPR8hE/ylZsg6l.', 1, '我爱Java', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1002, 'world', '$apr1$1002$T/ej7k6djN9c7Up..RQYL/', 0, '我爱JavaFX', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1003, '屌丝', '$apr1$1000$668/pMBPHtbCefk.Pm17X1', 1, '我不做游戏，我在做一个付费系统', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1004, '我心飞扬', '$apr1$1004$YjYqjugalWF5GYYsZLSxI0', 1, '把你放在 我心、最柔软的 地方', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1005, '金刚互撸娃', '$apr1$1000$668/pMBPHtbCefk.Pm17X1', 1, '写代码，我很快乐~', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

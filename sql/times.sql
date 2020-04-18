/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : times

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 18/04/2020 23:29:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源名称',
  `pid` int NOT NULL COMMENT '上级ID',
  `sort` int DEFAULT NULL COMMENT '排序',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `api_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问地址',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型 menu、button',
  `hidden` tinyint DEFAULT NULL COMMENT '是否隐藏',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, 'system', '系统管理', 0, 1, '', '/system', '0', 0, '2020-04-06 23:21:56');
INSERT INTO `menu` VALUES (2, 'user', '用户管理', 1, 2, 'system/user/index', '/user', '1', 0, '2020-04-06 23:21:56');
INSERT INTO `menu` VALUES (3, 'role', '角色管理', 1, 3, 'system/role/index', '/role', '1', 0, '2020-04-06 23:21:56');
INSERT INTO `menu` VALUES (4, 'menu', '菜单管理', 1, 4, 'system/menu/index', '/menu', '1', 0, '2020-04-06 23:21:56');
INSERT INTO `menu` VALUES (5, NULL, '新增用户', 2, 1, 'adduser', '/system/user/add', '2', 1, '2020-04-06 23:21:56');
INSERT INTO `menu` VALUES (6, NULL, '编辑用户', 2, 2, 'edituser', '/system/user/update/**', '2', 1, '2020-04-06 23:21:56');
INSERT INTO `menu` VALUES (7, NULL, '删除用户', 2, 3, 'deluser', '/system/user/del/*', '2', 1, '2020-04-06 23:21:56');
INSERT INTO `menu` VALUES (8, NULL, '查看所有用户', 2, 4, 'listuser', '/system/user/list/**', '2', 1, '2020-04-09 17:27:22');
INSERT INTO `menu` VALUES (9, NULL, '查看一个用户', 2, 5, 'seeuser', '/system/user/list/*', '2', 1, '2020-04-09 18:07:21');
COMMIT;

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `menu_id` int DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `menu_id` (`menu_id`) USING BTREE,
  CONSTRAINT `menu_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `menu_role_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
BEGIN;
INSERT INTO `menu_role` VALUES (1, 1, 1);
INSERT INTO `menu_role` VALUES (2, 1, 2);
INSERT INTO `menu_role` VALUES (3, 1, 3);
INSERT INTO `menu_role` VALUES (4, 1, 4);
INSERT INTO `menu_role` VALUES (5, 1, 5);
INSERT INTO `menu_role` VALUES (6, 1, 6);
INSERT INTO `menu_role` VALUES (7, 1, 7);
INSERT INTO `menu_role` VALUES (8, 2, 1);
INSERT INTO `menu_role` VALUES (9, 1, 8);
INSERT INTO `menu_role` VALUES (10, 1, 9);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `remarks` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'ROLE_ADMIN', '系统管理员', '系统管理员');
INSERT INTO `role` VALUES (2, 'ROLE_GUEST', '访客', '访客');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别 0:男 1:女',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `enabled` tinyint unsigned DEFAULT NULL COMMENT '状态 1启用，0禁用',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$Gap5izSdP48aE55eZIuDeOg5yisVMbnV6HtAMcbKottcWyyUoG.DS', 'sdl', '1', '15183303003', '10086@qq.com', 'http://qzapp.qlogo.cn/qzapp/101536330/86F96F92387D69BD7659C4EC3CD6BD69/100', 1, '2020-03-24 14:59:48', '2020-03-24 14:59:48');
INSERT INTO `user` VALUES (2, 'user', '$2a$10$Gap5izSdP48aE55eZIuDeOg5yisVMbnV6HtAMcbKottcWyyUoG.DS', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

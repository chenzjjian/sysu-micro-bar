/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : sysu_micro_bar

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-06-23 05:53:33
*/

SET FOREIGN_KEY_CHECKS=0;
DROP DATABASE IF EXISTS `sysu_micro_bar`;
CREATE DATABASE `sysu_micro_bar`;
USE sysu_micro_bar;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stu_no` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `head_image_url` varchar(500) DEFAULT NULL,
  `authority` int DEFAULT NULL,
  `register_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creator_id` int NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `tag` int DEFAULT 0,
  `create_time` timestamp DEFAULT now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for floor
-- ----------------------------
DROP TABLE IF EXISTS `floor`;
CREATE TABLE `floor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `account_id` int NOT NULL,
  `reply_floor_id` int,
  `is_reply` tinyint(1) DEFAULT NULL,
  `detail` text,
  `level_num` int DEFAULT 0,
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for floor_file
-- ----------------------------
DROP TABLE IF EXISTS `floor_file`;
CREATE TABLE `floor_file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `floor_id` int NOT NULL,
  `file_url` varchar(500) DEFAULT NULL,
  `file_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for history_message
-- ----------------------------
DROP TABLE IF EXISTS `history_message`;
CREATE TABLE `history_message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `floor_id` int DEFAULT NULL,
  `is_checked` tinyint(1) DEFAULT NULL,
  `is_comment` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE post ADD CONSTRAINT FK_Reference_1 FOREIGN KEY (creator_id)
      REFERENCES account (id) ON DELETE CASCADE;

ALTER TABLE floor_file ADD CONSTRAINT FK_Reference_2 FOREIGN KEY (floor_id)
      REFERENCES floor (id) ON DELETE CASCADE;

ALTER TABLE floor ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (post_id)
      REFERENCES post (id) ON DELETE CASCADE;

ALTER TABLE floor ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (account_id)
      REFERENCES account (id) ON DELETE CASCADE;

ALTER TABLE floor ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (reply_floor_id)
      REFERENCES floor (id) ON DELETE SET NULL;

ALTER TABLE history_message ADD CONSTRAINT FK_Reference_6 FOREIGN KEY (account_id)
      REFERENCES account (id) ON DELETE CASCADE;

ALTER TABLE history_message ADD CONSTRAINT FK_Reference_7 FOREIGN KEY (floor_id)
      REFERENCES floor (id) ON DELETE SET NULL;

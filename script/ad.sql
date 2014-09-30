/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : weixin_cms

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2014-08-18 14:46:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_ad`
-- ----------------------------
DROP TABLE IF EXISTS `tb_ad`;
CREATE TABLE `tb_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `description` text,
  `create_time` datetime NOT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_ad
-- ----------------------------
INSERT INTO `tb_ad` VALUES ('5', 'wang2', '/ad/ba42d07190d1477d9e4b3828d8310e73.jpg', 'wang2', '2014-08-17 16:22:47', '0');
INSERT INTO `tb_ad` VALUES ('6', 'ceshi2', '/ad/fd6d5b59b734401192904878e877139e.jpg', 'ceshi', '2014-08-17 16:57:31', '2');

-- ----------------------------
-- Table structure for `tb_ad_stats`
-- ----------------------------
DROP TABLE IF EXISTS `tb_ad_stats`;
CREATE TABLE `tb_ad_stats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `visit_count` bigint(20) DEFAULT '0',
  `ref_count` bigint(20) DEFAULT '0',
  `ad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ad_id` (`ad_id`),
  CONSTRAINT `tb_ad_stats_ibfk_1` FOREIGN KEY (`ad_id`) REFERENCES `tb_ad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_ad_stats
-- ----------------------------
INSERT INTO `tb_ad_stats` VALUES ('2', '3', '8', '8');
INSERT INTO `tb_ad_stats` VALUES ('3', '2', '7', '10');

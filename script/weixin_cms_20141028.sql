/*
MySQL Data Transfer
Source Host: localhost
Source Database: weixin_cms
Target Host: localhost
Target Database: weixin_cms
Date: 2014/10/28 12:57:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for notify_notification
-- ----------------------------
CREATE TABLE `notify_notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `read_time` datetime NOT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for notify_task
-- ----------------------------
CREATE TABLE `notify_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `progress` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `command` varchar(255) DEFAULT NULL,
  `message` text,
  `title` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notify_task_user_idx` (`user_id`),
  CONSTRAINT `fk_notify_task_user` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sec_menu_item
-- ----------------------------
CREATE TABLE `sec_menu_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `css_class` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_menu_item_parent_idx` (`parent_id`),
  KEY `fk_menu_item_role_idx` (`parent_id`),
  KEY `fk_menu_item_role` (`role_id`),
  CONSTRAINT `fk_menu_item_parent` FOREIGN KEY (`parent_id`) REFERENCES `sec_menu_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_menu_item_role` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
CREATE TABLE `sec_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `permissions` text,
  `cn_name` varchar(255) DEFAULT NULL,
  `internal` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
CREATE TABLE `sec_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sec_user_role
-- ----------------------------
CREATE TABLE `sec_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_idx` (`role_id`),
  KEY `fk_user_role_user_idx` (`user_id`),
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_action
-- ----------------------------
CREATE TABLE `tb_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(255) NOT NULL,
  `cn_name` varchar(255) NOT NULL,
  `min_value` int(11) NOT NULL DEFAULT '0',
  `cycle` tinyint(4) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `max_value` int(11) DEFAULT NULL,
  `value_type` tinyint(4) NOT NULL DEFAULT '0',
  `is_valid` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `action_name_UNIQUE` (`action_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_action_log
-- ----------------------------
CREATE TABLE `tb_action_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_time` datetime NOT NULL,
  `value` int(11) NOT NULL,
  `action` varchar(255) NOT NULL,
  `cn_name` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `resource` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_action_log_user_idx` (`user_id`),
  CONSTRAINT `fk_action_log_user` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_ad
-- ----------------------------
CREATE TABLE `tb_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `description` text,
  `create_time` datetime NOT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_ad_stats
-- ----------------------------
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
-- Table structure for tb_agent
-- ----------------------------
CREATE TABLE `tb_agent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agent_name` varchar(255) NOT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `post` varchar(255) DEFAULT NULL,
  `state` int(5) DEFAULT NULL,
  `oper` varchar(255) DEFAULT NULL,
  `oper_time` datetime DEFAULT NULL,
  `section_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_agent_secid` (`section_id`),
  CONSTRAINT `fk_agent_secid` FOREIGN KEY (`section_id`) REFERENCES `tb_section_agent` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
CREATE TABLE `tb_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `node_id` bigint(20) NOT NULL,
  `content` text,
  `source_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `meta_id_UNIQUE` (`node_id`),
  KEY `fk_article_content_idx` (`node_id`),
  CONSTRAINT `fk_article_content` FOREIGN KEY (`node_id`) REFERENCES `tb_node` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_article_group
-- ----------------------------
CREATE TABLE `tb_article_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_node_id` bigint(20) NOT NULL,
  `node_id` bigint(20) NOT NULL,
  `order_index` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_multi_article_idx` (`content_node_id`),
  KEY `fk_multi_meta_idx` (`node_id`),
  CONSTRAINT `fk_article_group_content_node_id` FOREIGN KEY (`content_node_id`) REFERENCES `tb_node` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_auto_reply_config
-- ----------------------------
CREATE TABLE `tb_auto_reply_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_key` varchar(255) DEFAULT NULL,
  `about_node_id` bigint(20) DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `key_word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `about_node_id` (`about_node_id`),
  CONSTRAINT `tb_auto_reply_config_ibfk_1` FOREIGN KEY (`about_node_id`) REFERENCES `tb_node` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_cache
-- ----------------------------
CREATE TABLE `tb_cache` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cache_key` varchar(255) NOT NULL,
  `cache_value` text NOT NULL,
  `expires_time` int(11) NOT NULL,
  `cache_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_UNIQUE` (`cache_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_config
-- ----------------------------
CREATE TABLE `tb_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `val` varchar(255) DEFAULT NULL,
  `cn_name` varchar(255) DEFAULT NULL,
  `is_show` tinyint(4) NOT NULL DEFAULT '0',
  `action_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tb_config` (`action_id`),
  CONSTRAINT `FK_tb_config` FOREIGN KEY (`action_id`) REFERENCES `tb_action` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_m_menu
-- ----------------------------
CREATE TABLE `tb_m_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0',
  `parent_id` bigint(20) DEFAULT NULL,
  `node_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tb_m_menu_parent_idx` (`parent_id`),
  KEY `fk_tb_m_menu_node_idx` (`node_id`),
  CONSTRAINT `fk_tb_m_menu_node` FOREIGN KEY (`node_id`) REFERENCES `tb_node` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_tb_m_menu_parent` FOREIGN KEY (`parent_id`) REFERENCES `tb_m_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_node
-- ----------------------------
CREATE TABLE `tb_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `last_modified` datetime NOT NULL,
  `type` varchar(50) NOT NULL,
  `type_description` varchar(50) NOT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `pic_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`user_id`) USING BTREE,
  CONSTRAINT `tb_node_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `order_num` varchar(255) NOT NULL,
  `total_price` double NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_user_id_idx` (`user_id`),
  CONSTRAINT `fk_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
CREATE TABLE `tb_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_item_order_idx` (`order_id`),
  KEY `fk_order_item_product_idx` (`product_id`),
  CONSTRAINT `fk_order_item_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tb_order_item` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_point_detail
-- ----------------------------
CREATE TABLE `tb_point_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `cn_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `point` double NOT NULL DEFAULT '0',
  `user_name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_poll
-- ----------------------------
CREATE TABLE `tb_poll` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `node_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_poll_node_idx` (`node_id`) USING BTREE,
  CONSTRAINT `tb_poll_ibfk_1` FOREIGN KEY (`node_id`) REFERENCES `tb_node` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_poll_option
-- ----------------------------
CREATE TABLE `tb_poll_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poll_id` bigint(20) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `title` varchar(255) NOT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_poll_option_poll_idx` (`poll_id`) USING BTREE,
  CONSTRAINT `tb_poll_option_ibfk_1` FOREIGN KEY (`poll_id`) REFERENCES `tb_poll` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_poll_option_record
-- ----------------------------
CREATE TABLE `tb_poll_option_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poll_record_id` bigint(20) NOT NULL,
  `poll_option_id` bigint(20) NOT NULL,
  `correct` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `fk_poll_option_record_poll_record_idx` (`poll_record_id`) USING BTREE,
  KEY `fk_poll_option_record_poll_option_idx` (`poll_option_id`) USING BTREE,
  CONSTRAINT `tb_poll_option_record_ibfk_1` FOREIGN KEY (`poll_record_id`) REFERENCES `tb_poll_record` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_poll_option_record_ibfk_2` FOREIGN KEY (`poll_option_id`) REFERENCES `tb_poll_option` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_poll_option_value
-- ----------------------------
CREATE TABLE `tb_poll_option_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poll_option_id` bigint(20) NOT NULL,
  `value` varchar(255) NOT NULL,
  `answer` char(1) NOT NULL DEFAULT 'N',
  `type` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_poll_option_value_poll_option_idx` (`poll_option_id`) USING BTREE,
  CONSTRAINT `tb_poll_option_value_ibfk_1` FOREIGN KEY (`poll_option_id`) REFERENCES `tb_poll_option` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_poll_option_value_record
-- ----------------------------
CREATE TABLE `tb_poll_option_value_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poll_option_record_id` bigint(20) DEFAULT NULL,
  `poll_option_value_id` bigint(20) DEFAULT NULL,
  `text_value` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_value_record_option_value_idx` (`poll_option_value_id`) USING BTREE,
  KEY `fk_value_record_poll_option_record_idx` (`poll_option_record_id`) USING BTREE,
  CONSTRAINT `tb_poll_option_value_record_ibfk_1` FOREIGN KEY (`poll_option_record_id`) REFERENCES `tb_poll_option_record` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_poll_option_value_record_ibfk_2` FOREIGN KEY (`poll_option_value_id`) REFERENCES `tb_poll_option_value` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_poll_record
-- ----------------------------
CREATE TABLE `tb_poll_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poll_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `score` float DEFAULT NULL,
  `reward` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `fk_poll_record_poll_idx` (`poll_id`) USING BTREE,
  KEY `fk_poll_record_user_idx` (`user_id`) USING BTREE,
  CONSTRAINT `tb_poll_record_ibfk_1` FOREIGN KEY (`poll_id`) REFERENCES `tb_poll` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `tb_poll_record_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_poll_visibility
-- ----------------------------
CREATE TABLE `tb_poll_visibility` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poll_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_poll_visibility_poll_idx` (`poll_id`) USING BTREE,
  KEY `fk_poll_visibility_author_idx` (`user_id`) USING BTREE,
  CONSTRAINT `tb_poll_visibility_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_poll_visibility_ibfk_2` FOREIGN KEY (`poll_id`) REFERENCES `tb_poll` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
CREATE TABLE `tb_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `description` text NOT NULL,
  `status` tinyint(4) NOT NULL,
  `price` double DEFAULT NULL,
  `last_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_section_agent
-- ----------------------------
CREATE TABLE `tb_section_agent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(255) DEFAULT NULL,
  `oper_time` date DEFAULT NULL,
  `oper` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_text
-- ----------------------------
CREATE TABLE `tb_text` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `node_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `meta` (`node_id`),
  CONSTRAINT `fk_content_meta` FOREIGN KEY (`node_id`) REFERENCES `tb_node` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_traffic_detail
-- ----------------------------
CREATE TABLE `tb_traffic_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `cn_name` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `effect_time` datetime DEFAULT NULL,
  `traffic` double NOT NULL DEFAULT '0',
  `user_name` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `job_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user_profile
-- ----------------------------
CREATE TABLE `tb_user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `open_id` (`mobile`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_user_profile_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_wifi_code
-- ----------------------------
CREATE TABLE `tb_wifi_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `feed_user_id` bigint(20) DEFAULT NULL,
  `feed_user_name` varchar(255) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `feed_time` datetime DEFAULT NULL,
  `order_item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `order_item_id` (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `sec_menu_item` VALUES ('1', '全局', '/admin/index.do', 'icon-dashboard', null, '1', null);
INSERT INTO `sec_menu_item` VALUES ('2', '任务管理', '/component/basic/task/index.do', null, '1', '1', null);
INSERT INTO `sec_menu_item` VALUES ('3', '通知管理', '/component/basic/notify/index.do', null, '1', '1', null);
INSERT INTO `sec_menu_item` VALUES ('4', '权限管理', null, 'icon-desktop', null, '1', null);
INSERT INTO `sec_menu_item` VALUES ('5', '用户管理', '/component/security/user/index.do', null, '4', '1', null);
INSERT INTO `sec_menu_item` VALUES ('6', '角色管理', '/component/security/role/index.do', null, '4', '1', null);
INSERT INTO `sec_menu_item` VALUES ('7', '菜单管理', '/component/security/menu/index.do', null, '4', '1', null);
INSERT INTO `sec_menu_item` VALUES ('8', 'CMS', '', 'icon-edit', null, '2', null);
INSERT INTO `sec_menu_item` VALUES ('9', '文本内容', '/admin/cms/text/index.do', null, '8', '1', null);
INSERT INTO `sec_menu_item` VALUES ('10', '微信管理', '', 'icon-comment-alt', null, '2', null);
INSERT INTO `sec_menu_item` VALUES ('11', '菜单管理', '/admin/menu/index.do', null, '10', '1', null);
INSERT INTO `sec_menu_item` VALUES ('13', '单图文管理', '/admin/cms/article/index.do', null, '8', '1', null);
INSERT INTO `sec_menu_item` VALUES ('14', '广告管理', '', 'icon-adn', null, '1', null);
INSERT INTO `sec_menu_item` VALUES ('15', '广告内容', '/admin/ad/index.do', null, '14', '1', null);
INSERT INTO `sec_menu_item` VALUES ('16', '多图文管理', '/admin/cms/articlegroup/index.do', null, '8', '1', null);
INSERT INTO `sec_menu_item` VALUES ('17', '投票管理', '/admin/poll/index.do', null, '8', '1', null);
INSERT INTO `sec_menu_item` VALUES ('18', '商城', '', 'icon-tag', null, '1', null);
INSERT INTO `sec_menu_item` VALUES ('19', '产品管理', '/admin/mall/product/index.do', null, '18', '1', null);
INSERT INTO `sec_menu_item` VALUES ('20', '系统设置', null, 'icon-home', null, '1', null);
INSERT INTO `sec_menu_item` VALUES ('21', '规则配置', '/admin/action/index.do', null, '20', '1', null);
INSERT INTO `sec_menu_item` VALUES ('22', '配置项管理', '/admin/config/index.do', null, '20', '1', null);
INSERT INTO `sec_menu_item` VALUES ('23', '统计管理', '', 'icon-bar-chart', null, '1', null);
INSERT INTO `sec_menu_item` VALUES ('24', '积分明细', '/admin/log/point.do', null, '23', '1', null);
INSERT INTO `sec_menu_item` VALUES ('25', '流量统计', '/admin/log/traffic.do', null, '23', '1', null);
INSERT INTO `sec_menu_item` VALUES ('26', '自动回复', '/admin/auto_reply/index.do', null, '10', '1', null);
INSERT INTO `sec_menu_item` VALUES ('27', 'WIFI串码管理', '/admin/mall/wifi/index.do', null, '18', '1', null);
INSERT INTO `sec_menu_item` VALUES ('28', '网点管理', '', 'icon-tag', null, '1', null);
INSERT INTO `sec_menu_item` VALUES ('29', '网点维护', '/admin/agent/index.do', null, '28', '2', null);
INSERT INTO `sec_menu_item` VALUES ('30', '片区管理', '/admin/agent/indexSection.do', null, '28', '1', null);
INSERT INTO `sec_role` VALUES ('1', 'admin', null, '超级管理员组', '1');
INSERT INTO `sec_role` VALUES ('2', 'user', null, '普通用户', '1');
INSERT INTO `sec_user` VALUES ('1', 'admin', '0192023a7bbd73250516f069df18b500', '超级管理员', null);
INSERT INTO `sec_user` VALUES ('21', 'oXM8DuEWlOyv20znX-A7OGbHzKpU', '17fe70178b831075a74e233df74aa790', '握不住的沙', null);
INSERT INTO `sec_user` VALUES ('22', 'oXM8DuJjAoKkM-638smVE5CYgmN0', 'b99e4e8875025ea6963f99eaaecc5460', null, null);
INSERT INTO `sec_user` VALUES ('23', 'oXM8DuKkLtLDVutGds3p6RhAtrlI', '770662208eb31945e880ab5ec00be1b1', 'devil', null);
INSERT INTO `sec_user` VALUES ('24', 'oXM8DuPm5B632W9hjer_4JEzGWr8', '2a94aee6beb7b0a464259755bc42f602', 'ZP', null);
INSERT INTO `sec_user` VALUES ('25', 'oXM8DuFbLMDGndsixBBdlKdk4aMQ', 'd869065026f027ac5275ae4ee7346eff', 'Awesome Time', null);
INSERT INTO `sec_user` VALUES ('26', 'oXM8DuOGm1w09pnqqBwT2cUEaOCg', '9a256d0355c6aa621cd8798a67d0c4b9', '何沪彬', null);
INSERT INTO `sec_user` VALUES ('27', 'oXM8DuDHl7WK4gduc5MStEFN4pUc', '9b36e0c99f9f19632c8e637cd6995e91', 'HG', null);
INSERT INTO `sec_user` VALUES ('28', 'oXM8DuEZtQxSEvkhaL-Joa_oTtxE', 'c843b457a2de702212e394e8e7dceb63', '戈', null);
INSERT INTO `sec_user` VALUES ('29', 'oXM8DuHC4lYBU5o00kLJEDa0O-Q4', 'b5b1df3cc1db1376565676bb7fcb310d', '挠儿妈', null);
INSERT INTO `sec_user` VALUES ('30', 'oXM8DuCCR18UYFoMM3M6iwdXDVzM', 'f66dc5a5a13906bd2e88235278d85f0d', '澄澄姥姥', null);
INSERT INTO `sec_user` VALUES ('31', 'oXM8DuGL3uoGoB-rQMkp_urSbwzY', 'a3f23df4be59aa828b5af79bb1394249', '洪明玉', null);
INSERT INTO `sec_user` VALUES ('35', 'oXM8DuJ8K9kVqy1O3iCWRZdoGaDY', '1896a3da732e0e447660c970d5536c2a', '我叫包子', null);
INSERT INTO `sec_user` VALUES ('36', 'oXM8DuBJd6ERvlnGl2RhOMP0eG2Y', '9fcefb810cf8d4f2d8cd1d5b3e9b9fe2', '天空飘来五个字', null);
INSERT INTO `sec_user` VALUES ('37', 'oXM8DuNedIAmNoL2cF7LXbaUmJQs', 'cd6bd6726b25056743f6997abe191ed5', null, null);
INSERT INTO `sec_user` VALUES ('38', 'oXM8DuI4BeR_s2VRd2xH2BkrrWCE', '7b1c48f14538935590baec8006b30319', '阿智--Janet', null);
INSERT INTO `sec_user` VALUES ('39', 'oXM8DuM05uNF9tfnJSLOUAjqlqhQ', '19a64bdf8361ee7c138586a3a76eb6c1', '杨国忠', null);
INSERT INTO `sec_user` VALUES ('40', 'oXM8DuFm9a7myLEA5p4ozOqriwaE', 'dbe9323f197abe056e18d5ea5df48254', '方龙', null);
INSERT INTO `sec_user` VALUES ('41', 'oXM8DuLHmq8wT9FvvlijHY-8jJqI', '216c08455470f2321f5d75c00e17a12a', '春天花会开^_^', null);
INSERT INTO `sec_user` VALUES ('42', 'oXM8DuPyzzdUDSQv8NmFMnpZJV4c', 'e182f18e47ed935023b1f4b1081cb4c3', '韩', null);
INSERT INTO `sec_user` VALUES ('43', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', 'f913aa01c33ccc414e73739dc15baf33', '挺好', null);
INSERT INTO `sec_user` VALUES ('44', 'oXM8DuK4jkxuZizQ1NhXmzGnxUws', 'eb004b1056a73b3d30cb7583755b1e5a', 'panda', null);
INSERT INTO `sec_user` VALUES ('45', 'oXM8DuHbJQfC3FHaJjOclOk57OhQ', '9ac7907dfb098ee99767e0c0ff298ca6', 'Listening', null);
INSERT INTO `sec_user` VALUES ('46', 'oXM8DuGNGJkoydrwAJCWmknQM6zQ', '6a11d4a4c65db2fc80d7103e3a94956b', '无人像你i', null);
INSERT INTO `sec_user` VALUES ('47', 'oXM8DuJOeLPkDfA27F43MKyBtKQk', 'f69f021c9b4f7984a6984a71887766c4', 'Elva-由由', null);
INSERT INTO `sec_user` VALUES ('48', 'oXM8DuNiyRlPoA9p7SC-HMNVPOgM', '6dba0553b4ef2b7f912223686648162e', '梁大焯（深圳市德诚发电子）', null);
INSERT INTO `sec_user` VALUES ('49', 'oXM8DuKeWikKdsoVFw1ZpE0NlGn4', '6d460e133529bd1ce71c763ea88d2788', '雅玲', null);
INSERT INTO `sec_user` VALUES ('50', 'oXM8DuMHpyskhQI_TZH5CpEjQUdc', '938b6cbfd6c3e9a54d5f797f79d473d5', '赵庆明', null);
INSERT INTO `sec_user` VALUES ('51', 'oXM8DuDWzOUGa5e4NZwl5aT2212I', 'c90971e726fc45639f2192074f37b49c', 'A smile~', null);
INSERT INTO `sec_user` VALUES ('52', 'oXM8DuAwZ12gkS5DtxnBl2oXCM8I', '4d8b5216a4ce28075d59f75422a62f3e', '心在春天', null);
INSERT INTO `sec_user` VALUES ('53', 'oXM8DuBk2O1OBw-0l5hjTZ5tbNwE', '627b1fa49be68433426c9e8c39f0cbec', '。，、：／', null);
INSERT INTO `sec_user` VALUES ('54', 'oXM8DuBc6RYGH3gpmcIZkn8jD0aI', '26d112d4ddd5bf79f59ef2309e9926d6', '可有可无', null);
INSERT INTO `sec_user` VALUES ('55', 'oXM8DuLDXhHBBoqxo6wFdTe3BvFA', 'ae8040e3cce732401d7197e6a52cb5c3', '小平', null);
INSERT INTO `sec_user` VALUES ('56', 'oXM8DuJfbNTrFKzpmjhTT49HP87U', '5f7c68231ce18e7edba397c0412ea00b', '杂草', null);
INSERT INTO `sec_user` VALUES ('57', 'oXM8DuKwblQmp6vD2sid7nybT_Tg', 'fecf0f041f42b35ed2803c9c8358faad', '程希臣', null);
INSERT INTO `sec_user` VALUES ('58', 'oXM8DuJeXZDxtTnsM_3k2y3ULYDg', 'e90c73946cbcac841bd148ab505e497e', 'LiuQXia', null);
INSERT INTO `sec_user` VALUES ('59', 'oXM8DuA8FjlefpfJ_z1cUA4g_3Ic', '112a050dde9dd0a99ed2ad85bc4aec40', '王康江', null);
INSERT INTO `sec_user` VALUES ('60', 'oXM8DuCiW1ZFaH4qn1tTFcpq13BU', '301ed73663c7820fdecfafe6aae59bd7', '靓仔', null);
INSERT INTO `sec_user` VALUES ('61', 'oXM8DuPVYw5tU_PeJEAUPXQwENV4', 'b62e223300d3728bd8a26a570296fd4e', '大头娃娃', null);
INSERT INTO `sec_user` VALUES ('62', 'oXM8DuJWYranUED3PbPLFEm7-0TM', '257af5e9b8f1ff2075b9bb2ff8ba1876', '冷静思索', null);
INSERT INTO `sec_user` VALUES ('63', 'oXM8DuK-HjzXz3xhuL8NA7VkvllI', '68c6c3d4edd5701e0b04c8e46abe04cd', 'Lmh', null);
INSERT INTO `sec_user` VALUES ('64', 'oXM8DuFspVqZxH9fpAQDEndfLM20', '89b4666143f9c06451a417b5462adacb', '胡慧香', null);
INSERT INTO `sec_user` VALUES ('65', 'oXM8DuOLbc_Owo01cz6o6GzsEKLs', '8061e18dd45b345d262d34d73c324cbc', 'Jane', null);
INSERT INTO `sec_user` VALUES ('66', 'oXM8DuFw-ACFO_fZ4T3JsoeKb80s', '716375b86c8c2ec1c494d2269c5f2f5e', '吴稳', null);
INSERT INTO `sec_user` VALUES ('67', 'oXM8DuNrVNU6oGfqgStTvZxaNQlk', 'fff41a84c30322d1c866c0672388f6c6', 'w.o', null);
INSERT INTO `sec_user` VALUES ('68', 'oXM8DuE88gX4Bel0brSmZ1lLghjI', '52f10c123abd8f51f2d6249ac5228962', '浩云如风', null);
INSERT INTO `sec_user` VALUES ('69', 'oXM8DuJXZUZ0d06FxiIrFfBOnD50', '4d191d6adcbfa97fb3e4b6f01b3f7a0b', '大中至正', null);
INSERT INTO `sec_user` VALUES ('70', 'oXM8DuO6oxlv45Hyc2_2KVP0TjJk', '4813f8f1baae802f4a27073e52a52df1', '许欣友', null);
INSERT INTO `sec_user` VALUES ('71', 'oXM8DuFU5rhVywYi3k-98TqwpWlA', 'aa0d537d6bdd13ae8d7f50e09ae66624', '唯依', null);
INSERT INTO `sec_user` VALUES ('72', 'oXM8DuP7ZRYl8YDew3XXb_3L3Yjo', 'f757b2654dd3c3d414d6e6586f84e74e', '唐艾', null);
INSERT INTO `sec_user` VALUES ('73', 'oXM8DuB9UYQQfShQuvMFlAJFd4Ys', '1baab2b149ac58cdd380c159f6fd8d3f', '敏', null);
INSERT INTO `sec_user` VALUES ('74', 'oXM8DuH05baulHR5jrPd-VmE-1Rg', '4c8a25d20c5ca470c976f75d176eefaf', 'simplesherry', null);
INSERT INTO `sec_user` VALUES ('75', 'oXM8DuJ2ocwFK5h04YCPRtLiT3LM', '1c8dcfe2e9b59accaa3fbd69d8a937c4', '若水', null);
INSERT INTO `sec_user` VALUES ('76', 'oXM8DuMPPR19reJAl6udSmgxMFm0', '01861563f5b5869c35aac70523f24e1a', '大蒋蒋蒋蒋', null);
INSERT INTO `sec_user` VALUES ('77', 'oXM8DuDpIZ8ktnMdN5yvigI5jhVA', 'f672af899fd061d496ff0e0d36f25dff', 'Tromso自由的心', null);
INSERT INTO `sec_user` VALUES ('78', 'oXM8DuJMcFVH3nHhrXX0ShS5D_vM', '1f79bef54b21bf40991daa27c1664edb', '壹個鳥人', null);
INSERT INTO `sec_user` VALUES ('79', 'oXM8DuPtJp7v8wMa7J0SY76MDxJI', 'c9b1ed8b0240063d41a98be0a4ba711c', '沉鱼＂落雁＂', null);
INSERT INTO `sec_user` VALUES ('80', 'oXM8DuIOHBkn85302LJtyqokuqS8', 'e4aac2d81b68e7e1927c8fcfc4691e8e', 'cao xiaoqiang', null);
INSERT INTO `sec_user` VALUES ('81', 'oXM8DuOVa_rZeVrayP7xUwoI2_ws', 'd1a6e92eda85841dee909a829746c6b7', 'FAiRLiGHTA', null);
INSERT INTO `sec_user` VALUES ('82', 'oXM8DuJjdcmwJU1ap_1FBvJ3Q488', '3f74fbd53123ca7844b4c11ed7783db0', 'ma', null);
INSERT INTO `sec_user` VALUES ('83', 'oXM8DuEEmRMl37SdTuoh28j0rD9w', '1142eb20f45f071a947adbfbf687175c', 'CENY', null);
INSERT INTO `sec_user` VALUES ('84', 'oXM8DuOVKEG4tszAbfxS7UNw7VGQ', '951255bd9e4fed3449bd5780d84d03f8', '陈建文', null);
INSERT INTO `sec_user` VALUES ('85', 'oXM8DuAlbDk59NEWV4aTRXXm9v-Y', 'd1e5d361ecf6179c9b0ef6b76d8424ce', '逆爱', null);
INSERT INTO `sec_user` VALUES ('86', 'oXM8DuIifKqHgdvXlkrYRIfRi5QQ', '433cbff21e7e5af894ec34d39c0ad520', '湘晨', null);
INSERT INTO `sec_user` VALUES ('87', 'oXM8DuO-VIpAqExDNlD6ktQJYOzU', 'a33d715a564ca44d6d586b0a2875076c', '杨杨', null);
INSERT INTO `sec_user` VALUES ('88', 'oXM8DuD6Knr5YFtimbNRR0qdcG0Q', '3d4c24414ce67c8d08036c30caca5b07', '冰雨', null);
INSERT INTO `sec_user` VALUES ('89', 'oXM8DuEeyTO9TG-tU9Qf09OIO_W0', 'f97cf99145566b3b92e9203c116f309c', 'tanny', null);
INSERT INTO `sec_user` VALUES ('90', 'oXM8DuC57o8h48kNOlvaHKkbr7_I', '84597dc28b232fba8ef1f3ebc2f8a5a0', '乐天', null);
INSERT INTO `sec_user` VALUES ('91', 'oXM8DuMojXXNaEtF7jTFbfhJQdzA', '8dc0df6b960ef6a4891355197e7e3f2b', '徐诚', null);
INSERT INTO `sec_user` VALUES ('92', 'oXM8DuKn9Hak4a1b1xez5BQGrrY8', 'a3fa8abca518c7598c07a91d8e4649e9', '男人站直别趴下！', null);
INSERT INTO `sec_user` VALUES ('93', 'oXM8DuPMk9yaVr0vr1VCghMBbMT8', '6dc986ca253991cb39baa4ba9918cc72', 'hellman', null);
INSERT INTO `sec_user` VALUES ('94', 'oXM8DuL7QwtqV13mG4BiOXGCbYxk', '01cf9a84517c02aed8911c8c18a1c95a', 'raya', null);
INSERT INTO `sec_user` VALUES ('95', 'o_G87uD3ctrcF633nE-MlaQ5JjO8', 'ab674096a8c6c5d78e7896795d0e9055', null, null);
INSERT INTO `sec_user` VALUES ('96', 'oXM8DuJXSoowtDxkjH4CX90BjjaM', '7fbffa0c6935b1dd4b9eb078448ace90', 'Lok', null);
INSERT INTO `sec_user` VALUES ('97', 'oXM8DuETdCTkoPzUNUX8MXusLoYk', 'a17b91b024085f2fc0c63283c0fcf359', '你的爱我全部占有', null);
INSERT INTO `sec_user` VALUES ('98', 'oXM8DuG5M9RLo-emUO0oBMtHj7qk', 'b2245b80dbb07b4d64701c4c398e4006', '王琳', null);
INSERT INTO `sec_user` VALUES ('99', 'oXM8DuF06JHVGkbZCAT1L_QS843I', 'a24b27230b3b67285535b05022e594a8', '忍耐', null);
INSERT INTO `sec_user` VALUES ('100', 'oXM8DuKlHH6GCYyYjOAZwOXhsmH4', '4fccc51f928f147ed5651b0de1065f95', '平平淡淡就好', null);
INSERT INTO `sec_user` VALUES ('101', 'o_G87uAct4qqFdLPk8zfiMjxbxTk', '15b0c8a987be469474feefbd92936353', null, null);
INSERT INTO `sec_user` VALUES ('102', 'o_G87uMf-hR10pXznwpVR02sFBa4', 'ecd710cc07b60c064599931dec07e61c', null, null);
INSERT INTO `sec_user` VALUES ('104', 'oXM8DuGYNOPaOtu4GB79ny7DeRkA', 'e34a2f277fa9bf4cae272d59dd1fc206', '童话', null);
INSERT INTO `sec_user` VALUES ('105', 'oXM8DuDWjE2A5EaAqEl6RzKtYPOQ', 'bc9dd25b28397525dd41aacbfe1811f3', '旺旺', null);
INSERT INTO `sec_user` VALUES ('106', 'oXM8DuHHcVDlEhYi4isntQQV6_SA', '069fba21edff4f28d3bdf596ac234f0f', '吴坤', null);
INSERT INTO `sec_user` VALUES ('107', 'oXM8DuGq0GWijXMuEtyqG-4Y8qsE', '5ff43baa562e62e3c16b092a84b55f0f', '苏睿', null);
INSERT INTO `sec_user` VALUES ('108', 'oXM8DuBNGd-j50SR6V32tKWJlefQ', '982a8f34096758e0862869520d84ee68', '腾腾李', null);
INSERT INTO `sec_user` VALUES ('109', 'oXM8DuBj7EEGpAGUxiamXW8FvxeM', '43e3da4ea28bc26eb7c4ca2495d42fc5', '吴春伟', null);
INSERT INTO `sec_user` VALUES ('110', 'oXM8DuBKV9cRt2XiS9lfx-QwxW44', 'dedf630a0a91c0604185bdf92664b3af', 'Sunwayz', null);
INSERT INTO `sec_user` VALUES ('111', 'oXM8DuFuf65nGcn9g_1xDzH66y-k', '6200deabf06aca1fa08b1c9ecfee6338', '蓝海', null);
INSERT INTO `sec_user` VALUES ('112', 'oXM8DuItiyZfh8Yh787TJHRWsVgo', '80c935d3534370fd174fc6fb70ed46fc', 'Apple', null);
INSERT INTO `sec_user` VALUES ('113', 'oXM8DuCKlPz0vGpXW2EktFRL_zXU', '30647d764f7263e338ee75da7cbff309', '临风', null);
INSERT INTO `sec_user` VALUES ('114', 'oXM8DuHpBYNRx6iNf1FHSKz_gmxE', '52b39a6c860bfa2bec7e5365c2ba08bb', '老庄', null);
INSERT INTO `sec_user` VALUES ('115', 'oXM8DuNR1jRa2uMX-9VboDGVbMx0', 'aad179aac6cc7461301f1bda4f02a5ab', null, null);
INSERT INTO `sec_user` VALUES ('118', 'oXM8DuBjvFwTdLa5XJnjTPFt96Og', 'a946c2459c7d9c6d8ee4c2201302f9c1', null, null);
INSERT INTO `sec_user_role` VALUES ('1', '1', '1');
INSERT INTO `sec_user_role` VALUES ('20', '21', '2');
INSERT INTO `sec_user_role` VALUES ('23', '23', '2');
INSERT INTO `sec_user_role` VALUES ('24', '24', '2');
INSERT INTO `sec_user_role` VALUES ('26', '25', '2');
INSERT INTO `sec_user_role` VALUES ('27', '26', '2');
INSERT INTO `sec_user_role` VALUES ('28', '27', '2');
INSERT INTO `sec_user_role` VALUES ('30', '28', '2');
INSERT INTO `sec_user_role` VALUES ('31', '29', '2');
INSERT INTO `sec_user_role` VALUES ('32', '30', '2');
INSERT INTO `sec_user_role` VALUES ('36', '31', '2');
INSERT INTO `sec_user_role` VALUES ('41', '36', '2');
INSERT INTO `sec_user_role` VALUES ('43', '38', '2');
INSERT INTO `sec_user_role` VALUES ('44', '39', '2');
INSERT INTO `sec_user_role` VALUES ('45', '40', '2');
INSERT INTO `sec_user_role` VALUES ('46', '41', '2');
INSERT INTO `sec_user_role` VALUES ('49', '42', '2');
INSERT INTO `sec_user_role` VALUES ('51', '44', '2');
INSERT INTO `sec_user_role` VALUES ('53', '46', '2');
INSERT INTO `sec_user_role` VALUES ('54', '47', '2');
INSERT INTO `sec_user_role` VALUES ('55', '48', '2');
INSERT INTO `sec_user_role` VALUES ('56', '49', '2');
INSERT INTO `sec_user_role` VALUES ('57', '50', '2');
INSERT INTO `sec_user_role` VALUES ('58', '51', '2');
INSERT INTO `sec_user_role` VALUES ('59', '52', '2');
INSERT INTO `sec_user_role` VALUES ('61', '35', '2');
INSERT INTO `sec_user_role` VALUES ('62', '53', '2');
INSERT INTO `sec_user_role` VALUES ('64', '54', '2');
INSERT INTO `sec_user_role` VALUES ('66', '55', '2');
INSERT INTO `sec_user_role` VALUES ('67', '56', '2');
INSERT INTO `sec_user_role` VALUES ('70', '57', '2');
INSERT INTO `sec_user_role` VALUES ('73', '58', '2');
INSERT INTO `sec_user_role` VALUES ('74', '59', '2');
INSERT INTO `sec_user_role` VALUES ('75', '60', '2');
INSERT INTO `sec_user_role` VALUES ('77', '62', '2');
INSERT INTO `sec_user_role` VALUES ('78', '63', '2');
INSERT INTO `sec_user_role` VALUES ('79', '64', '2');
INSERT INTO `sec_user_role` VALUES ('80', '65', '2');
INSERT INTO `sec_user_role` VALUES ('81', '66', '2');
INSERT INTO `sec_user_role` VALUES ('82', '67', '2');
INSERT INTO `sec_user_role` VALUES ('86', '68', '2');
INSERT INTO `sec_user_role` VALUES ('87', '69', '2');
INSERT INTO `sec_user_role` VALUES ('88', '70', '2');
INSERT INTO `sec_user_role` VALUES ('89', '71', '2');
INSERT INTO `sec_user_role` VALUES ('90', '72', '2');
INSERT INTO `sec_user_role` VALUES ('91', '73', '2');
INSERT INTO `sec_user_role` VALUES ('92', '74', '2');
INSERT INTO `sec_user_role` VALUES ('110', '75', '2');
INSERT INTO `sec_user_role` VALUES ('111', '76', '2');
INSERT INTO `sec_user_role` VALUES ('119', '43', '2');
INSERT INTO `sec_user_role` VALUES ('120', '77', '2');
INSERT INTO `sec_user_role` VALUES ('125', '78', '2');
INSERT INTO `sec_user_role` VALUES ('126', '79', '2');
INSERT INTO `sec_user_role` VALUES ('127', '80', '2');
INSERT INTO `sec_user_role` VALUES ('130', '81', '2');
INSERT INTO `sec_user_role` VALUES ('132', '83', '2');
INSERT INTO `sec_user_role` VALUES ('133', '84', '2');
INSERT INTO `sec_user_role` VALUES ('134', '85', '2');
INSERT INTO `sec_user_role` VALUES ('135', '86', '2');
INSERT INTO `sec_user_role` VALUES ('139', '88', '2');
INSERT INTO `sec_user_role` VALUES ('140', '61', '2');
INSERT INTO `sec_user_role` VALUES ('141', '89', '2');
INSERT INTO `sec_user_role` VALUES ('144', '90', '2');
INSERT INTO `sec_user_role` VALUES ('148', '91', '2');
INSERT INTO `sec_user_role` VALUES ('153', '92', '2');
INSERT INTO `sec_user_role` VALUES ('154', '93', '2');
INSERT INTO `sec_user_role` VALUES ('155', '94', '2');
INSERT INTO `sec_user_role` VALUES ('156', '45', '2');
INSERT INTO `sec_user_role` VALUES ('161', '95', '2');
INSERT INTO `sec_user_role` VALUES ('162', '96', '2');
INSERT INTO `sec_user_role` VALUES ('163', '87', '2');
INSERT INTO `sec_user_role` VALUES ('164', '97', '2');
INSERT INTO `sec_user_role` VALUES ('166', '98', '2');
INSERT INTO `sec_user_role` VALUES ('167', '99', '2');
INSERT INTO `sec_user_role` VALUES ('168', '100', '2');
INSERT INTO `sec_user_role` VALUES ('172', '102', '2');
INSERT INTO `sec_user_role` VALUES ('173', '101', '2');
INSERT INTO `sec_user_role` VALUES ('174', '104', '2');
INSERT INTO `sec_user_role` VALUES ('175', '82', '2');
INSERT INTO `sec_user_role` VALUES ('176', '105', '2');
INSERT INTO `sec_user_role` VALUES ('177', '106', '2');
INSERT INTO `sec_user_role` VALUES ('178', '107', '2');
INSERT INTO `sec_user_role` VALUES ('179', '108', '2');
INSERT INTO `sec_user_role` VALUES ('180', '109', '2');
INSERT INTO `sec_user_role` VALUES ('181', '110', '2');
INSERT INTO `sec_user_role` VALUES ('182', '111', '2');
INSERT INTO `sec_user_role` VALUES ('183', '112', '2');
INSERT INTO `sec_user_role` VALUES ('184', '113', '2');
INSERT INTO `sec_user_role` VALUES ('185', '114', '2');
INSERT INTO `sec_user_role` VALUES ('186', '115', '2');
INSERT INTO `sec_user_role` VALUES ('188', '22', '2');
INSERT INTO `sec_user_role` VALUES ('189', '37', '2');
INSERT INTO `sec_user_role` VALUES ('190', '118', '2');
INSERT INTO `tb_action` VALUES ('2', 'user.sign', '用户签到', '5', '0', '1', '15', '1', '1');
INSERT INTO `tb_action` VALUES ('3', 'user.bound', '用户绑定', '60', '0', '1', '60', '0', '1');
INSERT INTO `tb_action` VALUES ('4', 'activity.sign', '活动签到', '0', '3', '1', '0', '0', '1');
INSERT INTO `tb_action` VALUES ('5', 'invite.reward', '邀请奖励', '10', '2', '5', '10', '0', '1');
INSERT INTO `tb_action_log` VALUES ('191', '2014-09-25 00:17:46', '13', 'user.sign', '用户签到', '22', null);
INSERT INTO `tb_action_log` VALUES ('192', '2014-09-25 00:19:09', '200', 'activity.sign', '活动签到', '22', null);
INSERT INTO `tb_action_log` VALUES ('193', '2014-09-25 00:26:23', '15', 'user.sign', '用户签到', '45', null);
INSERT INTO `tb_action_log` VALUES ('194', '2014-09-25 00:28:09', '200', 'activity.sign', '活动签到', '45', null);
INSERT INTO `tb_action_log` VALUES ('195', '2014-09-25 01:39:33', '7', 'user.sign', '用户签到', '61', null);
INSERT INTO `tb_action_log` VALUES ('196', '2014-09-25 14:35:32', '6', 'user.sign', '用户签到', '37', null);
INSERT INTO `tb_action_log` VALUES ('197', '2014-09-25 14:36:59', '200', 'activity.sign', '活动签到', '37', null);
INSERT INTO `tb_action_log` VALUES ('198', '2014-09-26 13:43:20', '14', 'user.sign', '用户签到', '43', null);
INSERT INTO `tb_action_log` VALUES ('199', '2014-09-26 13:43:51', '0', 'activity.sign', '活动签到', '43', null);
INSERT INTO `tb_action_log` VALUES ('200', '2014-09-26 16:01:50', '15', 'user.sign', '用户签到', '22', null);
INSERT INTO `tb_action_log` VALUES ('201', '2014-09-28 14:10:35', '9', 'user.sign', '用户签到', '22', null);
INSERT INTO `tb_ad` VALUES ('5', 'wang2第三方', '/ad/ba42d07190d1477d9e4b3828d8310e73.jpg', 'wang2水电费都是\r\n随碟附送的', '2014-08-17 16:22:47', '0sdf');
INSERT INTO `tb_ad` VALUES ('6', 'ceshi2', '/ad/fd6d5b59b734401192904878e877139e.jpg', 'ceshi', '2014-08-17 16:57:31', '2');
INSERT INTO `tb_ad` VALUES ('7', 'ww', '/ad/e63b40e3a2c24f4da8c0ff9aa86e0b48.sql', 'w', '2014-10-05 22:23:46', 'w');
INSERT INTO `tb_ad` VALUES ('8', '第三方', '/ad/cd09e51780a24b58b8a4c02da4aa7800.sql', '到上房顶上', '2014-10-05 22:26:02', '的到上房顶上');
INSERT INTO `tb_ad` VALUES ('9', '1', '/ad/35ed0a040e0c452291ffeacba4060aef.sql', '1', '2014-10-05 22:37:03', '1');
INSERT INTO `tb_ad_stats` VALUES ('2', '3', '8', '8');
INSERT INTO `tb_ad_stats` VALUES ('3', '2', '7', '10');
INSERT INTO `tb_agent` VALUES ('8', '1', '1', '1', '1', '1', null, null, null, null, '4');
INSERT INTO `tb_agent` VALUES ('9', '2', '2', '2', '2', '2', null, null, null, null, '3');
INSERT INTO `tb_article` VALUES ('10', '64', '', 'http://218.1.64.75/cms/front/at/my/sign.do');
INSERT INTO `tb_article` VALUES ('11', '70', '<p><span style=\"font-size:2EM\"><strong>您在&ldquo;简单生活节&rdquo;当日，【成功注册】签个到，并参加【每日签到】及【活动签到】，即可现场领取精美读卡器一个。</strong><br />\r\n【注册】<br />\r\n电信用户：可获得100M 3G流量。<br />\r\n非电信用户：可获得100个积点。<br />\r\n累计500积点可兑换3小时wifi时长。<br />\r\n【每日签到】<br />\r\n电信用户：可随机获取5-15M 3G流量。<br />\r\n非电信用户：可随机获得5-15积点。<br />\r\n两者不可兼得。<br />\r\n累计500积点可兑换3小时wifi时长。<br />\r\n【活动签到】<br />\r\n完成活动签到后即可获得200个积点<br />\r\n累计500积点可兑换3小时wifi时长。<br />\r\n<a href=\"http://218.1.64.75/cms/upload/ckeditor/images/HereMap.jpg\" target=\"_blank\"><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/HereMap.jpg\" style=\"float:left; height:115px; width:285px\" /></a></span></p>\r\n', '');
INSERT INTO `tb_article` VALUES ('12', '1', '<p>&nbsp;</p>\r\n\r\n<p><br />\r\n<strong>1.基本使用规则<br />\r\nQ1：我不是电信用户，能否使用&ldquo;签个到&rdquo;？</strong><br />\r\nA：能。本产品面向所有移动互联网用户。电信用户可获得3G流量；<br />\r\n不是电信用户，可以通过领取积点获取由中国电信提供的ChinaNet免费WiFi时长。<br />\r\n<br />\r\n<strong>Q2：使用&ldquo;签个到&rdquo;需要收费吗？</strong><br />\r\nA：无需费用。用户使用微信服务号均不收费。<br />\r\n<br />\r\n<strong>Q3：&ldquo;签个到&rdquo;产品是怎么玩儿的？</strong><br />\r\nA：用户通过&ldquo;首次注册&rdquo;、&ldquo;邀请好友&rdquo;、&ldquo;每日签到&rdquo;、&ldquo;参加活动&rdquo;的方式，电信用户即可领取3G流量和积点，积点可兑换礼品。非电信用户则可获得积点，积点可兑换礼品（WiFi时长）。<br />\r\n<br />\r\n<strong>2.产品使用问题</strong><br />\r\n------注册登陆------<br />\r\n<strong>Q1：初次使用&ldquo;签个到&rdquo;如何注册账号？</strong><br />\r\nA：请按照以下步骤注册：<br />\r\n1.电信用户/非电信用户输入手机号码、密码、重复填写密码、邀请人号码；<br />\r\n2.点击&ldquo;发送验证码&rdquo;，手机获取验证码短信以后填入；<br />\r\n以上信息填写正确，点击确认并登陆，提示注册成功。<br />\r\n<br />\r\n<strong>Q2：当完成注册或已注册，该如何登陆?</strong><br />\r\nA：账号处填入手机号，密码处填入已设置的密码；<br />\r\n以上信息填写正确，点击登录即可。<br />\r\n<br />\r\n<strong>Q3：忘记密码，如何找回密码或重制密码？</strong><br />\r\nA：请使用手机号进行登陆，如忘记密码按照以下步骤重制：<br />\r\n1.电信用户/非电信用户输入手机号码；<br />\r\n2.点击&ldquo;发送验证码&rdquo;，手机获取验证码短信以后填入；<br />\r\n3.输入新密码后，点击确定并登陆<br />\r\n修改后的密码保存并默认其登录状态。<br />\r\n<br />\r\n------流量问题------<br />\r\n<strong>Q1：我是电信用户，该如何领取流量？</strong><br />\r\nA：电信用户通过首次注册、邀请好友、每日签到，参加活动等，获取相应额度的电信3G流量。<br />\r\n<br />\r\n<strong>Q2：我是非电信用户，该如何领取流量？</strong><br />\r\nA：平台针对电信用户发放的是3G流量，非电信用户不能直接领取3G流量，需要通过积点来兑换免费WiFi时长。<br />\r\n<br />\r\n<strong>Q3：送的是3G还是4G流量?</strong><br />\r\nA：3G还是4G视您使用的手机套餐业务而定，与您本身的套餐流量额度无关。<br />\r\n<br />\r\n<strong>Q4：&ldquo;签个到&rdquo;需做些什么可领到流量？</strong><br />\r\nA：如果您是活跃用户，每天登录使用并且积极参加活动，可获得不少的流量。具体机制：<br />\r\n3G流量获取规则（电信用户）<br />\r\n每日签到 - 每日可随机获得5-15M的流量，连续签到可获得更多.<br />\r\n首次注册 - 用户注册成功即可获得60M/次.<br />\r\n邀请注册 - 成功邀请好友注册为新用户可得流量奖励10M，限邀请5人/月。被邀请好友（电信用户）获10M流量/次；（非电信用户）获10个积点/次。<br />\r\n参加活动 - 用户通过平台举办的各类活动来获取一定量的流量<br />\r\n<br />\r\n<strong>Q5：如何知晓自己领取了多少流量？</strong><br />\r\nA：您可在登陆后，通过【我的】-【流量历史】板块，您可以查询到自己的流量领取记录。<br />\r\n<br />\r\n<strong>Q6：领到的流量能立即生效的吗？</strong><br />\r\nA：不是。用户提交生效申请，3天后生效，可在流量历史中的月历史中查看是否生效。<br />\r\n<br />\r\n<strong>Q7：当月兑换的3G流量有效期限？</strong><br />\r\nA：30天。<br />\r\n<br />\r\n<strong>Q8：领到的流量可折换成现金或兑换礼品吗？</strong><br />\r\nA：不能。您所领到的流量不能折现、不可兑换成实物礼品或电信其他产品。<br />\r\n<br />\r\n<strong>Q9：领到的流量可以赠送给其他好友使用吗？</strong><br />\r\nA：抱歉不能。平台目前暂不支持&ldquo;流量转赠&rdquo;相关功能。<br />\r\n您在平台上所领到的流量不能转增给好友。<br />\r\n<br />\r\n------积点问题------<br />\r\n<strong>Q1：什么是&ldquo;积点&rdquo;？</strong><br />\r\nA：&ldquo;签个到&rdquo;移动应用平台所发放的积分，即称为&ldquo;积点&rdquo;。<br />\r\n<br />\r\n<strong>Q2：&ldquo;积点&rdquo;有什么用？</strong><br />\r\nA：您可以进入【怎么玩】-【积点兑换】专区兑换礼品；<br />\r\n<br />\r\n<strong>Q3：我是电信用户，该如何领取积点？</strong><br />\r\nA：参加互动活动可获取不等的积点。<br />\r\n<br />\r\n<strong>Q4：我是非电信用户，该如何领取积点？</strong><br />\r\nA：非电信用户通过首次注册、邀请好友、每日签到，参加活动获取相应额度的积点。<br />\r\n<br />\r\n<strong>Q5：如何知晓自己领取了多少积点？</strong><br />\r\nA：您可在登陆后，通过【我的】-【积点历史】板块，您可以查询到自己的积点情况。<br />\r\n<br />\r\n<strong>Q6：领到的积点不使用会过期吗？</strong><br />\r\nA：不会。为了给用户最大的回馈，平台对积点不设置有效期，不用担心积分过期。<br />\r\n<br />\r\n<strong>Q7：领到的积点可以积攒使用吗？</strong><br />\r\nA：可以。您所领到的积分会累积存入您的积点账户中。<br />\r\n<br />\r\n<strong>Q8：领到的积点可以折换成现金吗？</strong><br />\r\nA：抱歉不能。您所领到的积点不可折现。<br />\r\n<br />\r\n<strong>Q9：领到的积点可以兑换哪些物品？</strong><br />\r\nA：您所领取的积点可进行积点兑换：WIFI流量。平台会根据业务拓展不断增加兑换的物品，包括电信其他产品、实物礼品等。<br />\r\n<br />\r\n<strong>Q10：不同账户领到的积点可以合并起来吗？</strong><br />\r\nA：抱歉不能。因此建议您使用同一个账户来获取积分。<br />\r\n<br />\r\n------兑换问题------<br />\r\n<strong>Q1：如何查询我已兑换到的物品记录？</strong><br />\r\nA：您可在登陆后，通过【我的】-【兑换历史】板块，您可以查询到自己的兑换历史记录。<br />\r\n<br />\r\n<strong>Q2：电信用户除了可以兑换WIFI流量外，还能兑换其他物品吗？</strong><br />\r\nA：一期平台业务只能兑换WIFI流量，平台会根据业务需求不断增加兑换的物品供用户选择。后期如: 电信服务、电信宽带升速包、实物礼品等。<br />\r\n<br />\r\n<strong>Q3：需要多少个积点，才能兑换到WIFI流量？</strong><br />\r\nA：（电信/非电信）用户消耗200个积分可换取一个WIFI串码<br />\r\n<br />\r\n<strong>Q4：兑换到的WIFI流量如何发放和使用？</strong><br />\r\nA：平台兑换的WIFI流量是以串码的形式短信发出，一个WIFI时常3小时，使用串码登陆chinanet即可使用。<br />\r\n<br />\r\n<strong>Q5：兑换到的WIFI流量不使用会不会过期？</strong><br />\r\nA：不会，用户不使用串码，则有效期无限。<br />\r\n<br />\r\n<strong>Q6：兑换到的物品可以折换成现金吗？</strong><br />\r\nA：抱歉不能。您所兑换的物品不可折现。<br />\r\n<br />\r\n<strong>Q7：兑换到的物品不喜欢可以换吗？</strong><br />\r\nA：抱歉不能。您所兑换的物品一经兑出不可因任何理由进行更换。<br />\r\n<br />\r\n<strong>Q8：积分兑换的物品是否可以提供发票？</strong><br />\r\nA：抱歉不能。由于积分兑换的并非现金买卖礼品，是平台为答谢广大客户所提供的回馈服务，因此在兑换的物品均不提供发票。<br />\r\n<br />\r\n------活动问题------<br />\r\n<strong>Q1：我已参加过一次活动，可以重复参加吗？</strong><br />\r\nA：根据不同的活动会有不同的参加规则，规则中会有注明，请注意活动信息。<br />\r\n<br />\r\n<strong>Q2：只要参加了活动就能获得礼品吗？</strong><br />\r\nA：不一定。根据不同的活动规则，有不同的礼品奖励机制。<br />\r\n<br />\r\n------联系我们------<br />\r\n<strong>Q1：如何联系在线客服？</strong><br />\r\nA：若您在签个到产品使用中出现任何问题，可直接在微信服务号中进行提问，客服将会在工作日内予以答复。<br />\r\n客服时间：工作日 9:00-18:00</p>\r\n', '');
INSERT INTO `tb_article` VALUES ('13', '90', '', 'http://61.172.243.71/m/front/at/my/point.do');
INSERT INTO `tb_article` VALUES ('14', '91', '', 'http://218.1.64.75/cms/front/at/auth/login.do');
INSERT INTO `tb_article` VALUES ('15', '92', '\r\n\r\n	<title></title>\r\n\r\n\r\n<p><br />\r\n点击下方&lsquo;活动签到&rsquo;按钮后签到，即可完成此次活动，与每日签到一起完成即可在简单生活节现场领取礼品！<br />\r\n<a href=\"http://218.1.64.75/cms/upload/ckeditor/images/HereMap.jpg\" target=\"_blank\"><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/HereMap.jpg\" style=\"float:left; height:113px; width:280px\" /></a><br />\r\n<br />\r\n<br />\r\n<br />\r\n<br />\r\n<u><a href=\"http://218.1.64.75/cms//front/at/my/activity_sign.do\">活动签到</a></u></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n\r\n', '');
INSERT INTO `tb_article` VALUES ('16', '93', '', 'http://218.1.64.75/cms/front/at/my/traffic.do');
INSERT INTO `tb_article` VALUES ('17', '94', '', 'http://218.1.64.75/cms/front/at/my/order.do');
INSERT INTO `tb_article` VALUES ('18', '95', '', 'http://218.1.64.75/cms/front/at/mall/index.do');
INSERT INTO `tb_article` VALUES ('19', '96', '', 'http://218.1.64.75/cms/front/at/my/sign.do');
INSERT INTO `tb_article` VALUES ('21', '100', '<p><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/u%3D4010670789%2C4188088586%26fm%3D21%26gp%3D0.jpg\" style=\"height:100px; width:95px\" />test</p>\r\n', 'tet');
INSERT INTO `tb_article` VALUES ('22', '103', '<p><span style=\"font-size:2em\"><a href=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen1.jpg\" target=\"_blank\"><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen1.jpg\" style=\"float:left; height:131px; width:285px\" /></a><br />\r\n<br />\r\n<br />\r\n<br />\r\n<br />\r\n<strong>上海简单生活节你知道吗？</strong></span></p>\r\n\r\n<p><span style=\"font-size:2em\">做喜欢的事，让喜欢的事有价值。金秋十月的世博公园，2014简单生活节将首度在内地隆重上演，上月底揭晓的首批豪华阵容&ldquo;亮瞎了&rdquo;大众眼球后，在这夏日燥热中，这张名单上也迎来了又一股台湾小清新文艺风&mdash;&mdash;&ldquo;创作才子&rdquo;卢广仲、&ldquo;独立女声&rdquo;魏如萱、&quot;年轻鬼才&quot;Suming（舒米恩）以及&ldquo;民谣歌手&ldquo;陈建年、林生祥及其音乐伙伴大竹研等，都将来到上海简单生活节，令小清新浪潮席卷10月的世博公园。</span></p>\r\n\r\n<p><span style=\"font-size:2em\"><a href=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen2.jpg\" target=\"_blank\"><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen2.jpg\" style=\"height:212px; width:150px\" /></a><br />\r\n李宗盛掌舵，云集张惠妹陈绮贞朴树<br />\r\n简单生活节十月首度进内地落户上海<br />\r\n首发名单已是重量级，8、9月还将陆续揭晓<br />\r\n比音乐节宽一点，让喜欢的事有价值</span></p>\r\n\r\n<p><span style=\"font-size:2em\"><a href=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen3.jpg\" target=\"_blank\"><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen3.jpg\" style=\"float:left; height:93px; width:120px\" /></a><a href=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen4.jpg\" target=\"_blank\"><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen4.jpg\" style=\"float:left; height:92px; width:122px\" /></a><br />\r\n<br />\r\n<br />\r\n<br />\r\n<br />\r\n朋克传奇+雷鬼天团+治愈名团<br />\r\n简单生活节三天欧美乐队带来三种惊喜<br />\r\n<strong>地图</strong></span></p>\r\n\r\n<p><span style=\"font-size:2em\"><a href=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen5.png\" target=\"_blank\"><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen5.png\" style=\"float:left; height:200px; width:280px\" /></a><br />\r\n<br />\r\n<br />\r\n<br />\r\n<br />\r\n<br />\r\n<br />\r\n<br />\r\n<strong>节目单</strong></span></p>\r\n\r\n<p><span style=\"font-size:2em\"><a href=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen6.jpg\" target=\"_blank\"><img alt=\"\" src=\"http://218.1.64.75/cms/upload/ckeditor/images/xinwen6.jpg\" style=\"float:left; height:676px; width:250px\" /></a></span></p>\r\n', '');
INSERT INTO `tb_article` VALUES ('23', '106', '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p style=\"text-align: center;\">123123</p>\r\n</body>\r\n</html>\r\n', '');
INSERT INTO `tb_article` VALUES ('24', '2', '\r\n\r\n	\r\n\r\n\r\n<p>服务条款服务条款服务条款服务条款服务条款服务条款服务条款服务条款服务条款</p>\r\n\r\n\r\n', '');
INSERT INTO `tb_article_group` VALUES ('1', '70', '74', '1');
INSERT INTO `tb_article_group` VALUES ('2', '64', '74', '2');
INSERT INTO `tb_article_group` VALUES ('9', '78', '88', '1');
INSERT INTO `tb_article_group` VALUES ('10', '79', '88', '1');
INSERT INTO `tb_article_group` VALUES ('12', '92', '74', '3');
INSERT INTO `tb_article_group` VALUES ('14', '1', '99', '0');
INSERT INTO `tb_article_group` VALUES ('21', '103', '74', '0');
INSERT INTO `tb_article_group` VALUES ('22', '91', '74', '4');
INSERT INTO `tb_auto_reply_config` VALUES ('13', 'sys.subscribe', '108', '0', null);
INSERT INTO `tb_cache` VALUES ('1', 'weixin.access.token', 'eRYhbvUPoIRDg67Ph5V7zj3cPsGh2fEy_bN4TD1Px4xF527dgAfSBi9w_hdfvvrEvP-19eFVODkXNNqTWvY7Ew', '3600', '2014-09-29 15:46:20');
INSERT INTO `tb_config` VALUES ('1', 'system.config.tele.point', '0', '电信用户积分', '0', null);
INSERT INTO `tb_config` VALUES ('2', 'system.config.tele.traffic', '0', '电信用户流量', '0', null);
INSERT INTO `tb_config` VALUES ('3', 'system.config.other.point', '0', '非电信用户积分', '0', null);
INSERT INTO `tb_config` VALUES ('4', 'system.config.other.traffic', '0', '非电信用户流量', '0', null);
INSERT INTO `tb_config` VALUES ('5', 'system.config.tele.point', '0', '电信用户积分', '0', '2');
INSERT INTO `tb_config` VALUES ('6', 'system.config.tele.traffic', '1', '电信用户流量', '0', '2');
INSERT INTO `tb_config` VALUES ('7', 'system.config.other.point', '1', '非电信用户积分', '0', '2');
INSERT INTO `tb_config` VALUES ('8', 'system.config.other.traffic', '0', '非电信用户流量', '0', '2');
INSERT INTO `tb_config` VALUES ('9', 'system.config.tele.point', '0', '电信用户积分', '0', '3');
INSERT INTO `tb_config` VALUES ('10', 'system.config.tele.traffic', '1', '电信用户流量', '0', '3');
INSERT INTO `tb_config` VALUES ('11', 'system.config.other.point', '1', '非电信用户积分', '0', '3');
INSERT INTO `tb_config` VALUES ('12', 'system.config.other.traffic', '0', '非电信用户流量', '0', '3');
INSERT INTO `tb_config` VALUES ('17', 'system.config.tele.point', '0', '电信用户积分', '0', '5');
INSERT INTO `tb_config` VALUES ('18', 'system.config.tele.traffic', '1', '电信用户流量', '0', '5');
INSERT INTO `tb_config` VALUES ('19', 'system.config.other.point', '1', '非电信用户积分', '0', '5');
INSERT INTO `tb_config` VALUES ('20', 'system.config.other.traffic', '0', '非电信用户流量', '0', '5');
INSERT INTO `tb_config` VALUES ('21', 'system.config.tele.point', '0', '电信用户积分', '0', '4');
INSERT INTO `tb_config` VALUES ('22', 'system.config.tele.traffic', '1', '电信用户流量', '0', '4');
INSERT INTO `tb_config` VALUES ('23', 'system.config.other.point', '0', '非电信用户积分', '0', '4');
INSERT INTO `tb_config` VALUES ('24', 'system.config.other.traffic', '0', '非电信用户流量', '0', '4');
INSERT INTO `tb_m_menu` VALUES ('16', '我的', '', '2', null, null);
INSERT INTO `tb_m_menu` VALUES ('18', '怎么玩', '', '2', null, null);
INSERT INTO `tb_m_menu` VALUES ('19', '签个到', 'm_cms_96', '3', null, '96');
INSERT INTO `tb_m_menu` VALUES ('20', '积点历史', 'm_cms_90', '3', '16', '90');
INSERT INTO `tb_m_menu` VALUES ('21', '流量历史', 'm_cms_93', '3', '16', '93');
INSERT INTO `tb_m_menu` VALUES ('32', '积点兑换', 'm_cms_95', '3', '18', '95');
INSERT INTO `tb_m_menu` VALUES ('34', '热门活动', 'm_cms_74', '3', '18', '74');
INSERT INTO `tb_m_menu` VALUES ('37', '兑换历史', 'm_cms_94', '3', '16', '94');
INSERT INTO `tb_m_menu` VALUES ('38', '帮助Q&A', 'm_cms_1', '3', '18', '1');
INSERT INTO `tb_m_menu` VALUES ('40', '活动签到', 'm_cms_92', '3', '18', '92');
INSERT INTO `tb_node` VALUES ('1', '帮助Q&A', '1', '2014-09-27 12:33:08', 'Article', '单图文', '签个到客服', '2014-09-17 10:59:18', '点击查看更多帮助信息', '/article/1.jpg');
INSERT INTO `tb_node` VALUES ('2', '服务条款', '1', '2014-09-29 16:21:32', 'Article', '单图文', '服务条款', '2014-09-26 19:20:16', '服务条款', '\\article\\109.jpg');
INSERT INTO `tb_node` VALUES ('64', '活动一(*^__^*) 【每日签到】', '1', '2014-09-28 14:08:35', 'Article', '单图文', '签个到团队', '2014-09-05 17:35:44', '', '/article/64.jpg');
INSERT INTO `tb_node` VALUES ('70', '【活动须知】亲！参加以下两项活动必读哟~', '1', '2014-09-25 15:21:52', 'Article', '单图文', '签个到团队', '2014-09-05 18:02:54', '', '/article/70.jpg');
INSERT INTO `tb_node` VALUES ('74', '简单生活节指南', '1', '2014-09-29 18:02:27', 'ArticleGroup', '多图文', '超级管理员', '2014-09-06 09:01:36', null, null);
INSERT INTO `tb_node` VALUES ('77', '测试一个简单的投票', '1', '2014-09-16 14:46:34', 'Poll', '投票', '超级管理员', '2014-09-08 10:53:20', '这是一个忧伤的投票测试', '/poll/77.png');
INSERT INTO `tb_node` VALUES ('78', '测试2', '1', '2014-09-08 10:52:45', 'Poll', '投票', '超级管理员', '2014-09-08 10:53:20', null, null);
INSERT INTO `tb_node` VALUES ('79', '计分型问卷', '1', '2014-09-08 10:52:45', 'Poll', '投票', '超级管理员', '2014-09-08 10:53:20', null, null);
INSERT INTO `tb_node` VALUES ('90', '积点历史', '1', '2014-09-25 11:20:02', 'Article', '单图文', '签到团队', '2014-09-18 23:54:29', '点击查看您获取积点的详细记录', '/article/90.jpg');
INSERT INTO `tb_node` VALUES ('91', '注册登陆', '1', '2014-09-24 22:21:35', 'Article', '单图文', '签个到团队', '2014-09-19 10:13:48', '立刻注册登陆领取流量和积点', '/article/91.jpg');
INSERT INTO `tb_node` VALUES ('92', '活动二(*^__^*) 【活动签到】', '1', '2014-09-26 18:54:43', 'Article', '单图文', '签个到团队', '2014-09-21 17:06:15', '', '/article/92.jpg');
INSERT INTO `tb_node` VALUES ('93', '流量历史', '1', '2014-09-24 17:39:03', 'Article', '单图文', '签到团队', '2014-09-22 10:21:28', '点击查看您的流量详情', '/article/93.jpg');
INSERT INTO `tb_node` VALUES ('94', '兑换历史', '1', '2014-09-24 17:41:12', 'Article', '单图文', '签到团队', '2014-09-22 10:34:44', '点击查看我的兑换历史', '/article/94.jpg');
INSERT INTO `tb_node` VALUES ('95', '积点兑换', '1', '2014-09-24 17:46:31', 'Article', '单图文', '签到团队', '2014-09-22 10:37:07', '点击查看丰富的可兑换礼品', '/article/95.jpg');
INSERT INTO `tb_node` VALUES ('96', '每日签到', '1', '2014-09-25 00:26:00', 'Article', '单图文', '签个到团队', '2014-09-22 14:29:47', '每日签到获取电信流量和积点', '/article/96.jpg');
INSERT INTO `tb_node` VALUES ('100', 'test', '1', '2014-09-27 12:38:25', 'Article', '单图文', 'test', '2014-09-23 12:55:06', 'etsdfasdfad', 'ad\\article\\100.jpg');
INSERT INTO `tb_node` VALUES ('102', '【调查问卷】呦，快来疯抢礼物吧！', '1', '2014-09-23 15:49:29', 'Poll', '投票', '超级管理员', '2014-09-23 15:49:29', '圣诞节了快过节了改扩建的反馈给经典款非感觉掉率反光镜贷款方就搞定了反光镜抵抗力费跟进对方龙桂江', null);
INSERT INTO `tb_node` VALUES ('103', '简单生活节指南', '1', '2014-09-25 15:57:30', 'Article', '单图文', '签个到团队', '2014-09-23 20:22:45', '点击查看了解更多简单生活节咨询', '/article/103.jpg');
INSERT INTO `tb_node` VALUES ('106', 'test1qwerqwer', '1', '2014-09-24 18:15:09', 'Article', '单图文', 'etweetqwerq', '2014-09-24 18:15:09', 'qwer', null);
INSERT INTO `tb_node` VALUES ('108', '欢迎语句', '1', '2014-09-25 10:12:58', 'Text', '文本', '超级管理员', '2014-09-25 10:12:58', null, null);
INSERT INTO `tb_point_detail` VALUES ('215', '22', 'user.sign', '用户签到', '2014-09-25 00:17:46', '13', '命﹫运', 'oXM8DuJjAoKkM-638smVE5CYgmN0');
INSERT INTO `tb_point_detail` VALUES ('216', '22', 'activity.sign', '活动签到', '2014-09-25 00:19:09', '0', '命﹫运', 'oXM8DuJjAoKkM-638smVE5CYgmN0');
INSERT INTO `tb_point_detail` VALUES ('217', '45', 'user.sign', '用户签到', '2014-09-25 00:26:23', '15', 'Listening', 'oXM8DuHbJQfC3FHaJjOclOk57OhQ');
INSERT INTO `tb_point_detail` VALUES ('218', '45', 'activity.sign', '活动签到', '2014-09-25 00:28:09', '0', 'Listening', 'oXM8DuHbJQfC3FHaJjOclOk57OhQ');
INSERT INTO `tb_point_detail` VALUES ('219', '61', 'user.sign', '用户签到', '2014-09-25 01:39:33', '7', '大头娃娃', 'oXM8DuPVYw5tU_PeJEAUPXQwENV4');
INSERT INTO `tb_point_detail` VALUES ('220', '37', 'user.sign', '用户签到', '2014-09-25 14:35:32', '0', '麒麟', 'oXM8DuNedIAmNoL2cF7LXbaUmJQs');
INSERT INTO `tb_point_detail` VALUES ('221', '37', 'activity.sign', '活动签到', '2014-09-25 14:36:59', '0', '麒麟', 'oXM8DuNedIAmNoL2cF7LXbaUmJQs');
INSERT INTO `tb_point_detail` VALUES ('222', '43', 'user.sign', '用户签到', '2014-09-26 13:43:20', '0', '挺好', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc');
INSERT INTO `tb_point_detail` VALUES ('223', '22', 'user.sign', '用户签到', '2014-09-26 16:01:50', '15', '命﹫运', 'oXM8DuJjAoKkM-638smVE5CYgmN0');
INSERT INTO `tb_point_detail` VALUES ('224', '22', 'user.sign', '用户签到', '2014-09-28 14:10:35', '9', '命﹫运', 'oXM8DuJjAoKkM-638smVE5CYgmN0');
INSERT INTO `tb_poll` VALUES ('1', '0', '2014-08-15 17:02:10', '2014-10-03 17:00:00', '1', '77');
INSERT INTO `tb_poll` VALUES ('3', '0', '2014-08-15 22:14:49', '2014-08-29 22:00:00', '2', '78');
INSERT INTO `tb_poll` VALUES ('4', '1', '2014-09-05 15:50:33', '2014-11-29 00:00:00', '1', '79');
INSERT INTO `tb_poll` VALUES ('6', '2', '2014-09-23 15:47:52', '2014-09-30 00:00:00', '1', '102');
INSERT INTO `tb_poll_option` VALUES ('1', '1', '0', '你最喜欢什么颜色？', null);
INSERT INTO `tb_poll_option` VALUES ('2', '1', '1', '你喜欢以下哪几种运动？', null);
INSERT INTO `tb_poll_option` VALUES ('4', '1', '2', '今天心情怎么样？', null);
INSERT INTO `tb_poll_option` VALUES ('5', '1', '0', '你正在使用的浏览器是', null);
INSERT INTO `tb_poll_option` VALUES ('6', '4', '0', '电信客服电话是多少？', '10');
INSERT INTO `tb_poll_option` VALUES ('7', '4', '0', '下面哪个图标是电信Logo', '15');
INSERT INTO `tb_poll_option` VALUES ('9', '6', '0', '你觉得简单生活节特点是什么？', null);
INSERT INTO `tb_poll_option_record` VALUES ('1', '1', '1', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('2', '1', '2', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('3', '1', '4', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('4', '1', '5', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('5', '2', '1', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('6', '2', '2', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('7', '2', '4', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('8', '2', '5', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('9', '3', '1', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('10', '3', '2', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('11', '3', '4', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('12', '3', '5', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('14', '6', '2', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('15', '6', '1', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('16', '6', '5', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('17', '6', '4', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('18', '7', '2', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('19', '7', '1', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('20', '7', '4', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('21', '8', '2', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('22', '8', '1', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('23', '8', '5', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('24', '8', '4', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('25', '9', '2', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('26', '9', '1', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('27', '9', '5', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('28', '9', '4', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('29', '10', '2', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('30', '10', '1', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('31', '10', '5', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('32', '10', '4', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('33', '13', '9', 'N');
INSERT INTO `tb_poll_option_record` VALUES ('34', '14', '9', 'N');
INSERT INTO `tb_poll_option_value` VALUES ('1', '1', '红色', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('2', '1', '蓝色', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('3', '1', '白色', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('4', '1', '绿色', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('5', '2', '篮球', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('6', '2', '足球', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('7', '2', '网球', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('8', '2', '羽毛球', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('9', '5', 'Android原生浏览器', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('10', '5', 'UC', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('11', '5', 'Chrome', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('12', '5', 'Safari', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('13', '5', '猎豹', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('14', '6', '10000', 'Y', '0');
INSERT INTO `tb_poll_option_value` VALUES ('15', '6', '10086', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('16', '6', '10010', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('17', '7', '\\poll\\17.png', 'N', '1');
INSERT INTO `tb_poll_option_value` VALUES ('21', '9', '崇尚简单生活理念', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('22', '9', '突出环保理念', 'N', '0');
INSERT INTO `tb_poll_option_value` VALUES ('23', '9', '让其自我发觉对于生活的态度', 'N', '0');
INSERT INTO `tb_poll_option_value_record` VALUES ('1', '1', '1', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('2', '2', '5', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('3', '2', '6', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('4', '3', null, 'good');
INSERT INTO `tb_poll_option_value_record` VALUES ('5', '4', '10', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('6', '5', '1', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('7', '6', '6', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('8', '6', '7', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('9', '7', null, 'bad');
INSERT INTO `tb_poll_option_value_record` VALUES ('10', '8', '10', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('11', '9', '2', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('12', '10', '5', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('13', '10', '6', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('14', '10', '8', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('15', '11', null, 'just so so');
INSERT INTO `tb_poll_option_value_record` VALUES ('16', '12', '9', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('18', '14', '5', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('19', '14', '6', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('20', '15', '1', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('21', '16', '10', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('22', '17', null, '不错');
INSERT INTO `tb_poll_option_value_record` VALUES ('23', '18', '5', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('24', '18', '7', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('25', '19', '1', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('26', '20', null, '');
INSERT INTO `tb_poll_option_value_record` VALUES ('27', '21', '5', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('28', '21', '6', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('29', '22', '2', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('30', '23', '9', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('31', '24', null, 'uuuuu');
INSERT INTO `tb_poll_option_value_record` VALUES ('32', '25', '8', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('33', '26', '1', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('34', '27', '9', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('35', '28', null, '你妈逼的');
INSERT INTO `tb_poll_option_value_record` VALUES ('36', '29', '5', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('37', '30', '1', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('38', '31', '10', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('39', '32', null, '');
INSERT INTO `tb_poll_option_value_record` VALUES ('40', '33', '23', null);
INSERT INTO `tb_poll_option_value_record` VALUES ('41', '34', '22', null);
INSERT INTO `tb_poll_record` VALUES ('1', '1', '23', null, 'N');
INSERT INTO `tb_poll_record` VALUES ('2', '1', '22', null, 'N');
INSERT INTO `tb_poll_record` VALUES ('3', '1', '21', null, 'Y');
INSERT INTO `tb_poll_record` VALUES ('6', '1', '35', null, 'Y');
INSERT INTO `tb_poll_record` VALUES ('7', '1', '43', null, 'Y');
INSERT INTO `tb_poll_record` VALUES ('8', '1', '37', null, 'N');
INSERT INTO `tb_poll_record` VALUES ('9', '1', '61', null, 'N');
INSERT INTO `tb_poll_record` VALUES ('10', '1', '45', null, 'N');
INSERT INTO `tb_poll_record` VALUES ('11', '6', '22', null, 'N');
INSERT INTO `tb_poll_record` VALUES ('12', '6', '61', null, 'N');
INSERT INTO `tb_poll_record` VALUES ('13', '6', '45', null, 'N');
INSERT INTO `tb_poll_record` VALUES ('14', '6', '43', null, 'N');
INSERT INTO `tb_product` VALUES ('1', 'WIFI时长卡', '/product/1.jpg', '兑换需：500积点\r\nWIFI时长：3小时 \r\n发放形式：<短信>\r\n使用方式：提取短信中串码登陆ChinaNet进行使用。\r\n有效期限：无限制（串码使用后为3小时时长）。', '0', '500', '2014-08-23 10:57:59');
INSERT INTO `tb_product` VALUES ('25', 'java', '\\product\\25.jpg', '', '0', '123', '2014-09-30 13:26:28');
INSERT INTO `tb_section_agent` VALUES ('3', '的', null, null, null);
INSERT INTO `tb_section_agent` VALUES ('4', 'a', null, null, null);
INSERT INTO `tb_text` VALUES ('42', '您好，感谢您的关注！', '108');
INSERT INTO `tb_traffic_detail` VALUES ('140', '22', 'user.sign', '用户签到', '2014-09-25 00:17:46', null, '0', '命﹫运', 'oXM8DuJjAoKkM-638smVE5CYgmN0', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('141', '22', 'activity.sign', '活动签到', '2014-09-25 00:19:09', null, '0', '命﹫运', 'oXM8DuJjAoKkM-638smVE5CYgmN0', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('142', '45', 'user.sign', '用户签到', '2014-09-25 00:26:23', null, '0', 'Listening', 'oXM8DuHbJQfC3FHaJjOclOk57OhQ', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('143', '45', 'activity.sign', '活动签到', '2014-09-25 00:28:09', null, '0', 'Listening', 'oXM8DuHbJQfC3FHaJjOclOk57OhQ', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('144', '61', 'user.sign', '用户签到', '2014-09-25 01:39:33', null, '0', '大头娃娃', 'oXM8DuPVYw5tU_PeJEAUPXQwENV4', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('145', '37', 'user.sign', '用户签到', '2014-09-25 14:35:32', null, '6', '麒麟', 'oXM8DuNedIAmNoL2cF7LXbaUmJQs', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('146', '37', 'activity.sign', '活动签到', '2014-09-25 14:36:59', null, '200', '麒麟', 'oXM8DuNedIAmNoL2cF7LXbaUmJQs', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('147', '43', 'user.sign', '用户签到', '2014-09-26 13:43:20', null, '14', '挺好', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('148', '22', 'user.sign', '用户签到', '2014-09-26 16:01:50', null, '0', '命﹫运', 'oXM8DuJjAoKkM-638smVE5CYgmN0', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('149', '22', 'user.sign', '用户签到', '2014-09-28 14:10:35', null, '0', '命﹫运', 'oXM8DuJjAoKkM-638smVE5CYgmN0', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('150', '43', 'user.sign', '用户签到', '2014-09-29 10:35:55', null, '10000', '', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', '0', null);
INSERT INTO `tb_traffic_detail` VALUES ('151', '43', null, '申请流量', '2014-09-29 10:40:25', null, '-100', '挺好', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', '2', null);
INSERT INTO `tb_traffic_detail` VALUES ('152', '43', null, '申请流量', '2014-09-30 10:21:15', '2014-09-30 10:23:09', '-100', '挺好', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', '3', null);
INSERT INTO `tb_traffic_detail` VALUES ('153', '43', null, '申请流量', '2014-09-30 10:26:26', null, '-100', '挺好', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', '1', null);
INSERT INTO `tb_traffic_detail` VALUES ('154', '43', null, '申请流量', '2014-09-30 10:28:30', null, '-100', '挺好', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', '2', null);
INSERT INTO `tb_traffic_detail` VALUES ('155', '43', null, '申请流量', '2014-09-30 10:31:37', null, '-100', '挺好', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', '2', null);
INSERT INTO `tb_traffic_detail` VALUES ('156', '43', null, '申请流量...', '2014-09-30 10:32:37', null, '-100', '挺好', 'oXM8DuNdk-3tS5LCw9zzkn-Q4LNc', '2', null);
INSERT INTO `tb_user_profile` VALUES ('18', '35', '13321870879');
INSERT INTO `tb_user_profile` VALUES ('19', '43', '18939802525');
INSERT INTO `tb_user_profile` VALUES ('20', '37', '18918589383');
INSERT INTO `tb_user_profile` VALUES ('21', '45', '13918745727');
INSERT INTO `tb_user_profile` VALUES ('22', '61', '13482234891');
INSERT INTO `tb_user_profile` VALUES ('23', '77', '18918796913');
INSERT INTO `tb_user_profile` VALUES ('24', '78', '13501953936');
INSERT INTO `tb_user_profile` VALUES ('26', '22', '18501752646');

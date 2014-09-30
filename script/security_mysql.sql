DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `permissions` text DEFAULT NULL,
  `cn_name` varchar(255) DEFAULT NULL,
  `internal` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sec_role`(`name`, `cn_name`, `internal`) VALUES ('admin', '超级管理员组', 1);

DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `avatar` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sec_user`(`login_name`, `password`, `name`) VALUES ('admin','0192023a7bbd73250516f069df18b500','超级管理员');


DROP TABLE IF EXISTS `sec_user_role`;
CREATE TABLE `sec_user_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_idx` (`role_id`),
  KEY `fk_user_role_user_idx` (`user_id`),
  CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `sec_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `sec_user_role`(`user_id`, `role_id`) VALUES (1, 1);

DROP TABLE IF EXISTS `sec_menu_item`;
CREATE TABLE `sec_menu_item` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `url` VARCHAR(255),
  `css_class` VARCHAR(100),
  `parent_id` BIGINT,
  `role_id` BIGINT,
  PRIMARY KEY (`id`),
  KEY `fk_menu_item_parent_idx` (`parent_id`),
  KEY `fk_menu_item_role_idx` (`role_id`),
  CONSTRAINT `fk_menu_item_parent` FOREIGN KEY (`parent_id`) REFERENCES `sec_menu_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_menu_item_role` FOREIGN KEY (`role_id`) REFERENCES `sec_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sec_menu_item` VALUES ('1', '全局', '/admin/index.do', 'icon-dashboard', null, '1');
INSERT INTO `sec_menu_item` VALUES ('2', '任务管理', '/component/basic/task/index.do', null, '1', '1');
INSERT INTO `sec_menu_item` VALUES ('3', '通知管理', '/component/basic/notify/index.do', null, '1', '1');
INSERT INTO `sec_menu_item` VALUES ('4', '权限管理', null, 'icon-desktop', null, '1');
INSERT INTO `sec_menu_item` VALUES ('5', '用户管理', '/component/security/user/index.do', null, '4', '1');
INSERT INTO `sec_menu_item` VALUES ('6', '角色管理', '/component/security/role/index.do', null, '4', '1');
INSERT INTO `sec_menu_item` VALUES ('7', '菜单管理', '/component/security/menu/index.do', null, '4', '1');
INSERT INTO `sec_menu_item` VALUES ('8', 'CMS', null, 'icon-edit', null, '1');
INSERT INTO `sec_menu_item` VALUES ('9', '内容管理', '/admin/content/index.do', null, '8', '1');
INSERT INTO `sec_menu_item` VALUES ('10', '微信管理', null, 'icon-comment-alt', null, '1');
INSERT INTO `sec_menu_item` VALUES ('11', '菜单管理', '/admin/mobile/menu/index.do', null, '10', '1');
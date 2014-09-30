DROP TABLE IF EXISTS `notify_notification`;
CREATE TABLE `notify_notification` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `title` varchar(255) NOT NULL,
    `create_time` datetime NOT NULL,
    `read_time` datetime NOT NULL,
    `type` tinyint(1) DEFAULT NULL,
    `status` tinyint(1) DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `notify_task`;
CREATE TABLE `notify_task` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `progress` int NOT NULL,
    `start_time` datetime NOT NULL,
    `end_time` datetime NOT NULL,
    `status` tinyint(1) DEFAULT NULL,
    `command` varchar(255) DEFAULT NULL,
    `message` TEXT DEFAULT NULL,
    `title` varchar(255) not null,
    `user_id` BIGINT NOT NULL,
    KEY `fk_notify_task_user_idx` (`user_id`),
    CONSTRAINT `fk_notify_task_user` FOREIGN KEY (`user_id`)
        REFERENCES `sec_user` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_poll`;
CREATE TABLE `tb_poll` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` TINYINT(4) DEFAULT 0 NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `status` tinyint(4) DEFAULT 0 NOT NULL,
  `node_id` BIGINT NOT NULL,
  KEY `fk_poll_node_idx` (`node_id`),
  CONSTRAINT `fk_poll_node` FOREIGN KEY (`node_id`)
  REFERENCES `tb_node` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_poll_visibility`;
CREATE TABLE `tb_poll_visibility` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `poll_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  KEY `fk_poll_visibility_poll_idx` (`poll_id`),
  CONSTRAINT `fk_poll_visibility_poll` FOREIGN KEY (`poll_id`)
  REFERENCES `tb_poll` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `fk_poll_visibility_author_idx` (`user_id`),
  CONSTRAINT `fk_poll_visibility_author` FOREIGN KEY (`user_id`)
  REFERENCES `sec_user` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_poll_option`;
CREATE TABLE `tb_poll_option` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `poll_id` BIGINT NOT NULL,
  `type` tinyint(4) NOT NULL,
  `title` varchar(255) NOT NULL,
  `score` FLOAT NULL,
  KEY `fk_poll_option_poll_idx` (`poll_id`),
  CONSTRAINT `fk_poll_option_poll` FOREIGN KEY (`poll_id`)
  REFERENCES `tb_poll` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_poll_option_value`;
CREATE TABLE `tb_poll_option_value` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `poll_option_id` BIGINT NOT NULL,
  `value` varchar(255) NOT NULL,
  `answer` CHAR(1) NOT NULL DEFAULT 'N',
  `type` tinyint(4) NOT NULL,
  KEY `fk_poll_option_value_poll_option_idx` (`poll_option_id`),
  CONSTRAINT `fk_poll_option_value_poll_option` FOREIGN KEY (`poll_option_id`)
  REFERENCES `tb_poll_option` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_poll_record`;
CREATE TABLE `tb_poll_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `poll_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `score` FLOAT NULL,
  `reward` CHAR(1) NOT NULL DEFAULT 'N',
  KEY `fk_poll_record_poll_idx` (`poll_id`),
  KEY `fk_poll_record_user_idx` (`user_id`),
  CONSTRAINT `fk_poll_record_poll` FOREIGN KEY (`poll_id`)
  REFERENCES `tb_poll` (`id`)
    ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_poll_record_user` FOREIGN KEY (`user_id`)
  REFERENCES `sec_user` (`id`)
    ON DELETE NO ACTION ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_poll_option_record`;
CREATE TABLE `tb_poll_option_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `poll_record_id` BIGINT NOT NULL,
  `poll_option_id` BIGINT NOT NULL,
  `correct` CHAR(1) NOT NULL DEFAULT 'N',
  KEY `fk_poll_option_record_poll_record_idx` (`poll_record_id`),
  CONSTRAINT `fk_poll_option_record_poll_record` FOREIGN KEY (`poll_record_id`)
  REFERENCES `tb_poll_record` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `fk_poll_option_record_poll_option_idx` (`poll_option_id`),
  CONSTRAINT `fk_poll_option_record_poll_option` FOREIGN KEY (`poll_option_id`)
  REFERENCES `tb_poll_option` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `tb_poll_option_value_record`;
CREATE TABLE `tb_poll_option_value_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `poll_option_record_id` BIGINT NOT NULL,
  `poll_option_value_id` BIGINT,
  `text_value` VARCHAR(1024),
  KEY `fk_value_record_poll_option_record_idx` (`poll_option_record_id`),
  CONSTRAINT `fk_value_record_poll_option_record` FOREIGN KEY (`poll_option_record_id`)
  REFERENCES `tb_poll_option_record` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `fk_value_record_option_value_idx` (`poll_option_value_id`),
  CONSTRAINT `fk_value_record_option_value` FOREIGN KEY (`poll_option_value_id`)
    REFERENCES `tb_poll_option_value` (`id`)
    ON DELETE NO ACTION ON UPDATE CASCADE ,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_m_menu`;
CREATE TABLE `tb_m_menu` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `value` VARCHAR(255),
  `type` TINYINT(4) DEFAULT 0,
  `parent_id` BIGINT,
  PRIMARY KEY (`id`),
  KEY `fk_tb_m_menu_parent_idx` (`parent_id`),
  CONSTRAINT `fk_tb_m_menu_parent` FOREIGN KEY (`parent_id`) REFERENCES `tb_m_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
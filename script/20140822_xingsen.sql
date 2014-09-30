CREATE TABLE `weixin_cms`.`tb_product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `pic` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `status` TINYINT NOT NULL,
  `price` DOUBLE NOT NULL,
  `last_modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `weixin_cms`.`tb_order` (
  `id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `order_num` VARCHAR(255) NOT NULL,
  `total_price` DOUBLE NOT NULL,
  `create_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user_id_idx` (`user_id` ASC),
  CONSTRAINT `fk_order_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `weixin_cms`.`sec_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `weixin_cms`.`tb_order_item` (
  `id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  `count` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_item_order_idx` (`order_id` ASC),
  INDEX `fk_order_item_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_order_item_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `weixin_cms`.`tb_order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_item_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `weixin_cms`.`tb_product` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `weixin_cms`.`tb_product`
CHANGE COLUMN `price` `price` DOUBLE NULL ;

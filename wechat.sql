DROP DATABASE IF EXISTS `wechat`;
CREATE DATABASE IF NOT EXISTS `wechat` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
use `wechat`;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `wechat_user`;
CREATE TABLE IF NOT EXISTS `wechat_user`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` VARCHAR(50) DEFAULT NULL ,
  `phone` VARCHAR(20) DEFAULT NULL ,
  `question` VARCHAR(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` VARCHAR(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` INT(4) NOT NULL COMMENT '角色0-管理员，1-普通用户',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 10,DEFAULT CHARSET = utf8;

BEGIN;
INSERT INTO `wechat_user` VALUES ('1', 'admin', '9a844e2a7c2278261b9e12e2a754569c', 'admin@happymmall.com', '13800138000', '问题', '答案', '1', '2016-11-06 16:56:45', '2017-04-04 19:27:36'), ('13', 'geely', '2f25530555eb460188bea5302f08d4e9', 'geely@happymmall.com', '13800138000', '问题', '答案', '0', '2016-11-19 22:19:25', '2016-11-19 22:19:25'), ('17', 'rosen', '2f25530555eb460188bea5302f08d4e9', 'rosen1@happymmall.com', '13800138000', '问题', '答案', '0', '2017-03-17 10:51:33', '2017-04-09 23:13:26'), ('21', 'soonerbetter', '2f25530555eb460188bea5302f08d4e9', 'test06@happymmall.com', '13800138000', '105204', '105204', '0', '2017-04-13 21:26:22', '2017-04-13 21:26:22');
COMMIT;

DROP TABLE IF EXISTS `wechat_item`;
CREATE TABLE IF NOT EXISTS `wechat_item`(
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '图文id',
  `item_id` VARCHAR(20) NOT NULL COMMENT '图文标识',
  `title` VARCHAR(20) NOT NULL COMMENT '图文标题',
  `description` VARCHAR(50)  COMMENT '图文描述',
  `url` VARCHAR(100) NOT NULL COMMENT '图文内容URL',
  `img_url` VARCHAR(200) NOT NULL COMMENT '图文图片URL',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id_unique` (`item_id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 1000,DEFAULT CHARSET = utf8;

BEGIN;
INSERT INTO `wechat_item` (`item_id`,`title`,`description`,`url`,`img_url`,`create_time`,`update_time`) VALUES ('neUzquH','大辣娇--加1元再来1桶','白象大辣娇，加1元再来1桶，感恩回馈消费者！','http://wangzy.tunnel.qydev.com/wechat-tools/page/push/neUzquH.html','http://ali1.rabbitpre.com/db290bb8-484b-44e0-9980-66a2f85ec573.jpeg@0-0-0-0a_401w_70Q_1an.src?crossorigin=1',now(),now());
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
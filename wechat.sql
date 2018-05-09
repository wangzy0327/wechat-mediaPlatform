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
  `title` VARCHAR(30) NOT NULL COMMENT '图文标题',
  `description` VARCHAR(50)  COMMENT '图文描述',
  `url` VARCHAR(100) NOT NULL COMMENT '图文内容URL',
  `img_url` VARCHAR(200) NOT NULL COMMENT '图文图片URL',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id_unique` (`item_id`) USING BTREE
)COMMENT'图文消息表',ENGINE = InnoDB AUTO_INCREMENT = 1000,DEFAULT CHARSET = utf8;

BEGIN;
INSERT INTO `wechat_item` (`item_id`,`title`,`description`,`url`,`img_url`,`create_time`,`update_time`) VALUES ('neUzquH','大辣娇--加1元再来1桶','白象大辣娇，加1元再来1桶，感恩回馈消费者！','http://wangzy.tunnel.qydev.com/wechat-tools/page/push/neUzquH.html','http://ali1.rabbitpre.com/db290bb8-484b-44e0-9980-66a2f85ec573.jpeg@0-0-0-0a_401w_70Q_1an.src?crossorigin=1',now(),now()),('FvmIZ3i','小罐茶品牌上市发布会','小罐茶 大师作','http://wangzy.tunnel.qydev.com/wechat-tools/page/push/FvmIZ3i.html','http://tenc3.rabbitpre.com/609ee0bd-1aab-4511-8127-0383b92748da.png@0-0-0-0a_214w_70Q_1an.src?crossorigin=1',now(),now());
INSERT INTO `wechat_item` (`item_id`,`title`,`description`,`url`,`img_url`,`create_time`,`update_time`) VALUES
  ('FBfM7nk','镶牙不带挂钩，中老年人优选！','无挂钩镶牙，不拔牙、不带挂钩，更适合50岁以上中老年人！！','http://wangzy.tunnel.qydev.com/wechat-tools/page/push/FBfM7nk.html','http://file.rabbitpre.com/01f9f01a-28c6-4911-842e-d582113a90ad-1348?imageMogr2/quality/70/auto-orient',now(),now()),
  ('UZJvi7Yf','移动互联出行行业现状展望分享会','滴滴打车引领出行行业飞速发展，大数据、新模式的行业信息精彩纷呈，9月1日，现场分享，相约你我共赢。','http://wangzy.tunnel.qydev.com/wechat-tools/page/push/UZJvi7Yf.html','http://file2.rabbitpre.com/8dfbc1eb-097e-4f25-974f-dbc522c07c17-4349?imageMogr2/quality/70/auto-orient',now(),now()),
  ('UZjYBjz','人生中最好的一天，一生活一场五月天','唱出这个时代的音乐！','http://wangzy.tunnel.qydev.com/wechat-tools/page/push/UZjYBjz.html','http://tenc2.rabbitpre.com/209e39c9-cdc9-420e-886e-1d030fc10355.jpg@0-0-0-0a_586w_70Q_1an.src?crossorigin=1',now(),now()),
  ('JfEnUro','智诚房地产企业招聘','','http://wangzy.tunnel.qydev.com/wechat-tools/page/push/JfEnUro.html','http://tenc3.rabbitpre.com/ee860ebb-cdd9-4581-984d-030e662d96a7@643w_70Q_1an.src?crossorigin=1',now(),now());
COMMIT;

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category`(
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `name` VARCHAR(20) NOT NULL COMMENT '类别标题',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
)COMMENT'类表',ENGINE = InnoDB AUTO_INCREMENT = 10,DEFAULT CHARSET = utf8;

BEGIN;
INSERT INTO `category` (`name`,`create_time`,`update_time`) VALUES ('时尚',now(),now()),('财经',now(),now()),('服装',now(),now()),('汽车',now(),now()),('数码',now(),now()),('科技',now(),now()),('旅游',now(),now()),('房产',now(),now()),('家居',now(),now()),('教育',now(),now()),('健康',now(),now()),('艺术',now(),now()),('娱乐',now(),now()),('手机',now(),now()),('游戏',now(),now());
COMMIT;

DROP TABLE  IF EXISTS `cate_item`;
CREATE TABLE IF NOT EXISTS `cate_item`(
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cate_id` INT(11) NOT NULL COMMENT '类别id',
  `item_id` INT(11) NOT NULL COMMENT '图文id',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  INDEX `cate_id_index` (`cate_id`) USING BTREE,
  INDEX `item_id_index` (`item_id`) USING BTREE
)COMMENT'图文分类表',ENGINE = InnoDB AUTO_INCREMENT = 100,DEFAULT CHARSET = utf8;
BEGIN;
INSERT INTO `cate_item` (`cate_id`,`item_id`,`create_time`,`update_time`) VALUES (25,1000,now(),now()),(25,1003,now(),now());
INSERT INTO `cate_item` (`cate_id`,`item_id`,`create_time`,`update_time`)
VALUES (20,1004,now(),now()),
  (15,1005,now(),now()),
  (22,1006,now(),now()),
  (17,1007,now(),now());
COMMIT;

DROP TABLE  IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag`(
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(30) NOT NULL COMMENT '标签名',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
)COMMENT'标签表',ENGINE = InnoDB AUTO_INCREMENT = 100,DEFAULT CHARSET = utf8;
BEGIN;
INSERT INTO `tag` (`name`,`create_time`,`update_time`) VALUES ('青春',now(),now()),('赠送',now(),now());
COMMIT;

DROP TABLE  IF EXISTS `tag_item`;
CREATE TABLE IF NOT EXISTS `tag_item`(
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tag_id` INT(11) NOT NULL COMMENT '标签id',
  `item_id` INT(11) NOT NULL COMMENT '图文id',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
)COMMENT'图文标签表',ENGINE = InnoDB AUTO_INCREMENT = 100,DEFAULT CHARSET = utf8;

BEGIN;
INSERT INTO `tag_item` (`tag_id`,`item_id`,`create_time`,`update_time`) VALUES (100,1000,now(),now()),(101,1000,now(),now());
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
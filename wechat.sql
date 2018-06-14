DROP DATABASE IF EXISTS `wechat`;
CREATE DATABASE IF NOT EXISTS `wechat` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
use `wechat`;

SET NAMES utf8mb4;
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
  `description` VARCHAR(50) DEFAULT '' COMMENT '图文描述',
  `url` VARCHAR(100) NOT NULL COMMENT '图文内容URL',
  `img_url` VARCHAR(200) NOT NULL COMMENT '图文图片URL',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `update_time` DATETIME NOT NULL COMMENT '最后一次更新时间',
  `state` tinyint(4) DEFAULT '0' COMMENT '添加状态位来标识是否删除 0-未删除 1-已删除',
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
  `content` varchar(200) DEFAULT '' COMMENT '类别内容包含',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
)COMMENT'类表',ENGINE = InnoDB AUTO_INCREMENT = 10,DEFAULT CHARSET = utf8;

BEGIN;
INSERT INTO `category` (`name`,`create_time`,`update_time`) VALUES
  ('时尚/服饰',now(),now()),
  ('金融/银行',now(),now()),
  ('电子数码',now(),now()),
  ('旅游/景区',now(),now()),
  ('地产/家具',now(),now()),
  ('家居家电',now(),now()),
  ('教育/培训',now(),now()),
  ('美容',now(),now()),
  ('文化/娱乐',now(),now()),
  ('媒体/广告',now(),now()),
  ('互联网/IT',now(),now()),
  ('电商/微商',now(),now()),
  ('酒店/酒业',now(),now()),
  ('婚庆',now(),now()),
  ('母婴',now(),now()),
  ('装修/设计',now(),now()),
  ('农林渔牧',now(),now()),
  ('个人',now(),now()),
  ('企业招聘',now(),now()),
  ('企业宣传',now(),now()),
  ('会议邀请',now(),now()),
  ('产品介绍',now(),now()),
  ('报名培训',now(),now()),
  ('品牌推广',now(),now()),
  ('节日推广',now(),now()),
  ('数据报告',now(),now()),
  ('事件祝福',now(),now()),
  ('新品发布',now(),now()),
  ('周年庆',now(),now()),
  ('餐饮/食品',now(),now());
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
  `name` VARCHAR(30) UNIQUE NOT NULL COMMENT '标签名',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
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


DROP TABLE  IF EXISTS `fans`;
CREATE TABLE IF NOT EXISTS `fans` (
  `open_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'openId粉丝关注公众号唯一标识',
  `subscribe_status` tinyint(4) NOT NULL COMMENT '粉丝订阅状态 0-未订阅,1-订阅',
  `subscribe_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '粉丝订阅时间',
  `nickname` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `wxid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信号',
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别',
  `language` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'zh_CN' COMMENT '语言',
  `country` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '国家',
  `province` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省份',
  `city` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市',
  `head_img_url` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`open_id`),
  KEY `gender_index` (`gender`) USING BTREE,
  KEY `province_index` (`province`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='粉丝表';


INSERT fans(`open_id`,`subscribe_status`,`subscribe_time`,`nickname`,`gender`,`language`,`country`,`province`,`city`,`head_img_url`,`create_time`,`update_time`)
VALUES
  ('ohIIkvzAbG-xRrXO1Fso2uH97EdA',1,'2018-04-03 11:43:34','段沛奇',1,'zh_CN','中国','重庆','南岸','http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLDo5WiaBhpfxibChZl2T1HsVCZX5GzDW7Bwz2TC5JiclfhkMc9nZj4qfRLDj9PYibrBIaL50o3lrLJibSQ/132',now(),now()),
  ('ohIIkv2d--Ndb-yeD1xwCnbAKDyI',1,'2018-04-02 22:01:59','李岩',1,'zh_CN','中国','北京','朝阳','http://thirdwx.qlogo.cn/mmopen/BicpoNMJYncY71daWYia9WXcnX5XMPvT2T46X23Nw7xuRkyPpGByVaicD5mta9SII0hzsUmFGfhrdibOoBJnDFA8zicjKgkw57go4/132',now(),now()),
  ('ohIIkv1EQR3QlLDx1nDZFA-eJ9Rw',1,'2018-04-02 13:22:29','畅叙幽情',1,'zh_CN','中国','辽宁','锦州','http://thirdwx.qlogo.cn/mmopen/C1icxQuONQ5RgufI7oZrhrzoDFUviayF0Qerd3GnsMicHpA9E3oQrakynibADZTuNibEmPlshFJKibyGjooTNS6BOaXGtW5GTp2av9/132',now(),now()),
  ('ohIIkv2yQrt-o1QVnf5U1V2HgUQI',1,'2018-04-27 16:09:10','小小',2,'zh_CN','中国','河南','洛阳','http://thirdwx.qlogo.cn/mmopen/C1icxQuONQ5RgufI7oZrhr7krwJzzAW7EicLUzAPSaA0XGRhrwdl4BjwjY0dloiaia4DJQFawQribnibt8ib3zLQZibDBWdWM8mDJicst/132',now(),now()),
  ('ohIIkv0EWHRMIFNgCn9iM4obFktI',1,'2018-04-27 16:15:03','笑傲江湖',1,'zh_CN','中国','山西','临汾','http://thirdwx.qlogo.cn/mmopen/C1icxQuONQ5RgufI7oZrhr7krwJzzAW7EicLUzAPSaA0XGRhrwdl4BjwjY0dloiaia4DJQFawQribnibt8ib3zLQZibDBWdWM8mDJicst/132',now(),now()),
  ('ohIIkvzGeXvrBfpPvY6R-F-hGbtk',1,'2018-05-17 20:07:41','飞奔的小考拉',2,'zh_CN','中国','辽宁','沈阳','http://thirdwx.qlogo.cn/mmopen/1LlgQzJVOyC5vWEcm5QOcibEOQKvRxAbatRibMo1DHBxBzGUibv9U3PKYs7icjA8hIE7xhTIsRkLLJNNu1jzFxFhfA/132',now(),now()),
  ('ohIIkv7YSqaQhDIwKYo-ldf-dkc4',1,'2018-05-17 20:14:43','小马',1,'zh_CN','中国','山西','运城','http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLDlIqib95ny0Kibx4JGN83KzWiavYTfIDMpx5OzcPVs4pmOiaSiar3dhycZPagRY5qMeF7nsKTia7cO8ZPA/132',now(),now()),
  ('oohIIkv6X9n_yeANlLqEk2JpMavwQ',1,'2018-05-18 10:47:51','BAI',1,'zh_CN','中国','陕西','西安','http://thirdwx.qlogo.cn/mmopen/ILAyqAIJUDGX0FiaQDoAq8luYe8dLXKqpZKu4b36FUtg0icFLgU77UibnsNJsFd6D34AUeiaWt4iaiaEaru2BWibArm9Waw3xUQzfrB/132',now(),now()),
  ('ohIIkvzRa0Ydg9Am1HNQhZTGOS6o',1,'2018-05-18 11:14:49','huihui.',2,'zh_CN','中国','山西','临汾','http://thirdwx.qlogo.cn/mmopen/C1icxQuONQ5RgufI7oZrhr6hwUkTD0NYDwic56zgcd4JGu4ax0iaic6dGvszU9WvDvzaCjN4susoI7JnBxMhA8K731oL1asxMAw9/132',now(),now()),
  ('oohIIkv5987LlIEThyGbnacUnD1PY',1,'2018-05-18 10:44:55','浩宕天涯',1,'zh_CN','中国','江苏','南京','http://thirdwx.qlogo.cn/mmopen/1LlgQzJVOyCoetYw1a2MXibHq35VLKrBM8sO6GDB4c7dRGZppbbCz5MvfKTUaA9hPQgeYNLgBxPsB9jvm8KSfNoFIACf4E1ic9/132',now(),now()),
  ('ohIIkvw0WSTNTC60NORfdvKlGQ2U',1,'2018-05-18 10:54:58','10',1,'zh_CN','中国','山西','运城','http://thirdwx.qlogo.cn/mmopen/ILAyqAIJUDH3PhJN75vM9cSpibL6qeqCFcdOKPsWW885XKDQMr3UUDSVMibQOMsnfV3Q2sO3P3icBBymLQVhErnlnA7Aw9icZZ48/132',now(),now()),
  ('ohIIkv5dPrjtGbbTAwgeV4isWXeg',1,'2018-05-18 11:51:46','击剑___婧🤺🤺🤺',2,'zh_CN','中国','山西','太原','http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM6nm4TwcmjyKwamfXgJAic5kIWueX7OFN3fzhOP82ibUA8Nt4BDD1GkHDHUVrtS96a1ibX746EQCAtnXvnrwzSSDwx1aWibEjSZDs0/132',now(),now()),
  ('ohIIkv7k85OlLDFwStncbQTulgc8',1,'2018-05-18 11:48:58','独自等待',1,'zh_CN','中国','北京','海淀','http://thirdwx.qlogo.cn/mmopen/C1icxQuONQ5RgufI7oZrhribicC1Owyd476YcObrktGEGNttORpnkTWEDlIMJicxicZGEOx7NhnX0KzPqt6hIN880T0XCs7ANN6Kk/132',now(),now()),
  ('ohIIkvyWrBmozKAHehHjI9Pb4zNs',1,'2018-05-18 12:11:14','侯子',1,'zh_CN','中国','山西','临汾','http://thirdwx.qlogo.cn/mmopen/ILAyqAIJUDGLwzzpKicib07hu7EQXplbiaiboNuVicoscBic8EUwuny6cuic5KiaGz1fvRGcXRw5rpyWvxichm5JmveicpSjmwqDOGfhMY/132',now(),now()),
  ('ohIIkv7W02M5XXv7OshLnghnXFdg',1,'2018-05-18 12:08:18','仰望星空',1,'zh_CN','中国','北京','海淀','http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM5oj7SicW5iaUGbjTQOtLRmIxnib1EBJuO3oYxZ8uiauVFoKiayiabMrncNlDIbTS4oAldcEmf2My7UynCw/132',now(),now());

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE  IF EXISTS `fans_category`;
CREATE TABLE IF NOT EXISTS `fans_category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `open_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'openId粉丝关注公众号唯一标识',
  `cate_id` INT(11) NOT NULL COMMENT '类别id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`id`),
  KEY `open_id_cate_id_index` (`open_id`,`cate_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='粉丝类别表-一个粉丝可以有多个感兴趣的分类';

DROP TABLE IF EXISTS `fans_read`;
CREATE TABLE IF NOT EXISTS `fans_read`(
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '阅读唯一标识',
  `open_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'openId粉丝关注公众号唯一标识',
  `item_id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图文消息id',
  `read_time` INT(11) NOT NULL COMMENT '阅读时间',
  `read_times` INT(11) NOT NULL COMMENT '阅读次数',
  `share_times` int(11) DEFAULT '0' COMMENT '分享次数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`id`),
  KEY `open_id_index` (`open_id`) USING BTREE,
  KEY `item_id_index` (`item_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='粉丝阅读习惯表';


DROP TABLE  IF EXISTS `wechat_push`;
CREATE TABLE IF NOT EXISTS `wechat_push`(
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '微信图文推送id',
  `open_id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'openId粉丝关注公众号唯一标识',
  `item_id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图文消息id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`id`),
  KEY `open_id_index` (`open_id`) USING BTREE,
  KEY `item_id_index` (`item_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='微信图文推送表';


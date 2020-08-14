/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : cbbs

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2020-08-14 10:25:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for booking
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `bid` int(11) NOT NULL AUTO_INCREMENT COMMENT '预订信息(订单)编号',
  `school` varchar(50) NOT NULL COMMENT '所属学院',
  `applicant` varchar(20) NOT NULL COMMENT '申请人姓名',
  `campusId` char(12) NOT NULL COMMENT '学号或工号(和user.username等值)',
  `phone` char(11) NOT NULL COMMENT '手机号码',
  `email` varchar(40) NOT NULL COMMENT '邮箱',
  `useTime` char(50) NOT NULL COMMENT '申请使用的时间段',
  `reason` varchar(120) NOT NULL COMMENT '申请原因(限输入60字以内)',
  `submitTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交申请的时间(系统自动生成)',
  `status` varchar(120) DEFAULT '待审核' COMMENT '审核状态(默认为待审核)',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=10048 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of booking
-- ----------------------------
INSERT INTO `booking` VALUES ('10001', '计算机科学与软件学院', '陈一诺', '2016001325', '1**********', '****@163.com', '2020年03月16日,19:30-22:00', '社团交流会。\r\n使用人数：35人', '2020-03-09 21:43:08', '审核通过');
INSERT INTO `booking` VALUES ('10004', '政法学院', '刘邦驰', '22222222', '1**********', '****@123.com', '2020年03月20日,16:30-18:30', '吉他协会周年庆排练。\r\n使用人数：20人', '2020-03-09 21:57:12', '审核不通过');
INSERT INTO `booking` VALUES ('10019', '音乐学院', '王力洪', '201824103101', '1**********', '****@163.com', '2020年03月25日,19:30-22:00', '协会部门联谊。使用人数：32人', '2020-03-10 15:07:25', '审核通过');
INSERT INTO `booking` VALUES ('10027', '计算机科学与软件学院', '陈一诺', '2016001325', '1**********', '****@163.com', '2020年04月08日,09:30-11:30', '与中文系专业联谊。使用人数：58人', '2020-03-24 12:02:13', '审核通过');
INSERT INTO `booking` VALUES ('10039', '计算机科学与软件学院', '陈一诺', '2016001325', '1**********', '****@163.com', '2020年04月23日,14:30-16:20', '毕业晚会。使用人数：32人', '2020-04-16 09:52:00', '待审核');

-- ----------------------------
-- Table structure for canceled_booking
-- ----------------------------
DROP TABLE IF EXISTS `canceled_booking`;
CREATE TABLE `canceled_booking` (
  `bid` int(11) NOT NULL COMMENT '预订信息(订单)编号',
  `school` varchar(50) NOT NULL COMMENT '所属学院',
  `applicant` varchar(20) NOT NULL COMMENT '申请人姓名',
  `campusId` char(12) NOT NULL COMMENT '学号或工号(和user.username等值)',
  `phone` char(11) NOT NULL COMMENT '手机号码',
  `useTime` char(50) NOT NULL COMMENT '申请使用的时间段',
  `reason` varchar(120) NOT NULL COMMENT '申请原因',
  `submitTime` timestamp NULL DEFAULT NULL COMMENT '提交订单的时间',
  `status` varchar(120) DEFAULT NULL COMMENT '订单状态',
  `cancelTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '取消/删除订单的时间(自动获取)',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of canceled_booking
-- ----------------------------

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题编号',
  `question` varchar(240) NOT NULL COMMENT '问题描述(限输入120字以内)',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '反馈提交时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '上周去活动室聚会，发现有2个麦克风坏掉了，希望负责人能更换一下哈！', '2020-03-18 16:36:45');
INSERT INTO `feedback` VALUES ('2', '希望学校可以再扩建几个活动室，这样就不会一室难求了。', '2020-04-03 16:36:54');
INSERT INTO `feedback` VALUES ('3', '落霞与孤鹜齐飞，秋水共长天一色。', '2020-05-30 22:34:17');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告的编号',
  `title` varchar(60) NOT NULL COMMENT '公告的标题(30字内)',
  `content` varchar(1000) NOT NULL COMMENT '公告的内容(500字内)',
  `publishTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间(自动生成)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '关于端午节期间活动室开放的通知', '富强、民主、文明、和谐、自由、平等、公正、法制、爱国、敬业、诚信、友善', '2029-03-06 15:50:44');
INSERT INTO `notice` VALUES ('2', '关于暂停开放活动室的紧急通知', '富强、民主、文明、和谐、自由、平等、公正、法制、爱国、敬业、诚信、友善', '2029-04-15 14:35:48');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(20) NOT NULL COMMENT '角色名(英文)',
  `nameZh` varchar(10) NOT NULL COMMENT '角色名称(中文)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员');
INSERT INTO `role` VALUES ('2', 'user', '普通用户');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `school` varchar(100) NOT NULL COMMENT '所在部门/二级学院',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `username` char(12) NOT NULL COMMENT '帐号(学号或工号)',
  `password` varchar(20) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '计算机科学与软件学院', '陈一诺', '2016001325', '123456');
INSERT INTO `user` VALUES ('2', '校工会', '关礼元', '10248888', '10248888');
INSERT INTO `user` VALUES ('3', '体育学院', '项羽升', '11111111', '11111111');
INSERT INTO `user` VALUES ('4', '政法学院', '刘邦驰', '22222222', '22222222');
INSERT INTO `user` VALUES ('5', '外国语学院', '唐晓一', '201623110218', '201623110218');
INSERT INTO `user` VALUES ('6', '音乐学院', '王力洪', '201824103101', '201824103101');
INSERT INTO `user` VALUES ('7', '外国语学院', '成宇修', '201626311106', '201626311106');
INSERT INTO `user` VALUES ('8', '美术学院', '周程', '201621320618', '201621320618');
INSERT INTO `user` VALUES ('9', '文学院', '郑佳仪', '201631081621', '201631081621');
INSERT INTO `user` VALUES ('10', '生命科学学院', '李文意', '201628130419', '201628130419');
INSERT INTO `user` VALUES ('11', '生命科学学院', '陈晨', '201635421123', '201635421123');
INSERT INTO `user` VALUES ('12', '美术学院', '黄琪秀', '202023190001', '202023190001');
INSERT INTO `user` VALUES ('13', '经济与管理学院', '袁慕', '202037091118', '202037091118');
INSERT INTO `user` VALUES ('14', '工程学院', '陈方磊', '202109384101', '202109384101');
INSERT INTO `user` VALUES ('15', '资源与环境学院', '鲁艺', '201909311128', '201909311128');
INSERT INTO `user` VALUES ('16', '历史与旅游学院', '王承勋', '10001', '10001');
INSERT INTO `user` VALUES ('17', '教育科学学院', '李建德', '10002', '10002');
INSERT INTO `user` VALUES ('18', '工业与设计学院', '黄威', '10003', '10003');
INSERT INTO `user` VALUES ('19', '文学院', '张成航', '10004', '10004');
INSERT INTO `user` VALUES ('20', '数学院', '徐建伟', '10005', '10005');
INSERT INTO `user` VALUES ('21', '政法学院', '赵小玲', '10006', '10006');
INSERT INTO `user` VALUES ('22', '工程学院', '冯立刚', '10007', '10007');
INSERT INTO `user` VALUES ('23', '电子与电气学院', '翟天力', '10008', '10008');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户_角色的编号',
  `userId` int(11) DEFAULT NULL COMMENT '用户编号',
  `roleId` int(11) DEFAULT '2' COMMENT '角色编号(1是admin，2是user)',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `user_role_fk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_role_fk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '2');
INSERT INTO `user_role` VALUES ('2', '2', '1');
INSERT INTO `user_role` VALUES ('3', '3', '2');
INSERT INTO `user_role` VALUES ('4', '4', '2');
INSERT INTO `user_role` VALUES ('5', '5', '2');
INSERT INTO `user_role` VALUES ('6', '6', '2');
INSERT INTO `user_role` VALUES ('7', '7', '2');
INSERT INTO `user_role` VALUES ('8', '8', '2');
INSERT INTO `user_role` VALUES ('9', '9', '2');
INSERT INTO `user_role` VALUES ('10', '10', '2');
INSERT INTO `user_role` VALUES ('11', '11', '2');
INSERT INTO `user_role` VALUES ('12', '12', '2');
INSERT INTO `user_role` VALUES ('13', '13', '2');
INSERT INTO `user_role` VALUES ('14', '14', '2');
INSERT INTO `user_role` VALUES ('15', '15', '2');
INSERT INTO `user_role` VALUES ('16', '16', '2');
INSERT INTO `user_role` VALUES ('17', '17', '2');
INSERT INTO `user_role` VALUES ('18', '18', '2');
INSERT INTO `user_role` VALUES ('19', '19', '2');
INSERT INTO `user_role` VALUES ('20', '20', '2');
INSERT INTO `user_role` VALUES ('21', '21', '2');
INSERT INTO `user_role` VALUES ('22', '22', '2');
INSERT INTO `user_role` VALUES ('23', '23', '2');
DROP TRIGGER IF EXISTS `cancelRecord`;
DELIMITER ;;
CREATE TRIGGER `cancelRecord` AFTER DELETE ON `booking` FOR EACH ROW BEGIN
	insert into `canceled_booking` (`bid`, `school`, `applicant`, `campusId`, `phone`, `useTime`, `reason`, `submitTime`, `status`, `cancelTime`) 
	values (old.`bid`, old.`school`, old.`applicant`, old.`campusId`, old.`phone`, old.`useTime`, old.`reason`, old.`submitTime`, old.`status`, NOW());	
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `roleAllocation`;
DELIMITER ;;
CREATE TRIGGER `roleAllocation` AFTER INSERT ON `user` FOR EACH ROW BEGIN
	insert into `user_role`(`userId`)
	values(new.id);
END
;;
DELIMITER ;

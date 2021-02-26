/*
SQLyog Trial v10.2 
MySQL - 5.7.13-log : Database - mall_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall_test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mall_test`;

/*Table structure for table `integrate_water` */

DROP TABLE IF EXISTS `integrate_water`;

CREATE TABLE `integrate_water` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分流水表ID',
  `user_id` int(11) NOT NULL COMMENT '用户表ID（mall_user）',
  `number` int(11) NOT NULL COMMENT '积分数量',
  `type` int(11) NOT NULL COMMENT '0:新用户赠送；1:邀请奖励；-1：兑换商品',
  `status` int(11) NOT NULL COMMENT '1新增、-1扣除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `integrate_water_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `mall_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `integrate_water` */

insert  into `integrate_water`(`id`,`user_id`,`number`,`type`,`status`,`create_time`) values (1,1,500,0,1,'2021-01-18 17:56:17'),(2,1,300,-1,-1,'2021-01-18 17:58:27');

/*Table structure for table `mall_order` */

DROP TABLE IF EXISTS `mall_order`;

CREATE TABLE `mall_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单表id',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `orderscore` int(11) NOT NULL COMMENT '订单积分',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0未发货，1已发货',
  `product_id` int(11) NOT NULL COMMENT '商品表ID（mall_product）',
  `user_id` int(11) NOT NULL COMMENT '用户表ID（mall_user）',
  `consignee` varchar(10) NOT NULL COMMENT '收货人',
  `phone` varchar(11) NOT NULL COMMENT '收货电话',
  `address` varchar(100) NOT NULL COMMENT '收货地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `mall_order_ibfk_1` (`product_id`),
  CONSTRAINT `mall_order_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `mall_product` (`id`),
  CONSTRAINT `mall_order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `mall_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mall_order` */

insert  into `mall_order`(`id`,`order_no`,`orderscore`,`status`,`product_id`,`user_id`,`consignee`,`phone`,`address`,`create_time`) values (1,'0b3e614be1d940ba897d16d932a904bb',300,0,1,1,'李四','12345678933','A','2021-01-18 17:58:27');

/*Table structure for table `mall_product` */

DROP TABLE IF EXISTS `mall_product`;

CREATE TABLE `mall_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品表ID',
  `name` varchar(100) NOT NULL COMMENT '商品名',
  `image` varchar(200) NOT NULL COMMENT '商品图片',
  `details` text NOT NULL COMMENT '商品详情',
  `stock` int(11) NOT NULL COMMENT '商品库存',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态：0下架、1上架',
  `score` int(11) DEFAULT NULL COMMENT '兑换所需积分',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '0正常，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mall_product` */

insert  into `mall_product`(`id`,`name`,`image`,`details`,`stock`,`status`,`score`,`create_time`,`update_time`,`del`) values (1,'超保暖羽绒服','http://localhost:8001//file/image/d63e205f29e741438431ad88e6bf9613.jpg','<img src=\"http://localhost:8001//file/image/26fdd24987424b65be5568c446be6c18.jpg\" alt=\"undefined\">',9,1,300,'2021-01-18 17:58:00','2021-01-18 17:58:00',0);

/*Table structure for table `mall_user` */

DROP TABLE IF EXISTS `mall_user`;

CREATE TABLE `mall_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `email` varchar(30) NOT NULL COMMENT '用户邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `balance` int(11) NOT NULL DEFAULT '0' COMMENT '积分余额',
  `user_id` int(11) DEFAULT NULL COMMENT '邀请人',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态：0正常，1冻结',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '0正常，1删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mall_user` */

insert  into `mall_user`(`id`,`username`,`password`,`email`,`phone`,`balance`,`user_id`,`status`,`create_time`,`update_time`,`del`) values (1,'zhangsan0','123456','12@qq.com','12345678912',200,NULL,0,'2021-01-18 17:56:17','2021-01-18 17:56:17',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

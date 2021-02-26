/*
SQLyog Trial v10.2 
MySQL - 5.7.13-log : Database - mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mall`;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `integrate_water` */

insert  into `integrate_water`(`id`,`user_id`,`number`,`type`,`status`,`create_time`) values (2,1,500,0,1,'2021-01-17 16:18:34'),(3,1,500,0,1,'2021-01-17 16:20:16'),(4,1,500,0,1,'2021-01-17 21:39:57'),(5,1,3000,-1,-1,'2021-01-18 10:16:36'),(6,13,500,0,1,'2021-01-18 12:04:04'),(7,1,11111,-1,-1,'2021-01-18 14:09:05'),(8,12,20,-1,-1,'2021-01-18 14:16:07');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `mall_order` */

insert  into `mall_order`(`id`,`order_no`,`orderscore`,`status`,`product_id`,`user_id`,`consignee`,`phone`,`address`,`create_time`) values (1,'b2fe3b14585d11eb8428482ae335fdb2',600,1,1,1,'test','12345678911','B点','2021-01-17 08:48:19'),(2,'b2fe3b14585d11eb8428482ae335fdb2',600,1,1,1,'test','12345678911','C点','2021-01-17 08:48:19'),(3,'86f32e6d635a4985823bcd1072938a5d',3000,1,4,1,'小王','12234568799','A点','2021-01-18 10:16:36'),(4,'0f7d2b1a26dc4ee09794752c34e45faa',11111,0,5,1,'小李','12345678977','D点','2021-01-18 14:09:05');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `mall_product` */

insert  into `mall_product`(`id`,`name`,`image`,`details`,`stock`,`status`,`score`,`create_time`,`update_time`,`del`) values (1,'小米笔记本电脑','http://localhost:8081//file/image/cb4d6c6a10af4c92a638fcee4a0dd21b.jpg','<img src=\"http://localhost:8081//file/image/8878b397920b4e9b8aba030c7b0730dc.jpg\" alt=\"undefined\">',30,1,60000,'2021-01-16 16:50:34','2021-01-18 09:53:48',0),(2,'小米11 豪华版','http://localhost:8081//file/image/1801b5ab6435472cba008419e1c5b5c1.jpg','<img src=\"http://localhost:8081//file/image/18373b035812433db5f21e65f8f473c5.jpg\" alt=\"undefined\">',10,1,50000,'2021-01-16 18:00:24','2021-01-18 09:55:25',0),(3,'羽绒服超保暖','http://localhost:8081//file/image/1b5e891c454c438aa336e64af75775df.jpg','<img src=\"http://localhost:8081//file/image/5d3b1e6e1a75403e82a77d6ef4ff672c.jpg\" alt=\"undefined\">',20,1,300000,'2021-01-17 08:25:53','2021-01-18 10:11:52',0),(4,'山地自行车','http://localhost:8081//file/image/bcce8c29810a4d40b1e7f024d0491dc7.jpg','<p><img src=\"http://localhost:8081//file/image/071b3f36fbe2417084ac4ccb0bf25038.jpg\" alt=\"undefined\"></p><p><img src=\"http://localhost:8081//file/image/4cc85e02a0e74ff28cfa2687c06c604d.jpg\" alt=\"undefined\"></p>',9,1,3000,'2021-01-17 14:34:03','2021-01-18 10:12:17',0),(5,'iphone11ProMax','http://localhost:8081//file/image/50a4a5800c494eebb8e989b9d84caa87.jpg','very good<br>',1110,1,11111,'2021-01-17 20:40:11','2021-01-18 10:12:34',0),(6,'口罩 超级预防','http://localhost:8081//file/image/bd7b3ebf73fc482c99e6a74cb62dca32.jpg','保护家人的健康！！！<br>',110,1,20,'2021-01-18 10:14:27','2021-01-18 10:14:27',0),(7,'ipad 经典款','http://localhost:8081//file/image/9a8e8f0c6d064c2fbd3f8632b751f96d.jpg','<p>ios</p><p>···<br></p><p><br></p>',111,1,6000,'2021-01-18 10:15:07','2021-01-18 10:15:07',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `mall_user` */

insert  into `mall_user`(`id`,`username`,`password`,`email`,`phone`,`balance`,`user_id`,`status`,`create_time`,`update_time`,`del`) values (1,'zhansan','123456','123@qq.com','',9985889,NULL,1,'2021-01-15 10:28:04','2021-01-15 10:28:04',0),(2,'zhansan1','123456','1234@qq.com','',500,NULL,0,'2021-01-15 10:28:04','2021-01-15 10:28:04',0),(3,'zhansan2','123456','1235@qq.com','',500,NULL,0,'2021-01-15 10:28:04','2021-01-15 10:28:04',0),(4,'zhansan3','123456','1236@qq.com','',500,NULL,0,'2021-01-15 10:28:04','2021-01-15 10:28:04',0),(5,'zhansan4','123456','1237@qq.com','',500,NULL,0,'2021-01-15 10:28:04','2021-01-15 10:28:04',0),(6,'zhansan5','123456','1238@qq.com','',500,NULL,1,'2021-01-15 10:28:04','2021-01-15 10:28:04',0),(7,'zhansan6','123456','1239@qq.com','',500,NULL,0,'2021-01-15 10:28:04','2021-01-15 10:28:04',0),(8,'zhansan7','123456','123@qq.com','',500,NULL,0,'2021-01-15 10:28:04','2021-01-15 10:28:04',0),(10,'123456','123456','123456@aa.com','12345678900',0,NULL,0,'2021-01-17 16:18:34','2021-01-17 16:18:34',0),(11,'1234567','123456','123456@123.com','12345678900',0,NULL,0,'2021-01-17 16:20:16','2021-01-17 16:20:16',0),(12,'xuehaolin','123456','12113@qq.com','12345678911',2980,NULL,0,'2021-01-17 21:39:57','2021-01-17 21:39:57',0),(13,'zhangsan8','123456','12334@qq.com','12345674897',500,NULL,0,'2021-01-18 12:04:04','2021-01-18 12:04:04',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

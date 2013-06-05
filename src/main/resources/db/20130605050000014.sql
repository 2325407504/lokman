-- MySQL dump 10.13  Distrib 5.1.63, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: lokman
-- ------------------------------------------------------
-- Server version	5.1.63-0+squeeze1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`),
  KEY `FKB9D38A2D7DD3F182` (`region_id`),
  KEY `FKB9D38A2DC6E163F4` (`customer_id`),
  CONSTRAINT `FKB9D38A2DC6E163F4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKB9D38A2D7DD3F182` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'2013-05-13 18:44:42','2013-05-13 18:44:42','','cem@aripd.com','67e34ed173dcf2b555855f3408d5e664','cem',1,1),(2,'2013-05-13 18:44:42','2013-05-13 18:44:42','','admin@aripd.com','21232f297a57a5a743894a0e4a801fc3','admin',2,1),(3,'2013-05-13 18:44:42','2013-05-13 18:44:42','','user@aripd.com','ee11cbb19052e40b07aac0ca060c23ee','user',3,1),(4,'2013-05-13 18:44:42','2013-05-13 18:44:42','','fatih.gemalmaz@lgk.com.tr','10218503d7eb33d8e7328b0ead2466b0','fgemalmaz',4,1),(5,'2013-05-13 18:44:42','2013-05-13 18:44:42','','yusuf.memnun@lgk.com.tr','6def389332961997178bfe39808c11e4','ymemnun',5,1),(6,'2013-05-13 18:44:42','2013-05-13 18:44:42','','murat.dogan@lgk.com.tr','cbbf6881282bd14f1f745d0114e31fd0','mdogan',6,1),(7,'2013-05-13 18:44:42','2013-05-13 18:44:42','','esma.akkaya@lgk.com.tr','73c516d3fc9a58f2d86cbf355678ffc8','eakkaya',7,1),(8,'2013-05-13 18:44:42','2013-05-13 18:44:42','','omer.hayir@lgk.com.tr','827ccb0eea8a706c4c34a16891f84e7b','ohayir',8,1),(9,'2013-05-13 18:44:42','2013-05-13 18:44:42','','arif.hayir@lgk.com.tr','827ccb0eea8a706c4c34a16891f84e7b','ahayir',9,2),(10,'2013-05-13 18:44:42','2013-05-13 18:44:42','','banu.cevikel@lgk.com.tr','827ccb0eea8a706c4c34a16891f84e7b','bcevikel',10,2),(11,'2013-05-13 18:44:42','2013-05-13 18:44:42','','hasan.ozturk@lgk.com.tr','827ccb0eea8a706c4c34a16891f84e7b','hozturk',11,2),(12,'2013-05-13 18:44:42','2013-05-13 18:44:42','','ergin.oral@lgk.com.tr','827ccb0eea8a706c4c34a16891f84e7b','eoral',12,2),(13,'2013-05-20 08:23:57','2013-05-13 18:44:42','','armagan.yilmazer@lgk.com.tr','827ccb0eea8a706c4c34a16891f84e7b','ayilmazer',13,3),(14,'2013-05-20 08:18:34','2013-05-20 08:18:34','','oktay.ozsuer@lgk.com.tr','827ccb0eea8a706c4c34a16891f84e7b','oozsuer',14,4);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_role` (
  `account_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK410D03481F1509F4` (`role_id`),
  KEY `FK410D0348DCEC7860` (`account_id`),
  CONSTRAINT `FK410D0348DCEC7860` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK410D03481F1509F4` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1,1),(1,2),(1,3),(2,2),(2,3),(3,3),(4,2),(5,2),(6,2),(7,2),(8,3),(9,3),(10,3),(11,3),(12,3),(13,3),(14,3);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'2013-05-13 18:44:42','2013-05-13 18:44:42','Cem','ARIPD'),(2,'2013-05-13 18:44:42','2013-05-13 18:44:42','Admin','ARIPD'),(3,'2013-05-13 18:44:42','2013-05-13 18:44:42','User','ARIPD'),(4,'2013-05-13 18:44:42','2013-05-13 18:44:42','Fatih','Gemalmaz'),(5,'2013-05-13 18:44:42','2013-05-13 18:44:42','Yusuf','Memnun'),(6,'2013-05-13 18:44:42','2013-05-13 18:44:42','Murat','Doğan'),(7,'2013-05-13 18:44:42','2013-05-13 18:44:42','Esma','Akkaya'),(8,'2013-05-13 18:44:42','2013-05-13 18:44:42','Ömer','Hayır'),(9,'2013-05-13 18:44:42','2013-05-13 18:44:42','Arif','Hayır'),(10,'2013-05-13 18:44:42','2013-05-13 18:44:42','Banu','Çevikel'),(11,'2013-05-13 18:44:42','2013-05-13 18:44:42','Hasan','Öztürk'),(12,'2013-05-13 18:44:42','2013-05-13 18:44:42','Ergin','Oral'),(13,'2013-05-13 18:44:42','2013-05-13 18:44:42','Armağan','Yılmazer'),(14,'2013-05-20 08:18:34','2013-05-20 08:18:34','Oktay','Özsüer');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `active` bit(1) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FKB11C98287DD3F182` (`region_id`),
  CONSTRAINT `FKB11C98287DD3F182` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'2013-05-20 07:21:02','2013-05-20 07:21:02','\0','34 YK 1063','Zekai DOGAN','2005',1),(2,'2013-05-20 07:21:07','2013-05-20 07:21:07','\0','35 SEC 10','Osman DUR','2041',1),(3,'2013-05-20 07:21:43','2013-05-20 07:21:43','\0','35 U 1884','SADIK KAL','2007',1),(4,'2013-05-20 07:23:41','2013-05-20 07:23:41','\0','35 HA 6914','Cemal ATAY','2092',2),(5,'2013-05-20 07:24:31','2013-05-20 07:24:31','\0','34 AT 2912','Abdullah KANMAZ','',2),(6,'2013-05-20 07:24:47','2013-05-20 07:24:47','\0','34 TE 6123','Tahir MALİ','Telefon6',2),(7,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID7','Sürücü7','Telefon7',3),(8,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID8','Sürücü8','Telefon8',3),(9,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID9','Sürücü9','Telefon9',3),(10,'2013-05-20 08:55:17','2013-05-20 08:55:17','\0','35 HVU 92','Korkmaz COSKUN','9026',4),(11,'2013-05-20 08:55:51','2013-05-20 08:55:51','\0','35 AG 1786 ','Ceyhan Dalgıc','2075',4),(12,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID12','Sürücü12','Telefon12',5),(13,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID13','Sürücü13','Telefon13',5),(14,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID14','Sürücü14','Telefon14',6),(15,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID15','Sürücü15','Telefon15',6),(16,'2013-05-20 07:22:49','2013-05-20 07:22:49','\0','35 ZH 318','Engin Şentürk','2001',1);
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `amount` decimal(19,2) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `documentDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `submitted` bit(1) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB1F4C858DCEC7860` (`account_id`),
  CONSTRAINT `FKB1F4C858DCEC7860` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forwarding`
--

DROP TABLE IF EXISTS `forwarding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forwarding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `driver` varchar(255) DEFAULT NULL,
  `endingPoint` varchar(255) DEFAULT NULL,
  `endingTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `loadWeightInTonne` int(11) DEFAULT NULL,
  `plate` varchar(255) DEFAULT NULL,
  `shippingCost` decimal(19,2) DEFAULT NULL,
  `startingTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `submitted` bit(1) NOT NULL,
  `waybillNo` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) NOT NULL,
  `quota_id` bigint(20) DEFAULT NULL,
  `subcontractor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `waybillNo` (`waybillNo`),
  KEY `FK243652FD94A4172` (`quota_id`),
  KEY `FK243652FDDCEC7860` (`account_id`),
  KEY `FK243652FDEB2A8452` (`subcontractor_id`),
  CONSTRAINT `FK243652FDEB2A8452` FOREIGN KEY (`subcontractor_id`) REFERENCES `subcontractor` (`id`),
  CONSTRAINT `FK243652FD94A4172` FOREIGN KEY (`quota_id`) REFERENCES `quota` (`id`),
  CONSTRAINT `FK243652FDDCEC7860` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forwarding`
--

LOCK TABLES `forwarding` WRITE;
/*!40000 ALTER TABLE `forwarding` DISABLE KEYS */;
INSERT INTO `forwarding` VALUES (4,'2013-05-14 12:01:11','2013-05-14 12:01:11','Mehmet ÖZDEMİR','Gebze Depo','2013-05-13 21:00:00',3000,'35 GB 111','0.00','2013-05-13 21:00:00','\0','0',9,1,2),(5,'2013-05-15 08:05:57','2013-05-15 08:05:57','mehmet özdemir','gebze tesis','2013-05-01 21:00:00',1120,'41 D 1479',NULL,'2013-05-01 21:00:00','','',9,1,2),(16,'2013-05-20 07:57:20','2013-05-20 07:57:20','mehmet özdemir','gebze tesis','2013-05-03 21:00:00',2780,'41 D 1479',NULL,'2013-05-03 21:00:00','','1100',9,1,2),(20,'2013-05-20 07:57:10','2013-05-20 07:57:10','mehmet özdemir','gebze tesis','2013-05-03 21:00:00',2780,'41 D 1479',NULL,'2013-05-03 21:00:00','','1111',9,1,2),(22,'2013-05-17 09:27:38','2013-05-17 09:27:38','mehmet özdemir','gebze tesis','2013-05-05 21:00:00',3260,'41 D 1479',NULL,'2013-05-05 21:00:00','','1112',9,1,2),(24,'2013-05-17 10:56:54','2013-05-17 10:56:54','mehmet özdemir','gebze tesis','2013-05-06 21:00:00',5740,'41 D 1479',NULL,'2013-05-06 21:00:00','','1113',9,1,2),(25,'2013-05-17 10:58:41','2013-05-17 10:58:41','mehmet özdemir','gebze tesis','2013-05-07 21:00:00',11320,'41 D 1479',NULL,'2013-05-07 21:00:00','','1114',9,1,2),(26,'2013-05-17 10:59:42','2013-05-17 10:59:42','mehmet özdemir','gebze tesis','2013-05-08 21:00:00',5320,'41 D 1479',NULL,'2013-05-08 21:00:00','','1115',9,1,2),(27,'2013-05-17 11:00:28','2013-05-17 11:00:28','mehmet özdemir','gebze tesis','2013-05-09 21:00:00',11460,'41 D 1479',NULL,'2013-05-09 21:00:00','','1116',9,1,2),(28,'2013-05-17 11:01:10','2013-05-17 11:01:10','mehmet özdemir','gebze tesis','2013-05-10 21:00:00',7180,'41 D 1479',NULL,'2013-05-10 21:00:00','','1117',9,1,2),(29,'2013-05-17 11:03:04','2013-05-17 11:03:04','mehmet özdemir','gebze tesis','2013-05-12 21:00:00',6240,'41 D 1479',NULL,'2013-05-12 21:00:00','','11178',9,1,2),(30,'2013-05-17 11:04:32','2013-05-17 11:04:32','mehmet özdemir','gebze tesis','2013-05-13 21:00:00',1740,'41 D 1479',NULL,'2013-05-13 21:00:00','','1118',9,1,2),(31,'2013-05-17 11:05:56','2013-05-17 11:05:56','mehmet özdemir','gebze tesis','2013-05-14 21:00:00',5880,'41 D 1479',NULL,'2013-05-14 21:00:00','','1119',9,1,2),(32,'2013-05-17 11:09:22','2013-05-17 11:09:22','mehmet özdemir','gebze tesis','2013-05-15 21:00:00',7100,'41 D 1479',NULL,'2013-05-15 21:00:00','','1120',9,1,2),(33,'2013-05-17 11:13:40','2013-05-17 11:13:40','ismail ipek','gebze tesis','2013-05-01 21:00:00',640,'34 NGM 64',NULL,'2013-05-01 21:00:00','','1121',9,1,3),(34,'2013-05-17 11:14:46','2013-05-17 11:14:46','ismail ipek','gebze tesis','2013-05-02 21:00:00',1060,'34 NGM 64',NULL,'2013-05-02 21:00:00','','1122',9,1,3),(35,'2013-05-17 11:15:33','2013-05-17 11:15:33','ismail ipek','gebze tesis','2013-05-03 21:00:00',1180,'34 NGM 64',NULL,'2013-05-03 21:00:00','','1123',9,1,3),(36,'2013-05-17 11:16:28','2013-05-17 11:16:28','ismail ipek','gebze tesis','2013-05-06 21:00:00',1220,'34 NGM 64',NULL,'2013-05-06 21:00:00','','1124',9,1,3),(37,'2013-05-17 11:17:25','2013-05-17 11:17:25','ismail ipek','gebze tesis','2013-05-07 21:00:00',3000,'34 NGM 64',NULL,'2013-05-07 21:00:00','','1125',9,1,3),(38,'2013-05-17 11:18:07','2013-05-17 11:18:07','ismail ipek','gebze tesis','2013-05-08 21:00:00',1340,'34 NGM 64',NULL,'2013-05-08 21:00:00','','1126',9,1,3),(39,'2013-05-17 11:19:15','2013-05-17 11:19:15','ismail ipek','gebze tesis','2013-05-09 21:00:00',760,'34 NGM 64',NULL,'2013-05-09 21:00:00','','1127',9,1,3),(40,'2013-05-17 11:20:13','2013-05-17 11:20:13','ismail ipek','gebze tesis','2013-05-10 21:00:00',1460,'34 NGM 64',NULL,'2013-05-10 21:00:00','','1128',9,1,3),(41,'2013-05-17 11:21:07','2013-05-17 11:21:07','ismail ipek','gebze tesis','2013-05-12 21:00:00',1220,'34 NGM 64',NULL,'2013-05-12 21:00:00','','1129',9,1,3),(42,'2013-05-17 11:21:44','2013-05-17 11:21:44','ismail ipek','gebze tesis','2013-05-13 21:00:00',980,'34 NGM 64',NULL,'2013-05-13 21:00:00','','1130',9,1,3),(43,'2013-05-17 11:22:25','2013-05-17 11:22:25','ismail ipek','gebze tesis','2013-05-14 21:00:00',920,'34 NGM 64',NULL,'2013-05-14 21:00:00','','1131',9,1,3),(44,'2013-05-17 11:24:08','2013-05-17 11:24:08','ismail ipek','gebze tesis','2013-05-15 21:00:00',1280,'34 NGM 64',NULL,'2013-05-15 21:00:00','','1132',9,1,3),(45,'2013-05-17 12:20:30','2013-05-17 12:20:30','yavuz narman','gebze tesis','2013-05-01 21:00:00',4780,'34 EKS 12',NULL,'2013-05-01 21:00:00','','1133',9,1,5),(46,'2013-05-17 12:21:21','2013-05-17 12:21:21','yavuz narman','gebze tesis','2013-05-02 21:00:00',2760,'34 EKS 12',NULL,'2013-05-02 21:00:00','','1134',9,1,5),(47,'2013-05-20 07:43:11','2013-05-20 07:43:11','yavuz narman','gebze tesis','2013-05-03 21:00:00',920,'34 EKS 12',NULL,'2013-05-03 21:00:00','','1135',9,1,5),(48,'2013-05-17 12:23:03','2013-05-17 12:23:03','yavuz narman','gebze tesis','2013-05-05 21:00:00',900,'34 EKS 12',NULL,'2013-05-05 21:00:00','','1136',9,1,5),(49,'2013-05-17 12:25:48','2013-05-17 12:25:48','yavuz narman','gebze tesis','2013-05-06 21:00:00',1180,'34 EKS 12',NULL,'2013-05-06 21:00:00','','1137',9,1,5),(50,'2013-05-17 12:26:39','2013-05-17 12:26:39','yavuz narman','gebze tesis','2013-05-07 21:00:00',940,'34 EKS 12',NULL,'2013-05-07 21:00:00','','1138',9,1,5),(51,'2013-05-17 12:27:41','2013-05-17 12:27:41','yavuz narman','gebze tesis','2013-05-08 21:00:00',2520,'34 EKS 12',NULL,'2013-05-08 21:00:00','','1139',9,1,5),(52,'2013-05-17 12:28:17','2013-05-17 12:28:17','yavuz narman','gebze tesis','2013-05-09 21:00:00',1000,'34 EKS 12',NULL,'2013-05-09 21:00:00','','1140',9,1,5),(53,'2013-05-17 12:29:05','2013-05-17 12:29:05','yavuz narman','gebze tesis','2013-05-10 21:00:00',1300,'34 EKS 12',NULL,'2013-05-10 21:00:00','','1141',9,1,5),(54,'2013-05-17 12:30:08','2013-05-17 12:30:08','yavuz narman','gebze tesis','2013-05-11 21:00:00',1120,'34 EKS 12',NULL,'2013-05-11 21:00:00','','1142',9,1,5),(55,'2013-05-17 12:31:53','2013-05-17 12:31:53','yavuz narman','gebze tesis','2013-05-12 21:00:00',1600,'34 EKS 12',NULL,'2013-05-12 21:00:00','','1143',9,1,5),(56,'2013-05-17 12:34:07','2013-05-17 12:34:07','yavuz narman','gebze tesis','2013-05-14 21:00:00',1280,'34 EKS 12',NULL,'2013-05-14 21:00:00','','1144',9,1,5),(57,'2013-05-17 12:50:59','2013-05-17 12:50:59','AHMET AKSOY','SOYSAL ','2013-05-01 21:00:00',4740,'41 TM 339',NULL,'2013-05-01 21:00:00','','3694',9,1,27),(58,'2013-05-20 07:57:42','2013-05-20 07:57:42','mehmet özdemir','gebze tesis','2013-05-16 21:00:00',5400,'41 D 1479',NULL,'2013-05-16 21:00:00','','1145',9,1,2),(59,'2013-05-20 07:56:44','2013-05-20 07:56:44','yavuz narman','gebze tesis','2013-05-16 21:00:00',1180,'34 EKS 12',NULL,'2013-05-16 21:00:00','','1146',9,1,5),(60,'2013-05-20 06:34:12','2013-05-20 06:34:12','AHMET AKSOY','İNNOVA','2013-05-02 21:00:00',6680,'41 TM 339',NULL,'2013-05-02 21:00:00','','3696',9,1,27),(61,'2013-05-20 07:58:10','2013-05-20 07:58:10','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-04-30 21:00:00',2220,'34 DUS 76',NULL,'2013-04-30 21:00:00','','1147',9,1,6),(62,'2013-05-20 09:13:58','2013-05-20 09:13:58','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-01 21:00:00',2080,'34 DUS 76',NULL,'2013-05-01 21:00:00','','1148',9,1,6),(63,'2013-05-20 09:14:10','2013-05-20 09:14:10','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-02 21:00:00',1340,'34 DUS 76',NULL,'2013-05-02 21:00:00','','1149',9,1,6),(64,'2013-05-20 09:14:29','2013-05-20 09:14:29','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-03 21:00:00',1580,'34 DUS 76',NULL,'2013-05-03 21:00:00','','1150',9,1,6),(65,'2013-05-20 09:14:59','2013-05-20 09:14:59','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-05 21:00:00',2620,'34 DUS 76',NULL,'2013-05-05 21:00:00','','1151',9,1,6),(66,'2013-05-20 09:15:43','2013-05-20 09:15:43','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-07 21:00:00',3260,'34 DUS 76',NULL,'2013-05-07 21:00:00','','1152',9,1,6),(67,'2013-05-20 09:16:02','2013-05-20 09:16:02','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-08 21:00:00',2420,'34 DUS 76',NULL,'2013-05-08 21:00:00','','1153',9,1,6),(68,'2013-05-20 09:16:18','2013-05-20 09:16:18','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-10 21:00:00',4660,'34 DUS 76',NULL,'2013-05-10 21:00:00','','1154',9,1,6),(69,'2013-05-20 08:51:25','2013-05-20 08:51:25','Murat murat','KATEK','2013-05-19 21:00:00',17000,'35 GB 111','850.00','2013-05-19 21:00:00','\0','2687',14,1,16),(70,'2013-05-20 09:16:40','2013-05-20 09:16:40','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-12 21:00:00',4800,'34 DUS 76',NULL,'2013-05-12 21:00:00','','1155',9,1,6),(71,'2013-05-20 09:17:40','2013-05-20 09:17:40','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-13 21:00:00',2760,'34 DUS 76',NULL,'2013-05-13 21:00:00','','1156',9,1,6),(72,'2013-05-20 09:18:21','2013-05-20 09:18:21','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-14 21:00:00',1500,'34 DUS 76',NULL,'2013-05-14 21:00:00','','1157',9,1,6),(73,'2013-05-20 09:20:30','2013-05-20 09:20:30','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-15 21:00:00',1860,'34 DUS 76',NULL,'2013-05-15 21:00:00','','1158',9,1,6),(74,'2013-05-20 09:21:21','2013-05-20 09:21:21','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-16 21:00:00',1760,'34 DUS 76',NULL,'2013-05-16 21:00:00','','1159',9,1,6),(75,'2013-05-20 09:22:29','2013-05-20 09:22:29','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-17 21:00:00',3440,'34 DUS 76',NULL,'2013-05-17 21:00:00','','1160',9,1,6),(76,'2013-05-20 10:41:06','2013-05-20 10:41:06','MUSTAFA TİRYAKİ','AKYAZI','2013-05-02 21:00:00',11300,'54 HE 973',NULL,'2013-05-02 21:00:00','','3701',9,1,27),(77,'2013-05-20 10:43:47','2013-05-20 10:43:47','AHMET AKSOY','SOYSAL','2013-05-05 21:00:00',6490,'41 TM 339',NULL,'2013-05-05 21:00:00','','3698',9,1,27),(78,'2013-05-20 10:46:28','2013-05-20 10:46:28','MUSTAFA TİRYAKİ','AKYAZI','2013-05-06 21:00:00',8200,'54 HE 973',NULL,'2013-05-06 21:00:00','','3751',9,1,27),(79,'2013-05-20 10:48:46','2013-05-20 10:48:46','CEMAL ATAY','İNNOVA','2013-05-07 21:00:00',1820,'35 HA 6914',NULL,'2013-05-07 21:00:00','','3752',9,1,27),(80,'2013-05-20 10:50:42','2013-05-20 10:50:42','AYKUT AYAR','AKYAZI','2013-05-07 21:00:00',7420,'54 KT 386',NULL,'2013-05-07 21:00:00','','3753',9,1,27),(81,'2013-05-20 10:52:26','2013-05-20 10:52:26','MUSTAFA TİRYAKİ','AKYAZI','2013-05-08 21:00:00',9600,'54 HE 973',NULL,'2013-05-08 21:00:00','','3755',9,1,27),(82,'2013-05-20 18:07:08','2013-05-20 18:07:08','METİN KURT','AKYAZI','2013-05-08 21:00:00',9120,'54 TR 204',NULL,'2013-05-08 21:00:00','','3756',9,1,27),(83,'2013-05-20 18:09:14','2013-05-20 18:09:14','MUSTAFA TİRYAKİ','AKYAZI','2013-05-12 21:00:00',9960,'54 HE 973',NULL,'2013-05-12 21:00:00','','3757',9,1,27),(84,'2013-05-20 18:11:03','2013-05-20 18:11:03','AHMET AKSOY','İNNOVA','2013-05-13 21:00:00',7540,'41 TM 339',NULL,'2013-05-13 21:00:00','','3759',9,1,27),(85,'2013-05-20 18:12:59','2013-05-20 18:12:59','AHMET AKSOY','İNNOVA','2013-05-13 21:00:00',7860,'41 TM 339',NULL,'2013-05-13 21:00:00','','3761',9,1,27),(86,'2013-05-20 18:15:05','2013-05-20 18:15:05','ÖZGÜR KURT','AKYAZI','2013-05-14 21:00:00',8480,'54 D 1983',NULL,'2013-05-14 21:00:00','','3762',9,1,27),(87,'2013-05-20 18:17:45','2013-05-20 18:17:45','VEDAT ARSLAN','SOYSAL','2013-05-14 21:00:00',7320,'34 V 2239',NULL,'2013-05-14 21:00:00','','3763',9,1,27),(88,'2013-05-20 18:20:27','2013-05-20 18:20:27','MUSTAFA TİRYAKİ','AKYAZI','2013-05-14 21:00:00',9200,'54 HE 973',NULL,'2013-05-14 21:00:00','','3764',9,1,27),(89,'2013-05-20 18:22:13','2013-05-20 18:22:13','VEDAT ARSLAN','SOYSAL','2013-05-16 21:00:00',8060,'34 V 2239',NULL,'2013-05-16 21:00:00','','3766',9,1,27),(90,'2013-05-20 18:23:56','2013-05-20 18:23:56','VEDAT ARSLAN','SOYSAL','2013-05-17 21:00:00',8040,'34 V 2239',NULL,'2013-05-17 21:00:00','','3767',9,1,27),(91,'2013-05-21 07:41:17','2013-05-21 07:41:17','mehmet özdemir','gebze tesis','2013-05-19 21:00:00',6360,'41 D 1479',NULL,'2013-05-19 21:00:00','','1161',9,1,2),(92,'2013-05-21 07:42:21','2013-05-21 07:42:21','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-19 21:00:00',2580,'34 DUS 76',NULL,'2013-05-19 21:00:00','','1162',9,1,6),(93,'2013-05-21 07:43:53','2013-05-21 07:43:53','yavuz narman','gebze tesis','2013-05-19 21:00:00',1040,'34 EKS 12',NULL,'2013-05-19 21:00:00','','1163',9,1,5),(94,'2013-05-21 09:09:03','2013-05-21 09:09:03','MUSTAFA TİRYAKİ','AKYAZI','2013-05-19 21:00:00',9860,'54 HE 973',NULL,'2013-05-19 21:00:00','','3768',9,1,27),(95,'2013-05-22 07:04:59','2013-05-22 07:04:59','mehmet özdemir','gebze tesis','2013-05-20 21:00:00',3480,'41 D 1479',NULL,'2013-05-20 21:00:00','','1164',9,1,2),(96,'2013-05-22 07:06:39','2013-05-22 07:06:39','ismail ipek','gebze tesis','2013-05-20 21:00:00',1340,'34 NGM 64',NULL,'2013-05-20 21:00:00','','1165',9,1,3),(97,'2013-05-22 07:07:41','2013-05-22 07:07:41','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-20 21:00:00',2880,'34 DUS 76',NULL,'2013-05-20 21:00:00','','1166',9,1,6),(98,'2013-05-22 07:12:07','2013-05-22 07:12:07','murat orhan','SOYSAL','2013-05-20 21:00:00',7840,'34 EBJ 75',NULL,'2013-05-20 21:00:00','','3769',9,1,27),(99,'2013-05-22 07:14:23','2013-05-22 07:14:23','SELÇUK TİRYAKİ','AKYAZI','2013-05-20 21:00:00',11300,'54 HV 245',NULL,'2013-05-20 21:00:00','','3770',9,1,27),(100,'2013-05-23 06:47:45','2013-05-23 06:47:45','mehmet özdemir','gebze tesis','2013-05-21 21:00:00',6440,'41 D 1479',NULL,'2013-05-21 21:00:00','','1167',9,1,2),(101,'2013-05-23 06:48:52','2013-05-23 06:48:52','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-21 21:00:00',2480,'34 DUS 76',NULL,'2013-05-21 21:00:00','','1168',9,1,6),(102,'2013-05-23 06:53:03','2013-05-23 06:53:03','AHMET AKSOY','İNNOVA','2013-05-22 09:00:00',5480,'41 TM 339',NULL,'2013-05-22 09:00:00','','3772',9,1,27),(108,'2013-05-27 19:54:03','2013-05-27 19:54:03','HAŞİM UYAR','AKYAZI FABRİKA','2013-05-27 06:23:00',14140,'41.PV.617','600.00','2013-05-25 13:14:00','','310733',12,1,8),(109,'2013-05-27 07:19:17','2013-05-27 07:19:17','MEHMET ÖZDEMİR','gebze tesis','2013-05-22 21:00:00',5360,'41 D 1479',NULL,'2013-05-22 21:00:00','','1169',9,1,2),(110,'2013-05-27 07:20:44','2013-05-27 07:20:44','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-22 21:00:00',1520,'34 DUS 76 ',NULL,'2013-05-22 21:00:00','','1170',9,1,6),(111,'2013-05-27 07:21:44','2013-05-27 07:21:44','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-05-23 21:00:00',980,'34 DUS 76 ',NULL,'2013-05-23 21:00:00','','1171',9,1,6),(112,'2013-05-27 07:23:14','2013-05-27 07:23:14','MEHMET ÖZDEMİR','gebze tesis','2013-05-23 21:00:00',6780,'41 D 1479',NULL,'2013-05-23 21:00:00','','1172',9,1,2),(113,'2013-05-27 11:29:02','2013-05-27 11:29:02','AHMET AKSOY','AKYAZI','2013-05-21 21:00:00',6400,'41 TM 339',NULL,'2013-05-21 21:00:00','','3773',9,1,27),(114,'2013-05-27 12:12:55','2013-05-27 12:12:55','MUSTAFA TİRYAKİ','AKYAZI','2013-05-22 21:00:00',9220,'54 HE 973',NULL,'2013-05-22 21:00:00','','3774',9,1,27),(115,'2013-05-27 12:14:52','2013-05-27 12:14:52','İRFAN IŞIK','AKYAZI','2013-05-23 21:00:00',8140,'54 HL 081',NULL,'2013-05-23 21:00:00','','3775',9,1,27),(116,'2013-05-27 12:17:01','2013-05-27 12:17:01','MURAT ORHAN','SOYSAL','2013-05-24 21:00:00',8880,'34 EBJ 75',NULL,'2013-05-24 21:00:00','','3777',9,1,27),(117,'2013-05-28 18:41:26','2013-05-28 18:41:26','KUDRET KAZANBAŞ','AKYAZI FABRİKA','2013-05-28 06:14:00',13800,'41.DN.420','800.00','2013-05-27 17:27:00','','310734',12,1,8),(118,'2013-05-28 06:05:12','2013-05-28 06:05:12','mehmet özdemir','gebze tesis','2013-05-24 21:00:00',1400,'41 D 1479',NULL,'2013-05-24 21:00:00','','1173',9,1,2),(119,'2013-05-28 06:06:10','2013-05-28 06:06:10','mehmet özdemir','gebze tesis','2013-05-26 21:00:00',16500,'41 D 1479',NULL,'2013-05-26 21:00:00','','1174',9,1,2),(120,'2013-05-28 06:08:35','2013-05-28 06:08:35','İSMAİL İPEK','gebze tesis','2013-05-26 21:00:00',940,'34 NGM 64',NULL,'2013-05-26 21:00:00','','1175',9,1,3),(121,'2013-05-28 06:09:30','2013-05-28 06:09:30','yavuz narman','gebze tesis','2013-05-26 21:00:00',1060,'34 EKS 12',NULL,'2013-05-26 21:00:00','','1176',9,1,5),(122,'2013-05-28 11:22:15','2013-05-28 11:22:15','MUSTAFA TİRYAKİ','akyazı','2013-05-24 21:00:00',10860,'54 HE 973',NULL,'2013-05-24 21:00:00','','3776',9,1,27),(123,'2013-05-28 11:24:28','2013-05-28 11:24:28','AHMET AKSOY','SOYSAL ','2013-05-26 21:00:00',11260,'41 TM 339',NULL,'2013-05-26 21:00:00','','3779',9,1,27),(124,'2013-05-30 18:42:28','2013-05-30 18:42:28','SALİH ALBAYRAKLAR','AKYAZI FABRİKA','2013-05-30 06:14:00',14580,'14.BR.317','600.00','2013-05-29 15:39:00','','310735',12,1,8),(125,'2013-05-31 17:16:53','2013-05-31 17:16:53','VAHİT AKYÜZ','AKYAZI FABRİKA','2013-05-31 06:12:00',13160,'81.DA.592','600.00','2013-05-30 16:44:00','','310736',12,1,8),(126,'2013-06-03 07:56:15','2013-06-03 07:56:15','mehmet özdemir','gebze tesis','2013-06-27 21:00:00',5000,'41 D 1479',NULL,'2013-06-27 21:00:00','','1177',9,1,2),(127,'2013-06-03 07:55:11','2013-06-03 07:55:11','mehmet özdemir','gebze tesis','2013-06-28 21:00:00',7820,'41 D 1479',NULL,'2013-06-28 21:00:00','','1178',9,1,2),(128,'2013-06-03 07:57:34','2013-06-03 07:57:34','mehmet özdemir','gebze tesis','2013-06-29 21:00:00',4020,'41 D 1479',NULL,'2013-06-29 21:00:00','','1179',9,1,2),(129,'2013-06-03 07:59:01','2013-06-03 07:59:01','mehmet özdemir','gebze tesis','2013-06-29 21:00:00',7660,'41 D 1479',NULL,'2013-06-29 21:00:00','','1180',9,1,2),(130,'2013-06-03 08:02:04','2013-06-03 08:02:04','İSMAİL İPEK','gebze tesis','2013-06-27 21:00:00',1460,'34 NGM 64',NULL,'2013-06-27 21:00:00','','1181',9,1,3),(131,'2013-06-03 08:03:16','2013-06-03 08:03:16','İSMAİL İPEK','gebze tesis','2013-06-28 21:00:00',1380,'34 NGM 64',NULL,'2013-06-28 21:00:00','','1182',9,1,3),(132,'2013-06-03 08:05:11','2013-06-03 08:05:11','İSMAİL İPEK','gebze tesis','2013-06-29 21:00:00',880,'34 NGM 64',NULL,'2013-06-29 21:00:00','','1183',9,1,3),(133,'2013-06-03 08:07:40','2013-06-03 08:07:40','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-06-28 21:00:00',1920,'34 DUS 76',NULL,'2013-06-28 21:00:00','','1184',9,1,6),(134,'2013-06-03 08:08:30','2013-06-03 08:08:30','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-06-29 21:00:00',3120,'34 DUS 76',NULL,'2013-06-29 21:00:00','','1185',9,1,6),(135,'2013-06-03 08:09:43','2013-06-03 08:09:43','SELAHATTİN KIZILTOPRAK','gebze tesis','2013-06-29 21:00:00',2400,'34 DUS 76',NULL,'2013-06-29 21:00:00','','1186',9,1,6),(136,'2013-06-03 08:10:52','2013-06-03 08:10:52','yavuz narman','gebze tesis','2013-06-28 21:00:00',1260,'34 EKS 12',NULL,'2013-06-28 21:00:00','','1187',9,1,5),(137,'2013-06-03 08:11:42','2013-06-03 08:11:42','yavuz narman','gebze tesis','2013-06-29 21:00:00',1240,'34 EKS 12',NULL,'2013-06-29 21:00:00','','1188',9,1,5),(138,'2013-06-04 20:25:49','2013-06-04 20:25:49','MEHMET ÖZTÜRK','AKYAZI FABRİKA','2013-06-03 14:00:00',3120,'54.TS.155','0.00','2013-06-03 05:00:00','','083415',12,1,7),(139,'2013-06-04 20:26:11','2013-06-04 20:26:11','MEHMET ÖZTÜRK','AKYAZI FABRİKA','2013-06-01 05:26:00',2700,'54.TS.155','0.00','2013-06-01 05:26:00','','083414',12,1,7),(140,'2013-06-04 20:26:18','2013-06-04 20:26:18','MEHMET ÖZTÜRK','AKYAZI FABRİKA','2013-06-04 05:30:00',2380,'54.TS.155','0.00','2013-06-04 05:30:00','','083416',12,1,7);
/*!40000 ALTER TABLE `forwarding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quota`
--

DROP TABLE IF EXISTS `quota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quota` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `active` bit(1) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quota`
--

LOCK TABLES `quota` WRITE;
/*!40000 ALTER TABLE `quota` DISABLE KEYS */;
INSERT INTO `quota` VALUES (1,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID1','LASDER'),(2,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID2','Çimento Fabrikası');
/*!40000 ALTER TABLE `quota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'2013-05-13 18:44:42','2013-05-13 18:44:42','İstanbul/Trakya'),(2,'2013-05-13 18:44:42','2013-05-13 18:44:42','Marmara'),(3,'2013-05-13 18:44:42','2013-05-13 18:44:42','Kuzey/Orta Anadolu'),(4,'2013-05-20 08:59:53','2013-05-20 08:59:53','Ege'),(5,'2013-05-13 18:44:42','2013-05-13 18:44:42','Doğu Akdeniz'),(6,'2013-05-13 18:44:42','2013-05-13 18:44:42','Güney/Orta Anadolu');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `code` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2013-05-13 18:44:42','2013-05-13 18:44:42','ROLE_SUPERADMIN','Süper Yönetici'),(2,'2013-05-13 18:44:42','2013-05-13 18:44:42','ROLE_ADMIN','Yönetici'),(3,'2013-05-13 18:44:42','2013-05-13 18:44:42','ROLE_USER','Kullanıcı');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcontractor`
--

DROP TABLE IF EXISTS `subcontractor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcontractor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `active` bit(1) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FKCDCE10B57DD3F182` (`region_id`),
  CONSTRAINT `FKCDCE10B57DD3F182` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcontractor`
--

LOCK TABLES `subcontractor` WRITE;
/*!40000 ALTER TABLE `subcontractor` DISABLE KEYS */;
INSERT INTO `subcontractor` VALUES (1,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID1','Fethi Topaktaş',1),(2,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID2','Niyazi Özdemir',2),(3,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID3','Newnak',2),(4,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID4','Ali Doğan',2),(5,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID5','Yavuz Narman',2),(6,'2013-05-20 07:43:51','2013-05-20 07:43:51','\0','UID6','Selahattin Kızıltoprak',2),(7,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID7','Çubukçular',2),(8,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID8','Veysel Kutucu',2),(9,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID9','Erdal Yalım Bursa',2),(10,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID10','Erdal Yalım Eskişehir',2),(11,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID11','Ali Caner',2),(12,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID12','İsmail Demirkol',2),(13,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID13','İbrahim Gedikli',3),(14,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID14','Diğer',3),(15,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID15','Yaşar Bayrakal',4),(16,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID16','Ömer Örücü',4),(17,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID17','Ramazan Kafa',4),(18,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID18','Diğer',4),(19,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID19','Veysel Kızıldağ',5),(20,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID20','Ayhan Aydın',5),(21,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID21','Murat Teke',5),(22,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID22','Ünsal Alemdar',5),(23,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID23','Senayi Öztürk',5),(24,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID24','Diğer',5),(25,'2013-05-13 18:44:42','2013-05-13 18:44:42','','UID25','Diğer',6),(26,'2013-05-14 07:00:29','2013-05-14 07:00:29','\0','İstanbul','LGK Ekip',1),(27,'2013-05-14 12:03:27','2013-05-14 12:03:27','\0','','Gebze LGK',2),(30,'2013-05-20 08:57:12','2013-05-20 08:57:12','\0','Ege','LGK Ekip',4);
/*!40000 ALTER TABLE `subcontractor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `endingKm` int(11) DEFAULT NULL,
  `endingPoint` varchar(255) DEFAULT NULL,
  `endingTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `loadWeightInTonne` int(11) DEFAULT NULL,
  `remark` text,
  `startingKm` int(11) DEFAULT NULL,
  `startingPoint` varchar(255) DEFAULT NULL,
  `startingTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `submitted` bit(1) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `driver_id` bigint(20) DEFAULT NULL,
  `truck_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK367425ABB62412` (`truck_id`),
  KEY `FK36742510C9F502` (`driver_id`),
  KEY `FK367425DCEC7860` (`account_id`),
  CONSTRAINT `FK367425DCEC7860` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK36742510C9F502` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`),
  CONSTRAINT `FK367425ABB62412` FOREIGN KEY (`truck_id`) REFERENCES `truck` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (1,'2013-05-14 07:56:23','2013-05-14 07:56:23',35150,'Depo','2013-05-14 10:00:00',3500,'',35100,'Depo','2013-05-14 06:00:00','',8,1,1),(5,'2013-05-15 07:25:22','2013-05-15 07:25:22',104280,'gebze tesis','2013-05-03 15:00:00',5000,'',103995,'gebze tesis','2013-05-03 05:00:00','',9,4,5),(6,'2013-05-15 07:27:22','2013-05-15 07:27:22',104483,'gebze tesis','2013-05-06 15:00:00',3780,'',104280,'gebze tesis','2013-05-06 05:00:00','',9,4,5),(7,'2013-05-15 07:32:53','2013-05-15 07:32:53',104860,'gebze tesis','2013-05-08 15:00:00',3720,'',104483,'gebze tesis','2013-05-07 05:00:00','',9,4,5),(8,'2013-05-15 07:34:21','2013-05-15 07:34:21',105025,'gebze tesis','2013-05-10 15:00:00',1580,'',104860,'gebze tesis','2013-05-10 05:00:00','',9,4,5),(9,'2013-05-15 07:35:38','2013-05-15 07:35:38',105219,'gebze tesis','2013-05-11 15:00:00',940,'',105025,'gebze tesis','2013-05-11 05:00:00','',9,4,5),(10,'2013-05-15 07:37:02','2013-05-15 07:37:02',105441,'gebze tesis','2013-05-13 15:00:00',2700,'',105219,'gebze tesis','2013-05-13 05:00:00','',9,4,5),(11,'2013-05-15 12:20:38','2013-05-15 12:20:38',245677,'DEPO','2013-05-15 12:00:00',2230,'GAZİOSMANPAŞA çevresi',245571,'DEPO','2013-05-15 05:30:00','',8,1,1),(12,'2013-05-15 12:21:43','2013-05-15 12:21:43',185831,'DEPO','2013-05-15 09:30:00',2720,'',185751,'DEPO','2013-05-15 05:30:00','',8,2,3),(13,'2013-05-15 14:04:18','2013-05-15 14:04:18',185919,'DEPO','2013-05-15 14:00:00',2300,'MAHMUTBEY VE CIVARI\r\n',1858831,'DEPO','2013-05-15 10:00:00','',8,2,3),(14,'2013-05-15 15:15:24','2013-05-15 15:15:24',204320,'DEPO','2013-05-15 15:00:00',3810,'ÇATALCA CİVARI',204169,'DEPO','2013-05-15 10:00:00','',8,3,2),(15,'2013-05-15 15:19:15','2013-05-15 15:19:15',245712,'DEPO','2013-05-15 15:10:00',1750,'BAĞCILAR\r\n',245677,'DEPO','2013-05-15 12:00:00','',8,1,1),(17,'2013-05-15 15:37:20','2013-05-15 15:37:20',172870,'DEPO','2013-05-15 12:00:00',3570,'HAYREBOLU',172785,'DEPO','2013-05-15 05:30:00','',8,1,4),(18,'2013-05-15 15:37:47','2013-05-15 15:37:47',172935,'DEPO','2013-05-15 15:30:00',3870,'LÜLEBURĞAZ\r\n',172870,'DEPO','2013-05-15 12:40:00','',8,1,4),(19,'2013-05-16 06:52:51','2013-05-16 06:52:51',105595,'gebze tesis','2013-05-15 09:00:00',1360,'',105441,'gebze tesis','2013-05-15 05:00:00','',9,4,5),(20,'2013-05-16 12:09:25','2013-05-16 12:09:25',186012,'DEPO','2013-05-16 11:30:00',4180,'HAVALİMANI',185919,'DEPO','2013-05-16 05:30:00','',8,1,3),(21,'2013-05-16 12:09:46','2013-05-16 12:09:46',245850,'DEPO','2013-05-16 11:30:00',1960,'BEŞİKTAŞ\r\n',245754,'DEPO','2013-05-16 05:30:00','',8,1,1),(22,'2013-05-16 12:10:04','2013-05-16 12:10:04',204350,'DEPO','2013-05-16 11:40:00',2580,'HAVALİMANI',204320,'DEPO','2013-05-16 08:00:00','',8,1,2),(23,'2013-05-16 12:22:12','2013-05-16 12:22:12',173085,'DEPO','2013-05-16 09:30:00',3490,'TEKİRDAĞ',172935,'DEPO','2013-05-16 05:30:00','',8,1,4),(24,'2013-05-16 13:37:35','2013-05-16 13:37:35',173225,'DEPO','2013-05-16 13:35:00',4740,'TEKİRDAĞ',173085,'DEPO','2013-05-16 10:00:00','',8,1,4),(25,'2013-05-17 10:07:45','2013-05-17 10:07:45',1150,'çetinkaya','2013-05-17 04:15:00',2000,'',1100,'Depo','2013-05-17 04:15:00','',13,5,5),(26,'2013-05-17 11:11:15','2013-05-17 11:11:15',105700,'gebze tesis','2013-05-16 14:00:00',3060,'',105595,'gebze tesis','2013-05-16 05:00:00','',9,4,5),(27,'2013-05-17 12:51:01','2013-05-17 12:51:01',245917,'DEPO','2013-05-17 16:30:00',1330,'ZEYTİNBURNU',245850,'DEPO','2013-05-17 12:30:00','',8,1,1),(28,'2013-05-17 12:51:15','2013-05-17 12:51:15',246004,'DEPO','2013-05-17 08:30:00',2240,'KAĞITHANE',245965,'DEPO','2013-05-17 05:30:00','',8,1,1),(29,'2013-05-17 12:51:30','2013-05-17 12:51:30',204591,'DEPO','2013-05-17 16:30:00',3440,'ÇERKEZKÖY',204320,'DEPO','2013-05-17 12:00:00','',8,1,2),(30,'2013-05-17 12:59:18','2013-05-17 12:59:18',186304,'DEPO','2013-05-17 11:30:00',3560,'ÇORLU',186012,'DEPO','2013-05-17 05:30:00','',8,1,3),(31,'2013-05-17 13:55:26','2013-05-17 13:55:26',173308,'DEPO','2013-05-17 10:00:00',4380,'ULAŞ',173137,'DEPO','2013-05-17 05:30:00','',8,1,4),(32,'2013-05-17 13:55:37','2013-05-17 13:55:37',173398,'DEPO','2013-05-17 13:30:00',4360,'MURATLI',173308,'DEPO','2013-05-17 10:00:00','',8,1,4),(33,'2013-05-17 15:06:25','2013-05-17 15:06:25',246080,'DEPO','2013-05-17 14:30:00',1960,'DOLAPDERE',246003,'DEPO','2013-05-17 09:30:00','',8,1,1),(34,'2013-05-17 15:06:39','2013-05-17 15:06:39',204635,'DEPO','2013-05-17 15:00:00',2780,'TATKAP CEBECİ',204592,'DEPO','2013-05-17 13:00:00','',8,1,2),(35,'2013-05-18 10:30:16','2013-05-18 10:30:16',173500,'DEPO','2013-05-18 09:00:00',1700,'MURATLI',173414,'DEPO','2013-05-18 05:30:00','',8,1,4),(36,'2013-05-18 10:30:29','2013-05-18 10:30:29',246179,'DEPO','2013-05-18 10:30:00',2030,'ORTAKÖY',246081,'DEPO','2013-05-18 05:30:00','',8,1,1),(37,'2013-05-18 10:30:41','2013-05-18 10:30:41',204761,'DEPO','2013-05-18 10:30:00',2950,'TATKAP VE SEFAKÖY CIVARI',204637,'DEPO','2013-05-18 05:30:00','',8,1,2),(38,'2013-05-21 07:37:05','2013-05-21 07:37:05',105826,'gebze tesis','2013-05-17 14:00:00',3100,'',105700,'gebze tesis','2013-05-17 05:00:00','',9,4,5),(39,'2013-05-20 08:35:16','2013-05-20 08:35:16',35500,'Depo','2013-05-20 12:00:00',5000,'',35000,'Depo','2013-05-20 06:00:00','',14,10,15),(40,'2013-05-20 08:37:34','2013-05-20 08:37:34',35700,'Depo','2013-05-21 05:00:00',3000,'',35500,'dep','2013-05-20 13:00:00','',14,10,12),(41,'2013-05-20 11:50:59','2013-05-20 11:50:59',246280,'DEPO','2013-05-20 10:30:00',2130,'LEVENT KAĞITHANE',246215,'DEPO','2013-05-20 05:30:00','',8,1,1),(45,'2013-05-21 07:38:35','2013-05-21 07:38:35',105967,'gebze tesis','2013-05-20 15:00:00',2960,'',105836,'gebze tesis','2013-05-20 05:00:00','',9,4,5),(46,'2013-05-21 17:15:24','2013-05-21 17:15:24',333467,'depo','2013-05-21 17:00:00',5100,'izmir\'den toplanan lastik',333456,'depo','2013-05-21 06:00:00','\0',14,10,15),(47,'2013-05-22 07:03:21','2013-05-22 07:03:21',106103,'gebze tesis','2013-05-21 15:00:00',3720,'',105967,'gebze tesis','2013-05-21 05:00:00','',9,4,5),(48,'2013-05-23 06:45:20','2013-05-23 06:45:20',106250,'gebze tesis','2013-05-22 14:00:00',2040,'',106103,'gebze tesis','2013-05-22 05:00:00','',9,4,5),(49,'2013-05-23 13:49:56','2013-05-23 13:49:56',246354,'DEPO','2013-05-20 14:30:00',2110,'',246280,'DEPO','2013-05-20 09:30:00','',8,1,1),(50,'2013-05-23 13:50:17','2013-05-23 13:50:17',246475,'DEPO','2013-05-21 10:30:00',1710,'',246354,'DEPO','2013-05-21 05:30:00','',8,1,1),(51,'2013-05-23 13:50:37','2013-05-23 13:50:37',246544,'DEPO','2013-05-21 15:10:00',2100,'',246475,'DEPO','2013-05-21 10:30:00','',8,1,1),(52,'2013-05-23 13:50:54','2013-05-23 13:50:54',204969,'DEPO','2013-05-21 05:10:00',3560,'',204969,'DEPO','2013-05-21 05:10:00','',8,2,2),(53,'2013-05-23 13:51:09','2013-05-23 13:51:09',205157,'DEPO','2013-05-21 11:40:00',4060,'',204969,'DEPO','2013-05-21 06:40:00','',8,2,2),(54,'2013-05-23 13:51:25','2013-05-23 13:51:25',186443,'DEPO','2013-05-22 09:30:00',3150,'',186314,'DEPO','2013-05-21 09:30:00','',8,2,3),(55,'2013-05-23 13:51:36','2013-05-23 13:51:36',246634,'DEPO','2013-05-22 10:30:00',1720,'',246575,'DEPO','2013-05-22 05:30:00','',8,1,1),(56,'2013-05-23 13:51:47','2013-05-23 13:51:47',246694,'DEPO','2013-05-22 14:30:00',2060,'',246634,'DEPO','2013-05-22 10:30:00','',8,1,1),(57,'2013-05-23 13:52:37','2013-05-23 13:52:37',205552,'DEPO','2013-05-23 11:00:00',4190,'',205157,'DEPO','2013-05-22 11:00:00','',8,2,2),(58,'2013-05-23 13:53:10','2013-05-23 13:53:10',246796,'DEPO','2013-05-23 10:00:00',2170,'',246731,'DEPO','2013-05-23 05:30:00','',8,1,1),(59,'2013-05-23 13:53:38','2013-05-23 13:53:38',186700,'DEPO','2013-05-23 10:30:00',3760,'',186447,'DEPO','2013-05-23 05:30:00','',8,2,3),(60,'2013-05-23 13:54:18','2013-05-23 13:54:18',173782,'DEPO','2013-05-20 15:11:00',4460,'KEŞAN',173515,'DEPO','2013-05-20 05:29:00','',8,3,4),(61,'2013-05-23 13:56:17','2013-05-23 13:56:17',174014,'DEPO','2013-05-21 05:30:00',4490,'TEKİRDAĞ\r\n',173785,'DEPO','2013-05-21 05:30:00','',8,3,4),(62,'2013-05-23 13:56:32','2013-05-23 13:56:32',174194,'DEPO','2013-05-22 14:31:00',3440,'KIRKLARELİ BABAESKİ',174014,'DEPO','2013-05-22 05:31:00','',8,3,4),(63,'2013-05-23 13:56:49','2013-05-23 13:56:49',246829,'DEPO','2013-05-23 13:27:00',2140,'',246796,'DEPO','2013-05-23 10:00:00','',8,1,1),(64,'2013-05-23 13:57:11','2013-05-23 13:57:11',174362,'DEPO','2013-05-23 12:51:00',3500,'',174158,'DEPO','2013-05-23 05:29:00','',8,3,4),(65,'2013-05-27 19:54:53','2013-05-27 19:54:53',60555,'AKYAZI FABRİKA','2013-05-02 13:21:00',3820,'AKYAZI-GÖYNÜK-MUDURNU',60332,'AKYAZI FABRİKA','2013-05-02 05:00:00','',12,5,6),(66,'2013-05-27 19:55:03','2013-05-27 19:55:03',60758,'AKYAZI FABRİKA','2013-05-03 13:38:00',2620,'AKYAZI-PAMUKOVA-ADAPAZARI-AKYAZI',60555,'AKYAZI FABRİKA','2013-05-03 05:15:00','',12,5,6),(67,'2013-05-27 19:55:12','2013-05-27 19:55:12',61091,'AKYAZI FABRİKA','2013-05-06 14:00:00',3520,'AKYAZI-KANDIRA-KAYNARCA',60893,'AKYAZI FABRİKA','2013-05-06 05:23:00','',12,5,6),(68,'2013-05-27 19:55:23','2013-05-27 19:55:23',61251,'AKYAZI FABRİKA','2013-05-07 13:48:00',4700,'PAMUKOVA /BEDELLİ ÖTL.ALIMI',61091,'AKYAZI FABRİKA','2013-05-07 05:00:00','',12,5,6),(69,'2013-05-27 19:55:42','2013-05-27 19:55:42',61435,'AKYAZI FABRİKA','2013-05-08 13:23:00',3280,'İZMİT VE ÇEVRESİ',61251,'AKYAZI FABRİKA','2013-05-08 05:14:00','',12,5,6),(70,'2013-05-27 19:55:50','2013-05-27 19:55:50',61673,'AKYAZI FABRİKA','2013-05-09 14:06:00',3200,'KARAMÜRSEL-İZMİT',61435,'AKYAZI FABRİKA','2013-05-09 05:06:00','',12,5,6),(71,'2013-05-27 19:55:58','2013-05-27 19:55:58',61802,'AKYAZI FABRİKA','2013-05-10 14:28:00',3440,'HENDEK-ADAPAZARI-AKYAZI',61687,'AKYAZI FABRİKA','2013-05-10 05:28:00','',12,5,6),(72,'2013-05-27 19:56:06','2013-05-27 19:56:06',62025,'AKYAZI FABRİKA','2013-05-11 14:14:00',3360,'İZNİK- ADAPAZARI',61802,'AKYAZI FABRİKA','2013-05-11 05:14:00','',12,5,6),(73,'2013-05-27 19:56:15','2013-05-27 19:56:15',62190,'AKYAZI FABRİKA','2013-05-13 14:21:00',4560,'PAMUKOVA-ADAPAZARI',62025,'AKYAZI FABRİKA','2013-05-13 05:21:00','',12,5,6),(74,'2013-05-27 19:56:25','2013-05-27 19:56:25',62297,'AKYAZI FABRİKA','2013-05-14 13:22:00',2540,'ADAPAZARI ÇEVRESİ - AKYAZI',62190,'AKYAZI FABRİKA','2013-05-14 05:33:00','',12,5,6),(75,'2013-05-27 19:56:41','2013-05-27 19:56:41',62531,'AKYAZI FABRİKA','2013-05-15 05:29:00',3520,'KARASU-KAYNARCA-AKYAZI',62297,'AKYAZI FABRİKA','2013-05-15 05:29:00','',12,5,6),(76,'2013-05-27 19:56:52','2013-05-27 19:56:52',62692,'AKYAZI FABRİKA','2013-05-16 14:08:00',3020,'GEYVE-SAPANCA-AKYAZI',62531,'AKYAZI FABRİKA','2013-05-16 05:08:00','',12,5,6),(77,'2013-05-27 19:57:02','2013-05-27 19:57:02',62842,'AKYAZI FABRİKA','2013-05-17 13:19:00',4860,'KARASU-FERİZLİ BEDELLİ ÖTL.ALIMI',62707,'AKYAZI FABRİKA','2013-05-17 08:07:00','',12,5,6),(78,'2013-05-27 19:57:11','2013-05-27 19:57:11',22140,'DÜZCE','2013-05-02 13:37:00',0,'YENİÇAĞA-BOLU-DÜZCE',21922,'DÜZCE','2013-05-02 05:31:00','',12,6,7),(79,'2013-05-27 19:57:22','2013-05-27 19:57:22',22236,'AKYAZI FABRİKA','2013-05-03 14:26:00',5240,'DÜZCE-AKYAZI-DÜZCE',22140,'DÜZCE','2013-05-03 05:26:00','',12,6,7),(80,'2013-05-27 19:57:33','2013-05-27 19:57:33',22378,'DÜZCE','2013-05-04 13:28:00',0,'KAYNAŞLI - DÜZCE',22236,'DÜZCE','2013-05-04 05:28:00','',12,6,7),(81,'2013-05-27 19:57:42','2013-05-27 19:57:42',22601,'DÜZCE','2013-05-06 14:00:00',0,'GEREDE-YENİÇAĞA-DÜZCE',22378,'DÜZCE','2013-05-06 06:00:00','',12,6,7),(82,'2013-05-27 19:57:56','2013-05-27 19:57:56',22690,'AKYAZI FABRİKA','2013-05-07 14:28:00',6000,'DÜZCE-FABRİKA-DÜZCE',22601,'DÜZCE','2013-05-07 05:20:00','',12,6,7),(83,'2013-05-27 19:58:05','2013-05-27 19:58:05',22841,'DÜZCE','2013-05-08 14:00:00',0,'KAYNAŞLI - DÜZCE',22690,'DÜZCE','2013-05-08 05:00:00','',12,6,7),(84,'2013-05-27 19:58:15','2013-05-27 19:58:15',22909,'AKYAZI FABRİKA','2013-05-09 07:15:00',2420,'DÜZCE-FABRİKA',22841,'DÜZCE','2013-05-09 05:00:00','',12,6,7),(85,'2013-05-27 19:58:27','2013-05-27 19:58:27',23184,'YALOVA','2013-05-09 15:15:00',6680,'YALOVA -BEDELLİ ÖTL.ALIMI',22909,'AKYAZI FABRİKA','2013-05-09 08:15:00','',12,6,7),(86,'2013-05-27 19:58:35','2013-05-27 19:58:35',23306,'DÜZCE','2013-05-10 14:10:00',0,'BOLU-KAYANAŞLI-DÜZCE',23184,'DÜZCE','2013-05-10 06:00:00','',12,6,7),(87,'2013-05-27 19:58:45','2013-05-27 19:58:45',23548,'DÜZCE','2013-05-11 14:13:00',7400,'DÜZCE-FABRİKA-DÜZCE',23306,'DÜZCE','2013-05-11 05:13:00','',12,6,7),(88,'2013-05-27 19:58:53','2013-05-27 19:58:53',23857,'DÜZCE','2013-05-13 14:25:00',0,'DÜZCE-BOLU-YIĞILCA',23548,'DÜZCE','2013-05-13 05:25:00','',12,6,7),(89,'2013-05-27 19:59:02','2013-05-27 19:59:02',23929,'DÜZCE','2013-05-14 14:28:00',5660,'DÜZCE-FABRİKA-DÜZCE',23857,'DÜZCE','2013-05-14 05:19:00','',12,6,7),(90,'2013-05-27 19:59:11','2013-05-27 19:59:11',24178,'DÜZCE','2013-05-15 14:19:00',0,'BEYKÖY-DÜZCE',23929,'DÜZCE','2013-05-15 05:19:00','',12,6,7),(91,'2013-05-27 19:59:19','2013-05-27 19:59:19',24322,'DÜZCE','2013-05-16 13:40:00',0,'GEREDE-YENİÇAĞA-BOLU',24178,'DÜZCE','2013-05-16 05:40:00','',12,6,7),(92,'2013-05-27 19:59:27','2013-05-27 19:59:27',24400,'DÜZCE','2013-05-17 13:23:00',6840,'DÜZCE-FABRİKA- BEDELLİ ÖTL.ALIMI',24322,'DÜZCE','2013-05-17 05:14:00','',12,6,7),(93,'2013-05-24 07:35:59','2013-05-24 07:35:59',205741,'DEPO','2013-05-24 07:00:00',4090,'ÇATALCA',205552,'DEPO','2013-05-23 11:00:00','',8,2,2),(94,'2013-05-24 07:36:10','2013-05-24 07:36:10',174578,'DEPO','2013-05-23 17:50:00',4250,'TEKİRDAĞ',174362,'DEPO','2013-05-23 12:50:00','',8,3,4),(95,'2013-05-24 07:36:22','2013-05-24 07:36:22',246895,'DEPO','2013-05-24 06:00:00',1950,'',246829,'DEPO','2013-05-23 06:00:00','',8,1,1),(96,'2013-05-24 15:44:34','2013-05-24 15:44:34',246946,'DEPO','2013-05-24 11:00:00',2040,'',246895,'DEPO','2013-05-24 07:34:00','',8,1,1),(97,'2013-05-24 15:44:46','2013-05-24 15:44:46',186838,'DEPO','2013-05-24 12:00:00',2490,'',186700,'DEPO','2013-05-24 05:30:00','',8,2,3),(98,'2013-05-24 15:44:58','2013-05-24 15:44:58',247012,'DEPO','2013-05-24 15:00:00',1980,'',246946,'DEPO','2013-05-24 11:00:00','',8,1,1),(99,'2013-05-25 20:57:14','2013-05-25 20:57:14',205799,'DEPO','2013-05-25 09:00:00',2320,'',205741,'DEPO','2013-05-24 07:00:00','',8,2,2),(100,'2013-05-25 20:57:31','2013-05-25 20:57:31',186958,'DEPO','2013-05-25 09:00:00',2090,'',186838,'DEPO','2013-05-25 05:32:00','',8,2,3),(101,'2013-05-27 07:15:17','2013-05-27 07:15:17',106412,'gebze tesis','2013-05-23 05:00:00',3320,'',106250,'gebze tesis','2013-05-23 05:00:00','',9,4,5),(102,'2013-05-27 07:16:47','2013-05-27 07:16:47',106530,'gebze tesis','2013-05-24 14:00:00',3200,'',106412,'gebze tesis','2013-05-24 05:00:00','',9,4,5),(103,'2013-05-29 07:10:30','2013-05-29 07:10:30',247103,'DEPO','2013-05-25 10:00:00',1870,'',247046,'DEPO','2013-05-25 05:30:00','',8,1,1),(104,'2013-05-29 07:10:49','2013-05-29 07:10:49',174758,'DEPO','2013-05-24 15:55:00',4360,'',174576,'DEPO','2013-05-24 05:30:00','',8,3,4),(105,'2013-05-29 07:13:25','2013-05-29 07:13:25',175084,'DEPO','2013-05-27 12:00:00',3500,'UZUNKÖPRÜ',174900,'DEPO','2013-05-27 05:31:00','',8,3,4),(106,'2013-05-27 19:59:33','2013-05-27 19:59:33',63031,'AKYAZI FABRİKA','2013-05-20 13:33:00',4020,'ADAPAZARI/HENDEK/AKYAZI',62923,'AKYAZI FABRİKA','2013-05-20 05:00:00','',12,5,6),(107,'2013-05-27 19:59:40','2013-05-27 19:59:40',63214,'AKYAZI FABRİKA','2013-05-21 14:16:00',4660,'KANDIRA/KAYNARCA BEDELLİ ÖTL.ALIMI',63031,'AKYAZI FABRİKA','2013-05-21 05:16:00','',12,5,6),(108,'2013-05-27 19:59:49','2013-05-27 19:59:49',63392,'AKYAZI FABRİKA','2013-05-22 13:25:00',4940,'KARAMÜRSEL/GÖLCÜK/İZMİT',63214,'AKYAZI FABRİKA','2013-05-22 05:00:00','',12,5,6),(109,'2013-05-27 19:59:55','2013-05-27 19:59:55',63570,'AKYAZI FABRİKA','2013-05-23 13:44:00',2300,'PAMUKOVA/ADAPAZARI',63392,'AKYAZI FABRİKA','2013-05-23 05:21:00','',12,5,6),(110,'2013-05-27 20:00:02','2013-05-27 20:00:02',63802,'AKYAZI FABRİKA','2013-05-24 14:18:00',3540,'KARAMÜRSEL/İZMİT/SAPANCA',63570,'AKYAZI FABRİKA','2013-05-24 05:24:00','',12,5,6),(111,'2013-05-27 20:00:09','2013-05-27 20:00:09',24600,'DÜZCE','2013-05-18 14:21:00',0,'BOLU/KAYNAŞLI',24400,'DÜZCE','2013-05-18 05:21:00','',12,6,7),(112,'2013-05-27 20:00:17','2013-05-27 20:00:17',24679,'DÜZCE','2013-05-20 14:35:00',0,'BEYKÖY/DÜZCE',24600,'DÜZCE','2013-05-20 05:35:00','',12,6,7),(113,'2013-05-27 20:00:25','2013-05-27 20:00:25',24729,'FABRİKA','2013-05-21 14:25:00',5500,'KAYANAŞLI/FABRİKA/DÜZCE',24679,'DÜZCE','2013-05-21 05:30:00','',12,6,7),(114,'2013-05-27 20:00:32','2013-05-27 20:00:32',24856,'DÜZCE','2013-05-22 14:37:00',0,'DÜZCE VE ÇEVRESİ,GÖLYAKA',24729,'DÜZCE','2013-05-22 05:23:00','',12,6,7),(115,'2013-05-27 20:00:40','2013-05-27 20:00:40',24936,'FABRİKA','2013-05-23 14:10:00',6700,'CUMAYERİ/FABRİKA/DÜZCE',24856,'DÜZCE','2013-05-23 05:23:00','',12,6,7),(116,'2013-05-27 20:00:47','2013-05-27 20:00:47',25202,'DÜZCE','2013-05-24 15:26:00',0,'BOLU VE ÇEVRESİ/DÜZCE',24936,'DÜZCE','2013-05-24 05:26:00','',12,6,7),(117,'2013-05-27 20:00:55','2013-05-27 20:00:55',25275,'FABRİKA','2013-05-25 13:24:00',5940,'DÜZCE/FABRİKA/DÜZCE',25202,'DÜZCE','2013-05-25 05:24:00','',12,6,7),(118,'2013-05-28 05:59:14','2013-05-28 05:59:14',106756,'gebze tesis','2013-05-27 15:00:00',3120,'',106530,'gebze tesis','2013-05-27 05:00:00','',9,4,5),(119,'2013-05-29 07:13:51','2013-05-29 07:13:51',175181,'DEPO','2013-05-27 16:00:00',3350,'edirne',175084,'DEPO','2013-05-27 12:00:00','',8,3,4),(120,'2013-05-29 07:14:20','2013-05-29 07:14:20',187131,'DEPO','2013-05-27 11:30:00',3020,'SARIYER',186958,'DEPO','2013-05-27 05:30:00','',8,16,3),(121,'2013-05-29 07:14:58','2013-05-29 07:14:58',187203,'DEPO','2013-05-28 07:29:00',3030,'',187131,'DEPO','2013-05-27 11:00:00','',8,16,3),(122,'2013-05-29 07:15:34','2013-05-29 07:15:34',187254,'DEPO','2013-05-28 12:47:00',3590,'',187203,'DEPO','2013-05-28 07:31:00','',8,16,3),(123,'2013-05-29 07:16:17','2013-05-29 07:16:17',206163,'DEPO','2013-05-27 11:30:00',4200,'',205799,'DEPO','2013-05-27 05:30:00','',8,2,2),(124,'2013-05-29 07:18:51','2013-05-29 07:18:51',206296,'DEPO','2013-05-28 07:34:00',2960,'',206163,'DEPO','2013-05-27 11:34:00','',8,2,2),(125,'2013-05-29 07:19:14','2013-05-29 07:19:14',206554,'DEPO','2013-05-28 14:33:00',4750,'SARAY',206296,'DEPO','2013-05-28 07:33:00','',8,2,2),(126,'2013-05-29 07:19:36','2013-05-29 07:19:36',247361,'DEPO','2013-05-28 11:55:00',2110,'',247288,'DEPO','2013-05-28 05:29:00','',8,1,1),(127,'2013-05-29 07:19:58','2013-05-29 07:19:58',247456,'DEPO','2013-05-29 07:01:00',2120,'',247361,'DEPO','2013-05-28 11:59:00','',8,1,1),(128,'2013-05-29 13:25:15','2013-05-29 13:25:15',187367,'DEPO','2013-05-29 12:30:00',3270,'',187285,'DEPO','2013-05-29 05:30:00','\0',8,16,3),(129,'2013-05-29 14:37:42','2013-05-29 14:37:42',247525,'DEPO','2013-05-29 11:31:00',1920,'',247456,'DEPO','2013-05-29 07:31:00','\0',8,1,1),(130,'2013-05-30 07:44:18','2013-05-30 07:44:18',187422,'DEPO','2013-05-30 05:32:00',3090,'',187367,'DEPO','2013-05-29 13:32:00','\0',8,16,3),(131,'2013-05-30 09:03:23','2013-05-30 09:03:23',206836,'DEPO','2013-05-29 11:40:00',3990,'',206554,'DEPO','2013-05-29 05:31:00','\0',8,2,2),(132,'2013-05-30 09:05:16','2013-05-30 09:05:16',207062,'DEPO','2013-05-30 05:39:00',3130,'',206836,'DEPO','2013-05-29 05:39:00','\0',8,2,2),(133,'2013-05-30 09:06:34','2013-05-30 09:06:34',207110,'DEPO','2013-05-30 09:00:00',3900,'NETLOK',207062,'DEPO','2013-05-30 07:00:00','\0',8,2,2),(134,'2013-06-04 20:28:08','2013-06-04 20:28:08',25452,'DÜZCE','2013-05-27 14:16:00',0,'BEYKÖY-DÜZCE',25275,'DÜZCE','2013-05-27 05:16:00','',12,6,7),(135,'2013-06-04 20:28:28','2013-06-04 20:28:28',25641,'DÜZCE','2013-05-28 14:28:00',5780,'DÜZCE-BOLU-FABRİKA',25452,'DÜZCE','2013-05-28 05:28:00','',12,6,7),(136,'2013-06-04 20:28:37','2013-06-04 20:28:37',25800,'DÜZCE','2013-05-29 15:00:00',0,'DÜZCE-YIĞILCA-DÜZCE',25641,'DÜZCE','2013-05-29 06:00:00','',12,6,7),(137,'2013-06-04 20:28:46','2013-06-04 20:28:46',25905,'DÜZCE','2013-05-30 14:35:00',7080,'DÜZCE-FABRİKA-DÜZCE',25800,'DÜZCE','2013-05-30 05:19:00','',12,6,7),(138,'2013-06-04 20:28:54','2013-06-04 20:28:54',26153,'DÜZCE','2013-05-31 14:32:00',0,'DÜZCE-BOLU-KAYANAŞLI-DÜZCE',25905,'DÜZCE','2013-05-31 05:42:00','',12,6,7),(139,'2013-06-04 20:29:01','2013-06-04 20:29:01',26221,'DÜZCE','2013-06-01 14:00:00',6360,'İRS.0833358***UATF.3410467699 - DÜZCE-FABRİKA-DÜZCE',26153,'DÜZCE','2013-06-01 06:00:00','',12,6,7),(140,'2013-06-04 20:29:10','2013-06-04 20:29:10',63922,'AKYAZI FABRİKA','2013-05-25 13:49:00',2440,'ADAPAZARI VE ÇEVRESİ',63802,'AKYAZI FABRİKA','2013-05-25 05:11:00','',12,5,6),(141,'2013-06-04 20:29:18','2013-06-04 20:29:18',64211,'AKYAZI FABRİKA','2013-05-27 14:20:00',3820,'MUDURNU-GÖYNÜK',63937,'AKYAZI FABRİKA','2013-05-27 05:20:00','',12,5,6),(142,'2013-06-04 20:29:26','2013-06-04 20:29:26',64423,'AKYAZI FABRİKA','2013-05-28 13:18:00',4360,'KAYNARCA-KANDIRA',64224,'AKYAZI FABRİKA','2013-05-28 05:18:00','',12,5,6),(143,'2013-06-04 20:29:34','2013-06-04 20:29:34',64749,'AKYAZI FABRİKA','2013-05-31 14:08:00',3640,'HENDEK-ADAPAZARI',64613,'AKYAZI FABRİKA','2013-05-31 05:00:00','',12,5,6),(144,'2013-06-03 07:48:40','2013-06-03 07:48:40',106877,'gebze tesis','2013-06-28 15:00:00',3300,'',106756,'gebze tesis','2013-06-28 05:00:00','',9,4,5),(145,'2013-06-03 07:50:33','2013-06-03 07:50:33',107065,'gebze tesis','2013-06-30 15:00:00',3780,'',106917,'gebze tesis','2013-06-30 05:00:00','',9,4,5),(146,'2013-06-04 20:29:43','2013-06-04 20:29:43',64940,'AKYAZI FABRİKA','2013-06-01 13:37:00',2820,'İRS-083301 - UATF.3410573576',64749,'AKYAZI FABRİKA','2013-06-01 05:33:00','',12,5,6),(147,'2013-06-04 20:29:53','2013-06-04 20:29:53',0,'DÜZCE','2013-06-01 13:27:00',6360,'İRS.083358  UATF.3410467699',0,'DÜZCE','2013-06-01 05:27:00','',12,6,7),(148,'2013-06-04 20:30:01','2013-06-04 20:30:01',65118,'AKYAZI FABRİKA','2013-06-03 13:25:00',3040,'İRS.083302  UATF.3410573577',64940,'AKYAZI FABRİKA','2013-06-03 05:25:00','',12,5,6),(149,'2013-06-04 20:30:10','2013-06-04 20:30:10',26592,'DÜZCE','2013-06-03 15:14:00',7260,'İRS.083359   UATF.3410467700',26221,'DÜZCE','2013-06-03 05:14:00','',12,6,7),(150,'2013-06-04 20:30:20','2013-06-04 20:30:20',65333,'AKYAZI FABRİKA','2013-06-04 14:34:00',4040,'İRS.083303   UATF.3410573578',65123,'AKYAZI FABRİKA','2013-06-04 05:23:00','',12,5,6),(151,'2013-06-04 20:30:29','2013-06-04 20:30:29',26879,'DÜZCE','2013-06-04 19:03:00',7300,'İRS.083360  UATF.3410573601',26592,'DÜZCE','2013-06-04 05:29:00','',12,6,7);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truck`
--

DROP TABLE IF EXISTS `truck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `truck` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `active` bit(1) NOT NULL,
  `plate` varchar(255) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `plate` (`plate`),
  KEY `FK6983C5F7DD3F182` (`region_id`),
  CONSTRAINT `FK6983C5F7DD3F182` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truck`
--

LOCK TABLES `truck` WRITE;
/*!40000 ALTER TABLE `truck` DISABLE KEYS */;
INSERT INTO `truck` VALUES (1,'2013-05-13 18:44:42','2013-05-13 18:44:42','','34 YK 1063',1),(2,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 SEC 10',1),(3,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 ZH 318',1),(4,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 U 1884',1),(5,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 HA 6914',2),(6,'2013-05-13 18:44:42','2013-05-13 18:44:42','','34 AT 2912',2),(7,'2013-05-13 18:44:42','2013-05-13 18:44:42','','34 TE 6123',2),(8,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 CEV 47',3),(9,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 HMH 47',3),(10,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 SAU 44',3),(11,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 AC 7870',3),(12,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 VH 793',4),(13,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 HNU 60',4),(14,'2013-05-13 18:44:42','2013-05-13 18:44:42','','09 D 9585',4),(15,'2013-05-13 18:44:42','2013-05-13 18:44:42','','35 HVU 92',4);
/*!40000 ALTER TABLE `truck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uatf`
--

DROP TABLE IF EXISTS `uatf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uatf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `city` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `loadWeightInTonne` int(11) DEFAULT NULL,
  `forwarding_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36A9FEB04524E2` (`forwarding_id`),
  CONSTRAINT `FK36A9FEB04524E2` FOREIGN KEY (`forwarding_id`) REFERENCES `forwarding` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uatf`
--

LOCK TABLES `uatf` WRITE;
/*!40000 ALTER TABLE `uatf` DISABLE KEYS */;
INSERT INTO `uatf` VALUES (9,'2013-05-17 12:50:19','2013-05-17 12:50:19','İSTANBUL','001','SOYSAL','ÜMRANİYE',4740,57),(10,'2013-05-20 06:33:48','2013-05-20 06:33:48','KOCAELİ','02','İNNOVA','ŞEKERPINAR',6680,60),(11,'2013-05-20 08:48:59','2013-05-20 08:48:59','İZMİR','3410538275','ALINTERI OTOMOTİV','URLA',NULL,69),(12,'2013-05-20 08:49:50','2013-05-20 08:49:50','İZMİR','3410052525','ALSANCAK OTO','GAZİEMİR',NULL,69),(13,'2013-05-20 08:50:05','2013-05-20 08:50:05','İZMİR','3410052522','Netlog','URLA',NULL,69),(14,'2013-05-20 10:40:18','2013-05-20 10:40:18','AKYAZI','03','AKYAZI','ADAPAZARI',11300,76),(15,'2013-05-20 10:43:13','2013-05-20 10:43:13','İSTANBUL','04','SOYSAL','PENDİK',6490,77),(16,'2013-05-20 10:46:10','2013-05-20 10:46:10','ADAPAZARI','05','AKYAZI','AKYAZI',8200,78),(17,'2013-05-20 10:48:29','2013-05-20 10:48:29','KOCAELİ','06','İNNOVA','ŞEKERPINAR',1820,79),(18,'2013-05-20 10:50:21','2013-05-20 10:50:21','ADAPAZARI','07','AKYAZI','AKYAZI',7420,80),(19,'2013-05-20 10:52:07','2013-05-20 10:52:07','ADAPAZARI','08','AKYAZI','AKYAZI',9600,81),(20,'2013-05-20 18:06:42','2013-05-20 18:06:42','İSTANBUL','09','AKYAZI','AKYAZI',9120,82),(21,'2013-05-20 18:08:49','2013-05-20 18:08:49','ADAPAZARI','10','AKYAZI','AKYAZI',9960,83),(22,'2013-05-20 18:10:39','2013-05-20 18:10:39','KOCAELİ','11','İNNOVA','ŞEKERPINAR',7540,84),(23,'2013-05-20 18:12:37','2013-05-20 18:12:37','KOCAELİ','12','İNNOVA','ŞEKERPINAR',7860,85),(24,'2013-05-20 18:14:45','2013-05-20 18:14:45','KOCAELİ','13','AKYAZI','AKYAZI',8480,86),(25,'2013-05-20 18:17:30','2013-05-20 18:17:30','İSTANBUL','14','SOYSAL','PENDİK',7320,87),(26,'2013-05-20 18:20:10','2013-05-20 18:20:10','ADAPAZARI','15','AKYAZI','AKYAZI',9200,88),(27,'2013-05-20 18:21:56','2013-05-20 18:21:56','İSTANBUL','16','SOYSAL','PENDİK',8060,89),(28,'2013-05-20 18:23:38','2013-05-20 18:23:38','İSTANBUL','17','SOYSAL','PENDİK',8040,90),(29,'2013-05-21 09:08:46','2013-05-21 09:08:46','ADAPAZARI','18','AKYAZI','AKYAZI',9860,94),(30,'2013-05-22 07:11:48','2013-05-22 07:11:48','İSTANBUL','19','SOYSAL','PENDİK',7840,98),(31,'2013-05-22 07:14:03','2013-05-22 07:14:03','ADAPAZARI','20','AKYAZI','AKYAZI',11300,99),(32,'2013-05-23 06:52:36','2013-05-23 06:52:36','KOCAELİ','19','İNNOVA','ŞEKERPINAR',5480,102),(33,'2013-05-27 11:28:03','2013-05-27 11:28:03','ADAPAZARI','19','AKYAZI','AKYAZI',6400,113),(34,'2013-05-27 12:12:38','2013-05-27 12:12:38','ADAPAZARI','20','AKYAZI','AKYAZI',9220,114),(35,'2013-05-27 12:14:33','2013-05-27 12:14:33','ADAPAZARI','21','AKYAZI','AKYAZI',8140,115),(36,'2013-05-27 12:16:37','2013-05-27 12:16:37','İSTANBUL','22','SOYSAL','PENDİK',8880,116),(37,'2013-05-28 11:21:41','2013-05-28 11:21:41','ADAPAZARI','19','AKYAZI','AKYAZI',10860,122),(38,'2013-05-28 11:24:04','2013-05-28 11:24:04','İSTANBUL','20','SOYSAL ','PENDİK',11260,123);
/*!40000 ALTER TABLE `uatf` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-05  5:00:01

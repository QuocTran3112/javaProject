-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javaweb22_db_v2
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_saleorder_products`
--

DROP TABLE IF EXISTS `tbl_saleorder_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_saleorder_products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `saleorder_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quality` int NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_saleorder_product_idx` (`saleorder_id`),
  KEY `fk_product_saleorder_idx` (`product_id`),
  CONSTRAINT `fk_product_saleorder` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_saleorder_product` FOREIGN KEY (`saleorder_id`) REFERENCES `tbl_saleorder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa phiếu mua hàng';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_saleorder_products`
--

LOCK TABLES `tbl_saleorder_products` WRITE;
/*!40000 ALTER TABLE `tbl_saleorder_products` DISABLE KEYS */;
INSERT INTO `tbl_saleorder_products` VALUES (69,43,178,1,NULL,NULL,NULL,NULL,1),(70,44,178,3,'2023-03-13 22:55:58',NULL,NULL,NULL,1),(71,44,177,1,'2023-03-13 22:55:58',NULL,NULL,NULL,1),(72,45,176,1,'2023-03-13 22:59:17',NULL,NULL,NULL,1),(73,45,177,1,'2023-03-13 22:59:17',NULL,NULL,NULL,1),(74,46,177,2,'2023-03-25 21:32:24',NULL,NULL,NULL,1),(75,46,178,4,'2023-03-25 21:32:24',NULL,NULL,NULL,1),(76,47,178,1,'2023-03-25 21:34:48',NULL,NULL,NULL,1),(77,48,178,1,'2023-03-25 21:36:54',NULL,NULL,NULL,1),(78,49,177,1,'2023-03-25 21:38:50',NULL,NULL,NULL,1),(79,50,177,1,'2023-03-25 21:39:52',NULL,NULL,NULL,1),(80,51,178,1,'2023-03-25 21:41:12',NULL,NULL,NULL,1),(81,52,178,1,'2023-03-25 21:41:40',NULL,NULL,NULL,1),(82,53,177,1,'2023-03-25 21:42:36',NULL,NULL,NULL,1),(83,54,177,1,'2023-03-25 21:44:17',NULL,NULL,NULL,1),(84,55,178,1,'2023-03-25 21:45:17',NULL,NULL,NULL,1),(85,56,178,1,'2023-03-25 21:46:51',NULL,NULL,NULL,1),(86,57,177,2,'2023-03-25 21:48:21',NULL,NULL,NULL,1),(87,58,178,1,'2023-03-25 21:54:45',NULL,NULL,NULL,1),(88,59,178,1,'2023-03-25 21:56:43',NULL,NULL,NULL,1),(89,60,178,2,'2023-03-25 21:57:19',NULL,NULL,NULL,1),(90,61,177,1,'2023-03-25 21:58:16',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `tbl_saleorder_products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-01 23:32:47

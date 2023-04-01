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
-- Table structure for table `tbl_products`
--

DROP TABLE IF EXISTS `tbl_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `price` decimal(13,2) NOT NULL,
  `price_sale` decimal(13,2) DEFAULT NULL,
  `short_description` varchar(3000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `detail_description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `seo` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `is_hot` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_category_products_idx` (`category_id`),
  CONSTRAINT `fk_category_products` FOREIGN KEY (`category_id`) REFERENCES `tbl_category` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa danh mục sản phẩm';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_products`
--

LOCK TABLES `tbl_products` WRITE;
/*!40000 ALTER TABLE `tbl_products` DISABLE KEYS */;
INSERT INTO `tbl_products` VALUES (176,'Áo polo nam',200000.00,135000.00,'Mềm mại dễ chịu','Áo polo là loại áo thun cổ bẻ, về hình thức c áổo giống với cổ sơ mi. Tuy nhiên tay áo chất thun dáng ngắn giữ bắp tay và bo tròn. Cổ áo được đính với cúc áo cài vào nhau. Thường được làm từ chất vải dệt kim, sợi bông hay sợi tổng hợp. Áo polo thích hợp cho cả nam và nữ, người già hoặc trẻ em mặc đều thích hợp. Thường được chọn làm kiểu áo thun gia đình sang trọng.','product/avatar/ao-polo-nam-apm3635-tny-8-yodyvn1677030521041.jpg',1,'2023-02-22 08:48:41',NULL,NULL,NULL,0,'',1),(177,'Áo khoác trắng',260000.00,200000.00,'áo khoác dạ là item kinh điển được phái đẹp ưu ái mỗi khi mùa Đông đến','Xuất hiện liên tiếp trong thiết kế của các nhà mốt lớn cũng như thời trang ứng dụng, áo khoác dạ là item kinh điển được phái đẹp ưu ái mỗi khi mùa Đông đến. Sở hữu công dụng giữ cực tốt cùng với tính ứng dụng cao và đa dạng trong thiết kế, áo khoác dạ dễ dàng đem đến “nguồn nhiệt” cho nàng khi trời trở lạnh, giúp nàng sở hữu vẻ ngoài thời thượng bởi không phải chìm trong “tầng tầng lớp lớp” quần áo.','product/avatar/1655259287fbf346f67752339f6766d239222dc662_thumbnail_900x1677683055123.webp',2,'2023-03-01 22:04:15',NULL,NULL,NULL,1,'',1),(178,'Quần âu nam',150000.00,120000.00,'Quần âu là tên gọi khác của quần tây được gọi để nhớ về nguồn gốc ra đời cũng như phong cách thời trang của chiếc quần này là từ Châu Âu.','Quần áo nam chính hãng Old Sailor. Giao hàng tận tay. Nhiều ưu đãi khi đặt hàng. CSKH chu đáo. Dịch vụ hấp dẫn. Shipping toàn quốc. Đổi trả trong 14 ngày. Model: Sơ Mi Big Size, Quần Jean Big Size, Áo Khoác Big Size, Shorts Big Size, Hoodie Big Size.','product/avatar/quan-au-nam-qam5071-tit041677683366718.jpg',3,'2023-03-01 22:09:27',NULL,NULL,NULL,1,'',1),(179,'Quần âu nữ',240000.00,190000.00,'Mua quần tây nữ giao tận nơi và tham khảo thêm nhiều sản phẩm khác. Miễn phí vận chuyển toàn quốc cho mọi đơn hàng . Đổi trả dễ dàng. Thanh toán bảo mật.','Mua nhiều lựa chọn về Áo quần nữ tại SHEIN. Chất lượng hàng đầu & Giảm giá lớn! Giảm giá lớn cho hợp thời trang Áo quần nữ. Rất nhiều lựa chọn. Mua ngay! Giao hàng nhanh.','product/avatar/qan5204-den-61677683599397.jpg',4,'2023-03-01 22:13:19',NULL,NULL,NULL,0,'',1),(180,'Quần bò nữ',300000.00,240000.00,'Quần bò là một dạng quần đã quá đỗi quen thuộc đối với tất cả mọi người, mọi tầng lớp, mọi tín đồ thời trang trên thế giới.','Quần Jean nữ baggy VM STYLE QJB00003 thiết kế trơn lai tua lưng cao phù hợp đi học đi chơi dạo phố. 169.000 ₫ ; Quần bò ống rộng loang rách đùi TC6 phamdu.','product/avatar/qjn5098-xdm-51677683713569.jpg',4,'2023-03-01 22:15:14',NULL,NULL,NULL,1,'',1),(181,'Quần nữ trắng',400000.00,320000.00,'Quần là một item thời trang cơ bản mà ai cũng phải có, bất kể giới tính hay độ tuổi nào. Để tạo ra các phong cách thời trang đa dạng','Những chiếc quần baggy jean nữ giá rẻ được bán khá đại trà, hãy nâng tầm thời trang, chọn kiểu dáng cao cấp hơn để có set đồ hoàn thiện. Quần jean lưng thun','product/avatar/cn20356855_a1pyleg6k2opezlq_61677683792790.webp',4,'2023-03-01 22:16:33',NULL,NULL,NULL,1,'',1);
/*!40000 ALTER TABLE `tbl_products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-01 23:32:48

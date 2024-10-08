-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: fishstore
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `fish`
--

-- DROP TABLE IF EXISTS `image_file`;
--
-- DROP TABLE IF EXISTS `fish`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;


DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user_details`;

CREATE TABLE IF NOT EXISTS `user_details`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `username` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `email`    varchar(255) DEFAULT NULL,
    `phone`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `role`
(
    `user_id`   int NOT NULL,
    `role_name` varchar(255) DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES `user_details` (id)
);

INSERT INTO `user_details`(username, password, email, phone)
VALUES ('admin', '$2a$12$/UYA.6N.RqfOTETvCVyuleYe1AvDfQxb/fxKs27quN2HW/CFEFive', 'adm@gmail.com', 999);
INSERT INTO `user_details`(username, password, email, phone)
VALUES ('user', '$2a$12$qrkslpAnyzIV9F8w8SlVJOgk6XgSgpBDonIHRCJBsnYMLW0Kplaru', 'user@gmail.com', 111);
INSERT INTO `role`(user_id, role_name)
VALUES ((SELECT ud.id FROM `user_details` ud WHERE username = 'ADMIN'), 'ADMIN');
INSERT INTO `role`(user_id, role_name)
VALUES ((SELECT ID FROM `user_details` WHERE username = 'USER'), 'USER');

CREATE TABLE IF NOT EXISTS `fish`
(
    `id`         int    NOT NULL AUTO_INCREMENT,
    `catch_date` datetime(6)  DEFAULT NULL,
    `name`       varchar(255) DEFAULT NULL,
    `price`      double NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 12
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `image_file`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `fish_id`   int NOT NULL,
    `file_name` varchar(255) DEFAULT NULL,
    FOREIGN KEY (`fish_id`) REFERENCES `fish` (`id`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 12
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2024-08-16 13:44:19

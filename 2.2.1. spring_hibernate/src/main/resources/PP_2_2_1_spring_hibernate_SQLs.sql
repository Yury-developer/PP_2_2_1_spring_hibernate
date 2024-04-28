DROP DATABASE IF EXISTS`spring_hiber`;
CREATE SCHEMA IF NOT EXISTS `spring_hiber` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

SHOW DATABASES;
USE `spring_hiber`;
SHOW TABLES FROM `spring_hiber`;
SELECT * FROM spring_hiber.users;
SELECT * FROM spring_hiber.cars;

TRUNCATE TABLE users;
TRUNCATE TABLE cars;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cars;
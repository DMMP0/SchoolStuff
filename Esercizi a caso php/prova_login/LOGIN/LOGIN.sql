SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE=`TRADITIONAL,ALLOW_INVALID_DATES`;



-- -----------------------------------------------------
-- Schema login
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `LOGIN` DEFAULT CHARACTER SET utf8 ;
USE `LOGIN` ;
--------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `LOGIN`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  `username` VARCHAR(30) NOT NULL, 
  `email` VARCHAR(50) NOT NULL, 
  `password` CHAR(128) NOT NULL, 
  `salt` CHAR(128) NOT NULL
) ENGINE = InnoDB;

--------------------------------------------------------------

CREATE TABLE `LOGIN`.`login_attempts` (
  `user_id` INT(11) NOT NULL,
  `time` VARCHAR(30) NOT NULL 
) ENGINE=InnoDB;

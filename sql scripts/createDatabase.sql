SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP DATABASE `onlineBookStore`;
CREATE DATABASE IF NOT EXISTS `onlineBookStore`;
USE `onlineBookStore`;

-- -----------------------------------------------------
-- Table `mydb`.`table1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlineBookStore`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`table2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlineBookStore`.`Book` (
  `book_id` INT NOT NULL COMMENT '',
  `book_name` VARCHAR(45) NOT NULL COMMENT '',
  `book_author` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`book_id`)  COMMENT '')
ENGINE = InnoDB;

INSERT INTO `onlineBookStore`.`User`(name, password) 
VALUES ('admin', 'admin');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

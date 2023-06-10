-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema asd
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema onoffit
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema onoffit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `onoffit` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `onoffit` ;

-- -----------------------------------------------------
-- Table `onoffit`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`user` (
  `user_id` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `user_nickname` VARCHAR(45) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`on_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`on_group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `group_title` VARCHAR(45) NOT NULL,
  `group_message` VARCHAR(45) NOT NULL,
  `group_description` VARCHAR(45) NOT NULL,
  `group_cnt` INT NOT NULL DEFAULT 1,
  `group_leader_user_id` VARCHAR(45) NOT NULL,
  `group_image` VARCHAR(45) NULL,
  `group_orgimage` VARCHAR(45) NULL,
  PRIMARY KEY (`group_id`),
  INDEX `group_leader_user_id_fk_idx` (`group_leader_user_id` ASC) VISIBLE,
  CONSTRAINT `group_leader_user_id_fk`
    FOREIGN KEY (`group_leader_user_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`off_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`off_group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `group_title` VARCHAR(45) NOT NULL,
  `group_message` VARCHAR(45) NOT NULL,
  `group_description` VARCHAR(45) NOT NULL,
  `group_cnt` INT NOT NULL DEFAULT 1,
  `group_leader_user_id` VARCHAR(45) NOT NULL,
  `group_address` VARCHAR(45) NOT NULL,
  `group_image` VARCHAR(45) NULL,
  `group_orgimage` VARCHAR(45) NULL,
  PRIMARY KEY (`group_id`),
  INDEX `group_leader_user_id_fk_idx` (`group_leader_user_id` ASC) VISIBLE,
  CONSTRAINT `group_leader_user_id_fk2`
    FOREIGN KEY (`group_leader_user_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`on_user_membership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`on_user_membership` (
  `group_id` INT NOT NULL,
  `membership_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`membership_id`),
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `group_id_fk_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `group_id_fk`
    FOREIGN KEY (`group_id`)
    REFERENCES `onoffit`.`on_group` (`group_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`off_user_membership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`off_user_membership` (
  `group_id` INT NOT NULL,
  `membership_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`membership_id`),
  INDEX `group_id_fk_idx` (`group_id` ASC) VISIBLE,
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `group_id_fk2`
    FOREIGN KEY (`group_id`)
    REFERENCES `onoffit`.`off_group` (`group_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_id_fk2`
    FOREIGN KEY (`user_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`video` (
  `video_id` VARCHAR(225) NOT NULL,
  `video_title` VARCHAR(225) NOT NULL,
  `video_url` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`video_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`video_membership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`video_membership` (
  `video_id` VARCHAR(225) NOT NULL,
  `group_id` INT NOT NULL,
  `membership_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`membership_id`),
  INDEX `video_id_fk_idx` (`video_id` ASC) VISIBLE,
  INDEX `group_id_fk_idx` (`group_id` ASC) VISIBLE,
  CONSTRAINT `video_id_fk3`
    FOREIGN KEY (`video_id`)
    REFERENCES `onoffit`.`video` (`video_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `group_id_fk3`
    FOREIGN KEY (`group_id`)
    REFERENCES `onoffit`.`on_group` (`group_id`)
   ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`board` (
  `board_title` VARCHAR(45) NOT NULL,
  `board_idx` INT NOT NULL AUTO_INCREMENT,
  `board_content` VARCHAR(45) NOT NULL,
  `writer_id` VARCHAR(45) NOT NULL,
  `board_created` DATETIME NOT NULL,
  `board_updated` DATETIME NULL,
  `board_view` INT NOT NULL DEFAULT 0,
  `board_like` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`board_idx`),
  INDEX `id_fk_idx` (`writer_id` ASC) VISIBLE,
  CONSTRAINT `id_fk`
    FOREIGN KEY (`writer_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`follow` (
  `follow_id` INT NOT NULL AUTO_INCREMENT,
  `follower_id` VARCHAR(45) NOT NULL,
  `following_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`follow_id`),
  INDEX `follower_id_fk_idx` (`follower_id` ASC) VISIBLE,
  INDEX `following_id_fk_idx` (`following_id` ASC) VISIBLE,
  CONSTRAINT `follower_id_fk`
    FOREIGN KEY (`follower_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `following_id_fk`
    FOREIGN KEY (`following_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`board_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`board_comment` (
  `comment_idx` INT NOT NULL AUTO_INCREMENT,
  `comment_content` VARCHAR(45) NOT NULL,
  `writer_id` VARCHAR(45) NOT NULL,
  `board_idx` INT NOT NULL,
  PRIMARY KEY (`comment_idx`),
  INDEX `writer_id_fk_idx` (`writer_id` ASC) VISIBLE,
  INDEX `board_idx_fk_idx` (`board_idx` ASC) VISIBLE,
  CONSTRAINT `writer_id_fk`
    FOREIGN KEY (`writer_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `board_idx_fk`
    FOREIGN KEY (`board_idx`)
    REFERENCES `onoffit`.`board` (`board_idx`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `onoffit`.`board_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onoffit`.`board_like` (
  `like_idx` INT NOT NULL AUTO_INCREMENT,
  `writer_id` VARCHAR(45) NOT NULL,
  `board_idx` INT NOT NULL,
  PRIMARY KEY (`like_idx`),
  INDEX `id_fk_idx` (`writer_id` ASC) VISIBLE,
  INDEX `board_idx_fk_idx` (`board_idx` ASC) VISIBLE,
  CONSTRAINT `id_fk3`
    FOREIGN KEY (`writer_id`)
    REFERENCES `onoffit`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `board_idx_fk3`
    FOREIGN KEY (`board_idx`)
    REFERENCES `onoffit`.`board` (`board_idx`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

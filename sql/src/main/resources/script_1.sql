

CREATE TABLE `tournamentdb`.`team` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `capitan` VARCHAR(45) NOT NULL,
  `coach` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `tournamentdb`.`match` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numberofrounds` INT NOT NULL,
  `score` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

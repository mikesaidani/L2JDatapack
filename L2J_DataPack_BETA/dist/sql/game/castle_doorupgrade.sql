CREATE TABLE IF NOT EXISTS `castle_doorupgrade` (
	`doorId` INT(11) NOT NULL DEFAULT 0,
	`ratio` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0,
	`castleId` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY (`doorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
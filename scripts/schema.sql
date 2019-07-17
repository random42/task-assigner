CREATE TABLE `users`
(
 `id`   int NOT NULL AUTO_INCREMENT ,
 `name` varchar(45) NOT NULL ,
 `role` enum("cook","chef") NOT NULL ,

PRIMARY KEY (`id`)
);

CREATE TABLE `recipes`
(
 `id`   int NOT NULL AUTO_INCREMENT,
 `name` varchar(45) NOT NULL ,

PRIMARY KEY (`id`)
);


CREATE TABLE `workshifts`
(
 `id`   int NOT NULL AUTO_INCREMENT,
 `from_date` datetime NOT NULL ,
 `to_date`   datetime NOT NULL ,

PRIMARY KEY (`id`)
);

CREATE TABLE `workshift_cooks`
(
 `cook`      int NOT NULL,
 `workshift` int NOT NULL ,

PRIMARY KEY (`cook`, `workshift`),
KEY `fkIdx_62` (`cook`),
CONSTRAINT `FK_62` FOREIGN KEY `fkIdx_62` (`cook`) REFERENCES `users` (`id`),
KEY `fkIdx_65` (`workshift`),
CONSTRAINT `FK_65` FOREIGN KEY `fkIdx_65` (`workshift`) REFERENCES `workshifts` (`id`)
);

CREATE TABLE `menus`
(
 `id`    int NOT NULL AUTO_INCREMENT,
 `title` varchar(45) NOT NULL ,

PRIMARY KEY (`id`)
);



CREATE TABLE `menu_recipes`
(
 `recipe` int NOT NULL ,
 `menu`   int NOT NULL ,

PRIMARY KEY (`recipe`, `menu`),
KEY `fkIdx_90` (`recipe`),
CONSTRAINT `FK_90` FOREIGN KEY `fkIdx_90` (`recipe`) REFERENCES `recipes` (`id`),
KEY `fkIdx_93` (`menu`),
CONSTRAINT `FK_93` FOREIGN KEY `fkIdx_93` (`menu`) REFERENCES `menus` (`id`)
);

CREATE TABLE `events`
(
 `id`   int NOT NULL AUTO_INCREMENT,
 `name` varchar(50) NULL ,
 `menu` int NOT NULL ,
 `chef` int NOT NULL ,

PRIMARY KEY (`id`),
KEY `fkIdx_69` (`menu`),
CONSTRAINT `FK_69` FOREIGN KEY `fkIdx_69` (`menu`) REFERENCES `menus` (`id`),
KEY `fkIdx_72` (`chef`),
CONSTRAINT `FK_72` FOREIGN KEY `fkIdx_72` (`chef`) REFERENCES `users` (`id`)
);

CREATE TABLE `tasks`
(
 `id`          int NOT NULL AUTO_INCREMENT,
 `description` varchar(200) NOT NULL ,
 `time`        int NOT NULL ,
 `done`        tinyint NOT NULL DEFAULT 0,
 `toPrepare`   tinyint NOT NULL DEFAULT 0,
 `recipe`      int,
 `event`       int NOT NULL ,

PRIMARY KEY (`id`),
KEY `fkIdx_103` (`recipe`),
CONSTRAINT `FK_103` FOREIGN KEY `fkIdx_103` (`recipe`) REFERENCES `recipes` (`id`),
KEY `fkIdx_109` (`event`),
CONSTRAINT `FK_109` FOREIGN KEY `fkIdx_109` (`event`) REFERENCES `events` (`id`)
);


CREATE TABLE `assignments`
(
 `id`          int NOT NULL AUTO_INCREMENT,
 `done`        tinyint NOT NULL DEFAULT 0,
 `description` varchar(200) NOT NULL ,
 `task`        int NOT NULL ,
 `workshift`   int NOT NULL ,

PRIMARY KEY (`id`),
KEY `fkIdx_112` (`task`),
CONSTRAINT `FK_112` FOREIGN KEY `fkIdx_112` (`task`) REFERENCES `tasks` (`id`),
KEY `fkIdx_115` (`workshift`),
CONSTRAINT `FK_115` FOREIGN KEY `fkIdx_115` (`workshift`) REFERENCES `workshifts` (`id`)
);



CREATE TABLE `assignment_cooks`
(
 `cook`       int NOT NULL ,
 `assignment` int NOT NULL ,

PRIMARY KEY (`cook`, `assignment`),
KEY `fkIdx_128` (`cook`),
CONSTRAINT `FK_128` FOREIGN KEY `fkIdx_128` (`cook`) REFERENCES `users` (`id`),
KEY `fkIdx_132` (`assignment`),
CONSTRAINT `FK_132` FOREIGN KEY `fkIdx_132` (`assignment`) REFERENCES `assignments` (`id`)
);

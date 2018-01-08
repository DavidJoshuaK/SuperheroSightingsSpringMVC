DROP DATABASE IF EXISTS SuperSightings;

CREATE DATABASE SuperSightings;

USE SuperSightings;

CREATE TABLE IF NOT EXISTS `Super` (
	`SuperId` int(11) not null auto_increment, 
	`Name` varchar(60) not null,
	`Description` text(100) not null,
    PRIMARY KEY(`SuperId`)
);

CREATE TABLE IF NOT EXISTS `Organization` (
	`OrganizationId` int(11) not null auto_increment,
    `Name` varchar(60) not null,
    `Description` text(100) not null, 
    `Type` varchar(7) not null,
    `Address` varchar(60) not null,
    `City` varchar(60) not null,
	`State` varchar(40) not null,
    `Zipcode` varchar(11) not null,
    `Phone` varchar(20) not null,    
    PRIMARY KEY(`OrganizationId`)
);

CREATE TABLE IF NOT EXISTS `SuperOrganization`(
	`SuperId`int(11) not null,
    `OrganizationId` int(11) not null,
    PRIMARY KEY (`SuperId`, `OrganizationId`)
);

ALTER TABLE `SuperOrganization`
	ADD CONSTRAINT `fk_SuperOrganization_Super`FOREIGN KEY (`SuperId`) REFERENCES `Super`
		(`SuperId`) ON DELETE NO ACTION;
ALTER TABLE `SuperOrganization`
	ADD CONSTRAINT `fk_SuperOrganization_Organization` FOREIGN KEY(`OrganizationId`) REFERENCES `Organization`
		(`OrganizationId`);

CREATE TABLE IF NOT EXISTS `Location` (
	`LocationId` int(11) not null auto_increment,
    `Name` varchar(60) not null,
    `Description` text(100) not null,
    `Address` varchar(60) not null,
    `City` varchar(60) not null,
	`State` varchar(40) not null,
    `Zipcode` varchar(11) not null,
    `Longitude` decimal(9,6) not null,
    `Latitude` decimal(9,6) not null,
    PRIMARY KEY(`LocationId`)
);

CREATE TABLE IF NOT EXISTS `Power` (
	`PowerId` int(11) not null auto_increment,
    `Description` text(100) not null,
    PRIMARY KEY(`PowerId`)
);

CREATE TABLE IF NOT EXISTS `SuperPower` (
	`SuperId` int(11) not null,
	`PowerId` int(11) not null,
    PRIMARY KEY(`SuperId`, `PowerId`)
);

ALTER TABLE `SuperPower`
	ADD CONSTRAINT `fk_SuperPower_Super` FOREIGN KEY (`SuperId`) REFERENCES `Super`
		(`SuperId`) ON DELETE NO ACTION;
ALTER TABLE `SuperPower`
	ADD CONSTRAINT `fk_SuperPower_Power` FOREIGN KEY (`PowerId`) REFERENCES `Power`
    	(`PowerId`) ON DELETE NO ACTION;
        
CREATE TABLE IF NOT EXISTS `Sighting` (
	`SightingId` int(11) not null auto_increment,
	`LocationId` int(11) not null,
    `Date` date not null,
    PRIMARY KEY(`SightingId`)
);
        
ALTER TABLE `Sighting`
	ADD CONSTRAINT `fk_Sighting_Location` FOREIGN KEY (`LocationId`) REFERENCES `Location`
		(`LocationId`) ON DELETE NO ACTION;
        
CREATE TABLE IF NOT EXISTS `SuperSighting`(
	`SuperId` int(11) not null,
    `SightingId` int(11) not null,
    PRIMARY KEY(`SuperId`, `SightingId`)
);

ALTER TABLE `SuperSighting`
	ADD CONSTRAINT `fk_SuperSighting_Super` FOREIGN KEY (`SuperId`) REFERENCES `Super`
		(`SuperId`) ON DELETE NO ACTION;
ALTER TABLE `SuperSighting`
	ADD CONSTRAINT `fk_SuperSighting_Sighting` FOREIGN KEY (`SightingId`) REFERENCES `Sighting`
		(`SightingId`) ON DELETE NO ACTION;






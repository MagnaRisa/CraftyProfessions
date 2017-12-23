-- ----------------------------------------------------------------------------------------------------------------------
-- This file should NOT be changed. Doing so will cause unforeseeable errors within the program. You have been Warned!
-- ----------------------------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS SpecializedIn;
DROP TABLE IF EXISTS UserProfHasAugments;
DROP TABLE IF EXISTS UserProfLevel;
DROP TABLE IF EXISTS Careers;
DROP TABLE IF EXISTS Bonus;
DROP TABLE IF EXISTS BonusTypes;
DROP TABLE IF EXISTS Specializations;
DROP TABLE IF EXISTS Augments;
DROP TABLE IF EXISTS `Levels`;
DROP TABLE IF EXISTS Professions;
DROP TABLE IF EXISTS Users;

-- Create the User Table
CREATE TABLE Users 
(
	UserID INT NOT NULL AUTO_INCREMENT,
	UUID VARCHAR (50) NOT NULL,
	Username VARCHAR (20) NOT NULL,
	TotalExp DOUBLE UNSIGNED DEFAULT 0,
	CurrentExp DOUBLE UNSIGNED DEFAULT NULL,
	TotalLevel INT UNSIGNED DEFAULT 0,
	DateOfCreation DATE,
	
	CONSTRAINT Users_UserID_PK PRIMARY KEY (UserID),
	CONSTRAINT Users_UUID_U UNIQUE (UUID),
	
	INDEX Users_UUID_IDX (UUID)
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create the Professions table
CREATE TABLE Professions
(
	ProfessionID INT NOT NULL AUTO_INCREMENT,
	ProfessionName VARCHAR (25) NOT NULL,
	Description TEXT,
	WageTableRef VARCHAR (30) NOT NULL,
	
	CONSTRAINT Professions_ProfessionID_PK PRIMARY KEY (ProfessionID)
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create the Levels table
CREATE TABLE Levels
(
	LevelID INT NOT NULL AUTO_INCREMENT,
	`Level` INT DEFAULT 0,
	ExpAmount DOUBLE UNSIGNED DEFAULT 0,
	
	CONSTRAINT Levels_LevelID_PK PRIMARY KEY (LevelID)
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create the BonusTypes table
CREATE TABLE BonusTypes
(
	BonusTypeID INT NOT NULL AUTO_INCREMENT,
	BonusType VARCHAR (20),
	
	CONSTRAINT BonusTypes_BonusTypeID_PK PRIMARY KEY (BonusTypeID)
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create the Augments Table
CREATE TABLE Augments
(
	AugmentID INT NOT NULL AUTO_INCREMENT,
	AugmentName VARCHAR (50) NOT NULL,
	AugShorthand VARCHAR (25) DEFAULT NULL,
	MinLevel INT NOT NULL,
	Description TEXT DEFAULT NULL,
	
	CONSTRAINT Augments_AugmentID_PK PRIMARY KEY (AugmentID)
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create the Specializations Augment subclass table
CREATE TABLE Specializations
(
	SpecID INT NOT NULL AUTO_INCREMENT,
	SpecName VARCHAR (25) NOT NULL,
	
	CONSTRAINT Specializations_SpecID_PK PRIMARY KEY (SpecID),
	CONSTRAINT Specializations_SpecID_FK FOREIGN KEY (SpecID)
		REFERENCES Augments (AugmentID) ON DELETE CASCADE
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create the Bonus Augments subclass table
CREATE TABLE Bonus
(
	BonusID INT NOT NULL AUTO_INCREMENT,
	BTypeID INT NOT NULL,
	BonusAmount DOUBLE DEFAULT 0,
	
	CONSTRAINT Bonus_BonusID_PK PRIMARY KEY (BonusID),
	CONSTRAINT Bonus_BTypeID_FK FOREIGN KEY (BTypeID)
		REFERENCES BonusTypes (BonusTypeID) ON DELETE CASCADE
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create the Professions and Users Relationship of Careers
CREATE TABLE Careers
(
	UserID INT NOT NULL,
	ProfessionID INT NOT NULL,
	
	CONSTRAINT Careers_UserID_ProfessionID_PK PRIMARY KEY (UserID, ProfessionID),
	CONSTRAINT Careers_UserID_FK FOREIGN KEY (UserID)
		REFERENCES Users (UserID) ON DELETE CASCADE,
	CONSTRAINT Careers_ProfessionID_FK FOREIGN KEY (ProfessionID)
		REFERENCES Professions (ProfessionID) ON DELETE CASCADE,
		
	INDEX Careers_ProfessionID_IDX (ProfessionID)
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Creates the Careers and Levels Relationship of UserProfLevel
CREATE TABLE UserProfLevel
(
	UserID INT NOT NULL,
	ProfessionID INT NOT NULL,
	LevelID INT NOT NULL,
	PrestigeLevel INT DEFAULT 0,
	
	CONSTRAINT UserProfLevel_UserID_ProessionID_LevelID 
		PRIMARY KEY (UserID, ProfessionID, LevelID),
	CONSTRAINT UserProfLevel_UserID_ProfessionID_FK FOREIGN KEY (UserID, ProfessionID)
		REFERENCES Careers (UserID, ProfessionID) ON DELETE CASCADE,
	CONSTRAINT UserProfLevel_LevelID FOREIGN KEY (LevelID)
		REFERENCES `Levels` (LevelID) ON DELETE CASCADE,
		
	INDEX UserProfLevel_LevelID_IDX (LevelID)
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create Careers and Augments Relationship of UserProfHasAugments
CREATE TABLE UserProfHasAugments
(
	UserID INT NOT NULL,
	ProfessionID INT NOT NULL,
	AugmentID INT NOT NULL,
	Slots INT DEFAULT 0,
	
	CONSTRAINT UserProfHasAugments_UserID_ProfessionID_AugmentID_PK 
		PRIMARY KEY (UserID, ProfessionID, AugmentID),
	CONSTRAINT UserProfHasAugments_UserID_ProfessionID_FK FOREIGN KEY (UserID, ProfessionID)
		REFERENCES Careers (UserID, ProfessionID) ON DELETE CASCADE,
	CONSTRAINT UserProfHasAugments_AugmentID_FK FOREIGN KEY (AugmentID)
		REFERENCES Augments (AugmentID) ON DELETE CASCADE,
		
	INDEX UserProfHasAugments_IDX (AugmentID)
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

-- Create Specializations and Professions Relationship of SpecializedIn
CREATE TABLE SpecializedIn
(
	ProfessionID INT NOT NULL,
	SpecID INT NOT NULL,
	
	CONSTRAINT SpecializedIn_ProfessionID_SpecID_PK PRIMARY KEY (ProfessionID, SpecID),
	CONSTRAINT SpecializedIn_ProfessionID_FK FOREIGN KEY (ProfessionID)
		REFERENCES Professions (ProfessionID) ON DELETE CASCADE,
	CONSTRAINT SpecializedIn_SpecID FOREIGN KEY (SpecID)
		REFERENCES Specializations (SpecID) ON DELETE CASCADE
) Engine=InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

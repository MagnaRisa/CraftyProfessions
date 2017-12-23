-- ----------------------------------------------------------------------------------------------------------------------
-- This file should NOT be changed. Doing so will cause unforeseeable errors within the program. You have been Warned!
-- ----------------------------------------------------------------------------------------------------------------------

-- These are the insertions into the Professions Table.
INSERT INTO Professions (ProfessionName, WageTableRef, Description) VALUES ("The Miner", "miner_wage.json", 
"Diggy Diggy Hole, Diggy Diggy Hole, I hate myself for remembering this song! *Dated Sourdough*");

INSERT INTO Professions (ProfessionName, WageTableRef, Description) VALUES ("The Angler", "angler_wage.json",
"This Profession is all focused around fishing and catching the various treasures of the sea.");

INSERT INTO Professions (ProfessionName, WageTableRef, Description) VALUES ("The Lumberjack", "lumberjack_wage.json",
"This Job will get you money based on chopping down trees for that oh’ so precious Lumber.");

INSERT INTO Professions (ProfessionName, WageTableRef, Description) VALUES ("The Knight", "knight_wage.json",
"Become a Knight and take on all of the things that go bump in the night and get paid while doing it!");

INSERT INTO Professions (ProfessionName, WageTableRef, Description) VALUES ("The Architect", "architect_wage.json",
"Your your innate building skillz to create masterpieces and get paid while creating those structurally sound builds.");

INSERT INTO Professions (ProfessionName, WageTableRef, Description) VALUES ("The Farmer", "farmer_wage.json",
"This profession will allow you to to obtain money from doing any type of farming within the game. Specializations of the Farmer Profession are The Rancher, and Crop Cultivation");

-- These are the insertions into the Augments Table.
INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Minimal Experience Bonus", "MinExpBonus", 1, 
"This augment allows this profession to gain more experience per action performed. For example if you have the miner profession, you will obtain slightly more exp per block mined.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Standard Experience Bonus", "StdExpBonus", 10, 
"This augment allows this profession to gain a moderate amount of experience gain. This will give more than the Minimal Exp Bonus.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Improved Experience Bonus", "ImpExpBonus", 25,
"This augment allows this profession to gain an improved amount of exp from the moderate level of experience.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Maximum Experience Bonus", "MaxExpBonus", 45,
"Description goes here.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Minimum Income Bonus", "MinIncomeBonus", 1,
"Description goes here.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Standared Income Bonus", "StdIncomeBonus", 10,
"Description goes here.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Improved Income Bonus", "ImpIncomeBonus", 25,
"Description goes here.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Maximum Income Bonus", "MaxIncomeBonus", 45,
"Description goes here.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Minimum [Token] Bonus", "MinTokBonus", 1, 
"Description goes here.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Standard [Token] Bonus", "StdTokBonus", 10, 
"Description goes here");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Improved [Token] Bonus", "ImpTokBonus", 25, 
"Description goes here.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Maximum [Token] Bonus", "MaxTokBonus", 45,
"Description goes here.");

INSERT INTO Augments (AugmentName, AugShorthand, MinLevel, Description) VALUES ("Specialize Profession", "SpecProfession", 25,
"Description goes here.");

-- These are the insertions into the BonusType Table.
INSERT INTO BonusTypes (BonusType) VALUES ("Experience");
INSERT INTO BonusTypes (BonusType) VALUES ("Income");
INSERT INTO BonusTypes (BonusType) VALUES ("[Token]");

-- These ARE the insertions INTO the Levels TABLE
INSERT INTO Levels (`Level`, ExpAmount) VALUES (1);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (2);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (3);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (4);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (5);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (6);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (7);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (8);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (9);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (10);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (11);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (12);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (13);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (14);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (15);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (16);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (17);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (18);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (19);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (20);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (21);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (22);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (23);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (24);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (25);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (26);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (27);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (28);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (29);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (30);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (31);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (32);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (33);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (34);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (35);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (36);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (37);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (38);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (39);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (40);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (41);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (42);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (43);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (44);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (45);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (46);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (47);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (48);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (49);
INSERT INTO Levels (`Level`, ExpAmount) VALUES (50);
CREATE DATABASE PAOdatabase;

USE PAOdatabase;

CREATE TABLE Soldier (
    UID INT PRIMARY KEY,
    username VARCHAR(50),
    discordTag VARCHAR(50),
    steamURL VARCHAR(200),
    rankType VARCHAR(30),
    s_rank INT,
    specialization VARCHAR(20),
    skill INT
);

CREATE TABLE Officer (
	UID INT PRIMARY KEY,
	infantryCommandSkill INT,
    tankCommandSkill INT,
    artilleryCommandSkill INT,
    navyCommandSkill INT,
    logisticsCommandSkill INT
);

ALTER TABLE Officer
ADD FOREIGN KEY (UID) REFERENCES Soldier(UID);

CREATE TABLE Vehicle (
	serialNumber INT PRIMARY KEY,
    type Varchar(50),
    drivetrain Varchar(50),
    seats INT,
    fuelCapacity INT,
    
    transportCapacity INT NULL,
    cargoType Varchar(50) NULL,
    
    weaponry Varchar(50) NULL,
    ammunitionType Varchar(50) NULL,
    ammunitionCapacity INT NULL,
    armor Varchar(50) NULL,
    crew INT NULL
);

CREATE TABLE Equipment (
	serialNumber INT PRIMARY KEY,
    name Varchar(50),
    type Varchar(50),
    inventorySpace INT,
    slot INT,
    
    ammoType Varchar(50) NULL,
    magazineSize INT NULL,
    effectiveRange INT NULL
);

CREATE TABLE Base (
	baseID INT PRIMARY KEY,
    hex Varchar(50),
    region Varchar(50),
    base_type Varchar(50),
    
    builderID INT NULL,
    upgrades Varchar(200),
    garrisonSize INT NULL,
    
    level INT NULL,
    victoryPoint bool NULL,
    garrisonHouses INT NULL,
    mortarHouses INT NULL,
    coastalGun bool NULL
);
ALTER TABLE Base
ADD base_type VARCHAR(50);

CREATE TABLE Operation (
	name Varchar(50) PRIMARY KEY,
    op_type Varchar(50),
    branches Varchar(200),
    startTime datetime,
    endTime datetime,
    objective Varchar(100),
    plan Varchar(200),
    commander_uid INT
);

CREATE TABLE Medal (
	name Varchar(50) PRIMARY KEY,
    milestone Varchar(50),
    medal_type Varchar(50)
);

CREATE TABLE OperationAssignment (
  soldier_uid INT,
  operation_name Varchar(50),
  PRIMARY KEY (soldier_uid, operation_name),
  FOREIGN KEY (soldier_uid) REFERENCES Soldier(UID),
  FOREIGN KEY (operation_name) REFERENCES Operation(name)
);

CREATE TABLE BaseAssignment (
	soldier_uid INT,
    baseID INT,
    PRIMARY KEY (soldier_uid, baseID),
    FOREIGN KEY (soldier_uid) REFERENCES Soldier(UID),
    FOREIGN KEY (baseID) REFERENCES Base(baseID)
);

CREATE TABLE BaseStorage (
	baseID INT,
    equipmentID INT,
    PRIMARY KEY (baseID, equipmentID),
    FOREIGN KEY (baseID) REFERENCES Base(baseID),
    FOREIGN KEY (equipmentID) REFERENCES Equipment(serialNumber)
);

CREATE TABLE VehicleStorage (
	vehicleID INT,
    equipmentID INT,
    PRIMARY KEY (vehicleID, equipmentID),
    FOREIGN KEY (vehicleID) REFERENCES Vehicle(serialNumber),
    FOREIGN KEY (equipmentID) REFERENCES Equipment(serialNumber)
);

CREATE TABLE BaseGarage (
	baseID INT,
    vehicleID INT,
    PRIMARY KEY (baseID, vehicleID),
    FOREIGN KEY (baseID) REFERENCES Base(baseID),
    FOREIGN KEY (vehicleID) REFERENCES Vehicle(serialNumber)
);

CREATE TABLE OperationVehicle (
	operationName Varchar(50),
    vehicleID INT,
    PRIMARY KEY (operationName, vehicleID),
    FOREIGN KEY (operationName) REFERENCES Operation(name),
    FOREIGN KEY (vehicleID) REFERENCES Vehicle(serialNumber)
);

SELECT * from Operation;

DELETE from OperationAssignment;
DELETE from BaseAssignment;
DELETE from OperationVehicle;
DELETE from Vehicle;
DELETE from Officer;
DELETE FROM Soldier;
DELETE from Equipment;
DELETE from Base;
DELETE from Medal;
DELETE from Operation;


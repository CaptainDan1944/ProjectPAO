import Awards.Medal;
import Awards.MedalService;
import Objectives.*;
import Materiel.*;
import Operations.*;
import Player.*;
import Assignment.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class MainService {
    Connection connection;
    SoldierService SoldierService = Player.SoldierService.getInstance();
    VehicleService VehicleService = Materiel.VehicleService.getInstance();
    MedalService MedalService = Awards.MedalService.getInstance();
    BaseService BaseService = Objectives.BaseService.getInstance();
    EquipmentService EquipmentService = Materiel.EquipmentService.getInstance();
    OperationService OperationService = Operations.OperationService.getInstance();
    SoldierBase SoldierBase = Assignment.SoldierBase.getInstance();
    SoldierOperation SoldierOperation = Assignment.SoldierOperation.getInstance();
    VehicleOperation VehicleOperation = Assignment.VehicleOperation.getInstance();


    static List<String> Actions = Arrays.asList("create_player", "create_equipment", "create_vehicle", "create_base", "create_operation", "create_medal",
            "assign_soldier", "assign_materiel", "load_equipment", "display_profile", "upgrade_base", "display_inventory",
            "assign_commander", "help", "stop");
    static List<String> Descriptions = Arrays.asList("Add a soldier to the regiment.", "Add a piece of equipment to the inventory of a base.", "Add a new vehicle to the inventory of a base",
            "Build a a new base.", "Plan a new operation.", "Award a medal to a Soldier.", "Assign a Soldier to a base or to an operation.",
            "Assign materiel to an operation.", "Load equipment inside a vehicle.", "Display a Soldier's profile.", "Add an upgrade to an existing base.",
            "Display the inventory of a base / vehicle", "Find and assign the most suitable Officer to lead an operation.", "See all actions", "Stop the app");

    static List<String> EnlistedRanks = Arrays.asList("Private", "Corporal", "Sergeant", "Staff Sergeant", "Master Sergeant", "First Sergeant", "Sergeant Major");
    static List<String> OfficerRanks = Arrays.asList("2nd Lieutenant", "Lieutenant", "Captain", "Major", "Lieutenant-Colonel", "Colonel", "Brigadier");

    private ArrayList<Soldier> soldiers = new ArrayList<>();
    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Operation> operations = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Medal> medals = new ArrayList<>();
    HashMap<Integer, Integer> soldierAssignment = new HashMap<>();
    HashMap<String, ArrayList<Equipment>> operationMateriel = new HashMap<String, ArrayList<Equipment>>();


    // getters

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public ArrayList<Base> getBases() {
        return bases;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<Medal> getMedals() {
        return medals;
    }


    public MainService() {
        this.soldiers = new ArrayList<>();
        this.bases = new ArrayList<>();
        this.operations = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.medals = new ArrayList<>();
    }

    public void make_connection() {

        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/paodatabase";
            String username = "root";
            String password = "Salam/ul2003";

            connection = DriverManager.getConnection(jdbcUrl, username, password);

            SoldierService.obtainConnection(connection);
            VehicleService.obtainConnection(connection);
            MedalService.obtainConnection(connection);
            EquipmentService.obtainConnection(connection);
            BaseService.obtainConnection(connection);
            OperationService.obtainConnection(connection);
            SoldierBase.obtainConnection(connection);
            SoldierOperation.obtainConnection(connection);
            VehicleOperation.obtainConnection(connection);


        }catch (SQLException str) {
            System.out.println (str.toString());
        }

    }

    public void end_connection() {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException str) {
            System.out.println (str.toString());
        }
    }

    public void create_player (Scanner input) {
        int rank, skill;
        String username, discordTag, steamURL, specialization, ranktype;
        int infantryCommandSkill, tankCommandSkill, artilleryCommandSkill, navyCommandSkill, logisticsCommandSkill;

        Soldier NewPlayer = null;

        System.out.println("Enter the soldier's username:");
        String option = input.nextLine();
        username = option;

        System.out.println("Enter the soldier's discordTag (name#1234):");
        option = input.nextLine();
        discordTag = option;

        System.out.println("Enter the soldier's steamURL:");
        option = input.nextLine();
        steamURL = option;

        System.out.println("Enter the soldier's specialization:");
        option = input.nextLine();
        specialization = option;

        System.out.println("Enter the soldier's skill ( 1 -> 10 ):");
        option = input.nextLine();
        skill = Integer.parseInt(option);
        String option1 = "abcd";

        while (!option1.equals("Enlisted") && !option1.equals("Officer")) {
            System.out.println("Do you want to add an 'Enlisted' Soldier or an 'Officer'?");
            option1 = input.nextLine();
            switch (option1) {
                case "Enlisted":
                    ranktype = "E";
                    System.out.println("Enter the Soldier's rank: (1 to 7 - Private to Sergeant Major)");
                    option = input.nextLine();
                    rank = Integer.parseInt(option);

                    NewPlayer = new Soldier(-1, username, discordTag, steamURL, ranktype, rank, specialization, skill);
                    try {
                        SoldierService.saveSoldier(NewPlayer);
                    } catch (SQLException msg) {
                        System.out.println("Error encountered when trying to save soldier through SoldierService: " + msg.getMessage());
                    }
                    break;

                case "Officer":
                    ranktype = "O";
                    System.out.println("Enter the Officer's rank: (1 to 7 - 2nd Lieutenant to Brigadier)");
                    option = input.nextLine();
                    rank = Integer.parseInt(option);

                    NewPlayer = new Soldier(-1, username, discordTag, steamURL, ranktype, rank, specialization, skill);

                    System.out.println("Enter the Officer's Infantry Command Skill (1 - 10):");
                    option = input.nextLine();
                    infantryCommandSkill = Integer.parseInt(option);

                    System.out.println("Enter the Officer's Tank Command Skill (1 - 10):");
                    option = input.nextLine();
                    tankCommandSkill = Integer.parseInt(option);

                    System.out.println("Enter the Officer's Artillery Command Skill (1 - 10):");
                    option = input.nextLine();
                    artilleryCommandSkill = Integer.parseInt(option);

                    System.out.println("Enter the Officer's Navy Command Skill (1 - 10):");
                    option = input.nextLine();
                    navyCommandSkill = Integer.parseInt(option);

                    System.out.println("Enter the Officer's Logistics Command Skill (1 - 10):");
                    option = input.nextLine();
                    logisticsCommandSkill = Integer.parseInt(option);

                    Officer NewOfficer = new Officer(NewPlayer, infantryCommandSkill, tankCommandSkill, artilleryCommandSkill, navyCommandSkill, logisticsCommandSkill);
                    try {
                        SoldierService.saveOfficer(NewOfficer);
                    } catch (SQLException msg) {
                        System.out.println("Error encountered when trying to save officer through SoldierService: " + msg.getMessage());
                    }
                    break;

            }
        }

        soldiers.add(NewPlayer);

    }

    public void update_player (Scanner input) {
        Soldier soldier_to_update;

        System.out.println("Enter the UID of the player you would like to update:");
        String option = input.nextLine();
        int UID = Integer.parseInt(option);

        soldier_to_update = SoldierService.getSoldierByUID(UID);

        System.out.println("For each field below introduce a new value if you wish to change it, or write 'No' otherwise:");

        System.out.println("Current username: " + soldier_to_update.getUsername());
        option = input.nextLine();
        switch (option) {
            case "No":
                ;
            default:
                soldier_to_update.setUsername(option);
        }

        System.out.println("Current discord Tag: " + soldier_to_update.getDiscordTag());
        option = input.nextLine();
        switch (option) {
            case "No":
                ;
            default:
                soldier_to_update.setDiscordTag(option);
        }

        System.out.println("Current steam URL: " + soldier_to_update.getSteamUrl());
        option = input.nextLine();
        switch (option) {
            case "No":
                ;
            default:
                soldier_to_update.setSteamUrl(option);
        }

        System.out.println("Current rank type: " + soldier_to_update.getRankType() + " Select a number from 1 to 7 below for the rank.");
        if(soldier_to_update.getRankType().equals("Enlisted")) System.out.println("Current rank: " + soldier_to_update.getRank() + " (" + EnlistedRanks.get(soldier_to_update.getRank()-1) + ")");
        else if(soldier_to_update.getRankType().equals("Officer")) System.out.println("Current rank: " + soldier_to_update.getRank() + " (" + OfficerRanks.get(soldier_to_update.getRank()-1) + ")");
        option = input.nextLine();
        switch (option) {
            case "No":
                ;
            default:
                if(Integer.parseInt(option) > 7)
                    System.out.println("Already at max rank!");
                else soldier_to_update.setRank(Integer.parseInt(option));
        }

        System.out.println("Current Specialization: " + soldier_to_update.getSpecialization());
        option = input.nextLine();
        switch (option) {
            case "No":
                ;
            default:
                soldier_to_update.setSpecialization(option);
        }

        System.out.println("Current Skill: " + soldier_to_update.getSkill());
        option = input.nextLine();
        switch (option) {
            case "No":
                ;
            default:
                soldier_to_update.setSkill(Integer.parseInt(option));
        }

        SoldierService.updateSoldier(soldier_to_update);
    }

    public void dismiss_player (Scanner input) {
        System.out.println("Would you like to dismiss by 'UID' or 'Username'?");
        String option = input.nextLine();
        switch(option) {
            case "UID":
                System.out.println("Enter the UID of the player you would like to dismiss:");
                option = input.nextLine();
                int UID = Integer.parseInt(option);

                SoldierService.deleteSoldierByUID(UID);
            case "Username":
                System.out.println("Enter the Username of the player you would like to dismiss:");
                option = input.nextLine();

                SoldierService.deleteSoldierByName(option);
        }

    }

    public void create_equipment (Scanner input) {
        String name, type, ammoType;
        int slot, inventorySpace, magazineSize, range;
        int baseID;
        Equipment item = null;


        System.out.println("Enter the item's designation:");
        String option = input.nextLine();
        name = option;

        System.out.println("Enter the item's type:");
        option = input.nextLine();
        type = option;

        System.out.println("Enter the inventory Space the item occupies (suggested values 1 - 25):");
        option = input.nextLine();
        inventorySpace = Integer.parseInt(option);

        System.out.println("Enter the item's slot number:");
        option = input.nextLine();
        slot = Integer.parseInt(option);

        if(!type.equals("Weapon")) { item = new Equipment(-1, name, type, inventorySpace, slot); }

        if(type.equals("Weapon")){
            System.out.println("Please specify the ammunition type for this weapon (caliber):");
            option = input.nextLine();
            ammoType = option;

            System.out.println("Enter the weapon's magazine size:");
            option = input.nextLine();
            magazineSize = Integer.parseInt(option);

            System.out.println("Enter the weapon's effective range:");
            option = input.nextLine();
            range = Integer.parseInt(option);

            item = new Weapon(-1, name, type, inventorySpace, slot, ammoType, magazineSize, range);
        }
        EquipmentService.saveEquipment(item);

    }


    public void update_equipment(Scanner input) {
        Equipment equipmentToUpdate;

        System.out.println("Enter the serial number of the equipment you would like to update:");
        int serialNumber = Integer.parseInt(input.nextLine());

        equipmentToUpdate = EquipmentService.getEquipmentBySerialNumber(serialNumber);

        System.out.println("For each field below, introduce a new value if you wish to change it, or write 'No' otherwise:");

        System.out.println("Current name: " + equipmentToUpdate.getName());
        String option = input.nextLine();
        if (!option.equals("No")) {
            equipmentToUpdate.setName(option);
        }

        System.out.println("Current type: " + equipmentToUpdate.getType());
        option = input.nextLine();
        if (!option.equals("No")) {
            equipmentToUpdate.setType(option);
        }

        System.out.println("Current inventory space: " + equipmentToUpdate.getInventorySpace());
        option = input.nextLine();
        if (!option.equals("No")) {
            equipmentToUpdate.setInventorySpace(Integer.parseInt(option));
        }

        System.out.println("Current slot: " + equipmentToUpdate.getSlot());
        option = input.nextLine();
        if (!option.equals("No")) {
            equipmentToUpdate.setSlot(Integer.parseInt(option));
        }

        if (equipmentToUpdate instanceof Weapon) {
            Weapon weapon = (Weapon) equipmentToUpdate;

            System.out.println("Current ammo type: " + weapon.getAmmoType());
            option = input.nextLine();
            if (!option.equals("No")) {
                weapon.setAmmoType(option);
            }

            System.out.println("Current magazine size: " + weapon.getMagazineSize());
            option = input.nextLine();
            if (!option.equals("No")) {
                weapon.setMagazineSize(Integer.parseInt(option));
            }

            System.out.println("Current effective range: " + weapon.getRange());
            option = input.nextLine();
            if (!option.equals("No")) {
                weapon.setRange(Integer.parseInt(option));
            }
        }

        EquipmentService.updateEquipment(equipmentToUpdate);
    }

    public void delete_equipment(Scanner input) {

        System.out.println("Enter the Serial Number of the equipment you would like to dismiss:");
        String option = input.nextLine();
        int serialNumber = Integer.parseInt(option);

        EquipmentService.deleteEquipmentBySerialNumber(serialNumber);

    }



    public void create_vehicle (Scanner input) {
        String type, drivetrain, weaponry, ammunitionType, armor, cargoType;
        int seats, fuelCapacity, crew, ammunitionCapacity, transportCapacity;
        //int baseID;
        ArrayList<Equipment> inventory = null;
        Vehicle wheelie = null;

        //System.out.println("Enter the ID of the base you would like the vehicle to be stored in:");
        //String option = input.nextLine();
        //baseID = Integer.parseInt(option);

        System.out.println("Enter the vehicle's designation:");
        String option = input.nextLine();
        type = option;

        System.out.println("Enter the vehicle's drivetrain type:");
        option = input.nextLine();
        drivetrain = option;

        System.out.println("Enter the vehicle's number of seats:");
        option = input.nextLine();
        seats = Integer.parseInt(option);

        System.out.println("Enter the vehicle's fuel capacity (in litres)");
        option = input.nextLine();
        fuelCapacity = Integer.parseInt(option);

        System.out.println("Choose whether the vehicle is a Transport vehicle or a Combat vehicle ('Transport' / 'Combat'):");
        option = input.nextLine();

        if(option.equals("Transport")){
            System.out.println("Please specify the cargo the vehicle is able to carry (Items, Liquids, Resources):");
            option = input.nextLine();
            cargoType = option;

            System.out.println("Enter the vehicle's transport capacity:");
            option = input.nextLine();
            transportCapacity = Integer.parseInt(option);

            wheelie = new TransportVehicle(-1, type, drivetrain, seats, fuelCapacity, transportCapacity, cargoType);
        }

        else if (option.equals("Combat")){
            System.out.println("Please specify the vehicle's main armament:");
            option = input.nextLine();
            weaponry = option;

            System.out.println("Please specify the vehicle's ammunition type (caliber):");
            option = input.nextLine();
            ammunitionType = option;

            System.out.println("Enter the vehicle's ammunition capacity:");
            option = input.nextLine();
            ammunitionCapacity = Integer.parseInt(option);

            System.out.println("Please specify the vehicle's armor at the front/sides/rear:");
            option = input.nextLine();
            armor = option;

            System.out.println("Enter the vehicle's optimum number of crewmembers in order to operate at peak performance:");
            option = input.nextLine();
            crew = Integer.parseInt(option);

            wheelie = new CombatVehicle(-1, type, drivetrain, seats, fuelCapacity, weaponry, ammunitionType, ammunitionCapacity, armor, crew);
        }

        VehicleService.saveVehicle(wheelie);
        //bases.get(baseID).addVehicle(wheelie);

    }


    public void update_vehicle(Scanner input) {
        Vehicle vehicleToUpdate;

        System.out.println("Enter the serial number of the vehicle you would like to update:");
        int serialNumber = Integer.parseInt(input.nextLine());

        vehicleToUpdate = VehicleService.getVehicleBySerialNumber(serialNumber);

        System.out.println("For each field below, introduce a new value if you wish to change it, or write 'No' otherwise:");

        System.out.println("Current type: " + vehicleToUpdate.getType());
        String option = input.nextLine();
        if(!option.equals("No")){
            vehicleToUpdate.setType(option);
        }

        if(!option.equals("No")){
            vehicleToUpdate.setType(option);
        }

        System.out.println("Current drivetrain: " + vehicleToUpdate.getDrivetrain());
        option = input.nextLine();
        if(!option.equals("No")){
            vehicleToUpdate.setDrivetrain(option);
        }

        System.out.println("Current number of seats: " + vehicleToUpdate.getSeats());
        option = input.nextLine();
        if(!option.equals("No")){
            vehicleToUpdate.setSeats(Integer.parseInt(option));
        }

        System.out.println("Current fuel capacity: " + vehicleToUpdate.getFuelCapacity());
        option = input.nextLine();
        if(!option.equals("No")){
            vehicleToUpdate.setFuelCapacity(Integer.parseInt(option));
        }


        if (vehicleToUpdate instanceof TransportVehicle) {
            TransportVehicle transportVehicle = (TransportVehicle) vehicleToUpdate;

            System.out.println("Current transport capacity: " + transportVehicle.getTransportCapacity());
            option = input.nextLine();
            if(!option.equals("No")){
                transportVehicle.setTransportCapacity(Integer.parseInt(option));
            }

            System.out.println("Current cargo type: " + transportVehicle.getCargoType());
            option = input.nextLine();
            if(!option.equals("No")){
                transportVehicle.setCargoType(option);
            }
        }

        else if (vehicleToUpdate instanceof CombatVehicle) {
            CombatVehicle combatVehicle = (CombatVehicle) vehicleToUpdate;

            System.out.println("Current weaponry: " + combatVehicle.getWeaponry());
            option = input.nextLine();
            if(!option.equals("No")){
                combatVehicle.setWeaponry(option);
            }

            System.out.println("Current ammunition type: " + combatVehicle.getAmmunitionType());
            option = input.nextLine();
            if(!option.equals("No")){
                combatVehicle.setAmmunitionType(option);
            }

            System.out.println("Current ammunition capacity: " + combatVehicle.getAmmunitionCapacity());
            option = input.nextLine();
            if(!option.equals("No")){
                combatVehicle.setAmmunitionCapacity(Integer.parseInt(option));
            }

            System.out.println("Current armor layout: " + combatVehicle.getArmor());
            option = input.nextLine();
            if(!option.equals("No")){
                combatVehicle.setArmor(option);
            }

            System.out.println("Current crew required for optimum functionality: " + combatVehicle.getcrew());
            option = input.nextLine();
            if(!option.equals("No")){
                combatVehicle.setcrew(Integer.parseInt(option));
            }
        }

        VehicleService.updateVehicle(vehicleToUpdate);
    }

    public void scrap_vehicle(Scanner input) {
        System.out.println("Enter the serial number of the vehicle you wish to scrap:");
        String option = input.nextLine();
        int serialNumber = Integer.parseInt(option);
        VehicleService.deleteVehicleBySerialNumber(serialNumber);
    }

    public void create_base(Scanner input) {
        System.out.println("Enter the base ID:");
        int baseID = Integer.parseInt(input.nextLine());

        System.out.println("Enter the hex location:");
        String hex = input.nextLine();

        System.out.println("Enter the region:");
        String region = input.nextLine();

        System.out.println("Enter the base type (FOB or World Base):");
        String baseType = input.nextLine();

        if (baseType.equals("FOB")) {
            System.out.println("Enter the UID of the builder:");
            int builderID = Integer.parseInt(input.nextLine());

            System.out.println("Enter the base's upgrades:");
            String upgrades = input.nextLine();

            System.out.println("Enter the garrison size:");
            int garrisonSize = Integer.parseInt(input.nextLine());

            // Create and insert the FOB object into the database
            FOB fob = new FOB(baseID, hex, region, baseType, builderID, upgrades, garrisonSize);
            BaseService.saveBase(fob);

        } else if (baseType.equals("World Base")) {
            System.out.println("Enter the base's level (1 - 3):");
            int level = Integer.parseInt(input.nextLine());

            System.out.println("Is the base a Victory Point? (true or false):");
            boolean victoryPoint = Boolean.parseBoolean(input.nextLine());

            System.out.println("Enter the number of garrison houses:");
            int garrisonHouses = Integer.parseInt(input.nextLine());

            System.out.println("Enter the number of mortar houses:");
            int mortarHouses = Integer.parseInt(input.nextLine());

            System.out.println("Does the town have a Coastal Gun? (true or false):");
            boolean coastalGun = Boolean.parseBoolean(input.nextLine());

            // Create and insert the World Base object into the database
            WorldBase worldBase = new WorldBase(baseID, hex, region, baseType, level, victoryPoint, garrisonHouses, mortarHouses, coastalGun);
            BaseService.saveBase(worldBase);
        } else {
            System.out.println("Invalid base type. Please enter either FOB or World Base.");
        }
    }

    public void update_base(Scanner input) {
        Base baseToUpdate;

        System.out.println("Enter the base ID of the base you would like to update:");
        int baseID = Integer.parseInt(input.nextLine());

        baseToUpdate = BaseService.getBaseByID(baseID);

        System.out.println("For each field below, introduce a new value if you wish to change it, or write 'No' otherwise:");
        System.out.println("Note that only some of the fields can be updated!");
        String option;

        if (baseToUpdate instanceof FOB) {
            FOB fob = (FOB) baseToUpdate;

            System.out.println("Current upgrades: " + fob.getUpgrades());
            option = input.nextLine();
            if (!option.equals("No")) {
                fob.setUpgrades(option);
            }

            System.out.println("Current garrison size: " + fob.getGarrisonSize());
            option = input.nextLine();
            if (!option.equals("No")) {
                fob.setGarrisonSize(Integer.parseInt(option));
            }
        } else if (baseToUpdate instanceof WorldBase) {
            WorldBase worldBase = (WorldBase) baseToUpdate;

            System.out.println("Current level: " + worldBase.getLevel());
            option = input.nextLine();
            if (!option.equals("No")) {
                worldBase.setLevel(Integer.parseInt(option));
            }

            System.out.println("Current victory point: " + worldBase.hasVictoryPoint());
            option = input.nextLine();
            if (!option.equals("No")) {
                worldBase.setVictoryPoint(Boolean.parseBoolean(option));
            }

            System.out.println("Current garrison houses: " + worldBase.getGarrisonHouses());
            option = input.nextLine();
            if (!option.equals("No")) {
                worldBase.setGarrisonHouses(Integer.parseInt(option));
            }

            System.out.println("Current mortar houses: " + worldBase.getMortarHouses());
            option = input.nextLine();
            if (!option.equals("No")) {
                worldBase.setMortarHouses(Integer.parseInt(option));
            }

            System.out.println("Current coastal gun: " + worldBase.hasCoastalGun());
            option = input.nextLine();
            if (!option.equals("No")) {
                worldBase.setCoastalGun(Boolean.parseBoolean(option));
            }
        }

        BaseService.updateBase(baseToUpdate);
    }

    public void delete_base(Scanner input) {
        System.out.println("Enter the ID of the base you would like to cancel:");
        int baseID = Integer.parseInt(input.nextLine());

        BaseService.deleteBaseByID(baseID);
    }




    public void create_operation (Scanner input) {
        String name, type, objective, plan;
        LocalDateTime startTime, endTime;
        String branches = null;
        int commanderUID;

        System.out.println("Enter the operation's name:");
        String option = input.nextLine();
        name = option;

        System.out.println("Enter the operation's type (Training / Assault / Defense):");
        option = input.nextLine();
        type = option;

        System.out.println("Enter the branches involved in the operation (Infantry, Artillery, Tanks, Navy, Engineers):");
        option = input.nextLine();
        branches = option;

        System.out.println("Enter the operation's start time (YYYY-MM-DD HH:MM):");
        option = input.nextLine();
        startTime = LocalDateTime.parse(option, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("Enter the operation's end time (YYYY-MM-DD HH:MM):");
        option = input.nextLine();
        endTime = LocalDateTime.parse(option, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("Enter the operation's objective:");
        option = input.nextLine();
        objective = option;

        System.out.println("Enter the operation plan / description:");
        option = input.nextLine();
        plan = option;

        System.out.println("Enter the operation commander's UID:");
        option = input.nextLine();
        commanderUID = Integer.parseInt(option);

        Operation NewOperation = new Operation(name, type, branches, startTime, endTime, objective, plan, commanderUID);
        OperationService.insertOperation(NewOperation);

    }

    public void update_operation(Scanner input) {
        Operation operationToUpdate;

        System.out.println("Enter the name of the operation you would like to update:");
        String operationName = input.nextLine();

        operationToUpdate = OperationService.getOperationByName(operationName);

        System.out.println("For each field below, introduce a new value if you wish to change it, or write 'No' otherwise:");

        System.out.println("Current operation name: " + operationToUpdate.getName());
        String option = input.nextLine();
        if (!option.equals("No")) {
            operationToUpdate.setName(option);
        }

        System.out.println("Current operation type: " + operationToUpdate.getType());
        option = input.nextLine();
        if (!option.equals("No")) {
            operationToUpdate.setType(option);
        }

        System.out.println("Current branches: " + operationToUpdate.getBranches());
        option = input.nextLine();
        if (!option.equals("No")) {
            operationToUpdate.setBranches(option);
        }

        System.out.println("Current start time: " + operationToUpdate.getStartTime());
        option = input.nextLine();
        if (!option.equals("No")) {
            LocalDateTime startTime = LocalDateTime.parse(option, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            operationToUpdate.setStartTime(startTime);
        }

        System.out.println("Current end time: " + operationToUpdate.getEndTime());
        option = input.nextLine();
        if (!option.equals("No")) {
            LocalDateTime endTime = LocalDateTime.parse(option, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            operationToUpdate.setEndTime(endTime);
        }

        System.out.println("Current objective: " + operationToUpdate.getObjective());
        option = input.nextLine();
        if (!option.equals("No")) {
            operationToUpdate.setObjective(option);
        }

        System.out.println("Current plan: " + operationToUpdate.getPlan());
        option = input.nextLine();
        if (!option.equals("No")) {
            operationToUpdate.setPlan(option);
        }

        System.out.println("Current commander UID: " + operationToUpdate.getCommander());
        option = input.nextLine();
        if (!option.equals("No")) {
            int commanderUID = Integer.parseInt(option);
            operationToUpdate.setCommander(commanderUID);
        }

        OperationService.updateOperation(operationToUpdate);
    }

    public void cancel_operation(Scanner input) {
        System.out.println("Enter the name of the operation you would like to cancel:");
        String operationName = input.nextLine();

        OperationService.deleteOperationByName(operationName);
    }



    public void create_medal (Scanner input) {

        String name, milestone, type;


        System.out.println("Enter the medal name:");
        String option = input.nextLine();
        name = option;

        System.out.println("Enter the milestone for which the medal was awarded:");
        option = input.nextLine();
        milestone = option;

        System.out.println("Enter the medal type (Performance / Veterancy / Commemorative):");
        option = input.nextLine();
        type = option;

        Medal NewMedal = new Medal(name, milestone, type);
        MedalService.insertMedal(NewMedal);

    }

    public void modify_medal(Scanner input) {
        Medal medalToUpdate;

        System.out.println("Enter the name of the medal you would like to update:");
        String name = input.nextLine();

        medalToUpdate = MedalService.getMedal(name);

        if (medalToUpdate != null) {
            System.out.println("For each field below, introduce a new value if you wish to change it, or write 'No' otherwise:");

            System.out.println("Current milestone: " + medalToUpdate.getMilestone());
            String option = input.nextLine();
            if (!option.equals("No")) {
                medalToUpdate.setMilestone(option);
            }

            System.out.println("Current medal type: " + medalToUpdate.getType());
            option = input.nextLine();
            if (!option.equals("No")) {
                medalToUpdate.setType(option);
            }

            MedalService.updateMedal(medalToUpdate);
        } else {
            System.out.println("No medal found with name: " + name);
        }
    }


    public void assign_soldier_to_base (Scanner input) {
        int soldierUID, baseID;


        System.out.println("The soldier will be unassigned from their current post. Enter the UID of the soldier you wish to assign:");
        String option = input.nextLine();
        soldierUID = Integer.parseInt(option);

        System.out.println("Enter the ID of the base you wish to assign the soldier to:");
        option = input.nextLine();
        baseID = Integer.parseInt(option);

        if(SoldierBase.checkAssignment(soldierUID) == -1){
            SoldierBase.assignSoldierToBase(soldierUID, baseID);
        }
        else {
            SoldierBase.unassignSoldierFromBase(soldierUID);
            SoldierBase.assignSoldierToBase(soldierUID, baseID);
        }
    }

    public void unassign_soldier_from_base (Scanner input) {
        int soldierUID, baseID;

        System.out.println("Enter the UID of the soldier you wish to unassign:");
        String option = input.nextLine();
        soldierUID = Integer.parseInt(option);

        SoldierBase.unassignSoldierFromBase(soldierUID);

    }

    public void display_base_roster(Scanner input) {
        System.out.println("Enter the UID of the base you'd like to see the roster for:");
        String option = input.nextLine();
        int baseID = Integer.parseInt(option);


        List<Integer> assignedSoldiers = SoldierBase.getAllAssigned(baseID);

        System.out.println("Base Roster - Base ID: " + baseID);
        System.out.println("--------------------------");

        for (int soldierUID : assignedSoldiers) {
            String soldierUsername = SoldierService.getSoldierByUID(soldierUID).getUsername();
            System.out.println("UID: " + soldierUID + " - Username: " + soldierUsername);
        }
    }

    public void assign_soldier_to_operation(Scanner input) {
        System.out.println("Enter the soldier's UID:");
        int soldierUID = Integer.parseInt(input.nextLine());

        System.out.println("Enter the operation's name:");
        String operationName = input.nextLine();

        if(SoldierOperation.checkAssignment(soldierUID).equals("-1")){
            SoldierOperation.assignSoldierToOperation(soldierUID, operationName);
        }
        else {
            SoldierOperation.unassignSoldierFromOperation(soldierUID, SoldierOperation.checkAssignment(soldierUID));
            SoldierOperation.assignSoldierToOperation(soldierUID, operationName);
        }


    }

    public void unassign_soldier_from_operation (Scanner input) {
        int soldierUID;
        String operationName;

        System.out.println("Enter the UID of the soldier you wish to unassign:");
        String option = input.nextLine();
        soldierUID = Integer.parseInt(option);

        System.out.println("Enter the name of the operation you wish to unassign the soldier from:");
        option = input.nextLine();
        operationName = option;

        SoldierOperation.unassignSoldierFromOperation(soldierUID, operationName);

    }

    public void display_operation_manpower(Scanner input) {
        System.out.println("Enter the name of the operation you wish to see the assigned players for:");
        String option = input.nextLine();
        String operationName = option;


        List<Integer> assignedSoldiers = SoldierOperation.getAllAssigned(operationName);

        System.out.println("Personnel assigned to Operation: " + operationName);
        System.out.println("--------------------------");

        for (int soldierUID : assignedSoldiers) {
            String soldierUsername = SoldierService.getSoldierByUID(soldierUID).getUsername();
            System.out.println("UID: " + soldierUID + " - Username: " + soldierUsername);
        }
    }



    public void assign_vehicle_to_operation(Scanner input) {
        System.out.println("Enter the serial number for the vehicle:");
        int serialNumber = Integer.parseInt(input.nextLine());

        System.out.println("Enter the operation's name:");
        String operationName = input.nextLine();

        if(VehicleOperation.checkAssignment(serialNumber).equals("-1")){
            VehicleOperation.assignVehicleToOperation(serialNumber, operationName);
        }
        else {
            VehicleOperation.unassignVehicleFromOperation(serialNumber, VehicleOperation.checkAssignment(serialNumber));
            VehicleOperation.assignVehicleToOperation(serialNumber, operationName);
        }
    }

    public void unassign_vehicle_from_operation (Scanner input) {
        int serialNumber;
        String operationName;

        System.out.println("Enter the serial number for the vehicle:");
        String option = input.nextLine();
        serialNumber = Integer.parseInt(option);

        System.out.println("Enter the name of the operation you wish to unassign the vehicle from:");
        option = input.nextLine();
        operationName = option;

        VehicleOperation.unassignVehicleFromOperation(serialNumber, operationName);

    }

    public void validate_operation (Scanner input) {
        String operationName;
        System.out.println("Validating an operation means checking whether there is enough crew for all the vehicles, and enough vehicles for all the passengers.");
        System.out.println("Enter the name of the operation you wish to validate:");
        operationName = input.nextLine();

        List<Integer> assignedVehicles = VehicleOperation.getAllAssigned(operationName);
        List<Integer> assignedSoldiers = SoldierOperation.getAllAssigned(operationName);

        int available_crew = 0, needed_crew = 0, passengers, passenger_seats = 0;

        available_crew = assignedSoldiers.size();
        for (int vehicleID : assignedVehicles) {
            Vehicle vic = VehicleService.getVehicleBySerialNumber(vehicleID);
            if(vic instanceof TransportVehicle) {
                needed_crew += 1;
                passenger_seats += vic.getSeats() - 1;
            }
            else if (vic instanceof CombatVehicle) {
                needed_crew += ((CombatVehicle) vic).getcrew();
            }
        }

        if(needed_crew > available_crew) {
            System.out.println("Operation " + operationName + " may not proceed commander - not enough crew! We still need " + (needed_crew-available_crew) + " soldiers.");
            return;
        }
        else {
            passengers = available_crew - needed_crew;
            if(passengers > passenger_seats) {
                System.out.println("Operation " + operationName + " strained - not enough transports for everybody! We lack " + (passengers-passenger_seats) + " seats.");
                return;
            }
            else {
                System.out.println("Operation " + operationName + " is a go commander. All assets are available and fully operational!");
            }
        }

    }


    /*public void assign_materiel (Scanner input) {
        String opName;
        int baseID, opID = 0, equipID;

        System.out.println("Enter the name of the operation you would like to assign materiel to:");
        String option = input.nextLine();
        opName = option;

        // need to check if number is ok (operation exists)
        for(int i = 0; i < operations.size(); i++)
            if (operations.get(i).getName() == opName)
                opID = i;

        System.out.println("Enter the ID of the base you wish to assign equipment from:");
        option = input.nextLine();
        baseID = Integer.parseInt(option);

        System.out.println("Enter the serial number of the equipment you wish to assign:");
        option = input.nextLine();
        equipID = Integer.parseInt(option);

        operationMateriel.get(opID).add(bases.get(baseID).getInventory().get(equipID));
        System.out.println("Equipment assigned successfully. Would you like to assign another item?");
        option = input.nextLine();
        switch(option) {
            case "Yes":
                assign_materiel(input); // gotta make it a repeating structure after the operation and base numbers are introduced
            case "No":
                break; // exit
        }
    }*/

    /*public void load_equipment (Scanner input) {

        int vicNumber, baseID, itemNumber;
        int vicIndex = 0, itemIndex = 0;

        System.out.println("Enter the serial number of the vehicle you'd like to load items in:");
        String option = input.nextLine();
        vicNumber = Integer.parseInt(option);

        System.out.println("Enter the ID of the base you'd like to transfer the items from:");
        option = input.nextLine();
        baseID = Integer.parseInt(option);

        System.out.println("Enter the serial number of the item you wish to transfer:");
        option = input.nextLine();
        itemNumber = Integer.parseInt(option);

        for(int i=0; i< vehicles.size(); i++)
        {
            if(vehicles.get(i).getSerialNumber() == vicNumber)
            {
                vicIndex = i;
                break;
            }
        }

        for(int i=0; i< bases.get(baseID).getInventory().size(); i++)
        {
            if(bases.get(baseID).getInventory().get(i).getSerialNumber() == itemNumber)
            {
                itemIndex = i;
                break;
            }
        }

        if(bases.get(baseID).getInventory().get(itemIndex).getType() == "Shell" && vehicles.get(vicIndex) instanceof CombatVehicle )
        {
            if(bases.get(baseID).getInventory().get(itemIndex).getInventorySpace() > vehicles.get(vicIndex).checkCapacity()) {
                ((CombatVehicle) vehicles.get(vicIndex)).addShell(bases.get(baseID).getInventory().get(itemIndex));
            }
            bases.get(baseID).removeItem(itemNumber);
            System.out.println("Shell successfully loaded into the tank.");
        }

        else if (vehicles.get(vicIndex) instanceof TransportVehicle){

            if(bases.get(baseID).getInventory().get(itemIndex).getInventorySpace() < vehicles.get(vicIndex).checkCapacity()) {
                ((TransportVehicle) vehicles.get(vicIndex)).addEquipment(bases.get(baseID).getInventory().get(itemIndex));
            }
            bases.get(baseID).removeItem(itemNumber);
            System.out.println("Equipment successfully loaded into the vehicle.");
        }

        // should also check if the vehicle is currently stationed in the garage of the base we're picking up items from :b

    }*/

    public void display_profile (Scanner input) {

        int soldierUID, soldierIndex = 0;

        System.out.println("Enter the name of the soldier whose profile you'd like to inspect:");
        String option = input.nextLine();

        Soldier soldier_displayed = SoldierService.getSoldierByName(option);

        System.out.println("      --- Personnel Record ---     ");
        System.out.println("UID: " + soldier_displayed.getUID());
        System.out.println("Name: " + soldier_displayed.getUsername());
        System.out.println("Discord Tag: " + soldier_displayed.getDiscordTag());
        System.out.println("Steam URL: " + soldier_displayed.getSteamUrl());
        if(soldier_displayed.getRankType().equals("E")) System.out.println("Rank: " + "E " + EnlistedRanks.get(soldier_displayed.getRank()));
        else if(soldier_displayed.getRankType().equals("O")) System.out.println("Rank: " + "O " + OfficerRanks.get(soldier_displayed.getRank()-1));
        System.out.println("Specialization: " + soldier_displayed.getSpecialization());
        System.out.println("Skill: " + soldier_displayed.getSkill() + "/10");

        /*System.out.println(" -o-  -o- Medal Cabinet -o-  -o- ");
        for(int i=0; i<medals.size(); i++)
        {
            if(medals.get(i).getRecipient() == soldierUID)
            {
                System.out.println(medals.get(i).getName() + " - Milestone achieved: " + medals.get(i).getMilestone());
            }
        }*/

    }

    public void display_vehicle(Scanner input) {
        System.out.println("Enter the serial number of the vehicle for which you want to see technical details:");
        int serialNumber = Integer.parseInt(input.nextLine());

        Vehicle vehicleDisplayed = VehicleService.getVehicleBySerialNumber(serialNumber);

        System.out.println("      --- Vehicle Tech Sheet ---     ");
        System.out.println("Serial Number: " + vehicleDisplayed.getSerialNumber());
        System.out.println("Designation: " + vehicleDisplayed.getType());
        System.out.println("Drivetrain: " + vehicleDisplayed.getDrivetrain());
        System.out.println("Seats: " + vehicleDisplayed.getSeats());
        System.out.println("Fuel Capacity: " + vehicleDisplayed.getFuelCapacity());

        if (vehicleDisplayed instanceof TransportVehicle) {
            TransportVehicle transportVehicle = (TransportVehicle) vehicleDisplayed;
            System.out.println("Transport Capacity: " + transportVehicle.getTransportCapacity());
            System.out.println("Cargo Type: " + transportVehicle.getCargoType());
        } else if (vehicleDisplayed instanceof CombatVehicle) {
            CombatVehicle combatVehicle = (CombatVehicle) vehicleDisplayed;
            System.out.println("Weaponry: " + combatVehicle.getWeaponry());
            System.out.println("Ammunition Type: " + combatVehicle.getAmmunitionType());
            System.out.println("Ammunition Capacity: " + combatVehicle.getAmmunitionCapacity());
            System.out.println("Armor: " + combatVehicle.getArmor());
            System.out.println("Optimal no. of Crew: " + combatVehicle.getcrew());
        }
    }

    public void display_equipment(Scanner input) {
        System.out.println("Enter the serial number of the equipment piece you wish to inspect:");
        int serialNumber = Integer.parseInt(input.nextLine());

        Equipment equipmentDisplayed = EquipmentService.getEquipmentBySerialNumber(serialNumber);

        if(equipmentDisplayed != null) {
            System.out.println("      --- Equipment Tech Sheet ---     ");
            System.out.println("Serial Number: " + equipmentDisplayed.getSerialNumber());
            System.out.println("Designation: " + equipmentDisplayed.getName());
            System.out.println("Type: " + equipmentDisplayed.getType());
            System.out.println("Inventory Space: " + equipmentDisplayed.getInventorySpace());
            System.out.println("Slot: " + equipmentDisplayed.getSlot());

            if (equipmentDisplayed instanceof Weapon) {
                Weapon weaponDisplayed = (Weapon) equipmentDisplayed;
                System.out.println("Ammo Type: " + weaponDisplayed.getAmmoType());
                System.out.println("Magazine Size: " + weaponDisplayed.getMagazineSize());
                System.out.println("Effective Range: " + weaponDisplayed.getRange());

            }
        }
        else {
            System.out.println("Equipment not found.");
        }
    }

    public void display_operation(Scanner input) {
        System.out.println("Enter the name of the operation you wish to display:");
        String operationName = input.nextLine();

        Operation operationDisplayed = OperationService.getOperationByName(operationName);

        if (operationDisplayed != null) {
            System.out.println("      --- Operation Details ---     ");
            System.out.println("Name: " + operationDisplayed.getName());
            System.out.println("Type: " + operationDisplayed.getType());
            System.out.println("Branches: " + operationDisplayed.getBranches());
            System.out.println("Start Time: " + operationDisplayed.getStartTime());
            System.out.println("End Time: " + operationDisplayed.getEndTime());
            System.out.println("Objective: " + operationDisplayed.getObjective());
            System.out.println("Plan: " + operationDisplayed.getPlan());
            System.out.println("Commander: " + SoldierService.getSoldierByUID(operationDisplayed.getCommander()).getUsername());
        } else {
            System.out.println("Operation not found.");
        }
    }

    public void display_base(Scanner input) {
        System.out.println("Enter the ID of the base you'd like to inspect:");
        int option = Integer.parseInt(input.nextLine());

        Base baseDisplayed = BaseService.getBaseByID(option);

        System.out.println("      --- Base Log ---     ");
        System.out.println("Base ID: " + baseDisplayed.getBaseID());
        System.out.println("Hex: " + baseDisplayed.getHex());
        System.out.println("Region: " + baseDisplayed.getRegion());
        System.out.println("Base Type: " + baseDisplayed.getType());

        String baseType = baseDisplayed.getType();
        if (baseType.equals("FOB")) {
            FOB fob = (FOB) baseDisplayed;
            System.out.println("Builder ID: " + fob.getBuilder());
            System.out.println("Upgrades: " + fob.getUpgrades());
            System.out.println("Garrison Size: " + fob.getGarrisonSize());
        } else if (baseType.equals("World Base")) {
            WorldBase worldBase = (WorldBase) baseDisplayed;
            System.out.println("Level: " + worldBase.getLevel());
            System.out.println("Victory Point: " + worldBase.hasVictoryPoint());
            System.out.println("Garrison Houses: " + worldBase.getGarrisonHouses());
            System.out.println("Mortar Houses: " + worldBase.getMortarHouses());
            System.out.println("Coastal Gun: " + worldBase.hasCoastalGun());
        }
    }



    public void upgrade_base (Scanner input) {
        // not yet implemented
    }

    /*public void display_inventory (Scanner input) {
        System.out.println("Do you wish to display the inventory of a base or of a vehicle? ('Base' / 'Vehicle')");
        String option = input.nextLine();
        switch (option) {
            case "Base":
                int baseID;

                System.out.println("Please enter the ID of the base in order to display its inventory:");
                option = input.nextLine();
                baseID = Integer.parseInt(option);

                System.out.println("Inventory of base #" + baseID + " situated in " + bases.get(baseID).getHex() + ":");
                for(int i=0; i<bases.get(baseID).getInventory().size(); i++){
                    System.out.println(bases.get(baseID).getInventory().get(i).getSerialNumber() + ": " + bases.get(baseID).getInventory().get(i).getName());
                }

            case "Vehicle":
                int vehicleID;

                System.out.println("Please enter the ID of the vehicle in order to display its inventory:");
                option = input.nextLine();
                vehicleID = Integer.parseInt(option);

                System.out.println("Inventory of vehicle #" + vehicleID + ":");
                for(int i=0; i<vehicles.get(vehicleID).getInventory().size(); i++){
                    System.out.println(vehicles.get(vehicleID).getInventory().get(i).getSerialNumber() + ": " + vehicles.get(vehicleID).getInventory().get(i).getName());
                }
        }
    }*/

    /*public void assign_commander (Scanner input) {

        String opName, opType;
        int opIndex = 0;

        System.out.println("Choose an operation you would like to assign a commander to:");
        String option = input.nextLine();
        opName = option;

        for(int i=0; i<operations.size(); i++)
        {
            if(operations.get(i).getName() == opName)
            {
                opIndex = i;
                break;
            }
        }

        opType = operations.get(opIndex).getBranches().get(0);

        System.out.println("Current operation commander is: " + operations.get(opIndex).getCommander() + "." + "Would you like to assign a new commander?");
        option = input.nextLine();
        switch (option) {
            case "Yes":
                int commIndex = -1;
                int topskill = -1;

                switch (opType) {
                    case "Infantry":

                        for(int i=0; i<soldiers.size(); i++)
                        {
                            if(soldiers.get(i) instanceof Officer && ((Officer) soldiers.get(i)).getInfantryCommandSkill() > topskill){
                                topskill = ((Officer) soldiers.get(i)).getInfantryCommandSkill();
                                commIndex = i;
                            }
                        }
                    case "Tanks":

                        for(int i=0; i<soldiers.size(); i++)
                        {
                            if(soldiers.get(i) instanceof Officer && ((Officer) soldiers.get(i)).getTankCommandSkill() > topskill){
                                topskill = ((Officer) soldiers.get(i)).getTankCommandSkill();
                                commIndex = i;
                            }
                        }
                    case "Artillery":

                        for(int i=0; i<soldiers.size(); i++)
                        {
                            if(soldiers.get(i) instanceof Officer && ((Officer) soldiers.get(i)).getArtilleryCommandSkill() > topskill){
                                topskill = ((Officer) soldiers.get(i)).getArtilleryCommandSkill();
                                commIndex = i;
                            }
                        }
                    case "Navy":

                        for(int i=0; i<soldiers.size(); i++)
                        {
                            if(soldiers.get(i) instanceof Officer && ((Officer) soldiers.get(i)).getNavyCommandSkill() > topskill){
                                topskill = ((Officer) soldiers.get(i)).getNavyCommandSkill();
                                commIndex = i;
                            }
                        }
                    case "Logistics":

                        for(int i=0; i<soldiers.size(); i++)
                        {
                            if(soldiers.get(i) instanceof Officer && ((Officer) soldiers.get(i)).getLogisticsCommandSkill() > topskill){
                                topskill = ((Officer) soldiers.get(i)).getLogisticsCommandSkill();
                                commIndex = i;
                            }
                        }
                }

                System.out.println("The best available candidate for leading the operation is the Officer with the UID:" + commIndex + ", having the specific leadership skill of " + topskill + ".");
                operations.get(opIndex).setCommander(soldiers.get(commIndex).getUID());

            case "No":
                break;

        }


    }*/

    public void showactions (Scanner input) {
        // should print a list of all the commands and their descriptions (the ones at the top of the Main File) - not yet implemented as it did not represent a priority
    }
}


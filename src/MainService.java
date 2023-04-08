import Awards.Medal;
import Base.*;
import Materiel.*;
import Operation.*;
import Player.*;

import java.util.*;


public class MainService {
    private ArrayList<Soldier> soldiers = new ArrayList<>();
    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Operation> operations = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Medal> medals = new ArrayList<>();
    HashMap<Integer, Integer> soldierAssignment = new HashMap<>();
    ArrayList<ArrayList<Equipment>> operationMateriel = new ArrayList<ArrayList<Equipment>>();


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

        while (option != "Enlisted" && option != "Officer") {
            System.out.println("Do you want to add an 'Enlisted' Soldier or an 'Officer'?");
            option = input.nextLine();
            switch (option) {
                case "Enlisted":
                    ranktype = "E";
                    System.out.println("Enter the Soldier's rank: (1 to 7 - Private to Sergeant Major)");
                    option = input.nextLine();
                    rank = Integer.parseInt(option);

                    NewPlayer = new Soldier(username, discordTag, steamURL, specialization, skill, ranktype, rank);
                    break;

                case "Officer":
                    ranktype = "O";
                    System.out.println("Enter the Officer's rank: (1 to 7 - 2nd Lieutenant to Brigadier)");
                    option = input.nextLine();
                    rank = Integer.parseInt(option);

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

                    NewPlayer = new Officer(username, discordTag, steamURL, specialization, skill, ranktype, rank,
                            infantryCommandSkill, tankCommandSkill, artilleryCommandSkill, navyCommandSkill, logisticsCommandSkill);
                    break;

            }
        }

        soldiers.add(NewPlayer);
    }

    public void create_equipment (Scanner input) {
        String name, type, ammoType;
        int slot, inventorySpace, magazineSize, range;
        int baseID;
        Equipment item = null;

        System.out.println("Enter the ID of the base you would like the item to be stored in:");
        String option = input.nextLine();
        baseID = Integer.parseInt(option);

        System.out.println("Enter the item's designation:");
        option = input.nextLine();
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

        if(type != "Weapon") { item = new Equipment(name, type, inventorySpace, slot); }

        if(type == "Weapon"){
            System.out.println("Please specify the ammunition type for this weapon (caliber):");
            option = input.nextLine();
            ammoType = option;

            System.out.println("Enter the weapon's magazine size:");
            option = input.nextLine();
            magazineSize = Integer.parseInt(option);

            System.out.println("Enter the weapon's effective range:");
            option = input.nextLine();
            range = Integer.parseInt(option);

            item = new Weapon(name, type, inventorySpace, slot, ammoType, magazineSize, range);
        }

        bases.get(baseID).addItem(item);

    }

    public void create_vehicle (Scanner input) {
        String type, drivetrain, weaponry, ammunitionType, armor, cargoType;
        int seats, fuelCapacity, crew, ammunitionCapacity, transportCapacity;
        int baseID;
        ArrayList<Equipment> inventory = null;
        Vehicle wheelie = null;

        System.out.println("Enter the ID of the base you would like the vehicle to be stored in:");
        String option = input.nextLine();
        baseID = Integer.parseInt(option);

        System.out.println("Enter the vehicle's designation:");
        option = input.nextLine();
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

        if(option == "Transport"){
            System.out.println("Please specify the cargo the vehicle is able to carry (Items, Liquids, Resources):");
            option = input.nextLine();
            cargoType = option;

            System.out.println("Enter the vehicle's transport capacity:");
            option = input.nextLine();
            transportCapacity = Integer.parseInt(option);

            wheelie = new TransportVehicle(type, drivetrain, seats, fuelCapacity, transportCapacity, cargoType, inventory);
        }

        else if (option == "Combat"){
            System.out.println("Please specify the vehicle's main armament:");
            option = input.nextLine();
            weaponry = option;

            System.out.println("Please specify the vehicle's ammunition type (caliber):");
            option = input.nextLine();
            ammunitionType = option;

            System.out.println("Enter the vehicle's ammunition capacity:");
            option = input.nextLine();
            ammunitionCapacity = Integer.parseInt(option);

            System.out.println("Please specify the cargo the vehicle's armor at the front/sides/rear:");
            option = input.nextLine();
            armor = option;

            System.out.println("Enter the vehicle's optimum number of crewmembers in order to operate at peak performance:");
            option = input.nextLine();
            crew = Integer.parseInt(option);

            wheelie = new CombatVehicle(type, drivetrain, seats, fuelCapacity, weaponry, ammunitionType, ammunitionCapacity, armor, crew, inventory);
        }

        bases.get(baseID).addVehicle(wheelie);
    }

    public void create_base (Scanner input) {
        int builderUID, baseID;
        String hex, region;
        ArrayList<String> upgrades = null;
        ArrayList<Vehicle> garage = null;
        ArrayList<Equipment> inventory = null;

        int level = 1, garrisonHouses = 0, mortarHouses = 0;
        boolean victoryPoint = false, coastalGun = false, seaport = false;

        Base NewBase = null;

        System.out.println("Enter the Base's ID:");
        String option = input.nextLine();
        baseID = Integer.parseInt(option);

        System.out.println("Enter the Base's hex:");
        option = input.nextLine();
        hex = option;

        System.out.println("Enter the Base's region:");
        option = input.nextLine();
        region = option;


        while (option != "World" && option != "FOB") {
            System.out.println("Do you want to add a 'World' Base or a 'FOB'?");
            option = input.nextLine();
            switch (option) {
                case "World":
                    System.out.println("What level is the base? (1 - 3)");
                    option = input.nextLine();

                    System.out.println("Is the base a Victory Point? (Yes/No)");
                    switch (option) {
                        case "Yes":
                            victoryPoint = true;
                            break;
                        case "No":
                            victoryPoint = false;
                            break;
                    }

                    System.out.println("How many Garrison Houses does the base have?");
                    option = input.nextLine();
                    garrisonHouses = Integer.parseInt(option);

                    System.out.println("How many Mortar Houses does the base have?");
                    option = input.nextLine();
                    garrisonHouses = Integer.parseInt(option);

                    NewBase = new WorldBase(baseID, hex, region, inventory, garage, level, victoryPoint, garrisonHouses, mortarHouses, coastalGun, seaport);

                    break;

                case "FOB":

                    System.out.println("Enter the UID of the player that built the base (6-digit number):");
                    option = input.nextLine();
                    builderUID = Integer.parseInt(option);

                    System.out.println("Enter the upgrades that the base has, one per line. Write 'Stop' when you have introduced all the upgrades.");
                    option = input.nextLine();
                    while(option != "Stop") {
                        upgrades.add(option);
                        option = input.nextLine();
                    }

                    NewBase = new FOB(baseID, hex, region, inventory, garage, builderUID, upgrades, 0);

                    break;

            }
        }
    }

    public void create_operation (Scanner input) {
        String name, type, startTime, endTime, objective, plan;
        ArrayList<String> branches = null;
        ArrayList<Soldier> manpower = null;
        int commanderUID;

        System.out.println("Enter the operation's name:");
        String option = input.nextLine();
        name = option;

        System.out.println("Enter the operation's type (Training / Assault / Defense):");
        option = input.nextLine();
        type = option;

        System.out.println("Enter the branches involved in the operation, one per line. Write 'Stop' when you have introduced all the branches (Infantry, Artillery, Tanks, Navy, Engineers)");
        option = input.nextLine();
        while(option != "Stop") {
            branches.add(option);
            option = input.nextLine();
        }

        System.out.println("Enter the operation's start time (DD/MM/YYYY XX:XX):");
        option = input.nextLine();
        startTime = option;

        System.out.println("Enter the operation's end time (DD/MM/YYYY XX:XX):");
        option = input.nextLine();
        endTime = option;

        System.out.println("Enter the operation's objective:");
        option = input.nextLine();
        objective = option;

        System.out.println("Enter the operation plan / description:");
        option = input.nextLine();
        plan = option;

        System.out.println("Enter the operation commander's UID:");
        option = input.nextLine();
        commanderUID = Integer.parseInt(option);

        Operation NewOperation = new Operation(name, type, branches, startTime, endTime, objective, plan, manpower, commanderUID);
        operations.add(NewOperation);

        ArrayList<Equipment> row = new ArrayList<Equipment>();
        operationMateriel.add(row);

    }

    public void create_medal (Scanner input) {

        String name, milestone, dateAwarded, citation, operationName, type;
        int recipientUID;


        System.out.println("Enter the medal name:");
        String option = input.nextLine();
        name = option;

        System.out.println("Enter the recipient's UID:");
        option = input.nextLine();
        recipientUID = Integer.parseInt(option);

        System.out.println("Enter the milestone for which the medal was awarded:");
        option = input.nextLine();
        milestone = option;

        System.out.println("Enter the date when the medal was awarded:");
        option = input.nextLine();
        dateAwarded = option;

        System.out.println("Enter the award citation:");
        option = input.nextLine();
        citation = option;

        System.out.println("Enter the operation name during which the medal was issued:");
        option = input.nextLine();
        operationName = option;

        System.out.println("Enter the medal type (Performance / Veterancy / Commemorative):");
        option = input.nextLine();
        type = option;

        Medal NewMedal = new Medal(name, recipientUID, milestone, dateAwarded, citation, operationName, type);
        medals.add(NewMedal);

    }

    public void assign_soldier (Scanner input) {
        int soldierUID, baseID;

        System.out.println("Enter the UID of the soldier you wish to assign:");
        String option = input.nextLine();
        soldierUID = Integer.parseInt(option);

        System.out.println("Enter the ID of the base you wish to assign the soldier to:");
        option = input.nextLine();
        baseID = Integer.parseInt(option);


        for (Soldier soldier : soldiers) {
            if (soldier.getUID() == soldierUID) {
                soldierAssignment.put(soldierUID, baseID);
                System.out.println("Player " + soldier.getUsername() + " has been assigned to base number " + bases.get(baseID).getBaseID() + " in " + bases.get(baseID).getHex() + ".");
            }
        }

    }

    public void assign_materiel (Scanner input) {
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
    }

    public void load_equipment (Scanner input) {

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

    }

    public void display_profile (Scanner input) {

        int soldierUID, soldierIndex = 0;

        System.out.println("Enter the name of the soldier whose profile you'd like to inspect:");
        String option = input.nextLine();
        soldierUID = Integer.parseInt(option);

        for(int i = 0; i < soldiers.size(); i++){
            if(soldiers.get(i).getUID() == soldierUID)
            {
                soldierIndex = i;
                break;
            }
        }
        System.out.println("      --- Personnel Record ---     ");
        System.out.println("UID: " + soldiers.get(soldierIndex).getUID());
        System.out.println("Name: " + soldiers.get(soldierIndex).getUsername());
        System.out.println("Discord Tag: " + soldiers.get(soldierIndex).getDiscordTag());
        System.out.println("Steam URL: " + soldiers.get(soldierIndex).getSteamUrl());
        System.out.println("Rank: " + soldiers.get(soldierIndex).getRankType() + soldiers.get(soldierIndex).getRank());
        System.out.println("Specialization: " + soldiers.get(soldierIndex).getSpecialization());
        System.out.println("Skill: " + soldiers.get(soldierIndex).getSkill() + "/10");

        System.out.println(" -o-  -o- Medal Cabinet -o-  -o- ");
        for(int i=0; i<medals.size(); i++)
        {
            if(medals.get(i).getRecipient() == soldierUID)
            {
                System.out.println(medals.get(i).getName() + " - Milestone achieved: " + medals.get(i).getMilestone());
            }
        }

    }

    public void upgrade_base (Scanner input) {
        // not yet implemented
    }

    public void display_inventory (Scanner input) {
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
    }

    public void assign_commander (Scanner input) {

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


    }

    public void showactions (Scanner input) {
        // should print a list of all the commands and their descriptions (the ones at the top of the Main File) - not yet implemented as it did not represent a priority
    }
}


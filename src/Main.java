import Awards.Medal;
import Base.*;
import Materiel.*;
import Operation.*;
import Player.*;

import java.util.*;

public class Main {

    static List<String> Actions = Arrays.asList("create_player", "create_equipment", "create_vehicle", "create_base", "create_operation", "create_medal",
                                                "assign_soldier", "assign_materiel", "load_equipment", "display_profile", "upgrade_base", "display_inventory",
                                                "assign_commander", "help", "stop");
    static List<String> Descriptions = Arrays.asList("Add a soldier to the regiment.", "Add a piece of equipment to the inventory of a base.", "Add a new vehicle to the inventory of a base",
                                                    "Build a a new base.", "Plan a new operation.", "Award a medal to a Soldier.", "Assign a Soldier to a base or to an operation.",
                                                    "Assign materiel to an operation.", "Load equipment inside a vehicle.", "Display a Soldier's profile.", "Add an upgrade to an existing base.",
                                                    "Display the inventory of a base / vehicle", "Find and assign the most suitable Officer to lead an operation.", "See all actions", "Stop the app");


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean stopflag = false;

        MainService mainService = new MainService();

        while (!stopflag) {
            System.out.println("Insert order: (enter 'help' to see available actions)");
            String order = in.nextLine();

            switch (order) {
                case "create_soldier" -> mainService.create_player(in); //done
                case "create_equipment" -> mainService.create_equipment(in); //done
                case "create_vehicle" -> mainService.create_vehicle(in); //done
                case "create_base" -> mainService.create_base(in); //done
                case "create_operation" -> mainService.create_operation(in); //done
                case "create_medal" -> mainService.create_medal(in); //done
                case "assign_soldier" -> mainService.assign_soldier(in); //done
                case "assign_materiel" -> mainService.assign_materiel(in); //done
                case "load_equipment" -> mainService.load_equipment(in); //done
                case "display_profile" -> mainService.display_profile(in); //done
                //case "upgrade_base" -> mainService.upgrade_base(in);
                case "display_inventory" -> mainService.display_inventory(in); //done
                case "assign_commander" -> mainService.assign_commander(in);
                case "stop" -> stopflag = true;
            }

        }
    }

}
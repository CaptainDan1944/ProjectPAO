

import java.util.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean stopflag = false;

        MainService mainService = new MainService();

        mainService.make_connection();

        while (!stopflag) {
            System.out.println("Insert order: (enter 'help' to see available actions)");
            String order = in.nextLine();

            switch (order) {

                case "create_player" -> mainService.create_player(in); //done
                case "create_equipment" -> mainService.create_equipment(in);
                case "create_vehicle" -> mainService.create_vehicle(in); //done
                case "create_base" -> mainService.create_base(in);
                case "create_operation" -> mainService.create_operation(in); //done
                case "create_medal" -> mainService.create_medal(in); //done
                //case "create_hex" -> mainService.create_hex(in);

                case "update_player" -> mainService.update_player(in); //done
                case "dismiss_player" -> mainService.dismiss_player(in); //done
                case "display_profile" -> mainService.display_profile(in); //done

                case "update_vehicle" -> mainService.update_vehicle(in); //done
                case "display_vehicle" -> mainService.display_vehicle(in); //done
                case "scrap_vehicle" -> mainService.scrap_vehicle(in); //done

                case "update_operation" -> mainService.update_operation(in); //done
                case "display_operation" -> mainService.display_operation(in); //done
                case "cancel_operation" -> mainService.cancel_operation(in);

                case "modify_medal" -> mainService.modify_medal(in); //done

                case "display_equipment" -> mainService.display_equipment(in); //done
                case "update_equipment" -> mainService.update_equipment(in); //done
                case "delete_equipment" -> mainService.delete_equipment(in); //done

                case "display_base" -> mainService.display_base(in); //done
                case "update_base" -> mainService.update_base(in); //done
                case "delete_base" -> mainService.delete_base(in); //done

                case "assign_soldier_to_base" -> mainService.assign_soldier_to_base(in); //done
                case "unassign_soldier_from_base" -> mainService.unassign_soldier_from_base(in); //done
                case "display_base_roster" -> mainService.display_base_roster(in); //done

                case "assign_soldier_to_operation" -> mainService.assign_soldier_to_operation(in); //done
                case "unassign_soldier_from_operation" -> mainService.unassign_soldier_from_operation(in); //done
                case "display_operation_manpower" -> mainService.display_operation_manpower(in); //done

                case "assign_vehicle_to_operation" -> mainService.assign_vehicle_to_operation(in); //done
                case "unassign_vehicle_from_operation" -> mainService.unassign_vehicle_from_operation(in); //done
                case "display_operation_vehicles" -> mainService.display_operation_vehicles(in); //done

                case "validate_operation" -> mainService.validate_operation(in); //done
                case "assign_materiel" -> mainService.assign_equipment_to_vehicle(in); //done
                case "transport_materiel" -> mainService.transport_materiel_to_base(in); //done
                //case "hand_out_medal" -> mainService.give_medal(in);

                case "display_vehicle_inventory" -> mainService.display_vehicle_inventory(in); //done
                case "find_commander" -> mainService.find_commander(in); //done
                case "stop" -> stopflag = true;
            }

        }

        mainService.end_connection();

    }

}
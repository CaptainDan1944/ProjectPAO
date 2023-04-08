package Base;
import Materiel.*;
import Player.Soldier;
import java.util.ArrayList;

public class Base {
    private final int baseID;
    private final String hex;
    private final String region;
    private ArrayList<Equipment> inventory;
    private ArrayList<Vehicle> garage;

    public void addItem (Equipment item) {
        inventory.add(item);
    }

    public void addVehicle (Vehicle wheelie) {
        garage.add(wheelie);
    }

    public void removeItem (int itemSerialNumber) {
        for(int i=0; i < inventory.size(); i++){
            if (inventory.get(i).getSerialNumber() == itemSerialNumber) {
                inventory.remove(i);
                break;
            }
        }
    }

    public Base(int baseID, String hex, String region, ArrayList<Equipment> inventory, ArrayList<Vehicle> garage) {
        this.baseID = baseID;
        this.hex = hex;
        this.region = region;
        this.inventory = inventory;
        this.garage = garage;
    }

    public int getBaseID() { return baseID; }
    public String getHex() {
        return hex;
    }

    public String getRegion() {
        return region;
    }

    public ArrayList<Equipment> getInventory() {
        return inventory;
    }

}

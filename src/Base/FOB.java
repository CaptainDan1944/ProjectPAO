package Base;
import Materiel.*;
import Player.Soldier;
import java.util.ArrayList;

public class FOB extends Base {
    private final int builder;
    private ArrayList<String> upgrades;
    private int garrisonSize;

    public FOB(int baseID, String hex, String region, ArrayList<Equipment> inventory, ArrayList<Vehicle> garage,
               int builder, ArrayList<String> upgrades, int garrisonSize) {
        super(baseID, hex, region, inventory, garage);
        this.builder = builder;
        this.upgrades = upgrades;
        this.garrisonSize = garrisonSize;
    }


    public void setUpgrades(ArrayList<String> upgrades) {
        this.upgrades = upgrades;
    }

    public void setGarrisonSize(int garrisonSize) {
        this.garrisonSize = garrisonSize;
    }

    public int getBuilder() {
        return builder;
    }

    public ArrayList<String> getUpgrades() {
        return upgrades;
    }

    public int getGarrisonSize() {
        return garrisonSize;
    }
}

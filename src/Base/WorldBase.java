package Base;
import Materiel.*;
import Player.Soldier;
import java.util.ArrayList;

public class WorldBase extends Base {
    private int level;
    private boolean victoryPoint;
    private int garrisonHouses;
    private int mortarHouses;
    private boolean coastalGun;
    private boolean seaport;

    public WorldBase(int baseID, String hex, String region, ArrayList<Equipment> inventory, ArrayList<Vehicle> garage,
                     int level, boolean victoryPoint, int garrisonHouses, int mortarHouses,
                     boolean coastalGun, boolean seaport) {
        super(baseID, hex, region, inventory, garage);
        this.level = level;
        this.victoryPoint = victoryPoint;
        this.garrisonHouses = garrisonHouses;
        this.mortarHouses = mortarHouses;
        this.coastalGun = coastalGun;
        this.seaport = seaport;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setVictoryPoint(boolean victoryPoint) {
        this.victoryPoint = victoryPoint;
    }

    public void setGarrisonHouses(int garrisonHouses) {
        this.garrisonHouses = garrisonHouses;
    }

    public void setMortarHouses(int mortarHouses) {
        this.mortarHouses = mortarHouses;
    }

    public void setCoastalGun(boolean coastalGun) {
        this.coastalGun = coastalGun;
    }

    public void setSeaport(boolean seaport) {
        this.seaport = seaport;
    }

    public int getLevel() {
        return level;
    }

    public boolean hasVictoryPoint() {
        return victoryPoint;
    }

    public int getGarrisonHouses() {
        return garrisonHouses;
    }

    public int getMortarHouses() {
        return mortarHouses;
    }

    public boolean hasCoastalGun() {
        return coastalGun;
    }

    public boolean hasSeaport() {
        return seaport;
    }
}


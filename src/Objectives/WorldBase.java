package Objectives;
import Materiel.*;

import java.util.ArrayList;

public class WorldBase extends Base {
    private int level;
    private boolean victoryPoint;
    private int garrisonHouses;
    private int mortarHouses;
    private boolean coastalGun;

    public WorldBase(int baseID, String hex, String region, String type,
                     int level, boolean victoryPoint, int garrisonHouses, int mortarHouses,
                     boolean coastalGun) {
        super(baseID, hex, region, type);
        this.level = level;
        this.victoryPoint = victoryPoint;
        this.garrisonHouses = garrisonHouses;
        this.mortarHouses = mortarHouses;
        this.coastalGun = coastalGun;
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

}


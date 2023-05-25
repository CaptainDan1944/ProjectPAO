package Objectives;
import Materiel.*;

import java.util.ArrayList;

public class FOB extends Base {
    private final int builder;
    private String upgrades;
    private int garrisonSize;

    public FOB(int baseID, String hex, String region, String type, int builder, String upgrades, int garrisonSize) {
        super(baseID, hex, region, type);
        this.builder = builder;
        this.upgrades = upgrades;
        this.garrisonSize = garrisonSize;
    }


    public void setUpgrades(String upgrades) {
        this.upgrades = upgrades;
    }

    public void setGarrisonSize(int garrisonSize) {
        this.garrisonSize = garrisonSize;
    }

    public int getBuilder() {
        return builder;
    }

    public String getUpgrades() {
        return upgrades;
    }

    public int getGarrisonSize() {
        return garrisonSize;
    }
}

package Objectives;
import Materiel.*;

import java.util.ArrayList;

public class Base {
    private final int baseID;
    private final String hex;
    private final String region;
    private final String type;



    public Base(int baseID, String hex, String region, String type) {
        this.baseID = baseID;
        this.hex = hex;
        this.region = region;
        this.type = type;
    }

    public int getBaseID() { return baseID; }
    public String getHex() {
        return hex;
    }
    public String getRegion() {
        return region;
    }
    public String getType() { return type; }

}

package Materiel;

import java.util.ArrayList;

public class CombatVehicle extends Vehicle {
    private String weaponry;
    private String ammunitionType;
    private int ammunitionCapacity;
    private String armor;
    private int crew;

    /*@Override
    public int checkCapacity() {
        int weight = 0;
        for (int i=0; i<inventory.size(); i++) {
            weight += 1;
        }

        return ammunitionCapacity - weight;
    }

    public void addShell(Equipment shell) {
        inventory.add(shell);
    }*/

    public CombatVehicle(int serialNumber, String type, String drivetrain, int seats, int fuelcapacity, String weaponry,
                         String ammunitionType, int ammunitionCapacity, String armor, int crew) {
        super(serialNumber, type, drivetrain, seats, fuelcapacity);
        this.weaponry = weaponry;
        this.ammunitionType = ammunitionType;
        this.ammunitionCapacity = ammunitionCapacity;
        this.armor = armor;
        this.crew = crew;
    }

    public void setWeaponry(String weaponry) {
        this.weaponry = weaponry;
    }

    public void setAmmunitionType(String ammunitionType) {
        this.ammunitionType = ammunitionType;
    }

    public void setAmmunitionCapacity(int ammunitionCapacity) {
        this.ammunitionCapacity = ammunitionCapacity;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public void setcrew(int crew) {
        this.crew = crew;
    }

    public String getWeaponry() {
        return weaponry;
    }

    public String getAmmunitionType() {
        return ammunitionType;
    }

    public int getAmmunitionCapacity() {
        return ammunitionCapacity;
    }

    public String getArmor() {
        return armor;
    }

    public int getcrew() {
        return crew;
    }
}

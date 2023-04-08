package Materiel;

import java.util.ArrayList;

public abstract class Vehicle {
    private static int buildNumber = 100000;
    private int serialNumber;
    private String type;
    private String drivetrain;
    private int seats;
    private int fuelcapacity;
    protected ArrayList<Equipment> inventory;

    public abstract int checkCapacity ();

    public Vehicle(String type, String drivetrain, int seats, int fuelcapacity, ArrayList<Equipment> inventory) {
        this.serialNumber = buildNumber;
        buildNumber++;
        this.type = type;
        this.drivetrain = drivetrain;
        this.seats = seats;
        this.fuelcapacity = fuelcapacity;
        this.inventory = inventory;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setFuelCapacity(int fuelcapacity) {
        this.fuelcapacity = fuelcapacity;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getType() {
        return type;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public int getSeats() {
        return seats;
    }

    public int getFuelCapacity() {
        return fuelcapacity;
    }

    public ArrayList<Equipment> getInventory() { return inventory; }
}


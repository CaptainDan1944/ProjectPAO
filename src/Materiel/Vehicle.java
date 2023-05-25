package Materiel;

import java.util.ArrayList;

public abstract class Vehicle {
    private static int buildNumber = 100000;
    private int serialNumber;
    private String type;
    private String drivetrain;
    private int seats;
    private int fuelcapacity;

   // public abstract int checkCapacity ();

    public Vehicle(int serialNumber, String type, String drivetrain, int seats, int fuelcapacity) {
        if(serialNumber == -1) {
            this.serialNumber = buildNumber;
            buildNumber++;
        }
        else this.serialNumber = serialNumber;
        this.type = type;
        this.drivetrain = drivetrain;
        this.seats = seats;
        this.fuelcapacity = fuelcapacity;
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

}


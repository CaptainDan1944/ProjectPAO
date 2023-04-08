package Materiel;

import java.util.*;

public class TransportVehicle extends Vehicle {
    private int transportCapacity;
    private String cargoType;


    @Override
    public int checkCapacity() {
        int weight = 0;
        for (int i=0; i<inventory.size(); i++) {
            weight += inventory.get(i).getInventorySpace();
        }

        return transportCapacity - weight;
    }

    public void addEquipment(Equipment item) {
        inventory.add(item);
    }

    public TransportVehicle(String type, String drivetrain, int seats, int fuelcapacity, int transportCapacity, String cargoType, ArrayList<Equipment> inventory) {
        super(type, drivetrain, seats, fuelcapacity, inventory);
        this.transportCapacity = transportCapacity;
        this.cargoType = cargoType;
    }


    public void setTransportCapacity(int transportCapacity) {
        this.transportCapacity = transportCapacity;
    }
    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public int getTransportCapacity() {
        return transportCapacity;
    }
    public String getCargoType() {
        return cargoType;
    }


}

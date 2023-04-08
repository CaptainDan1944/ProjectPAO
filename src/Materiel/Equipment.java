package Materiel;

public class Equipment {
    private static int buildNumber = 100000;
    private int serialNumber;
    private String name;
    private String type;
    private int inventorySpace;
    private int slot;

    public Equipment(String name, String type, int inventorySpace, int slot) {
        this.serialNumber = buildNumber;
        buildNumber++;
        this.name = name;
        this.type = type;
        this.inventorySpace = inventorySpace;
        this.slot = slot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setInventorySpace(int inventorySpace) {
        this.inventorySpace = inventorySpace;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSerialNumber() { return serialNumber; }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getInventorySpace() {
        return inventorySpace;
    }

    public int getSlot() {
        return slot;
    }
}

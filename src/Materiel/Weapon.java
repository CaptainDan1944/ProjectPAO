package Materiel;

public class Weapon extends Equipment {
    private String ammoType;
    private int magazineSize;
    private int range;

    public Weapon(int serialNumber, String name, String type, int inventorySpace, int slot, String ammoType, int magazineSize, int range) {
        super(serialNumber, name, type, inventorySpace, slot);
        this.ammoType = ammoType;
        this.magazineSize = magazineSize;
        this.range = range;
    }

    public void setAmmoType(String ammoType) {
        this.ammoType = ammoType;
    }

    public void setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getAmmoType() {
        return ammoType;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public int getRange() {
        return range;
    }
}

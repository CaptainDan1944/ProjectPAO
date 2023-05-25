package Materiel;


import java.sql.*;

public class EquipmentService {

    private static EquipmentService instance;
    Connection connection;

    private EquipmentService() {

    }

    public static EquipmentService getInstance() {
        if (instance == null) {
            instance = new EquipmentService();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }

    public void saveEquipment(Equipment equipment) {
        String query = "INSERT INTO Equipment (serialNumber, name, type, inventorySpace, slot, ammoType, magazineSize, effectiveRange) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, equipment.getSerialNumber());
            statement.setString(2, equipment.getName());
            statement.setString(3, equipment.getType());
            statement.setInt(4, equipment.getInventorySpace());
            statement.setInt(5, equipment.getSlot());

            if (equipment instanceof Weapon) {
                Weapon weapon = (Weapon) equipment;
                statement.setString(6, weapon.getAmmoType());
                statement.setInt(7, weapon.getMagazineSize());
                statement.setInt(8, weapon.getRange());
            } else {
                statement.setNull(6, java.sql.Types.VARCHAR);
                statement.setNull(7, java.sql.Types.INTEGER);
                statement.setNull(8, java.sql.Types.INTEGER);
            }

            statement.executeUpdate();

            System.out.println("Equipment piece stored successfully commander!");

        } catch (SQLException e) {
            System.out.println("Error inserting equipment: " + e.getMessage());
        }
    }


    public Equipment getEquipmentBySerialNumber(int serialNumber) {
        Equipment equipment = null;

        String query = "SELECT * FROM Equipment WHERE serialNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, serialNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int inventorySpace = resultSet.getInt("inventorySpace");
                int slot = resultSet.getInt("slot");

                // Check if the equipment is a weapon
                if (resultSet.getString("ammoType") != null) {
                    String ammoType = resultSet.getString("ammoType");
                    int magazineSize = resultSet.getInt("magazineSize");
                    int effectiveRange = resultSet.getInt("effectiveRange");

                    equipment = new Weapon(serialNumber, name, type, inventorySpace, slot, ammoType, magazineSize, effectiveRange);
                } else {
                    equipment = new Equipment(serialNumber, name, type, inventorySpace, slot);
                }
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving equipment: " + e.getMessage());
        }

        return equipment;
    }


    public void updateEquipment(Equipment newEquipment) {
        String query = "UPDATE Equipment SET name = ?, type = ?, inventorySpace = ?, slot = ?, " +
                "ammoType = ?, magazineSize = ?, effectiveRange = ? WHERE serialNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newEquipment.getName());
            statement.setString(2, newEquipment.getType());
            statement.setInt(3, newEquipment.getInventorySpace());
            statement.setInt(4, newEquipment.getSlot());

            if (newEquipment instanceof Weapon) {
                Weapon weapon = (Weapon) newEquipment;
                statement.setString(5, weapon.getAmmoType());
                statement.setInt(6, weapon.getMagazineSize());
                statement.setInt(7, weapon.getRange());
            } else {
                statement.setNull(5, java.sql.Types.VARCHAR);
                statement.setNull(6, java.sql.Types.INTEGER);
                statement.setNull(7, java.sql.Types.INTEGER);
            }

            statement.setInt(8, newEquipment.getSerialNumber());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Equipment piece updated successfully!");
            } else {
                System.out.println("No equipment found with serial number: " + newEquipment.getSerialNumber());
            }

        } catch (SQLException e) {
            System.out.println("Error updating equipment: " + e.getMessage());
        }
    }



    public void deleteEquipmentBySerialNumber(int serialNumber) {
        String query = "DELETE FROM Equipment WHERE serialNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, serialNumber);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Equipment piece removed successfully commander!");
            } else {
                System.out.println("No equipment found with serial number: " + serialNumber);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting equipment: " + e.getMessage());
        }
    }


}

package Assignment;

import Materiel.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleStorage {
    private static VehicleStorage instance;
    private Connection connection;

    private VehicleStorage() {
    }

    public static VehicleStorage getInstance() {
        if (instance == null) {
            instance = new VehicleStorage();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }

    public void assignEquipmentToVehicle(int equipmentID, int vehicleID) {
        String query = "INSERT INTO VehicleStorage (equipmentID, vehicleID) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, equipmentID);
            statement.setInt(2, vehicleID);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Equipment successfully assigned to the vehicle!");
            } else {
                System.out.println("Failed to assign equipment to the vehicle.");
            }
        } catch (SQLException e) {
            System.out.println("Error assigning equipment to the vehicle: " + e.getMessage());
        }
    }

    public int checkEquipmentAssignment(int equipmentID) {
        int vehicleID = -1;

        String query = "SELECT V.vehicleID FROM Vehicle V " +
                "INNER JOIN VehicleStorage VS ON V.serialNumber = VS.vehicleID " +
                "WHERE VS.equipmentID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, equipmentID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                vehicleID = resultSet.getInt("vehicleID");
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error checking equipment assignment: " + e.getMessage());
        }

        return vehicleID;
    }

    public void unassignEquipmentFromVehicle(int equipmentID, int vehicleID) {
        String query = "DELETE FROM VehicleStorage WHERE equipmentID = ? AND vehicleID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, equipmentID);
            statement.setInt(2, vehicleID);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Equipment successfully unassigned from the vehicle!");
            } else {
                System.out.println("No assignment found for the equipment.");
            }
        } catch (SQLException e) {
            System.out.println("Error unassigning equipment from the vehicle: " + e.getMessage());
        }
    }

    public List<Integer> getAllAssignedEquipment(int vehicleID) {
        List<Integer> assignedEquipment = new ArrayList<>();

        String query = "SELECT equipmentID FROM VehicleStorage WHERE vehicleID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vehicleID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int equipmentID = resultSet.getInt("equipmentID");
                assignedEquipment.add(equipmentID);
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving assigned equipment: " + e.getMessage());
        }

        return assignedEquipment;
    }


}

package Assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseStorage {
    private static BaseStorage instance;
    private Connection connection;

    private BaseStorage() {
    }

    public static BaseStorage getInstance() {
        if (instance == null) {
            instance = new BaseStorage();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }

    public void assignEquipmentToBase(int equipmentID, int baseID) {
        String query = "INSERT INTO BaseStorage (equipmentID, baseID) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, equipmentID);
            statement.setInt(2, baseID);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Equipment successfully assigned to the base!");
            } else {
                System.out.println("Failed to assign equipment to the base.");
            }
        } catch (SQLException e) {
            System.out.println("Error assigning equipment to the base: " + e.getMessage());
        }
    }

    public int checkEquipmentAssignment(int equipmentID) {
        int baseID = -1;

        String query = "SELECT B.baseID FROM Base B " +
                "INNER JOIN BaseStorage BS ON B.baseID = BS.baseID " +
                "WHERE BS.equipmentID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, equipmentID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                baseID = resultSet.getInt("baseID");
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error checking equipment assignment: " + e.getMessage());
        }

        return baseID;
    }

    public void unassignEquipmentFromBase(int equipmentID, int baseID) {
        String query = "DELETE FROM BaseStorage WHERE equipmentID = ? AND baseID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, equipmentID);
            statement.setInt(2, baseID);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Equipment successfully unassigned from the base!");
            } else {
                System.out.println("No assignment found for the equipment.");
            }
        } catch (SQLException e) {
            System.out.println("Error unassigning equipment from the base: " + e.getMessage());
        }
    }

    public List<Integer> getAllAssignedEquipment(int baseID) {
        List<Integer> assignedEquipment = new ArrayList<>();

        String query = "SELECT equipmentID FROM BaseStorage WHERE baseID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, baseID);

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

package Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleOperation {

    private static VehicleOperation instance;
    Connection connection;

    private VehicleOperation() {

    }

    public static VehicleOperation getInstance() {
        if (instance == null) {
            instance = new VehicleOperation();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }

    public void assignVehicleToOperation(int vehicleID, String operationName) {
        String query = "INSERT INTO OperationVehicle (vehicleID, operationName) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vehicleID);
            statement.setString(2, operationName);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Vehicle successfully assigned to operation!");
            } else {
                System.out.println("Failed to assign vehicle to operation.");
            }
        } catch (SQLException e) {
            System.out.println("Error assigning vehicle to operation: " + e.getMessage());
        }
    }

    public String checkAssignment(int vehicleID) {
        String operationName = "-1";

        String query = "SELECT operationName FROM OperationVehicle WHERE vehicleID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vehicleID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                operationName = resultSet.getString("name");
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error checking vehicle assignment: " + e.getMessage());
        }

        return operationName;
    }


    public void unassignVehicleFromOperation(int serialNumber, String operationName) {
        String query = "DELETE FROM OperationVehicle WHERE vehicleID = ? AND name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, serialNumber);
            statement.setString(2, operationName);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Vehicle successfully unassigned from operation!");
            } else {
                System.out.println("No assignment found for the vehicle.");
            }
        } catch (SQLException e) {
            System.out.println("Error unassigning soldier from operation: " + e.getMessage());
        }
    }

    public List<Integer> getAllAssigned(String operationName) {
        List<Integer> assignedVehicles = new ArrayList<>();

        String query = "SELECT vehicleID FROM OperationVehicle WHERE operationName = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, operationName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int vehicleID = resultSet.getInt("vehicleID");
                assignedVehicles.add(vehicleID);
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving assigned vehicles: " + e.getMessage());
        }

        return assignedVehicles;
    }


}

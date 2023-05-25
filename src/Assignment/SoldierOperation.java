package Assignment;


import java.sql.*;
import java.util.*;

public class SoldierOperation {

    private static SoldierOperation instance;
    Connection connection;

    private SoldierOperation() {

    }

    public static SoldierOperation getInstance() {
        if (instance == null) {
            instance = new SoldierOperation();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }


    public void assignSoldierToOperation(int soldierUID, String operationName) {
        String query = "INSERT INTO OperationAssignment (soldier_uid, operation_name) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, soldierUID);
            statement.setString(2, operationName);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Soldier with UID " + soldierUID + " assigned to operation " + operationName + " successfully commander!");
            } else {
                System.out.println("Failed to assign soldier to operation.");
            }
        } catch (SQLException e) {
            System.out.println("Error assigning soldier to operation: " + e.getMessage());
        }
    }

    public String checkAssignment(int soldierUID) {
        String query = "SELECT * FROM OperationAssignment WHERE soldier_uid = ?";
        String operationName = "-1";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, soldierUID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                operationName = resultSet.getString("name");
            }

        } catch (SQLException e) {
            System.out.println("Error checking soldier assignment: " + e.getMessage());
        }

        return operationName;
    }

    public void unassignSoldierFromOperation(int soldierUID, String operationName) {
        String query = "DELETE FROM OperationAssignment WHERE soldier_uid = ? AND operation_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, soldierUID);
            statement.setString(2, operationName);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Soldier successfully unassigned from operation!");
            } else {
                System.out.println("No assignment found for soldier with UID: " + soldierUID + " and operation: " + operationName);
            }
        } catch (SQLException e) {
            System.out.println("Error unassigning soldier from operation: " + e.getMessage());
        }
    }

    public List<Integer> getAllAssigned(String operationName) {
        List<Integer> assignedSoldiers = new ArrayList<>();

        String query = "SELECT soldier_uid FROM OperationAssignment WHERE operation_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, operationName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int soldierUID = resultSet.getInt("soldier_uid");
                assignedSoldiers.add(soldierUID);
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving assigned soldiers: " + e.getMessage());
        }

        //System.out.println(assignedSoldiers);
        return assignedSoldiers;
    }



}

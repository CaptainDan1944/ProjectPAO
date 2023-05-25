package Operations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class OperationService {

    private static OperationService instance;
    Connection connection;

    private OperationService() {

    }

    public static OperationService getInstance() {
        if (instance == null) {
            instance = new OperationService();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }

    public void insertOperation(Operation operation) {
        String query = "INSERT INTO Operation (name, op_type, branches, startTime, endTime, objective, plan, commander_uid) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, operation.getName());
            statement.setString(2, operation.getType());
            statement.setString(3, operation.getBranches());
            statement.setTimestamp(4, Timestamp.valueOf(operation.getStartTime()));
            statement.setTimestamp(5, Timestamp.valueOf(operation.getEndTime()));
            statement.setString(6, operation.getObjective());
            statement.setString(7, operation.getPlan());
            statement.setInt(8, operation.getCommander());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("New Operation planned successfully commander!");
            } else {
                System.out.println("Failed to plan new operation.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting operation: " + e.getMessage());
        }
    }


    public Operation getOperationByName(String operationName) {
        Operation operation = null;

        String query = "SELECT * FROM Operation WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, operationName);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String opType = resultSet.getString("op_type");
                String branches = resultSet.getString("branches");
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                String objective = resultSet.getString("objective");
                String plan = resultSet.getString("plan");
                int commanderUID = resultSet.getInt("commander_uid");

                operation = new Operation(name, opType, branches, startTime, endTime, objective, plan, commanderUID);
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving operation: " + e.getMessage());
        }

        return operation;
    }



    public void updateOperation(Operation operation) {
        String query = "UPDATE Operation SET op_type = ?, branches = ?, startTime = ?, endTime = ?, objective = ?, plan = ?, commander_uid = ? WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, operation.getType());
            statement.setString(2, operation.getBranches());
            statement.setTimestamp(3, Timestamp.valueOf(operation.getStartTime()));
            statement.setTimestamp(4, Timestamp.valueOf(operation.getEndTime()));
            statement.setString(5, operation.getObjective());
            statement.setString(6, operation.getPlan());
            statement.setInt(7, operation.getCommander());
            statement.setString(8, operation.getName()); // Assuming 'name' is the primary key

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Operation " + operation.getName() + " updated successfully!");
            } else {
                System.out.println("No operation found with name: " + operation.getName());
            }
        } catch (SQLException e) {
            System.out.println("Error updating operation: " + e.getMessage());
        }
    }


    public void deleteOperationByName(String operationName) {
        String query = "DELETE FROM Operation WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, operationName);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Operation " + operationName + " cancelled successfully commander.");
            } else {
                System.out.println("No operation found with name: " + operationName);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting operation: " + e.getMessage());
        }
    }



}

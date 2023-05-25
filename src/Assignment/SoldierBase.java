package Assignment;


import java.sql.*;
import java.util.*;

public class SoldierBase {

    private static SoldierBase instance;
    Connection connection;

    private SoldierBase() {

    }

    public static SoldierBase getInstance() {
        if (instance == null) {
            instance = new SoldierBase();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }


    public void assignSoldierToBase(int soldierUID, int baseID) {
        String query = "INSERT INTO BaseAssignment (soldier_uid, baseID) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, soldierUID);
            statement.setInt(2, baseID);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Soldier successfully assigned to base!");
            } else {
                System.out.println("Failed to assign soldier to base.");
            }
        } catch (SQLException e) {
            System.out.println("Error linking soldier and base: " + e.getMessage());
        }
    }

    public int checkAssignment(int soldierUID) {
        String query = "SELECT baseID FROM BaseAssignment WHERE soldier_uid = ?";
        int baseID = -1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, soldierUID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                baseID = resultSet.getInt("baseID");
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving the base where the soldier is assigned: " + e.getMessage());
        }

        return baseID;
    }

    public void unassignSoldierFromBase(int soldierUID) {
        String query = "DELETE FROM BaseAssignment WHERE soldier_uid = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, soldierUID);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Soldier successfully unassigned from base!");
            } else {
                System.out.println("No assignment found for soldier with UID: " + soldierUID);
            }
        } catch (SQLException e) {
            System.out.println("Error unassigning soldier from base: " + e.getMessage());
        }
    }

    public List<Integer> getAllAssigned(int baseID) {
        List<Integer> assignedSoldiers = new ArrayList<>();

        String query = "SELECT soldier_uid FROM BaseAssignment WHERE baseID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, baseID);

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

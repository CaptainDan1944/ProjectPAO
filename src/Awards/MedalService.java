package Awards;



import java.util.*;
import java.sql.*;

public class MedalService {

    private static MedalService instance;
    Connection connection;

    private MedalService() {

    }

    public static MedalService getInstance() {
        if (instance == null) {
            instance = new MedalService();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }

    public void insertMedal(Medal medal) {

        String sql = "INSERT INTO Medal (name, milestone, medal_type) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the values for the prepared statement
            statement.setString(1, medal.getName());
            statement.setString(2, medal.getMilestone());
            statement.setString(3, medal.getType());

            // Execute the insert query
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Medal inserted successfully!");
            } else {
                System.out.println("Failed to insert medal.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting medal: " + e.getMessage());
        }
    }

    public Medal getMedal(String name) {
        Medal medal = null;

        String query = "SELECT * FROM Medal WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String medalName = resultSet.getString("name");
                String milestone = resultSet.getString("milestone");
                String medalType = resultSet.getString("medal_type");

                medal = new Medal(medalName, milestone, medalType);
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving medal: " + e.getMessage());
        }

        return medal;
    }



    public void updateMedal(Medal medal) {
        String query = "UPDATE Medal SET milestone = ?, medal_type = ? WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, medal.getMilestone());
            statement.setString(2, medal.getType());
            statement.setString(3, medal.getName());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Medal parameters changed successfully commander!");
            } else {
                System.out.println("No medal found with name: " + medal.getName());
            }
        } catch (SQLException e) {
            System.out.println("Error updating medal: " + e.getMessage());
        }
    }



}

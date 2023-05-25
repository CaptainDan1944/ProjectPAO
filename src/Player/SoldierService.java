package Player;

import java.sql.*;

public class SoldierService {
    private static SoldierService instance;
    Connection connection;

    private SoldierService() {

    }

    public static SoldierService getInstance() {
        if (instance == null) {
            instance = new SoldierService();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }

    public void saveSoldier(Soldier soldier) throws SQLException {

        String query = "INSERT INTO Soldier (UID, username, discordTag, steamURL, rankType, s_rank, specialization, skill) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sql_insert = connection.prepareStatement(query);

        try{
            sql_insert.setInt(1, soldier.getUID());
            sql_insert.setString(2, soldier.getUsername());
            sql_insert.setString(3, soldier.getDiscordTag());
            sql_insert.setString(4, soldier.getSteamUrl());
            sql_insert.setString(5, soldier.getRankType());
            sql_insert.setInt(6, soldier.getRank());
            sql_insert.setString(7, soldier.getSpecialization());
            sql_insert.setInt(8, soldier.getSkill());

            sql_insert.executeUpdate();
            sql_insert.close();

            System.out.println("Soldier insertion executed successfully Commander!");

        } catch (SQLException msg) {
            System.out.println("Error encountered when saving Soldier to database" + msg.getMessage());
        }

    }

    public void saveOfficer(Officer officer) throws SQLException {

        saveSoldier(officer);

        String query = "INSERT INTO Officer (UID, infantryCommandSkill, tankCommandSkill, artilleryCommandSkill, navyCommandSkill, logisticsCommandSkill) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement sql_insert = connection.prepareStatement(query);

        try{
            sql_insert.setInt(1, officer.getUID());
            sql_insert.setInt(2, officer.getInfantryCommandSkill());
            sql_insert.setInt(3, officer.getTankCommandSkill());
            sql_insert.setInt(4, officer.getArtilleryCommandSkill());
            sql_insert.setInt(5, officer.getNavyCommandSkill());
            sql_insert.setInt(6, officer.getLogisticsCommandSkill());


            sql_insert.executeUpdate();
            sql_insert.close();

            System.out.println("Officer insertion executed successfully Commander!");

        } catch (SQLException msg) {
            System.out.println("Error encountered when saving Officer to database" + msg.getMessage());
        }

    }

    public Soldier getSoldierByUID(int UID) {

        Soldier soldier = null;


        String query = "SELECT * FROM Soldier WHERE UID = ?";

        try (PreparedStatement sql_retrieval = connection.prepareStatement(query)) {

            sql_retrieval.setInt(1, UID);

            ResultSet resultSet = sql_retrieval.executeQuery();

            if (resultSet.next()) {

                int soldierUID = resultSet.getInt("UID");
                String username = resultSet.getString("username");
                String discordTag = resultSet.getString("discordTag");
                String steamURL = resultSet.getString("steamURL");
                String rankType = resultSet.getString("rankType");
                int rank = resultSet.getInt("s_rank");
                String specialization = resultSet.getString("specialization");
                int skill = resultSet.getInt("skill");

                soldier = new Soldier(soldierUID, username, discordTag, steamURL, rankType, rank, specialization, skill);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Error retrieving soldier: " + e.getMessage());
        }

        return soldier;

    }

    public Soldier getSoldierByName(String name) {

        Soldier soldier = null;


        String query = "SELECT * FROM Soldier WHERE username = ?";

        try (PreparedStatement sql_retrieval = connection.prepareStatement(query)) {

            sql_retrieval.setString(1, name);

            ResultSet resultSet = sql_retrieval.executeQuery();

            if (resultSet.next()) {

                int soldierUID = resultSet.getInt("UID");
                String username = resultSet.getString("username");
                String discordTag = resultSet.getString("discordTag");
                String steamURL = resultSet.getString("steamURL");
                String rankType = resultSet.getString("rankType");
                int rank = resultSet.getInt("s_rank");
                String specialization = resultSet.getString("specialization");
                int skill = resultSet.getInt("skill");

                soldier = new Soldier(soldierUID, username, discordTag, steamURL, rankType, rank, specialization, skill);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Error retrieving soldier: " + e.getMessage());
        }

        return soldier;

    }

    public void updateSoldier(Soldier newSoldier) {

        String query = "UPDATE Soldier SET username = ?, discordTag = ?, steamURL = ?, rankType = ?, " +
                "s_rank = ?, specialization = ?, skill = ? WHERE UID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newSoldier.getUsername());
            statement.setString(2, newSoldier.getDiscordTag());
            statement.setString(3, newSoldier.getSteamUrl());
            statement.setString(4, newSoldier.getRankType());
            statement.setInt(5, newSoldier.getRank());
            statement.setString(6, newSoldier.getSpecialization());
            statement.setInt(7, newSoldier.getSkill());
            statement.setInt(8, newSoldier.getUID());


            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Soldier updated successfully commander!");
            } else {
                System.out.println("No soldier found with UID: " + newSoldier.getUID());
            }

        } catch (SQLException e) {
            System.out.println("Error updating soldier: " + e.getMessage());
        }
    }

    public void deleteSoldierByUID(int UID) {
        String query = "DELETE FROM Soldier WHERE UID = ?";
        String officer_query = "DELETE FROM Officer WHERE UID = ?";

        try (PreparedStatement statement1 = connection.prepareStatement(query);
                PreparedStatement statement2 = connection.prepareStatement(officer_query)) {

            statement1.setInt(1, UID);
            statement2.setInt(1, UID);

            int rowsDeleted1 = statement1.executeUpdate();
            int rowsDeleted2 = statement2.executeUpdate();


            if (rowsDeleted1 > 0) {
                System.out.println("Soldier with UID " + UID + " deleted successfully commander!");
            } else {
                System.out.println("No soldier found with UID: " + UID);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting soldier: " + e.getMessage());
        }
    }

    public void deleteSoldierByName(String name) {
        String query = "DELETE FROM Soldier WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Soldier with name " + name + " deleted successfully commander!");
            } else {
                System.out.println("No soldier found with UID: " + name);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting soldier: " + e.getMessage());
        }
    }


}
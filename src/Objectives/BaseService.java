package Objectives;



import java.sql.*;

public class BaseService {

    private static BaseService instance;
    Connection connection;

    private BaseService() {

    }

    public static BaseService getInstance() {
        if (instance == null) {
            instance = new BaseService();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }

    public void saveBase(Base base) {

        String query = "INSERT INTO Base (baseID, hex, region, base_type, builderID, upgrades, garrisonSize, level, victoryPoint, garrisonHouses, mortarHouses, coastalGun) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, base.getBaseID());
            statement.setString(2, base.getHex());
            statement.setString(3, base.getRegion());
            statement.setString(4, base.getType());

            if (base instanceof FOB) {
                FOB fob = (FOB) base;
                statement.setInt(5, fob.getBuilder());
                statement.setString(6, fob.getUpgrades());
                statement.setInt(7, fob.getGarrisonSize());

                // Set null values for World Base specific fields
                statement.setNull(8, Types.INTEGER);
                statement.setNull(9, Types.BOOLEAN);
                statement.setNull(10, Types.INTEGER);
                statement.setNull(11, Types.INTEGER);
                statement.setNull(12, Types.BOOLEAN);
            } else if (base instanceof WorldBase) {
                WorldBase worldBase = (WorldBase) base;
                statement.setNull(5, Types.INTEGER);
                statement.setNull(6, Types.VARCHAR);
                statement.setNull(7, Types.INTEGER);

                statement.setInt(8, worldBase.getLevel());
                statement.setBoolean(9, worldBase.hasVictoryPoint());
                statement.setInt(10, worldBase.getGarrisonHouses());
                statement.setInt(11, worldBase.getMortarHouses());
                statement.setBoolean(12, worldBase.hasCoastalGun());
            }

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Base inserted successfully!");
            } else {
                System.out.println("Failed to insert base.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting base: " + e.getMessage());
        }
    }


    public Base getBaseByID(int baseID) {
        Base base = null;
        String query = "SELECT * FROM Base WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, baseID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hex = resultSet.getString("hex");
                String region = resultSet.getString("region");
                String baseType = resultSet.getString("base_type");

                if (baseType.equals("FOB")) {
                    int builderID = resultSet.getInt("builderID");
                    String upgrades = resultSet.getString("upgrades");
                    int garrisonSize = resultSet.getInt("garrisonSize");
                    base = new FOB(baseID, hex, region, baseType, builderID, upgrades, garrisonSize);
                } else if (baseType.equals("World Base")) {
                    int level = resultSet.getInt("level");
                    boolean victoryPoint = resultSet.getBoolean("victoryPoint");
                    int garrisonHouses = resultSet.getInt("garrisonHouses");
                    int mortarHouses = resultSet.getInt("mortarHouses");
                    boolean coastalGun = resultSet.getBoolean("coastalGun");
                    base = new WorldBase(baseID, hex, region, baseType, level, victoryPoint, garrisonHouses, mortarHouses, coastalGun);
                }
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving base: " + e.getMessage());
        }

        return base;
    }


    public void updateBase(Base newBase) {
        String query = "UPDATE Base SET hex = ?, region = ?, base_type = ?, builderID = ?, upgrades = ?, " +
                "garrisonSize = ?, level = ?, victoryPoint = ?, garrisonHouses = ?, mortarHouses = ?, coastalGun = ? " +
                "WHERE baseID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newBase.getHex());
            statement.setString(2, newBase.getRegion());
            statement.setString(3, newBase.getType());

            if (newBase instanceof FOB) {
                FOB fob = (FOB) newBase;
                statement.setInt(4, fob.getBuilder());
                statement.setString(5, fob.getUpgrades());
                statement.setInt(6, fob.getGarrisonSize());
                statement.setNull(7, Types.INTEGER);
                statement.setNull(8, Types.BOOLEAN);
                statement.setNull(9, Types.INTEGER);
                statement.setNull(10, Types.INTEGER);
                statement.setNull(11, Types.BOOLEAN);
            } else if (newBase instanceof WorldBase) {
                WorldBase worldBase = (WorldBase) newBase;
                statement.setNull(4, Types.INTEGER);
                statement.setNull(5, Types.VARCHAR);
                statement.setNull(6, Types.INTEGER);
                statement.setInt(7, worldBase.getLevel());
                statement.setBoolean(8, worldBase.hasVictoryPoint());
                statement.setInt(9, worldBase.getGarrisonHouses());
                statement.setInt(10, worldBase.getMortarHouses());
                statement.setBoolean(11, worldBase.hasCoastalGun());
            }

            statement.setInt(12, newBase.getBaseID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Base updated successfully commander!");
            } else {
                System.out.println("No base found with baseID: " + newBase.getBaseID());
            }

        } catch (SQLException e) {
            System.out.println("Error updating base: " + e.getMessage());
        }
    }


    public void deleteBaseByID(int baseID) {
        String query = "DELETE FROM Base WHERE baseID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, baseID);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Base with ID " + baseID + " has been successfully deleted!");
            } else {
                System.out.println("No base found with ID: " + baseID);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting base: " + e.getMessage());
        }
    }


}

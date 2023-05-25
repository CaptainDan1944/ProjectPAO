package Materiel;



import java.sql.*;

public class VehicleService {

    private static VehicleService instance;
    Connection connection;

    private VehicleService() {

    }

    public static VehicleService getInstance() {
        if (instance == null) {
            instance = new VehicleService();
        }
        return instance;
    }

    public void obtainConnection(Connection connection) {
        this.connection = connection;
    }


    public void saveVehicle(Vehicle vehicle) {

        String query = "INSERT INTO Vehicle (serialNumber, type, drivetrain, seats, fuelCapacity, " +
                "transportCapacity, cargoType, weaponry, ammunitionType, ammunitionCapacity, armor, crew) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, vehicle.getSerialNumber());
            statement.setString(2, vehicle.getType());
            statement.setString(3, vehicle.getDrivetrain());
            statement.setInt(4, vehicle.getSeats());
            statement.setInt(5, vehicle.getFuelCapacity());

            if (vehicle instanceof TransportVehicle) {
                TransportVehicle transportVehicle = (TransportVehicle) vehicle;
                statement.setInt(6, transportVehicle.getTransportCapacity());
                statement.setString(7, transportVehicle.getCargoType());
                statement.setNull(8, java.sql.Types.VARCHAR);
                statement.setNull(9, java.sql.Types.VARCHAR);
                statement.setNull(10, java.sql.Types.INTEGER);
                statement.setNull(11, java.sql.Types.VARCHAR);
                statement.setNull(12, java.sql.Types.INTEGER);
            }
            else if (vehicle instanceof CombatVehicle) {
                CombatVehicle combatVehicle = (CombatVehicle) vehicle;
                statement.setNull(6, java.sql.Types.INTEGER);
                statement.setNull(7, java.sql.Types.VARCHAR);
                statement.setString(8, combatVehicle.getWeaponry());
                statement.setString(9, combatVehicle.getAmmunitionType());
                statement.setInt(10, combatVehicle.getAmmunitionCapacity());
                statement.setString(11, combatVehicle.getArmor());
                statement.setInt(12, combatVehicle.getcrew());
            }

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Vehicle inserted successfully!");
            } else {
                System.out.println("Failed to insert vehicle.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting vehicle: " + e.getMessage());
        }
    }

    public Vehicle getVehicleBySerialNumber(int serialNumber) {
        Vehicle vehicle = null;

        String query = "SELECT * FROM Vehicle WHERE serialNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, serialNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String type = resultSet.getString("type");
                String drivetrain = resultSet.getString("drivetrain");
                int seats = resultSet.getInt("seats");
                int fuelCapacity = resultSet.getInt("fuelCapacity");

                if (resultSet.getString("transportCapacity") != null) {
                    int transportCapacity = resultSet.getInt("transportCapacity");
                    String cargoType = resultSet.getString("cargoType");

                    vehicle = new TransportVehicle(serialNumber, type, drivetrain, seats, fuelCapacity, transportCapacity, cargoType);
                } else if (resultSet.getString("weaponry") != null) {
                    String weaponry = resultSet.getString("weaponry");
                    String ammunitionType = resultSet.getString("ammunitionType");
                    int ammunitionCapacity = resultSet.getInt("ammunitionCapacity");
                    String armor = resultSet.getString("armor");
                    int crew = resultSet.getInt("crew");

                    vehicle = new CombatVehicle(serialNumber, type, drivetrain, seats, fuelCapacity, weaponry, ammunitionType, ammunitionCapacity, armor, crew);
                }
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving vehicle: " + e.getMessage());
        }

        return vehicle;
    }


    public void updateVehicle (Vehicle newVehicle) {
        String query = "UPDATE Vehicle SET type = ?, drivetrain = ?, seats = ?, fuelCapacity = ?, " +
                "transportCapacity = ?, cargoType = ?, weaponry = ?, ammunitionType = ?, " +
                "ammunitionCapacity = ?, armor = ?, crew = ? WHERE serialNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newVehicle.getType());
            statement.setString(2, newVehicle.getDrivetrain());
            statement.setInt(3, newVehicle.getSeats());
            statement.setInt(4, newVehicle.getFuelCapacity());

            if (newVehicle instanceof TransportVehicle) {
                TransportVehicle transportVehicle = (TransportVehicle) newVehicle;
                statement.setInt(5, transportVehicle.getTransportCapacity());
                statement.setString(6, transportVehicle.getCargoType());
                statement.setNull(7, Types.VARCHAR);
                statement.setNull(8, Types.VARCHAR);
                statement.setNull(9, Types.INTEGER);
                statement.setNull(10, Types.VARCHAR);
                statement.setNull(11, Types.INTEGER);
            } else if (newVehicle instanceof CombatVehicle) {
                CombatVehicle combatVehicle = (CombatVehicle) newVehicle;
                statement.setNull(5, Types.INTEGER);
                statement.setNull(6, Types.VARCHAR);
                statement.setString(7, combatVehicle.getWeaponry());
                statement.setString(8, combatVehicle.getAmmunitionType());
                statement.setInt(9, combatVehicle.getAmmunitionCapacity());
                statement.setString(10, combatVehicle.getArmor());
                statement.setInt(11, combatVehicle.getcrew());
            }

            statement.setInt(12, newVehicle.getSerialNumber());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Vehicle updated successfully commander!");
            } else {
                System.out.println("No vehicle found with serial number: " + newVehicle.getSerialNumber());
            }
        } catch (SQLException e) {
            System.out.println("Error updating vehicle: " + e.getMessage());
        }
    }

    public void deleteVehicleBySerialNumber(int serialNumber) {
        String query = "DELETE FROM Vehicle WHERE serialNumber = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, serialNumber);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Vehicle with serial number " + serialNumber + " scrapped successfully commander!");
            } else {
                System.out.println("No vehicle found with serial number: " + serialNumber);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting vehicle: " + e.getMessage());
        }
    }


}

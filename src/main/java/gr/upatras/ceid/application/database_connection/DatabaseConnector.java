package gr.upatras.ceid.application.database_connection;

import java.sql.*;

public abstract class DatabaseConnector {

    // ----------------------------------------- Instance Field ----------------------------------------

    protected final Connection connection;
    protected final Statement statement;
    private boolean successful;

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public DatabaseConnector() {
        connection = openConnection();
        statement = createStatement();
    }

    // Opens a connection with the database
    protected Connection openConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/StaffEvaluation", "admin", "admin");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    // Closes the connection with the database
    protected void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    // Creates a statement
    protected Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    // TODO needs comment
    protected boolean existsInDatabase(String table, String column, String key) {
        try {
            String query = "SELECT 1 FROM %s WHERE %s = '%s'".formatted(table, column, key);
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                return true;
            } else {
                setUnsuccessful();
                closeConnection();
                return false;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            closeConnection();
            return false;
        }
    }

    // TODO needs comment
    protected boolean existsInDatabase(String table, String column, Integer key) {
        try {
            String query = "SELECT 1 FROM %s WHERE %s = '%s'".formatted(table, column, key);
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                return true;
            } else {
                setUnsuccessful();
                closeConnection();
                return false;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            closeConnection();
            return false;
        }
    }

    // ----------------------------------------- Setter Methods ----------------------------------------

    protected void setSuccessful() {
        successful = true;
    }

    protected void setUnsuccessful() {
        successful = false;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public boolean isSuccessful() {
        return successful;
    }

}

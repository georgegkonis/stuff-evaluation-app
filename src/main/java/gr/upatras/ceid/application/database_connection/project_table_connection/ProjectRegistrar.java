package gr.upatras.ceid.application.database_connection.project_table_connection;

import application.database_connection.DatabaseConnector;

import java.sql.SQLException;

public class ProjectRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Registers a new project in the database
    public void registerEmployeeProject(String employeeUsername, String description, String url) {
        try {
            String query = "INSERT INTO project (employee_username, description, url) VALUES ('%s', '%s', '%s');".formatted(employeeUsername, description, url);
            statement.executeUpdate(query);
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

}

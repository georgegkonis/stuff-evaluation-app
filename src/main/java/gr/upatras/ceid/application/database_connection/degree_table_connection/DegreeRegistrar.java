package gr.upatras.ceid.application.database_connection.degree_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Degree.Level;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Year;

public class DegreeRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // TODO needs comment
    public void registerEmployeeDegree(String employeeUsername, String title, String institution, Level level, Year gradYear, Float grade) {
        registerDegree(title, institution, level);
        try {
            String query = "INSERT INTO employee_degree (employee_username, degree_title, degree_institution, grad_year, grade) VALUES ('%s', '%s', '%s', '%s', '%s')".formatted(employeeUsername, title, institution, gradYear, grade);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // TODO needs comment
    public void registerDegree(String title, String institution, Level level) {
        try {
            String query = "INSERT INTO degree (title, institution, level) VALUES ('%s', '%s', '%s');".formatted(title, institution, level);
            statement.executeUpdate(query);
        } catch (SQLIntegrityConstraintViolationException exception) {
            System.out.printf("Tried to insert a duplicate key: %s %s%n", title, institution);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}

package gr.upatras.ceid.application.database_connection.language_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Employee.Language;

import java.sql.SQLException;

public class LanguageRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // TODO needs comment
    public void registerEmployeeLanguage(String employeeUsername, Language language) {
        try {
            String query = "INSERT INTO employee_language (employee_username, language) VALUES ('%s', '%s');".formatted(employeeUsername, language);
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

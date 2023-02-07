package gr.upatras.ceid.application.database_connection.language_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Employee.Language;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static application.Constants.EMPLOYEE_LANGUAGE;

public class LanguageLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // TODO needs comment
    public List<Language> loadEmployeeLanguages(String employeeUsername) {
        List<Language> languages = new ArrayList<>();
        try {
            String query = "SELECT language FROM employee_language WHERE employee_username = '%s';".formatted(employeeUsername);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Language language = Language.valueOf(resultSet.getString(EMPLOYEE_LANGUAGE.LANGUAGE));
                languages.add(language);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return languages;
    }

}

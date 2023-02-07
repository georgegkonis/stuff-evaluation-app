package gr.upatras.ceid.application.database_connection.project_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static application.Constants.PROJECT;

public class ProjectLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads all the project with the matching employee username from the database and adds them to a list
    public List<Project> loadEmployeeProjects(String employee_username) {
        List<Project> projects = new ArrayList<>();
        try {
            String query = "SELECT * FROM project WHERE employee_username = '%s'".formatted(employee_username);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                projects.add(createProjectModel(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return projects;
    }

    // Creates an object of the project class
    private Project createProjectModel(ResultSet result) throws SQLException {
        Integer idNum = result.getInt(PROJECT.ID_NUM);
        String employeeUsername = result.getString(PROJECT.EMPLOYEE_USERNAME);
        String description = result.getString(PROJECT.DESCRIPTION);
        String url = result.getString(PROJECT.URL);
        return new Project(idNum, employeeUsername, description, url);
    }

}

package gr.upatras.ceid.application.database_connection.user_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;
import gr.upatras.ceid.application.database_connection.user_table_connection.admin_table_connection.AdminLoader;
import gr.upatras.ceid.application.database_connection.user_table_connection.employee_table_connection.EmployeeLoader;
import gr.upatras.ceid.application.database_connection.user_table_connection.evaluator_table_connection.EvaluatorLoader;
import gr.upatras.ceid.application.database_connection.user_table_connection.manager_table_connection.ManagerLoader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import static application.database_models.User.*;
import static application.user_interface.ControllerUtilities.setLoggedUser;

public class UserLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads the user with the matching username and password from the database
    public void loadUser(String username, String password) {
        try {
            String query = "SELECT user_type FROM user WHERE username = '%s' AND password = '%s';".formatted(username, password);
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                UserType userType = UserType.valueOf(result.getString("user_type").toUpperCase(Locale.ROOT));
                switch (userType) {
                    case ADMIN -> {
                        AdminLoader adminLoader = new AdminLoader();
                        setLoggedUser(adminLoader.loadUserAsAdmin(username));
                    }
                    case MANAGER -> {
                        ManagerLoader managerLoader = new ManagerLoader();
                        setLoggedUser(managerLoader.loadUserAsManager(username));
                    }
                    case EVALUATOR -> {
                        EvaluatorLoader evaluatorLoader = new EvaluatorLoader();
                        setLoggedUser(evaluatorLoader.loadUserAsEvaluator(username));
                    }
                    case EMPLOYEE -> {
                        EmployeeLoader employeeLoader = new EmployeeLoader();
                        setLoggedUser(employeeLoader.loadUserAsEmployee(username));
                    }
                }
                updateLoggedUser(username);
                setSuccessful();
            } else {
                setUnsuccessful();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

    // Updates the current
    private void updateLoggedUser(String username) throws SQLException {
        String query = "UPDATE logged_user SET user_username = '%s';".formatted(username);
        statement.executeUpdate(query);
    }

}

package gr.upatras.ceid.application.database_connection.user_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;
import gr.upatras.ceid.application.database_connection.user_table_connection.employee_table_connection.EmployeeRegistrar;
import gr.upatras.ceid.application.database_connection.user_table_connection.evaluator_table_connection.EvaluatorRegistrar;
import gr.upatras.ceid.application.database_connection.user_table_connection.manager_table_connection.ManagerRegistrar;
import application.database_models.User.UserType;

import java.sql.SQLException;

public class UserRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Registers a new user in the database
    public void registerUser(UserType userType, String username, String password, String firstName, String lastName, String email, String company, Integer expYears) {
        try {
            String query = "INSERT INTO user (username, password, first_name, last_name, email) VALUES ('%s', '%s', '%s', '%s', '%s');".formatted(username, password, firstName, lastName, email);
            statement.executeUpdate(query);
            switch (userType) {
                case EMPLOYEE -> {
                    EmployeeRegistrar employeeRegistrar = new EmployeeRegistrar();
                    employeeRegistrar.registerEmployee(username, company, expYears);
                }
                case EVALUATOR -> {
                    EvaluatorRegistrar evaluatorRegistrar = new EvaluatorRegistrar();
                    evaluatorRegistrar.registerEvaluator(username, company, expYears);
                }
                case MANAGER -> {
                    ManagerRegistrar managerRegistrar = new ManagerRegistrar();
                    managerRegistrar.registerManager(username, company, expYears);
                }
            }
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

}

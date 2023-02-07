package gr.upatras.ceid.application.database_connection.user_table_connection.employee_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;

import java.sql.SQLException;

public class EmployeeRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Registers a new employee in the database
    public void registerEmployee(String username, String company, Integer expYears) throws SQLException {
        String query = "INSERT INTO employee (user_username, company_vat_num, exp_years) VALUES ('%s', '%s', '%s');".formatted(username, company, expYears);
        statement.executeUpdate(query);
    }
}

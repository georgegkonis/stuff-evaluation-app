package gr.upatras.ceid.application.database_connection.user_table_connection.manager_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;

import java.sql.SQLException;

public class ManagerRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Registers a new manager in the database
    public void registerManager(String username, String company, Integer expYears) throws SQLException {
        String query = "INSERT INTO manager (user_username, company_vat_num, exp_years) VALUES ('%s', '%s', '%s');".formatted(username, company, expYears);
        statement.executeUpdate(query);
    }
}

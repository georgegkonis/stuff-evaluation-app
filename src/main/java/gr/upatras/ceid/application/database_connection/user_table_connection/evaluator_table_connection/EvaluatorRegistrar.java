package gr.upatras.ceid.application.database_connection.user_table_connection.evaluator_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;

import java.sql.SQLException;

public class EvaluatorRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Registers a new evaluator in the database
    public void registerEvaluator(String username, String company, Integer expYears) throws SQLException {
        String query = "INSERT INTO evaluator (user_username, company_vat_num, exp_years) VALUES ('%s', '%s', '%s');".formatted(username, company, expYears);
        statement.executeUpdate(query);
    }
}

package gr.upatras.ceid.application.database_connection.evaluation_table_connection;

import application.database_connection.DatabaseConnector;

import java.sql.SQLException;

public class EvaluationRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // TODO needs comment
    public void registerEvaluation(Integer positionIdNum, String employeeUsername) {
        try {
            String query = "INSERT INTO evaluation (position_id_num, employee_username) VALUES ('%s', '%s');".formatted(positionIdNum, employeeUsername);
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

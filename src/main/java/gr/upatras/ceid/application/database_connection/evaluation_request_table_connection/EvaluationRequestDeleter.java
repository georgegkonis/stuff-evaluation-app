package gr.upatras.ceid.application.database_connection.evaluation_request_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Employee;

import java.sql.SQLException;

public class EvaluationRequestDeleter extends DatabaseConnector {

    // TODO needs comment
    public void deleteEvaluationRequest(Employee employee, Integer positionIdNum) {
        try {
            String query = "DELETE FROM employee_position WHERE employee_username = '%s';".formatted(employee.getUsername());
            statement.executeUpdate(query);
            deleteEvaluation(employee.getUsername(), positionIdNum);
            employee.loadPositions();
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

    // TODO needs comment
    private void deleteEvaluation(String employeeUsername, Integer positionIdNum) throws SQLException {
        String query = "DELETE FROM evaluation WHERE employee_username = '%s' AND position_id_num = '%s';".formatted(employeeUsername, positionIdNum);
        statement.executeUpdate(query);
    }

}

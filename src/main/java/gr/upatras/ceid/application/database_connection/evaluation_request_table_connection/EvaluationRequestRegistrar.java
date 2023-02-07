package gr.upatras.ceid.application.database_connection.evaluation_request_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_connection.evaluation_table_connection.EvaluationRegistrar;
import application.database_models.Employee;

import java.sql.SQLException;

public class EvaluationRequestRegistrar extends DatabaseConnector {

    // TODO needs comment
    public void registerEvaluationRequest(Employee employee, Integer positionIdNum) {
        try {
            String query = "INSERT INTO employee_position (employee_username, position_id_num) VALUES ('%s', '%s');".formatted(employee.getUsername(), positionIdNum);
            statement.executeUpdate(query);
            registerEvaluation(employee.getUsername(), positionIdNum);
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
    private void registerEvaluation(String employeeUsername, Integer positionIdNum) {
        EvaluationRegistrar evaluationRegistrar = new EvaluationRegistrar();
        evaluationRegistrar.registerEvaluation(positionIdNum, employeeUsername);
    }

}

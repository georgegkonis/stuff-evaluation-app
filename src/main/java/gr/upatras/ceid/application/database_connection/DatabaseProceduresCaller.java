package gr.upatras.ceid.application.database_connection;

import java.sql.SQLException;

public class DatabaseProceduresCaller extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // TODO needs comment
    public void callEvaluationStatusProcedure(Integer jobIdNum) {
        try {
            String query = "CALL evaluation_status(%s);".formatted(jobIdNum);
            statement.executeQuery(query);
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }



}

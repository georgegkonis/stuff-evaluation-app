package gr.upatras.ceid.application.database_connection.position_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_connection.field_table_connection.FieldRegistrar;
import application.database_models.Field;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PositionRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Registers a new position in the database
    public void registerPosition(String evaluatorUsername, String companyVatNum, Date startDate, Date submissionDate, Float salary, String position, String headquarters, List<Field> fields) {
        try {
            String query = "INSERT INTO position (evaluator_username, company_vat_num, start_date, submission_date, salary, title, headquarters) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');".formatted(evaluatorUsername, companyVatNum, startDate, submissionDate, salary, position, headquarters);
            statement.executeUpdate(query);
            registerPositionFields(fields);
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

    // TODO needs comment
    private void registerPositionFields(List<Field> positionFields) throws SQLException {
        Integer positionIdNum = findPositionId();
        for (Field positionField : positionFields) {
            FieldRegistrar fieldRegistrar = new FieldRegistrar();
            fieldRegistrar.registerPositionField(positionIdNum, positionField.getTitle());
        }
    }

    // TODO needs comment
    private Integer findPositionId() throws SQLException {
        String query = "SELECT MAX(id_num) FROM position;";
        ResultSet result = statement.executeQuery(query);
        result.next();
        return result.getInt(1);
    }

}

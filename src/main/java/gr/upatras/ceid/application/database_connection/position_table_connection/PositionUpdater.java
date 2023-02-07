package gr.upatras.ceid.application.database_connection.position_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_connection.field_table_connection.FieldRegistrar;
import application.database_models.Field;
import application.database_models.Position;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PositionUpdater extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Updates the specified position in the database
    public void updatePosition(Position position, Date startDate, Date submissionDate, Float salary, String title, String headquarters, List<Field> fields) {
        if (existsInDatabase("position", "id_num", position.getIdNum())) {
            try {
                String query = "UPDATE position SET start_date = '%s', submission_date = '%s', salary = '%s', title = '%s', headquarters = '%s' WHERE id_num = '%s';".formatted(startDate, submissionDate, salary, title, headquarters, position.getIdNum());
                statement.executeUpdate(query);
                updatePositionFields(position.getIdNum(), fields);
                position.setStartDate(startDate);
                position.setSubmissionDate(submissionDate);
                position.setSalary(salary);
                position.setTitle(title);
                position.setHeadquarters(headquarters);
                position.loadFields();
                setSuccessful();
            } catch (SQLException exception) {
                exception.printStackTrace();
                setUnsuccessful();
            } finally {
                closeConnection();
            }
        }
    }

    // Updates the specified position in the database
    public void updatePosition(Position position, Float salary) {
        if (existsInDatabase("position", "id_num", position.getIdNum())) {
            try {
                String query = "UPDATE position SET salary = '%s' WHERE id_num = '%s';".formatted(salary, position.getIdNum());
                statement.executeUpdate(query);
                position.setSalary(salary);
                setSuccessful();
            } catch (SQLException exception) {
                exception.printStackTrace();
                setUnsuccessful();
            } finally {
                closeConnection();
            }
        }
    }

    // TODO needs comment
    private void updatePositionFields(Integer positionIdNum, List<Field> positionFields) throws SQLException {
        String query = "DELETE FROM position_field WHERE job_id_num = '%s';".formatted(positionIdNum);
        statement.executeUpdate(query);
        for (Field positionField : positionFields) {
            FieldRegistrar fieldRegistrar = new FieldRegistrar();
            fieldRegistrar.registerPositionField(positionIdNum, positionField.getTitle());
        }
    }

}

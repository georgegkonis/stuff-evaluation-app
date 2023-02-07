package gr.upatras.ceid.application.database_connection.field_table_connection;

import application.database_connection.DatabaseConnector;

import java.sql.SQLException;

public class FieldRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Registers a new job field in the database
    public void registerField(String title, String description, String parentTitle) {
        try {
            String query;
            if (parentTitle == null) {
                query = "INSERT INTO field (title, description) VALUES ('%s', '%s');".formatted(title, description);
            } else {
                query = "INSERT INTO field (title, parent_title, description) VALUES ('%s', '%s', '%s');".formatted(title, parentTitle, description);
            }
            statement.executeUpdate(query);
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

    // TODO needs comment
    public void registerPositionField(Integer positionIdNum, String title) {
        try {
            String query = "INSERT INTO position_field (job_id_num, field_title) VALUES ('%s', '%s');".formatted(positionIdNum, title);
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

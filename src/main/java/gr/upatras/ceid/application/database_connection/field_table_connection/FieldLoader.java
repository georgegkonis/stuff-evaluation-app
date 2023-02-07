package gr.upatras.ceid.application.database_connection.field_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Field;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static application.Constants.FIELD;

public class FieldLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    /*
    // Loads the job field with the matching title from the database
    public Field loadField(String title) {
        try {
            String query = "SELECT * FROM field WHERE title = '%s';".formatted(title);
            ResultSet result = openConnection().createStatement().executeQuery(query);
            if (result.next()) {
                return createFieldModel(result);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }
    */ // TODO

    // Loads all the job fields from the database and adds them to an arraylist
    public List<Field> loadAllFields() {
        List<Field> fields = new ArrayList<>();
        try {
            String query = "SELECT * FROM field;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                fields.add(createFieldModel(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return fields;
    }

    // Loads all the fields of a position from the database and adds them to an arraylist
    public List<Field> loadPositionFields(Integer positionIdNum) {
        List<Field> fields = new ArrayList<>();
        try {
            String query = "SELECT * FROM position_field_view WHERE job_id_num = %s;".formatted(positionIdNum);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                fields.add(createFieldModel(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return fields;
    }

    // Creates an object of the job field class
    private Field createFieldModel(ResultSet result) throws SQLException {
        String title = result.getString(FIELD.TITLE);
        String parentTitle = result.getString(FIELD.PARENT_TITLE);
        String description = result.getString(FIELD.DESCRIPTION);
        /*
        if (!(parentTitle == null)) {
            for (Field field : fields) {
                if (field.getTitle().equals(parentTitle)) {
                    return new Field(title, description, field);
                }
            }
            return new Field(title, description, loadField(parentTitle));
        }
        */ // TODO
        return new Field(title, description, parentTitle);
    }

}

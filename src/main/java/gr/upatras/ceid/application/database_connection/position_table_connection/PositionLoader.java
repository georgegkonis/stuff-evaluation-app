package gr.upatras.ceid.application.database_connection.position_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Position;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static application.Constants.POSITION;

public class PositionLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads all the positions with the matching company vat number from the database
    public List<Position> loadCompanyPositions(String companyVatNum) {
        List<Position> positions = new ArrayList<>();
        try {
            String query = "SELECT * FROM position WHERE company_vat_num = '%s';".formatted(companyVatNum);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                positions.add(createPositionModel(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return positions;
    }

    // Loads all the positions with the matching employee username from the database
    public List<Position> loadEvaluatorPositions(String evaluatorUsername) {
        List<Position> positions = new ArrayList<>();
        try {
            String query = "SELECT * FROM position WHERE evaluator_username = '%s';".formatted(evaluatorUsername);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                positions.add(createPositionModel(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return positions;
    }

    // Loads all the positions with the matching employee username from the database
    public List<Position> loadEmployeePositions(String employeeUsername) {
        List<Position> positions = new ArrayList<>();
        try {
            String query = "SELECT * FROM employee_position_view WHERE employee_username = '%s';".formatted(employeeUsername);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                positions.add(createPositionModel(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return positions;
    }

    // Creates an object of the position class
    private Position createPositionModel(ResultSet result) throws SQLException {
        Integer idNum = result.getInt(POSITION.ID_NUM);
        String evaluatorUsername = result.getString(POSITION.EVALUATOR_USERNAME);
        String companyVatNum = result.getString(POSITION.COMPANY_VAT_NUM);
        Timestamp announcementTimestamp = result.getTimestamp(POSITION.ANNOUNCEMENT_DATE);
        Date startDate = result.getDate(POSITION.START_DATE);
        Date submissionDate = result.getDate(POSITION.SUBMISSION_DATE);
        Float salary = result.getFloat(POSITION.SALARY);
        String title = result.getString(POSITION.TITLE);
        String headquarters = result.getString(POSITION.HEADQUARTERS);
        return new Position(idNum, evaluatorUsername, companyVatNum, announcementTimestamp, startDate, submissionDate, salary, title, headquarters);
    }
}

package gr.upatras.ceid.application.database_connection.degree_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Degree;
import application.database_models.Degree.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static application.Constants.DEGREE;
import static application.Constants.EMPLOYEE_DEGREE;

public class DegreeLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // TODO needs comment
    public List<Degree> loadAllDegrees() {
        List<Degree> degrees = new ArrayList<>();
        try {
            String query = "SELECT * FROM degree;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                degrees.add(createDegreeModel(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return degrees;
    }

    // TODO needs comment
    public List<Degree> loadEmployeeDegrees(String employeeUsername) {
        List<Degree> degrees = new ArrayList<>();
        try {
            String query = "SELECT * FROM employee_degree_view WHERE employee_username = '%s';".formatted(employeeUsername);
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                degrees.add(createEmployeeDegreeModel(result));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return degrees;
    }

    // TODO needs comment
    private Degree createDegreeModel(ResultSet result) throws SQLException {
        String title = result.getString(DEGREE.TITLE);
        String institution = result.getString(DEGREE.INSTITUTION);
        Level level = Level.valueOf(result.getString(DEGREE.LEVEL));
        return new Degree(title, institution, level);
    }

    // TODO needs comment
    private Degree createEmployeeDegreeModel(ResultSet result) throws SQLException {
        String title = result.getString(DEGREE.TITLE);
        String institution = result.getString(DEGREE.INSTITUTION);
        Level level = Level.valueOf(result.getString(DEGREE.LEVEL));
        Float grade = result.getFloat(EMPLOYEE_DEGREE.GRADE);
        Year gradYear = Year.of(result.getInt(EMPLOYEE_DEGREE.GRAD_YEAR));
        return new Degree(title, institution, level, grade, gradYear);
    }

}

package gr.upatras.ceid.application.database_connection.evaluation_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Evaluation;
import application.database_models.Evaluation.EvaluationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvaluationLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // TODO needs comment
    public List<Evaluation> loadPositionEvaluations(Integer positionIdNum) {
        List<Evaluation> evaluations = new ArrayList<>();
        try {
            String query = "SELECT * FROM evaluation WHERE position_id_num = '%s';".formatted(positionIdNum);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                evaluations.add(createEvaluationModel(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return evaluations;
    }

    // TODO needs comment
    public List<Evaluation> loadEmployeeEvaluations(String employeeUsername) {
        List<Evaluation> evaluations = new ArrayList<>();
        try {
            String query = "SELECT * FROM evaluation WHERE employee_username = '%s';".formatted(employeeUsername);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                evaluations.add(createEvaluationModel(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return evaluations;
    }

    // TODO needs comment
    private Evaluation createEvaluationModel(ResultSet resultSet) throws SQLException {
        Integer idNum = resultSet.getInt("id_num");
        Integer positionIdNum = resultSet.getInt("position_id_num");
        String employeeUsername = resultSet.getString("employee_username");
        String comment = resultSet.getString("comment");
        Integer resumeGrade = resultSet.getInt("resume_grade");
        Integer evaluatorGrade = resultSet.getInt("interview_grade");
        Integer managerGrade = resultSet.getInt("manager_grade");
        Integer finalGrade = resultSet.getInt("final_grade");
        EvaluationStatus status = EvaluationStatus.valueOf(resultSet.getString("status"));
        return new Evaluation(idNum, positionIdNum, employeeUsername, comment, resumeGrade, evaluatorGrade, managerGrade, finalGrade, status);
    }

}

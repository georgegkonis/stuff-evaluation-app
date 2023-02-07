package gr.upatras.ceid.application.database_connection.evaluation_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Evaluation;

import java.sql.SQLException;

public class EvaluationUpdater extends DatabaseConnector {

    // TODO
    public void updateEvaluation(Evaluation evaluation, String comment, Integer resumeGrade, Integer interviewGrade) {
        try {
            String query = "UPDATE evaluation SET comment = '%s', resume_grade = '%s', interview_grade = '%s' WHERE id_num = '%s';".formatted(comment, resumeGrade, interviewGrade, evaluation.getIdNum());
            statement.executeUpdate(query);
            evaluation.setComment(comment);
            evaluation.setResumeGrade(resumeGrade);
            evaluation.setInterviewGrade(interviewGrade);
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

    // TODO
    public void updateEvaluation(Evaluation evaluation, Integer managerGrade) {
        try {
            String query = "UPDATE evaluation SET manager_grade = '%s' WHERE id_num = '%s';".formatted(managerGrade, evaluation.getIdNum());
            statement.executeUpdate(query);
            evaluation.setManagerGrade(managerGrade);
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

}

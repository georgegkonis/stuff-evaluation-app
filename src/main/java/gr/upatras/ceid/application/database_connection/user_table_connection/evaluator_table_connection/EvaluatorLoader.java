package gr.upatras.ceid.application.database_connection.user_table_connection.evaluator_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;
import application.database_models.Evaluator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EvaluatorLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads the evaluator with the matching username from the database
    public Evaluator loadUserAsEvaluator(String username) throws SQLException {
        Evaluator evaluator;
        String query = "SELECT * FROM user_evaluator_view WHERE username = '%s' AND user_username = '%s';".formatted(username, username);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) evaluator = createEvaluatorModel(resultSet);
        else throw new SQLException();
        closeConnection();
        return evaluator;
    }

    // TODO needs comment
    public List<Evaluator> loadCompanyEvaluators(String companyVatNum) {
        List<Evaluator> companyEvaluators = new ArrayList<>();
        try {
            String query = "SELECT * FROM user_evaluator_view WHERE company_vat_num = '%s';".formatted(companyVatNum);
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                companyEvaluators.add(createEvaluatorModel(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return companyEvaluators;
    }

    // TODO needs comment
    private Evaluator createEvaluatorModel(ResultSet resultSet) throws SQLException {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        Timestamp regDate = resultSet.getTimestamp("reg_date");
        String companyVatNum = resultSet.getString("company_vat_num");
        Integer expYears = resultSet.getInt("exp_years");
        return new Evaluator(username, password, firstName, lastName, email, regDate, companyVatNum, expYears);
    }

}

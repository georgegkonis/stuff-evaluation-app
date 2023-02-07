package gr.upatras.ceid.application.database_connection.user_table_connection.manager_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;
import application.database_models.Employee;
import application.database_models.Evaluator;
import application.database_models.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ManagerLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads the manager with the matching username from the database
    public Manager loadUserAsManager(String username) throws SQLException {
        Manager manager;
        String query = "SELECT * FROM user_manager_view WHERE username = '%s' AND user_username = '%s';".formatted(username, username);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) manager = createManagerModel(resultSet);
        else throw new SQLException();
        closeConnection();
        return manager;
    }

    // TODO needs comment
    private Manager createManagerModel(ResultSet resultSet) throws SQLException {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        Timestamp regDate = resultSet.getTimestamp("reg_date");
        String companyVatNum = resultSet.getString("company_vat_num");
        Integer expYears = resultSet.getInt("exp_years");
        return new Manager(username, password, firstName, lastName, email, regDate, companyVatNum, expYears);
    }

}

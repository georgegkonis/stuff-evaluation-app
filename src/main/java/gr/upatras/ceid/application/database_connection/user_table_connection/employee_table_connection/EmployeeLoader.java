package gr.upatras.ceid.application.database_connection.user_table_connection.employee_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;
import application.database_models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EmployeeLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads the employee with the matching username from the database
    public Employee loadUserAsEmployee(String username) throws SQLException {
        Employee employee;
        String query = "SELECT * FROM user_employee_view WHERE username = '%s' AND user_username = '%s';".formatted(username, username);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) employee = createEmployeeModel(resultSet);
        else throw new SQLException();
        closeConnection();
        return employee;
    }

    // TODO needs comment
    public List<Employee> loadCompanyEmployees(String companyVatNum) {
        List<Employee> companyEmployees = new ArrayList<>();
        try {
            String query = "SELECT * FROM user_employee_view WHERE company_vat_num = '%s';".formatted(companyVatNum);
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                companyEmployees.add(createEmployeeModel(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnection();
        }
        return companyEmployees;
    }

    // TODO needs comment
    private Employee createEmployeeModel(ResultSet resultSet) throws SQLException {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        Timestamp regDate = resultSet.getTimestamp("reg_date");
        String companyVatNum = resultSet.getString("company_vat_num");
        Integer expYears = resultSet.getInt("exp_years");
        String resume = resultSet.getString("resume");
        String awards = resultSet.getString("awards");
        String certificates = resultSet.getString("certificates");
        String references = resultSet.getString("references");
        return new Employee(username, password, firstName, lastName, email, regDate, companyVatNum, expYears, resume, awards, certificates, references);
    }

}

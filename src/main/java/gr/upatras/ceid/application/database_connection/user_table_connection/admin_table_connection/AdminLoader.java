package gr.upatras.ceid.application.database_connection.user_table_connection.admin_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;
import application.database_models.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AdminLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads the admin with the matching username from the database
    public Admin loadUserAsAdmin(String username) throws SQLException {
        Admin admin;
        String query = "SELECT * FROM user_admin_view WHERE username = '%s' AND user_username = '%s';".formatted(username, username);
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) admin = createAdminModel(resultSet);
        else throw new SQLException();
        closeConnection();
        return admin;
    }

    // Creates a model fot th
    private Admin createAdminModel(ResultSet resultSet) throws SQLException {
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        Timestamp regDate = resultSet.getTimestamp("reg_date");
        return new Admin(username, password, firstName, lastName, email, regDate);
    }

}

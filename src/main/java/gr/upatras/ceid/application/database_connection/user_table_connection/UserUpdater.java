package gr.upatras.ceid.application.database_connection.user_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;
import application.database_models.User;

import java.sql.SQLException;

public class UserUpdater extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Updates the specified user in the database
    public void updateUser(User user, String newPassword, String newFirstName, String newLastName, String newEmail) {
        if (existsInDatabase("user", "username", user.getUsername())) {
            try {
                String query = "UPDATE user SET password = '%s', first_name = '%s', last_name = '%s', email = '%s' WHERE username = '%s';".formatted(newPassword, newFirstName, newLastName, newEmail, user.getUsername());
                statement.executeUpdate(query);
                user.setPassword(newPassword);
                user.setFirstName(newFirstName);
                user.setLastName(newLastName);
                user.setEmail(newEmail);
                setSuccessful();
            } catch (SQLException exception) {
                exception.printStackTrace();
                setUnsuccessful();
            } finally {
                closeConnection();
            }
        }
    }

}


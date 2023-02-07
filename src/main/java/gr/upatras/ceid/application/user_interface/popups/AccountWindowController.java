package gr.upatras.ceid.application.user_interface.popups;

import gr.upatras.ceid.application.database_connection.user_table_connection.UserUpdater;
import gr.upatras.ceid.application.database_models.User;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountWindowController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private TextField regDateField, firsNameField, lastNameField, usernameField, emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label resultLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    // Initializes the scene
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = getLoggedUser();
        resultLabel.setText("");
        regDateField.setText(String.valueOf(user.getRegDate()));
        firsNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
        passwordField.setText(user.getPassword());
        enableFields(user.getUserType());
        disableSpaceKeystroke(usernameField);
        disableSpaceKeystroke(passwordField);
        disableSpaceKeystroke(emailField);
    }

    private void enableFields(User.UserType userType) {
        switch (userType) {
            case ADMIN, EVALUATOR -> {
                firsNameField.setDisable(false);
                lastNameField.setDisable(false);
                emailField.setDisable(false);
                passwordField.setDisable(false);
            }
            case MANAGER -> {
                emailField.setDisable(false);
                passwordField.setDisable(false);
            }
            case EMPLOYEE -> passwordField.setDisable(false);
        }
    }

    private boolean fieldsAreEmpty() {
        if ((usernameField.getText().isBlank())) return true;
        else if ((passwordField.getText().isBlank())) return true;
        else if ((emailField.getText().isBlank())) return true;
        else if ((firsNameField.getText().isBlank())) return true;
        else return lastNameField.getText().isBlank();
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void saveChangesButtonClicked() {
        if (!fieldsAreEmpty()) {
            User user = getLoggedUser();
            String password = passwordField.getText();
            String firstName = firsNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            UserUpdater userUpdater = new UserUpdater();
            userUpdater.updateUser(user, password, firstName, lastName, email);
            popUpWindow.close();
        } else {
            resultLabel.setText("Fill out all the necessary fields!");
            resultLabel.setTextFill(Color.RED);
        }
    }
}

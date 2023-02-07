package gr.upatras.ceid.application.user_interface;

import gr.upatras.ceid.application.database_connection.user_table_connection.UserLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private Label loginLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableSpaceKeystroke(usernameField);
        disableSpaceKeystroke(passwordField);
    }

    private boolean fieldsAreEmpty() {
        if ((usernameField.getText().isBlank())) return true;
        else return passwordField.getText().isBlank();
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void loginButtonClicked() throws IOException {
        if (fieldsAreEmpty()) {
            loginLabel.setText("Please enter your username and password!");
        } else {
            String inputUsername = usernameField.getText();
            String inputPassword = passwordField.getText();
            UserLoader login = new UserLoader();
            login.loadUser(inputUsername, inputPassword);
            if (login.isSuccessful()) {
                mainWindow = loadWindow("MainWindow", 1280, 720);
                loginWindow.close();
            } else
                loginLabel.setText("Incorrect credentials");
        }
    }
}

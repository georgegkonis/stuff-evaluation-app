package gr.upatras.ceid.application.user_interface;

import gr.upatras.ceid.application.database_models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Label userTypeLabel, firstNameLabel, lastNameLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    // Initializes the scene
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = getLoggedUser();
        setDashboard(user.getUserType().toString());
        userTypeLabel.setText(user.getUserType().toString());
        firstNameLabel.setText(user.getFirstName());
        lastNameLabel.setText(user.getLastName());
    }

    // Sets the user-userType specific dashboard
    private void setDashboard(String userType) {
        Node dashboard = loadNode(userType.toLowerCase() + "/" + userType.toLowerCase() + "Dashboard.fxml");
        mainBorderPane.setCenter(dashboard);
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void accountButtonClicked() throws IOException {
        showPopUpWindow(new Stage(), "accountWindow", "Account", 360, 720);
    }

    public void logoutButtonClicked() throws IOException {
        loginWindow = loadWindow("LoginWindow", 360, 420);
        mainWindow.close();
    }

}

package gr.upatras.ceid.application.user_interface.popups;

import gr.upatras.ceid.application.database_models.Project;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectWindowController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField urlField;
    @FXML
    private Label resultLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
    }

    private boolean fieldsAreEmpty() {
        if ((descriptionArea.getText().isBlank())) return true;
        else return urlField.getText().isBlank();
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void addProjectButtonClicked(ActionEvent actionEvent) {
        if (!fieldsAreEmpty()) {
            String username = getLoggedEmployee().getUsername();
            String description = descriptionArea.getText();
            String url = urlField.getText();
            Project project = new Project(0, username, description, url);
            getLoggedEmployee().getProjects().add(project);
            popUpWindow.close();
        } else {
            resultLabel.setText("Fill out all the necessary fields!");
            resultLabel.setTextFill(Color.RED);
        }
    }

}

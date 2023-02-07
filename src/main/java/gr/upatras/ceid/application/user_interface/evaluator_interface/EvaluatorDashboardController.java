package gr.upatras.ceid.application.user_interface.evaluator_interface;

import gr.upatras.ceid.application.database_models.Evaluator;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EvaluatorDashboardController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private BorderPane evaluatorBorderPane;
    @FXML
    private Label companyNameLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Evaluator evaluator = getLoggedEvaluator();
        companyNameLabel.setText(evaluator.getCompany().getName());
    }


    // --------------------------------------- On Action Methods ---------------------------------------

    public void createJobPositionButtonAction(ActionEvent actionEvent) {
        evaluatorBorderPane.setCenter(loadNode("evaluator/NewPositionPage.fxml"));
    }

    public void viewJobPositionsButtonAction(ActionEvent actionEvent) {
        evaluatorBorderPane.setCenter(loadNode("evaluator/ViewPositionsPage.fxml"));
    }

    public void viewJobApplicationsButtonAction(ActionEvent actionEvent) {
        evaluatorBorderPane.setCenter(loadNode("evaluator/ViewEvaluationsPage.fxml"));
    }

}

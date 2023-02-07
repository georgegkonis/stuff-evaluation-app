package gr.upatras.ceid.application.user_interface.manager_interface;

import gr.upatras.ceid.application.database_models.Manager;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerDashboardController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private BorderPane managerBorderPane;
    @FXML
    private Label companyNameLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Manager manager = getLoggedManager();
        companyNameLabel.setText(manager.getCompany().getName());
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void viewCompanyButtonCLicked(ActionEvent actionEvent) {
        managerBorderPane.setCenter(loadNode("manager/ViewCompanyPage.fxml"));
    }

    public void viewPositionsButtonClicked(ActionEvent actionEvent) {
        managerBorderPane.setCenter(loadNode("manager/ViewPositionsPage.fxml"));
    }

    public void viewEvaluationsButtonClicked(ActionEvent actionEvent) {
        managerBorderPane.setCenter(loadNode("manager/ViewEvaluationsPage.fxml"));
    }

    public void viewEvaluatorsButtonClicked(ActionEvent actionEvent) {
        managerBorderPane.setCenter(loadNode("manager/ViewEvaluatorsPage.fxml"));
    }

    public void viewEmployeesButtonClicked(ActionEvent actionEvent) {
        managerBorderPane.setCenter(loadNode("manager/ViewEmployeesPage.fxml"));
    }

}

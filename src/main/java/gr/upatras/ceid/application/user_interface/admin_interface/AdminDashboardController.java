package gr.upatras.ceid.application.user_interface.admin_interface;

import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private BorderPane adminBorderPane;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void addUserButtonClicked() {
        adminBorderPane.setCenter(loadNode("admin/NewUserPage.fxml"));
    }

    public void addCompanyButtonClicked() {
        adminBorderPane.setCenter(loadNode("admin/NewCompanyPage.fxml"));
    }

    public void addJobFieldButtonClicked() {
        adminBorderPane.setCenter(loadNode("admin/NewJobFieldPage.fxml"));
    }

    public void viewLogButtonClicked() {
        adminBorderPane.setCenter(loadNode("admin/ViewLogPage.fxml"));
    }

}

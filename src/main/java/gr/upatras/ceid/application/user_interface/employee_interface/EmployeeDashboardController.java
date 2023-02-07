package gr.upatras.ceid.application.user_interface.employee_interface;

import gr.upatras.ceid.application.database_models.Employee;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeDashboardController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private BorderPane employeeBorderPane;
    @FXML
    private Label companyNameLabel;

    // -------------------------------------- Initialize Methods ---------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee employee = getLoggedEmployee();
        companyNameLabel.setText(employee.getCompany().getName());

    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void viewEmployeeFolderButtonClicked() {
        employeeBorderPane.setCenter(loadNode("employee/EmployeeFolderPage.fxml"));
    }

    public void viewPositionsButtonClicked() {
        employeeBorderPane.setCenter(loadNode("employee/ViewPositionsPage.fxml"));
    }
}

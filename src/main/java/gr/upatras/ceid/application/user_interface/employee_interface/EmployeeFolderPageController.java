package gr.upatras.ceid.application.user_interface.employee_interface;

import gr.upatras.ceid.application.database_connection.user_table_connection.employee_table_connection.EmployeeUpdater;
import gr.upatras.ceid.application.database_models.Degree;
import gr.upatras.ceid.application.database_models.Employee;
import gr.upatras.ceid.application.database_models.Employee.Language;
import gr.upatras.ceid.application.database_models.Project;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFolderPageController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private TextArea resumeArea;
    @FXML
    private TextField referencesField, certificatesField, awardsField;
    @FXML
    private ComboBox<Language> languageComboBox;
    @FXML
    private ListView<Language> languageListView;
    @FXML
    private ListView<Degree> degreeListView;
    @FXML
    public ListView<Project> projectListView;
    @FXML
    private Label resultLabel;

    // -------------------------------------- Initialize Methods ---------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee employee = getLoggedEmployee();
        resultLabel.setText("");
        resumeArea.setText(employee.getResume());
        referencesField.setText(employee.getReferences());
        certificatesField.setText(employee.getCertificates());
        awardsField.setText(employee.getAwards());
        initLanguageComboBox();
        initLanguageListView(employee);
        initDegreeListView(employee);
        initProjectListView(employee);
    }

    private void initLanguageComboBox() {
        languageComboBox.getItems().setAll(Language.values());
        languageComboBox.setValue(Language.GR);
    }

    private void initLanguageListView(Employee employee) {
        languageListView.getItems().clear();
        languageListView.getItems().addAll(employee.getLanguages());
    }

    private void initDegreeListView(Employee employee) {
        degreeListView.getItems().clear();
        degreeListView.getItems().addAll(employee.getDegrees());
    }

    private void initProjectListView(Employee employee) {
        projectListView.getItems().clear();
        projectListView.getItems().addAll(employee.getProjects());
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void addLangButtonClicked(ActionEvent actionEvent) {
        if (!languageListView.getItems().contains(languageComboBox.getValue()) && !(languageComboBox.getValue() == null)) {
            languageListView.getItems().add(languageComboBox.getValue());
        }
    }

    public void removeLangButtonClicked(ActionEvent actionEvent) {
        if(!languageListView.getSelectionModel().getSelectedItems().isEmpty()) {
            languageListView.getItems().remove(languageListView.getSelectionModel().getSelectedIndex());
        }
    }

    public void addDegreeButtonClicked(ActionEvent actionEvent) throws IOException {
        showPopUpWindow(new Stage(), "DegreeWindow", "Degree", 360, 720);
        degreeListView.getItems().clear();
        degreeListView.getItems().addAll(getLoggedEmployee().getDegrees());
    }

    public void removeDegreeButtonClicked(ActionEvent actionEvent) {
        if(!degreeListView.getSelectionModel().getSelectedItems().isEmpty()) {
            degreeListView.getItems().remove(degreeListView.getSelectionModel().getSelectedIndex());
        }
    }

    public void addProjectButtonClicked(ActionEvent actionEvent) throws IOException {
        showPopUpWindow(new Stage(), "ProjectWindow", "Project", 360, 720);
        projectListView.getItems().clear();
        projectListView.getItems().addAll(getLoggedEmployee().getProjects());

    }

    public void removeProjectButtonClicked(ActionEvent actionEvent) {
        if(!projectListView.getSelectionModel().getSelectedItems().isEmpty()) {
            projectListView.getItems().remove(projectListView.getSelectionModel().getSelectedIndex());
        }
    }

    public void updateButtonClicked(ActionEvent actionEvent) {
        Employee employee = getLoggedEmployee();
        String resume = resumeArea.getText();
        String references = referencesField.getText();
        String certificates = certificatesField.getText();
        String awards = awardsField.getText();
        List<Language> languages = languageListView.getItems();
        List<Degree> degrees = degreeListView.getItems();
        List<Project> projects = projectListView.getItems();

        EmployeeUpdater employeeUpdater = new EmployeeUpdater();
        employeeUpdater.updateEmployee(employee, resume, certificates, references, awards, languages, degrees, projects);
        if (employeeUpdater.isSuccessful()) {
            setLabel(resultLabel, "Employee folder updated successfully!", Color.GREEN);
        } else {
            setLabel(resultLabel, "Employee folder could not be updated!", Color.RED);
        }
    }


}

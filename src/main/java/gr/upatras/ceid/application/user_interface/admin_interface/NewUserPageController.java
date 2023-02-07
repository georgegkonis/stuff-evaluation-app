package gr.upatras.ceid.application.user_interface.admin_interface;

import gr.upatras.ceid.application.database_connection.company_table_connection.CompanyLoader;
import gr.upatras.ceid.application.database_connection.user_table_connection.UserRegistrar;
import gr.upatras.ceid.application.database_models.Company;
import gr.upatras.ceid.application.database_models.User.UserType;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class NewUserPageController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private TextField firstNameField, lastNameField, usernameField, emailField;
    @FXML
    private PasswordField passwordField, passwordConfirmField;
    @FXML
    private ChoiceBox<String> userTypeChoiceBox;
    @FXML
    private ComboBox<Company> companyComboBox;
    @FXML
    private Spinner<Integer> expYearsSpinner;
    @FXML
    private Label resultLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        userTypeChoiceBox.getItems().addAll("Employee", "Manager", "Evaluator");
        userTypeChoiceBox.setValue("Employee");
        expYearsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        disableSpaceKeystroke(usernameField);
        disableSpaceKeystroke(passwordField);
        disableSpaceKeystroke(passwordConfirmField);
        disableSpaceKeystroke(emailField);
        initCompanyComboBox();
    }

    private void initCompanyComboBox() {
        CompanyLoader companyLoader = new CompanyLoader();
        companyComboBox.getItems().clear();
        companyLoader.loadAllCompanies();
    }

    private boolean fieldsAreEmpty() {
        if ((firstNameField.getText().isBlank())) return true;
        else if ((lastNameField.getText().isBlank())) return true;
        else if ((usernameField.getText().isBlank())) return true;
        else if ((emailField.getText().isBlank())) return true;
        else if ((passwordField.getText().isBlank())) return true;
        else return passwordConfirmField.getText().isBlank();
    }

    private boolean passwordsMatch() {
        return passwordField.getText().equals(passwordConfirmField.getText());
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void registerUserButtonAction() {
        if (fieldsAreEmpty()) {
            setLabel(resultLabel, "Please fill out all the required fields!", Color.RED);
        } else if (!passwordsMatch()) {
            setLabel(resultLabel, "Passwords don't match!", Color.RED);
        } else if (companyComboBox.getValue() == null) {
            setLabel(resultLabel, "Please select a company!", Color.RED);
        } else {
            UserType inputUserType = UserType.valueOf(userTypeChoiceBox.getValue().toUpperCase(Locale.ROOT));
            String inputUsername = usernameField.getText();
            String inputPassword = passwordField.getText();
            String inputFirsName = firstNameField.getText();
            String inputLastName = lastNameField.getText();
            String inputEmail = emailField.getText();
            String inputCompanyVatNum = companyComboBox.getValue().getVatNum();
            Integer inputExpYears = expYearsSpinner.getValue();
            UserRegistrar userRegistrar = new UserRegistrar();
            userRegistrar.registerUser(inputUserType, inputUsername, inputPassword, inputFirsName, inputLastName, inputEmail, inputCompanyVatNum, inputExpYears);
            if (userRegistrar.isSuccessful()) {
                setLabel(resultLabel, "User registered successfully!", Color.GREEN);
            } else {
                setLabel(resultLabel, "User could not be registered! Try using a different username.", Color.RED);
            }
        }
    }
}

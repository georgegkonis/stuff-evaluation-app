package gr.upatras.ceid.application.user_interface.admin_interface;

import gr.upatras.ceid.application.database_connection.company_table_connection.CompanyRegistrar;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class NewCompanyPageController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private TextField companyNameField, vatNumberField, taxOfficeField, countryField, cityField, streetField, streetNumberField, phoneNumberField;
    @FXML
    private Label resultLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
    }

    private boolean fieldsAreEmpty() {
        if ((companyNameField.getText().isBlank())) return true;
        else if ((vatNumberField.getText().isBlank())) return true;
        else if ((taxOfficeField.getText().isBlank())) return true;
        else if ((countryField.getText().isBlank())) return true;
        else if ((cityField.getText().isBlank())) return true;
        else if ((streetField.getText().isBlank())) return true;
        else if ((streetNumberField.getText().isBlank())) return true;
        else return phoneNumberField.getText().isBlank();
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void registerCompanyButtonAction() {
        if (fieldsAreEmpty()) {
            setLabel(resultLabel, "Please fill all the required fields!", Color.RED);
        } else {
            String inputCompanyName = companyNameField.getText();
            String inputVatNumber = vatNumberField.getText();
            String inputDoy = taxOfficeField.getText();
            String inputCountry = countryField.getText();
            String inputCity = cityField.getText();
            String inputStreet = streetField.getText();
            Integer inputStreetNumber = Integer.parseInt(streetNumberField.getText());
            Long inputPhoneNumber = Long.parseLong(phoneNumberField.getText());
            CompanyRegistrar companyRegistrar = new CompanyRegistrar();
            companyRegistrar.registerCompany(inputVatNumber, inputDoy, inputCompanyName, inputCountry, inputCity, inputStreet, inputStreetNumber, inputPhoneNumber);
            if (companyRegistrar.isSuccessful()) {
                setLabel(resultLabel, "Company registered successfully!", Color.GREEN);
            } else {
                setLabel(resultLabel, "Company could not be registered!! Try using a different vat number.", Color.RED);
            }
        }
    }
}

package gr.upatras.ceid.application.user_interface.manager_interface;

import gr.upatras.ceid.application.database_connection.company_table_connection.CompanyUpdater;
import gr.upatras.ceid.application.database_models.Company;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCompanyPageController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private TextField companyNameField, vatNumberField, taxOfficeField, countryField, cityField, streetField, streetNumField, phoneNumField;
    @FXML
    private Label resultLabel;

    // -------------------------------------- Initialize Methods ---------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        Company company = getLoggedManager().getCompany();
        companyNameField.setText(company.getName());
        vatNumberField.setText(company.getVatNum());
        taxOfficeField.setText(company.getTaxOffice());
        countryField.setText(company.getCountry());
        cityField.setText(company.getCity());
        streetField.setText(company.getStreet());
        streetNumField.setText(company.getStreetNum().toString());
        phoneNumField.setText(company.getPhoneNum().toString());
    }

    // ---------------------------------------- Utility Methods ----------------------------------------

    private boolean fieldsAreEmpty() {
        return (countryField.getText().isBlank() |
                cityField.getText().isBlank() |
                streetField.getText().isBlank() |
                streetNumField.getText().isBlank() |
                phoneNumField.getText().isBlank());
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void updateCompanyButtonAction() {
        if (fieldsAreEmpty()) {
            setLabel(resultLabel, "Please fill all the required fields!", Color.RED);
        } else {
            Company company = getLoggedManager().getCompany();
            String country = countryField.getText();
            String city = cityField.getText();
            String street = streetField.getText();
            Integer streetNum = Integer.parseInt(streetNumField.getText());
            Long phoneNum = Long.parseLong(phoneNumField.getText());

            CompanyUpdater companyUpdater = new CompanyUpdater();
            companyUpdater.updateCompany(company, country, city, street, streetNum, phoneNum);

            if (companyUpdater.isSuccessful()) {
                setLabel(resultLabel, "Company updated successfully!", Color.GREEN);
            } else {
                setLabel(resultLabel, "Company could not be updated!", Color.RED);
            }
        }
    }
}

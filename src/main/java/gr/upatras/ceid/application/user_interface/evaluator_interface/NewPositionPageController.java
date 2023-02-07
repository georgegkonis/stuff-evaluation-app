package gr.upatras.ceid.application.user_interface.evaluator_interface;

import gr.upatras.ceid.application.database_connection.field_table_connection.FieldLoader;
import gr.upatras.ceid.application.database_connection.position_table_connection.PositionRegistrar;
import gr.upatras.ceid.application.database_models.Field;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class NewPositionPageController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private Label resultLabel;
    @FXML
    private TextField titleField, headquartersField;
    @FXML
    private Spinner<Integer> salarySpinner;
    @FXML
    private ComboBox<Field> fieldsComboBox;
    @FXML
    private ListView<Field> fieldsListView;
    @FXML
    private DatePicker startDatePicker, deadlinePicker;

    // -------------------------------------- Initialize Methods ---------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        initSalarySpinner();
        initFieldsComboBox();
    }

    private void initSalarySpinner() {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 100000, 800, 50);
        salarySpinner.setValueFactory(spinnerValueFactory);
    }

    private void initFieldsComboBox() {
        FieldLoader fieldLoader = new FieldLoader();
        fieldsComboBox.getItems().clear();
        fieldsComboBox.getItems().addAll(fieldLoader.loadAllFields());
    }

    // ---------------------------------------- Utility Methods ----------------------------------------

    private boolean fieldsAreEmpty() {
        return (titleField.getText().isBlank()) |
                (headquartersField.getText().isBlank()) |
                (startDatePicker.getValue() == null) |
                (deadlinePicker.getValue() == null);
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void addButtonClicked(ActionEvent actionEvent) {
        if (!fieldsListView.getItems().contains(fieldsComboBox.getValue()) && !(fieldsComboBox.getValue() == null)) {
            fieldsListView.getItems().add(fieldsComboBox.getValue());
        }
    }

    public void removeButtonClicked(ActionEvent actionEvent) {
        if(!fieldsListView.getSelectionModel().getSelectedItems().isEmpty()) {
            fieldsListView.getItems().remove(fieldsListView.getSelectionModel().getSelectedIndex());
        }
    }

    public void announceButtonClicked(ActionEvent actionEvent) {
        if (fieldsAreEmpty()) {
            setLabel(resultLabel, "Please fill out all the required fields!", Color.RED);
        } else if (fieldsListView.getItems().isEmpty()) {
            setLabel(resultLabel, "Please select at least one required field!", Color.RED);
        } else {
            String evaluatorUsername = getLoggedUser().getUsername();
            String companyVatNum = getLoggedEvaluator().getCompanyVatNumber();
            String title = titleField.getText();
            Float salary = Float.valueOf(salarySpinner.getValue());
            String headquarters = headquartersField.getText();
            Date startDate = Date.valueOf(startDatePicker.getValue().toString());
            Date submissionDate = Date.valueOf(deadlinePicker.getValue().toString());
            List<Field> inputFields = fieldsListView.getItems();

            PositionRegistrar positionRegistrar = new PositionRegistrar();
            positionRegistrar.registerPosition(evaluatorUsername, companyVatNum, startDate, submissionDate, salary, title, headquarters, inputFields);

            if (positionRegistrar.isSuccessful()) {
                setLabel(resultLabel, "Position announced successfully!", Color.GREEN);
            } else {
                setLabel(resultLabel, "Position could not be announced!", Color.RED);
            }
        }
    }

}

package gr.upatras.ceid.application.user_interface.admin_interface;

import gr.upatras.ceid.application.database_connection.field_table_connection.FieldLoader;
import gr.upatras.ceid.application.database_connection.field_table_connection.FieldRegistrar;
import gr.upatras.ceid.application.database_models.Field;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class NewJobFieldPageController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ComboBox<Field> parentComboBox;
    @FXML
    private Label resultLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        initParentComboBox();
    }

    private void initParentComboBox() {
        parentComboBox.getItems().clear();
        FieldLoader fieldLoader = new FieldLoader();
        parentComboBox.getItems().addAll(fieldLoader.loadAllFields());
    }

    private boolean fieldsAreEmpty() {
        if (titleField.getText().isBlank()) return true;
        else return descriptionTextArea.getText().isBlank();
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void noneButtonClicked() {
        parentComboBox.setValue(null);
    }

    public void registerJobFieldButtonClicked() {
        if (fieldsAreEmpty()) {
            setLabel(resultLabel, "Please fill all the required fields!", Color.RED);
        } else {
            String title = titleField.getText();
            String description = descriptionTextArea.getText();
            String parentTitle = parentComboBox.getValue() == null ? null : parentComboBox.getValue().getTitle();
            FieldRegistrar fieldRegistrar = new FieldRegistrar();
            fieldRegistrar.registerField(title, description, parentTitle);
            initParentComboBox();
            if (fieldRegistrar.isSuccessful()) {
                setLabel(resultLabel, "Job field registered successfully!", Color.GREEN);
            } else {
                setLabel(resultLabel, "Job field could not be registered! Try using a different title.", Color.RED);
            }
        }
    }


}

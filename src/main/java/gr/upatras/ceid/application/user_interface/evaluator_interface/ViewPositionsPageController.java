package gr.upatras.ceid.application.user_interface.evaluator_interface;

import gr.upatras.ceid.application.database_connection.field_table_connection.FieldLoader;
import gr.upatras.ceid.application.database_connection.position_table_connection.PositionLoader;
import gr.upatras.ceid.application.database_connection.position_table_connection.PositionUpdater;
import gr.upatras.ceid.application.database_models.Field;
import gr.upatras.ceid.application.database_models.Position;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewPositionsPageController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private Label resultLabel;
    @FXML
    private Button updateButton;
    @FXML
    private TextField titleField, headquartersField;
    @FXML
    private Spinner<Integer> salarySpinner;
    @FXML
    private ComboBox<Field> fieldsComboBox;
    @FXML
    private ListView<Field> fieldsListView;
    @FXML
    private TableView<Position> positionsTableView;
    @FXML
    private TableColumn<Position, String> titleTableColumn, evaluatorTableColumn;
    @FXML
    private TableColumn<Position, Timestamp> timestampTableColumn;
    @FXML
    private DatePicker startDatePicker, deadlinePicker;

    // -------------------------------------- Initialize Methods ---------------------------------------


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        updateButton.setDisable(true);
        initPositionsTableView();
        initFieldsComboBox();
    }

    private void initFieldsComboBox() {
        FieldLoader fieldLoader = new FieldLoader();
        fieldsComboBox.getItems().clear();
        fieldsComboBox.getItems().addAll(fieldLoader.loadAllFields());
    }

    private void initPositionsTableView() {
        PositionLoader positionLoader = new PositionLoader();
        List<Position> positions = positionLoader.loadCompanyPositions(getLoggedEvaluator().getCompanyVatNumber());
        positionsTableView.getItems().clear();
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        evaluatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("evaluatorUsername"));
        timestampTableColumn.setCellValueFactory(new PropertyValueFactory<>("announcementTimestamp"));
        positionsTableView.getItems().addAll(positions);
    }

    // ---------------------------------------- Utility Methods ----------------------------------------

    private boolean fieldsAreEmpty() {
        return titleField.getText().isBlank() |
                headquartersField.getText().isBlank() |
                startDatePicker.getValue() == null |
                deadlinePicker.getValue() == null;
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void tableViewRowClicked(MouseEvent mouseEvent) {
        int index = positionsTableView.getSelectionModel().getSelectedIndex();
        Position position = positionsTableView.getItems().get(index);

        updateButton.setDisable(false);
        fieldsListView.getItems().clear();
        updateButton.setDisable(!position.getEvaluatorUsername().equals(getLoggedUser().getUsername()));
        titleField.setText(position.getTitle());
        headquartersField.setText(position.getHeadquarters());
        salarySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 100000, position.getSalary().intValue(), 50));
        startDatePicker.setValue(position.getStartDate().toLocalDate());
        deadlinePicker.setValue(position.getStartDate().toLocalDate());
        fieldsListView.getItems().addAll(position.getFields());
    }

    public void addFieldButtonClicked() {
        if (!(fieldsComboBox.getValue() == null)) {
            if (fieldsListView.getItems().stream().noneMatch(field -> Objects.equals(field.getTitle(), fieldsComboBox.getValue().getTitle()))) {
                fieldsListView.getItems().add(fieldsComboBox.getValue());
            }
        }
    }

    public void removeFieldButtonClicked() {
        if(!fieldsListView.getSelectionModel().getSelectedItems().isEmpty()) {
            fieldsListView.getItems().remove(fieldsListView.getSelectionModel().getSelectedIndex());
        }
    }

    public void updateButtonClicked() {
        if (fieldsAreEmpty()) {
            setLabel(resultLabel, "Please fill out all the required fields!", Color.RED);
        } else if (fieldsListView.getItems().isEmpty()) {
            setLabel(resultLabel, "Please select at least one required field!", Color.RED);
        } else {
            int index = positionsTableView.getSelectionModel().getSelectedIndex();
            Position position = positionsTableView.getItems().get(index);
            String title = titleField.getText();
            Float salary = Float.valueOf(salarySpinner.getValue());
            String headquarters = headquartersField.getText();
            Date startDate = Date.valueOf(startDatePicker.getValue().toString());
            Date submissionDate = Date.valueOf(deadlinePicker.getValue().toString());
            List<Field> fields = fieldsListView.getItems();

            PositionUpdater positionUpdater = new PositionUpdater();
            positionUpdater.updatePosition(position, startDate, submissionDate, salary, title, headquarters, fields);

            if (positionUpdater.isSuccessful()) {
                setLabel(resultLabel, "Position updated successfully!", Color.GREEN);
                initPositionsTableView();
                updateButton.setDisable(true);
            } else {
                setLabel(resultLabel, "Position could not be announced!", Color.RED);
            }
        }
    }

}

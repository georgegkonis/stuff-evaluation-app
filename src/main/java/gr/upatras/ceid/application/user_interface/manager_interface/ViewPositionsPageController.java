package gr.upatras.ceid.application.user_interface.manager_interface;

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
import java.sql.Timestamp;
import java.util.List;
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
    private DatePicker startDatePicker, deadlinePicker;
    @FXML
    private Spinner<Integer> salarySpinner;
    @FXML
    private ListView<Field> fieldListView;
    @FXML
    private TableView<Position> positionsTableView;
    @FXML
    private TableColumn<Position, String> titleTableColumn, evaluatorTableColumn;
    @FXML
    private TableColumn<Position, Timestamp> timestampTableColumn;

    // -------------------------------------- Initialize Methods ---------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        updateButton.setDisable(true);
        initPositionsTableView();
    }

    private void initPositionsTableView() {
        PositionLoader positionLoader = new PositionLoader();
        List<Position> positions = positionLoader.loadCompanyPositions(getLoggedManager().getCompanyVatNum());
        positionsTableView.getItems().clear();
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        evaluatorTableColumn.setCellValueFactory(new PropertyValueFactory<>("evaluatorUsername"));
        timestampTableColumn.setCellValueFactory(new PropertyValueFactory<>("announcementTimestamp"));
        positionsTableView.getItems().addAll(positions);
    }

    private void initFieldListView(Position position) {
        fieldListView.getItems().clear();
        fieldListView.getItems().addAll(position.getFields());
    }

    private void initSalarySpinner(Position position) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 100000, position.getSalary().intValue(), 50);
        salarySpinner.setValueFactory(spinnerValueFactory);
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void tableViewRowClicked(MouseEvent mouseEvent) {
        int index = positionsTableView.getSelectionModel().getSelectedIndex();
        Position position = positionsTableView.getItems().get(index);
        titleField.setText(position.getTitle());
        headquartersField.setText(position.getHeadquarters());
        startDatePicker.setValue(position.getStartDate().toLocalDate());
        deadlinePicker.setValue(position.getStartDate().toLocalDate());
        updateButton.setDisable(false);
        initFieldListView(position);
        initSalarySpinner(position);
    }

    public void updateButtonClicked() {
        int index = positionsTableView.getSelectionModel().getSelectedIndex();
        Position position = positionsTableView.getItems().get(index);
        Float salary = Float.valueOf(salarySpinner.getValue());

        PositionUpdater positionUpdater = new PositionUpdater();
        positionUpdater.updatePosition(position, salary);
        if (positionUpdater.isSuccessful()) {
            setLabel(resultLabel, "Position updated successfully!", Color.GREEN);
            initPositionsTableView();
            updateButton.setDisable(true);
        } else {
            setLabel(resultLabel, "Position could not be announced!", Color.RED);
        }
    }

}

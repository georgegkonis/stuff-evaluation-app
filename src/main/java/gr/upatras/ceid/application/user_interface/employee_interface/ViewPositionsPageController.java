package gr.upatras.ceid.application.user_interface.employee_interface;

import gr.upatras.ceid.application.database_connection.position_table_connection.PositionLoader;
import gr.upatras.ceid.application.database_connection.evaluation_request_table_connection.EvaluationRequestDeleter;
import gr.upatras.ceid.application.database_connection.evaluation_request_table_connection.EvaluationRequestRegistrar;
import gr.upatras.ceid.application.database_models.Employee;
import gr.upatras.ceid.application.database_models.Position;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Button withdrawButton, requestButton;
    @FXML
    private TableView<Position> positionsTableView;
    @FXML
    private TableColumn<Position, String> titleTableCol, headquartersTableCol;
    @FXML
    private TableColumn<Position, Timestamp> announcementTableCol;
    @FXML
    private TableColumn<Position, Float> salaryTableCol;
    @FXML
    private TableColumn<Position, Date> startDateTableCol, submissionsDateTableCol;
    @FXML
    private Label resultLabel;

    // -------------------------------------- Initialize Methods ---------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        withdrawButton.setDisable(true);
        requestButton.setDisable(true);
        initPositionsTableView();
    }

    private void initPositionsTableView() {
        positionsTableView.getItems().clear();

        titleTableCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        headquartersTableCol.setCellValueFactory(new PropertyValueFactory<>("headquarters"));
        announcementTableCol.setCellValueFactory(new PropertyValueFactory<>("announcementTimestamp"));
        salaryTableCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        startDateTableCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        submissionsDateTableCol.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));

        PositionLoader positionLoader = new PositionLoader();
        List<Position> positions = positionLoader.loadCompanyPositions(getLoggedEmployee().getCompanyVatNumber());
        positionsTableView.getItems().addAll(positions);
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void positionsTableViewRowClicked(MouseEvent mouseEvent) {
        resultLabel.setText("");
        int index = positionsTableView.getSelectionModel().getSelectedIndex();
        Position selectedPosition = positionsTableView.getItems().get(index);

        if (getLoggedEmployee().getPositions().stream().anyMatch(position -> Objects.equals(position.getIdNum(), selectedPosition.getIdNum()))) {
            withdrawButton.setDisable(false);
            requestButton.setDisable(true);
        } else {
            withdrawButton.setDisable(true);
            requestButton.setDisable(false);
        }
    }

    public void withdrawButtonClicked(ActionEvent actionEvent) {
        int index = positionsTableView.getSelectionModel().getSelectedIndex();
        Position position = positionsTableView.getItems().get(index);
        Employee employee = getLoggedEmployee();

        EvaluationRequestDeleter evaluationRequestDeleter = new EvaluationRequestDeleter();
        evaluationRequestDeleter.deleteEvaluationRequest(employee, position.getIdNum());
        if (evaluationRequestDeleter.isSuccessful()) {
            setLabel(resultLabel, "Request withdrawn successfully!", Color.GREEN);
            initPositionsTableView();
            withdrawButton.setDisable(true);
        } else {
            setLabel(resultLabel, "Request could not be withdrawn!", Color.RED);
        }
    }

    public void requestButtonClicked(ActionEvent actionEvent) {
        int index = positionsTableView.getSelectionModel().getSelectedIndex();
        Position position = positionsTableView.getItems().get(index);
        Employee employee = getLoggedEmployee();

        EvaluationRequestRegistrar evaluationRequestRegistrar = new EvaluationRequestRegistrar();
        evaluationRequestRegistrar.registerEvaluationRequest(employee, position.getIdNum());
        if (evaluationRequestRegistrar.isSuccessful()) {
            setLabel(resultLabel, "Evaluation requested successfully!", Color.GREEN);
            initPositionsTableView();
            requestButton.setDisable(true);
        } else {
            setLabel(resultLabel, "Evaluation could not be requested!", Color.RED);
        }
    }

}

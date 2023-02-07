package gr.upatras.ceid.application.user_interface.admin_interface;

import gr.upatras.ceid.application.database_connection.action_log_table_connection.ActionLogLoader;
import gr.upatras.ceid.application.database_models.ActionLog;
import gr.upatras.ceid.application.database_models.ActionLog.ActionLogEntry;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewLogPageController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    private TableView<ActionLogEntry> logTableView;
    @FXML
    private TableColumn<ActionLogEntry, String> userTableColumn, dateTableColumn, timeTableColumn, actionTableColumn, tableTableColumn, statusTableColumn;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTableView();
    }

    private void initTableView() {
        userTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("actionDate"));
        timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("actionTime"));
        actionTableColumn.setCellValueFactory(new PropertyValueFactory<>("actionType"));
        tableTableColumn.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("actionState"));
        ActionLogLoader actionLogLoader = new ActionLogLoader();
        ActionLog actionLog = actionLogLoader.loadActionLog();
        logTableView.getItems().addAll(actionLog.getActionLog());
    }
}

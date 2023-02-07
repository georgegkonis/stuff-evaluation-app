package gr.upatras.ceid.application.user_interface.evaluator_interface;

import gr.upatras.ceid.application.database_connection.DatabaseProceduresCaller;
import gr.upatras.ceid.application.database_connection.evaluation_table_connection.EvaluationLoader;
import gr.upatras.ceid.application.database_connection.evaluation_table_connection.EvaluationUpdater;
import gr.upatras.ceid.application.database_connection.position_table_connection.PositionLoader;
import gr.upatras.ceid.application.database_models.Evaluation;
import gr.upatras.ceid.application.database_models.Position;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewEvaluationsPageController extends ControllerUtilities implements Initializable {

    @FXML
    private Label resultLabel;
    @FXML
    private Button updateButton, finalizeButton;
    @FXML
    private TextField finalGradeField;
    @FXML
    private TextArea commentArea;
    @FXML
    private Spinner<Integer> interviewGradeSpinner, managerGradeSpinner, resumeGradeSpinner;
    @FXML
    private TableView<Position> positionTableView;
    @FXML
    private TableColumn<Position, String> titleTableViewCol;
    @FXML
    private TableView<Evaluation> evaluationTableView;
    @FXML
    private TableColumn<Evaluation, String> employeeTableViewCol, statusTableViewCol;

    // -------------------------------------- Initialize Methods ---------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        updateButton.setDisable(true);
        finalizeButton.setDisable(true);
        managerGradeSpinner.setDisable(true);
        initResumeGradeSpinner();
        initInterviewGradeSpinner();
        initManagerGradeSpinner();
        initPositionTableView();
    }

    public void initResumeGradeSpinner() {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 2, 0);
        resumeGradeSpinner.setValueFactory(spinnerValueFactory);
    }

    public void initInterviewGradeSpinner() {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0);
        interviewGradeSpinner.setValueFactory(spinnerValueFactory);
    }

    public void initManagerGradeSpinner() {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0);
        managerGradeSpinner.setValueFactory(spinnerValueFactory);
    }

    private void initPositionTableView() {
        PositionLoader positionLoader = new PositionLoader();
        List<Position> positions = positionLoader.loadEvaluatorPositions(getLoggedEvaluator().getUsername());

        positionTableView.getItems().clear();
        titleTableViewCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        positionTableView.getItems().addAll(positions);
    }


    private void initEvaluationTableView(Position position) {
        EvaluationLoader evaluationLoader = new EvaluationLoader();
        List<Evaluation> evaluations = evaluationLoader.loadPositionEvaluations(position.getIdNum());

        evaluationTableView.getItems().clear();
        employeeTableViewCol.setCellValueFactory(new PropertyValueFactory<>("employeeUsername"));
        statusTableViewCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        evaluationTableView.getItems().addAll(evaluations);
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void positionTableViewRowClicked(MouseEvent mouseEvent) {
        int index = positionTableView.getSelectionModel().getSelectedIndex();
        Position position = positionTableView.getItems().get(index);
        finalizeButton.setDisable(false);
        initEvaluationTableView(position);
    }

    public void evaluationTableViewRowClicked(MouseEvent mouseEvent) {
        int index = evaluationTableView.getSelectionModel().getSelectedIndex();
        Evaluation evaluation = evaluationTableView.getItems().get(index);

        if (evaluation.getStatus() == Evaluation.EvaluationStatus.FINAL) {
            interviewGradeSpinner.setDisable(true);
            resumeGradeSpinner.setDisable(true);
            managerGradeSpinner.setDisable(true);
            finalGradeField.setDisable(true);
            commentArea.setDisable(true);
        } else {
            updateButton.setDisable(false);
        }

        interviewGradeSpinner.getValueFactory().setValue(evaluation.getInterviewGrade());
        resumeGradeSpinner.getValueFactory().setValue(evaluation.getResumeGrade());
        managerGradeSpinner.getValueFactory().setValue(evaluation.getManagerGrade());
        finalGradeField.setText(evaluation.getFinalGrade().toString());
        commentArea.setText(evaluation.getComment() != null ? evaluation.getComment() : "");
    }

    public void updateButtonClicked(ActionEvent actionEvent) {
        int index = evaluationTableView.getSelectionModel().getSelectedIndex();
        Evaluation evaluation = evaluationTableView.getItems().get(index);

        Integer interviewGrade = interviewGradeSpinner.getValue();
        Integer resumeGrade = resumeGradeSpinner.getValue();
        String comment = commentArea.getText();

        EvaluationUpdater evaluationUpdater = new EvaluationUpdater();
        evaluationUpdater.updateEvaluation(evaluation, comment, resumeGrade, interviewGrade);

        if (evaluationUpdater.isSuccessful()) {
            setLabel(resultLabel, "Evaluation updated successfully!", Color.GREEN);
            initPositionTableView();
            updateButton.setDisable(true);
        } else {
            setLabel(resultLabel, "Evaluation could not be updated!", Color.RED);
        }
    }

    public void finalizeButtonClicked(ActionEvent actionEvent) {
        int index = positionTableView.getSelectionModel().getSelectedIndex();
        Position position = positionTableView.getItems().get(index);
        DatabaseProceduresCaller proceduresConnector = new DatabaseProceduresCaller();
        proceduresConnector.callEvaluationStatusProcedure(position.getIdNum());
        if (proceduresConnector.isSuccessful()) {
            setLabel(resultLabel, "Evaluations successfully finalized!", Color.GREEN);
            initPositionTableView();
            updateButton.setDisable(true);
        } else {
            setLabel(resultLabel, "Evaluations could not be finalized!", Color.RED);
        }
    }

}

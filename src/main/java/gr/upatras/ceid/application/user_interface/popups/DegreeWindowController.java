package gr.upatras.ceid.application.user_interface.popups;

import gr.upatras.ceid.application.database_models.Degree;
import gr.upatras.ceid.application.database_models.Degree.Level;
import gr.upatras.ceid.application.user_interface.ControllerUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

public class DegreeWindowController extends ControllerUtilities implements Initializable {

    // ---------------------------------------- FXML Components ----------------------------------------

    @FXML
    public TextField titleField;
    @FXML
    public TextField institutionField;
    @FXML
    public ChoiceBox<Level> levelChoiceBox;
    @FXML
    public Spinner<Integer> gradYearSpinner;
    @FXML
    public Spinner<Double> gradeSpinner;
    @FXML
    public Label resultLabel;

    // ---------------------------------------- Utility Methods ----------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultLabel.setText("");
        levelChoiceBox.getItems().addAll(Level.values());
        levelChoiceBox.setValue(Level.ASSOCIATE);
        gradYearSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2021, 2010));
        gradeSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(5.0, 10.0, 0.0, 0.1));
    }

    private boolean fieldsAreEmpty() {
        if ((titleField.getText().isBlank())) return true;
        else return institutionField.getText().isBlank();
    }

    // --------------------------------------- On Action Methods ---------------------------------------

    public void addDegreeButtonClicked(ActionEvent actionEvent) {
        if (!fieldsAreEmpty()) {
            String title = titleField.getText();
            String institution = institutionField.getText();
            Level level = levelChoiceBox.getValue();
            Float grade = gradeSpinner.getValue().floatValue();
            Year gradYear = Year.of(gradYearSpinner.getValue());
            Degree degree = new Degree(title, institution, level, grade, gradYear);
            getLoggedEmployee().getDegrees().add(degree);
            popUpWindow.close();
        } else {
            resultLabel.setText("Fill out all the necessary fields!");
            resultLabel.setTextFill(Color.RED);
        }
    }

}

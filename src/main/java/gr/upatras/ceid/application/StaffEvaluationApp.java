package gr.upatras.ceid.application;

import javafx.application.Application;
import javafx.stage.Stage;

import static application.user_interface.ControllerUtilities.startGui;

public class StaffEvaluationApp extends Application {

    // -------------------------------------------- Methods --------------------------------------------

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        startGui(window);
    }
}

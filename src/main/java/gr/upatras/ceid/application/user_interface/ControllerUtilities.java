package gr.upatras.ceid.application.user_interface;

import application.database_models.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class ControllerUtilities {

    // ------------------------------------------ Static Field -----------------------------------------

    private static User loggedUser;
    protected static Stage loginWindow;
    protected static Stage mainWindow;
    protected static Stage popUpWindow;

    // -------------------------------------------- Methods --------------------------------------------

    // Starts the gui
    public static void startGui(Stage stage) throws IOException {
        loginWindow = loadWindow("LoginWindow", 360, 420);
    }

    // Loads a window from the specified file
    protected static Stage loadWindow(String file, int h, int v) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ControllerUtilities.class.getResource("/fxml_files/windows/%s.fxml".formatted(file))));
        Stage window = new Stage();
        window.setScene(new Scene(root, h, v));
        window.setTitle("Staff Evaluation");
        window.centerOnScreen();
        window.setResizable(true);
        window.show();
        return window;
    }

    // Loads a node from the specified file
    protected static Node loadNode(String path) {
        try {
            return FXMLLoader.load(Objects.requireNonNull(ControllerUtilities.class.getResource("/fxml_files/" + path)));
        } catch (IOException exception) {
            return null;
        }
    }

    public static void showPopUpWindow(Stage window, String file, String title, int h, int v) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ControllerUtilities.class.getResource("/fxml_files/popups/%s.fxml".formatted(file))));
        popUpWindow = window;
        popUpWindow.setScene(new Scene(root, h, v));
        popUpWindow.setTitle(title);
        popUpWindow.centerOnScreen();
        popUpWindow.setResizable(false);
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.show();
    }

    // Sets the text and the color of a label
    protected void setLabel(Label label, String text, Color color) {
        label.setText(text);
        label.setTextFill(color);
    }
-
    // Disables the space keystroke on the selected text field
    public static void disableSpaceKeystroke(TextField textField) {
        TextFormatter<?> formatter = new TextFormatter<>((TextFormatter.Change change) -> {
            String text = change.getText();
            if (!text.isEmpty()) {
                String newText = text.replace(" ", "");
                int caretPos = change.getCaretPosition() - text.length() + newText.length();
                change.setText(newText);
                change.selectRange(caretPos, caretPos);
            }
            return change;
        });
        textField.setTextFormatter(formatter);
    }

    // ----------------------------------------- Setter Methods ----------------------------------------

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    protected User getLoggedUser() {
        return loggedUser;
    }

    protected Employee getLoggedEmployee() {
        return (Employee) loggedUser;
    }

    protected Evaluator getLoggedEvaluator() {
        return (Evaluator) loggedUser;
    }

    protected Manager getLoggedManager() {
        return (Manager) loggedUser;
    }

}

package phil;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    // Referenced in TextField fx:id
    @FXML
    private TextField userInput;

    private Phil phil;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    private void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    public void setPhil(Phil phil) {
        this.phil = phil;
    }

    // Don't forget to add the '@FXML' tag for the .fxml file to recognise this function
    // The FXML tag exposes the function to fxml file without needing public access
    // Referenced in TextField onAction
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        DialogBox userDialog = new DialogBox(input, userImage);
        DialogBox dukeDialog = new DialogBox(phil.getResponse(input), dukeImage);
        dialogContainer.getChildren().addAll(userDialog, dukeDialog);
        if (input.equals("bye")) {
            // Code adapted from a.b on https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx
            Stage stage = (Stage) this.dialogContainer.getScene().getWindow();
            stage.close();
        }
        userInput.clear();
    }
}


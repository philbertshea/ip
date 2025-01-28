package phil;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * Represents a Dialog Box instance, which when used with /view/DialogBox.fxml
 * represents a dialog for either an input by the user, or an output by the chatbot.
 *
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox instance.
     *
     * @param text Text String to be shown in the dialog.
     * @param img Image to be used in the dialog box as either the user or the chatbot.
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader loader = new FXMLLoader(PhilMain.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }
}


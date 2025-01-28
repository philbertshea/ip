package phil;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents PhilMain instance that is an Application
 * to be launched by the Launcher class upon calling gradle run.
 */
public class PhilMain extends Application {

    private Phil phil = new Phil();

    /**
     * Load the MainWindow.fxml file as an AnchorPane, attach the Scene to the Stage
     * and Start showing the Stage to initialise the App.
     *
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(PhilMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = loader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            loader.<MainWindow>getController().setPhil(this.phil);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package phil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PhilMain extends Application {

    private Phil phil = new Phil();
    private Stage stage;

    @Override
    public void start(Stage stage) {
        try {
            this.stage = stage;
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

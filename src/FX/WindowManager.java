package FX;

import FX.controller.MediaPlayerController;
import app.model.MediaLoader;
import app.model.MediaPlayer;
import app.model.MediaPlayerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.File;

import java.io.IOException;
import java.util.List;

/**
 * Object which manages transitions between all differents scenes in javaFX.
 *
 * @author Mcdostone
 */
public class WindowManager {

    private Stage stage;

    public WindowManager(Stage stage) {  this.stage = stage;  }

    private Parent loadFXML(String filename) {
        try {
            return FXMLLoader.load(getClass().getResource("/FX/view/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startHomePage() {
        Parent root = this.loadFXML("homepage.fxml");
        this.stage.setTitle("Sort my fucking photos !");
        this.stage.setScene(new Scene(root));
        this.stage.sizeToScene();
        this.stage.requestFocus();
        this.stage.show();
    }

    public void startMediaPlayer(List<File> paths) {
        FXMLLoader loader = this.getLoader("mediaPlayer.fxml");
        MediaPlayerController controller = new MediaPlayerController(paths);
        loader.setController(controller);
        Parent pane = null;

        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane);
        this.stage.setScene(scene);
        scene.getRoot().requestFocus();
        this.stage.show();
    }

    public FXMLLoader getLoader(String filename) {
        return new FXMLLoader(getClass().getResource("/FX/view/" + filename));
    }
}

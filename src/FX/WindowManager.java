package FX;

import FX.controller.MediaPlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Object which manages transitions between all differents scenes of the app.
 *
 * @author Mcdostone
 */
public class WindowManager {

    /** Main stage */
    private Stage stage;


    public WindowManager(Stage stage) {  this.stage = stage;  }

    /**
     * @param filename FXML file to load.
     * @return The Node
     */
    private Parent loadFXML(String filename) {
        try {
            return FXMLLoader.load(getClass().getResource("/FX/view/" + filename));
        } catch (IOException e) {  e.printStackTrace(); }

        return null;
    }

    /** Displays the homepage. */
    public void startHomePage() {
        Parent root = this.loadFXML("homepage.fxml");
        this.stage.setTitle("Sort my fucking photos !");
        this.setScene(new Scene(root));
    }

    /**
     * Display the mediaPlayer.
     * @param paths Paths to explore to find all medias to handle
     */
    public void startMediaPlayer(List<File> paths) {
        FXMLLoader loader = this.getLoader("mediaPlayer.fxml");
        MediaPlayerController controller = new MediaPlayerController(paths);
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setScene(new Scene(root));
    }

    /**
     * Modify the current scene of the stage.
     * @param scene The new scene
     */
    private void setScene(Scene scene) {
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        this.stage.requestFocus();
        this.stage.show();
    }

    /**
     *
     * @param filename FXML file to load.
     * @return The FXMLLader associeted to the given file
     */
    public FXMLLoader getLoader(String filename) {
        return new FXMLLoader(getClass().getResource("/FX/view/" + filename));
    }
}

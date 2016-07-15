package FX;

import app.model.MediaLoader;
import app.model.MediaPlayer;
import app.model.MediaPlayerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        this.stage.show();
    }

    public void startMediaPlayer(List<File> paths) {
        Parent root = this.loadFXML("mediaPlayer.fxml");
        MediaPlayer m = MediaPlayerFactory.createMediaPlayer(paths);
        MediaLoader loader = MediaLoader.getInstance();
        int capa = loader.capacity();
        capa = capa / 2;
        for(int i = -capa; i <= capa; i++)
            loader.add(m.get(i));
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }
}

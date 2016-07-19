package FX;

import FX.controller.MediaPlayerController;
import app.conf.Configuration;
import app.model.MyLogger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private Stage settingsStage;
    private Stage logsStage;


    public WindowManager(Stage stage) {
        this.stage = stage;
        this.logsStage = new Stage();
        this.logsStage.setScene((new Scene(this.loadFXML("logs.fxml"))));
        this.stage.setOnCloseRequest(event -> {
            logsStage.close();
            if(settingsStage != null)
                settingsStage.close();
            Platform.exit();
        });
    }

    /**
     * @param filename FXML file to load.
     * @return The Node
     */
    private Parent loadFXML(String filename) {
        try {
            return this.getLoader(filename).load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Displays the homepage. */
    public void startHomePage() {
        Parent root = this.loadFXML("homepage.fxml");
        this.stage.setTitle("Sort my fucking photos !");
        this.setScene(new Scene(root));
    }

    public void openSettingsWindow() {
        if(this.settingsStage == null)
            this.settingsStage = new Stage();
        this.settingsStage.setScene(new Scene(this.loadFXML("settings.fxml")));
        this.settingsStage.setOnCloseRequest(event -> {
            Configuration.getInstance().save();
            settingsStage.close();
        });
        this.settingsStage.show();
    }

    public void openLogsWindows() {  this.logsStage.show();  }

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
        this.stage.setMinHeight(300);
        this.stage.setMinWidth(300);
        this.stage.show();
    }

    public void toggleFullscreen() {
        this.stage.setFullScreen(!this.isFullscreen());
    }

    public boolean isFullscreen() {
        return this.stage.isFullScreen();
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

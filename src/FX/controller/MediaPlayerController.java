package FX.controller;

import FX.Window;
import FX.view.GridOverlay;
import app.conf.Configuration;
import app.model.Media;
import app.model.MediaPlayer;
import app.model.MediaPlayerFactory;
import app.model.MyLogger;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.File;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;

/**
 * Controller of the MediaPlayer scene.
 *
 * @author Mcdostone
 */
public class MediaPlayerController {

    /** The unique MediaPlayer */
    private MediaPlayer mediaPlayer;

    @FXML private ImageView preview;
    @FXML private HBox container;
    @FXML private BorderPane settingsButton;
    @FXML private BorderPane lockButton;
    @FXML private BorderPane gridButton;
    @FXML private BorderPane fullscreenButton;
    @FXML private BorderPane logsButton;
    private boolean toolbarLocked = true;

    private Timeline currentAnimation;


    @FXML private AnchorPane root;
    @FXML private GridPane toolbar;
    /** Grid Overlay for better sorting */
    private GridOverlay gridOverlay;


    /**
     * Constructor which fills in the mediaPlayer with paths given by the user.
     * @param paths Paths which contains medias to sort
     */
    public MediaPlayerController(List<File> paths) {
        this.mediaPlayer = MediaPlayerFactory.createMediaPlayer(paths);
        // Create the GridOverlay and hide it
        gridOverlay = new GridOverlay(Configuration.getInstance().enableGridAtStartup());
        this.gridOverlay.toFront();
        Configuration.getInstance().addObserver(this.gridOverlay);
    }

    @FXML public void initialize() {
        this.root.getChildren().add(gridOverlay);
        this.toolbar.toFront();
        this.toolbar.getParent().setOnMouseEntered(event -> {  showToolbar();  });
        this.toolbar.getParent().setOnMouseExited(event -> {  hideToolbar();  });

        // Listener which make responsive the ImageView component
        this.preview.getParent().layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                preview.setFitWidth(newValue.getWidth());
                preview.setFitHeight(newValue.getHeight());
                toolbar.setPrefHeight((newValue.getHeight()/10) > 60 ? newValue.getHeight()/10 : 60);
            }
        });

        // Listener which adapt the Grid overlay to the preview component
        this.preview.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                gridOverlay.setPrefSize(newValue.getWidth(), newValue.getHeight());
                gridOverlay.setLayoutX(newValue.getMinX());
                gridOverlay.setLayoutY(newValue.getMinY());
            }
        });
        // Init all listeners (toolbar, mouse shortcut ...)
        this.initControlsMediaPlayer();
        this.initControlsToolbar();

        if(!this.mediaPlayer.isEmpty())
            this.showMedia(this.mediaPlayer.firstMedia());
    }

    /**
     * Update the current Media which is displayed in the preview
     * @param m Media to display
     */
    private void showMedia(Media m) {
        if(m != null) {
            this.preview.setImage(new Image(new File(m.getPath()).toURI().toString()));
            MyLogger.getInstance().log(Level.INFO, "Show: " + m.getPath());
        }

    }

    /**
     * Init listeners for the MediaPlayer
     */
    private void initControlsMediaPlayer() {
        // Listener for the mouse
        this.container.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {  showMedia(mediaPlayer.next());  }
                if(event.getButton() == MouseButton.SECONDARY) {  showMedia(mediaPlayer.previous()); }
            }
        });

        // Listener for the keyboard
        this.root.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT)  showMedia(mediaPlayer.previous());
                if(event.getCode() == KeyCode.RIGHT)  showMedia(mediaPlayer.next());
            }
        });
    }

    /**
     * Init all listeners for differents actions of the toolbar.
     */
    private void initControlsToolbar() {
        if(this.gridOverlay.isVisible())
            this.gridButton.getStyleClass().add("active");
        this.lockButton.getStyleClass().add("active");
        this.gridButton.setOnMouseClicked(event -> {
            gridOverlay.setVisible(!gridOverlay.isVisible());
            MediaPlayerController.applyActiveStyle(gridButton, gridOverlay.isVisible());
        });
        this.fullscreenButton.setOnMouseClicked(event -> {
            Window.getWM().toggleFullscreen();
            MediaPlayerController.applyActiveStyle(fullscreenButton, Window.getWM().isFullscreen());
        });
        this.lockButton.setOnMouseClicked(event -> {
            lockButton.setId((toolbarLocked ? "unlocked" : "locked"));
            toolbarLocked = !toolbarLocked;
            MediaPlayerController.applyActiveStyle(lockButton, lockButton.getId().equals("locked"));
            if(!toolbarLocked)
                hideToolbar();
        });
        this.settingsButton.setOnMouseClicked(event -> Window.getWM().openSettingsWindow());
        this.logsButton.setOnMouseClicked(event -> Window.getWM().openLogsWindows());
    }

    private static void applyActiveStyle(BorderPane p, boolean add) {
        if(add)
            p.getStyleClass().add("active");
        else
            p.getStyleClass().remove("active");
    }

    private void hideToolbar() {
        System.out.println(toolbar.getTranslateY() + " - " + toolbar.getBoundsInParent().getMinY() + " - " + toolbar.getBoundsInParent().getMaxY());
        if(!toolbarLocked) {
            if(this.currentAnimation != null)
                this.currentAnimation.stop();
            toolbar.setMinHeight(container.getMaxHeight() - toolbar.getHeight());
            Timeline timeline = new Timeline();
            KeyValue hidden = new KeyValue(toolbar.translateYProperty(), toolbar.getHeight(), Interpolator.EASE_BOTH);
            KeyFrame keyFrame  = new KeyFrame(Duration.millis(400), hidden);
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(event -> toolbar.setVisible(false));
            this.currentAnimation = timeline;
            timeline.play();
        }
    }

    private void showToolbar() {
        if(!toolbarLocked) {
            if(this.currentAnimation != null)
                this.currentAnimation.stop();
            toolbar.setMinHeight(container.getMaxHeight());
            toolbar.setVisible(true);
            Timeline timeline = new Timeline();
            KeyValue show = new KeyValue(toolbar.translateYProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame keyFrame  = new KeyFrame(Duration.millis(400), show);
            timeline.getKeyFrames().add(keyFrame);
            this.currentAnimation = timeline;
            timeline.play();
        }
    }


}

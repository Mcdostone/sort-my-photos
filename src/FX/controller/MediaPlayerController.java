package FX.controller;

import FX.view.GridOverlay;
import app.model.Media;
import app.model.MediaPlayer;
import app.model.MediaPlayerFactory;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.util.List;

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
    @FXML private BorderPane gridButton;
    @FXML private AnchorPane root;
    @FXML private StackPane toolbar;
    /** Grid Overlay for better sorting */
    private GridOverlay gridOverlay;


    /**
     * Constructor which fills in the mediaPlayer with paths given by the user.
     * @param paths Paths which contains medias to sort
     */
    public MediaPlayerController(List<File> paths) {
        this.mediaPlayer = MediaPlayerFactory.createMediaPlayer(paths);

        // Create the GridOverlay and hide it
        gridOverlay = new GridOverlay();
        this.gridOverlay.setMouseTransparent(true);
        this.gridOverlay.setVisible(false);
        this.gridOverlay.toFront();
    }

    @FXML public void initialize() {
        this.root.getChildren().add(gridOverlay);
        this.toolbar.toFront();

        // Listener which make responsive the ImageView component
        this.preview.getParent().layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                preview.setFitWidth(newValue.getWidth());
                preview.setFitHeight(newValue.getHeight());
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

        this.showMedia(this.mediaPlayer.firstMedia());
    }

    /**
     * Update the current Media which is displayed in the preview
     * @param m Media to display
     */
    private void showMedia(Media m) {
        this.preview.setImage(new Image(new File(m.getPath()).toURI().toString()));
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
        this.container.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT)  showMedia(mediaPlayer.previous());
                if(event.getCode() == KeyCode.RIGHT)  showMedia(mediaPlayer.next());
            }
        });
    }

    private void initControlsToolbar() {
        this.gridButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gridOverlay.setVisible(!gridOverlay.isVisible());
            }
        });

    }

}

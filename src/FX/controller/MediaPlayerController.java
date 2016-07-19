package FX.controller;

import FX.view.GridOverlay;
import app.conf.Configuration;
import app.model.Media;
import app.model.MediaPlayer;
import app.model.MediaPlayerFactory;
import app.model.MyLogger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.File;
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
    @FXML private AnchorPane root;
    @FXML private StackPane toolbarContainer;
    @FXML private ToolbarController toolbarController;
    @FXML private StackPane sortingOverlayContainer;
    @FXML private SortingOverlayController sortingOverlayController;
    /** Grid Overlay for better sorting */
    private GridOverlay gridOverlay;

    /**
     * Constructor which fills in the mediaPlayer with paths given by the user.
     * @param paths Paths which contains medias to sort
     */
    public MediaPlayerController(List<File> paths) {
        this.mediaPlayer = MediaPlayerFactory.createMediaPlayer(paths);
        this.gridOverlay = new GridOverlay(Configuration.getInstance().enableGridAtStartup());
        this.gridOverlay.toFront();
        Configuration.getInstance().addObserver(this.gridOverlay);
    }

    @FXML public void initialize() {
        this.sortingOverlayContainer.setVisible(false);
        this.root.setOnMouseClicked(event -> {
            if(event.getTarget().equals(sortingOverlayContainer))
                Event.fireEvent(this.container, new MouseEvent(MouseEvent.MOUSE_RELEASED, 0, 0, 0, 0, event.getButton(), event.getClickCount(), true, true, true, true, true, true, true, true, true, true, null));
        });
        this.root.getChildren().add(gridOverlay);
        this.sortingOverlayContainer.toFront();
        this.toolbarContainer.toFront();
        this.toolbarController.registerGridOverlay(this.gridOverlay);
        this.toolbarController.registerSortingOverlay(this.sortingOverlayContainer);
        this.toolbarContainer.setOnMouseEntered(event -> {  toolbarController.showToolbar();  });
        this.toolbarContainer.setOnMouseExited(event -> {  toolbarController.hideToolbar();  });



        // Listener which make responsive the ImageView component
        this.preview.getParent().layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            preview.setFitWidth(newValue.getWidth());
            preview.setFitHeight(newValue.getHeight());
            toolbarContainer.setPrefHeight((newValue.getHeight()/10) > 60 ? newValue.getHeight()/10 : 60);
        });

        // Listener which adapt the Grid overlay to the preview component
        this.preview.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            gridOverlay.setPrefSize(newValue.getWidth(), newValue.getHeight());
            gridOverlay.setLayoutX(newValue.getMinX());
            gridOverlay.setLayoutY(newValue.getMinY());
        });
        // Init all listeners (toolbar, mouse shortcut ...)
        this.initControlsMediaPlayer();

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

    /** Init listeners for the MediaPlayer */
    private void initControlsMediaPlayer() {
        // Listener for the mouse
        this.container.setOnMouseReleased(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {  showMedia(mediaPlayer.next());  }
            if(event.getButton() == MouseButton.SECONDARY) {  showMedia(mediaPlayer.previous()); }
        });
        // Listener for the keyboard
        this.root.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.LEFT)  showMedia(mediaPlayer.previous());
            if(event.getCode() == KeyCode.RIGHT)  showMedia(mediaPlayer.next());
        });
    }

}


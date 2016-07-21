package FX.controller;

import FX.view.GridOverlay;
import app.conf.Configuration;
import app.model.*;
import app.model.Media;
import app.model.MediaPlayer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

/**
 * Controller of the MediaPlayer scene.
 *
 * @author Mcdostone
 */
public class MediaPlayerController implements Observer {

    /** The unique MediaPlayer */
    private MediaPlayer mediaPlayer;

    @FXML private AnchorPane root;
    @FXML private BorderPane container;

    @FXML private ToolbarController toolbarController;
    @FXML private SortingOverlayController sortingOverlayController;
    @FXML private InfosOverlayController infosOverlayController;

    @FXML private StackPane toolbarContainer;
    @FXML private StackPane infosContainer;
    @FXML private StackPane sortingOverlayContainer;
    @FXML private StackPane blurContainer;

    private GridOverlay grid;
    @FXML private ImageView preview;
    @FXML private ImageView bluredPreview;

    /**
     * Constructor which fills in the mediaPlayer with paths given by the user.
     * @param paths Paths which contains medias to sort
     */
    public MediaPlayerController(List<File> paths) {
        this.mediaPlayer = MediaPlayerFactory.createMediaPlayer(paths);
        this.mediaPlayer.addObserver(this);
        this.grid = new GridOverlay(Configuration.getInstance().showGridAtStartup());
        Configuration.getInstance().addObserver(this.grid);
    }

    @FXML public void initialize() {
        this.makeNodesResponsive();
        this.initControlsMediaPlayer();

        this.sortingOverlayContainer.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                sortingOverlayController.registerSortingManager(createSortingManager());
        });

        this.grid.setOverPane(this.preview);
        this.root.getChildren().add(this.grid);
        this.grid.toFront();

        this.infosContainer.toFront();
        this.toolbarContainer.toFront();
        this.blurContainer.toBack();
        this.blurContainer.setEffect(new BoxBlur(60, 60, 3));

        this.toolbarController.register("grid", this.grid);
        this.toolbarController.register("infos", this.infosContainer);
        this.toolbarController.register("sorting", this.sortingOverlayContainer);
        this.toolbarContainer.setOnMouseEntered(event ->  toolbarController.showToolbar()  );
        this.toolbarContainer.setOnMouseExited(event ->  toolbarController.hideToolbar()  );

        this.mediaPlayer.addObserver(this.infosOverlayController);
        this.showMedia(this.mediaPlayer.current());
        this.infosOverlayController.update(this.mediaPlayer, null);
    }

    private void makeNodesResponsive() {
        this.root.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            preview.setFitWidth(newValue.getWidth());
            preview.setFitHeight(newValue.getHeight());
            bluredPreview.setFitWidth(newValue.getWidth());
            bluredPreview.setFitHeight(newValue.getHeight());
            double topToolbar = newValue.getHeight() - 80;

            AnchorPane.setTopAnchor(this.toolbarContainer, topToolbar);
            AnchorPane.setLeftAnchor(this.infosContainer, newValue.getWidth() - this.infosContainer.getPrefWidth());
            AnchorPane.setBottomAnchor(infosContainer, newValue.getHeight() - topToolbar);
        });

        this.toolbarContainer.getChildren().get(0).translateYProperty().addListener((observable, oldValue, newValue) ->
            AnchorPane.setBottomAnchor(infosContainer, this.toolbarContainer.getHeight() - newValue.doubleValue())
      );
    }

    /** Init listeners for the MediaPlayer */
    private void initControlsMediaPlayer() {
        // Listener for the mouse
        this.container.setOnMousePressed(event -> {
            if(event.getButton() == MouseButton.PRIMARY)  mediaPlayer.next();
            if(event.getButton() == MouseButton.SECONDARY)  mediaPlayer.previous();
        });
        // Listener for the keyboard
        this.root.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.LEFT)  mediaPlayer.previous();
            if(event.getCode() == KeyCode.RIGHT)  mediaPlayer.next();
        });
        this.root.setOnMousePressed(event -> {
            if(event.getTarget().equals(this.sortingOverlayContainer.getChildren().get(0)))
                Event.fireEvent(this.container, new MouseEvent(MouseEvent.MOUSE_PRESSED, 0, 0, 0, 0, event.getButton(), event.getClickCount(), true, true, true, true, true, true, true, true, true, true, null));
        });
    }

    /**
     * Update the current Media which is displayed in the preview
     * @param m Media to display
     */
    private void showMedia(Media m) {
        if(m != null) {
            Image i = new Image(new File(m.getPath()).toURI().toString());
            m.loadMediaProperties();
            this.bluredPreview.setImage(i);
            this.preview.setImage(i);
            MyLogger.getInstance().log(Level.INFO, "Show: " + m.getPath());
        }
        else
            this.preview.setImage(null);
    }

    private SortingManager createSortingManager() {
        return new SortingManager(this.mediaPlayer, Configuration.getInstance().getAcceptedDirectory(), Configuration.getInstance().getRejectDirectory());
    }

    @Override
    public void update(Observable o, Object arg) {
        this.showMedia(this.mediaPlayer.current());
    }
}


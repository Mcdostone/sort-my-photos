package FX.controller;

import FX.Window;
import FX.view.GridOverlay;
import app.conf.Configuration;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the toolbar.
 *
 * @author Mcdostone
 */
public class ToolbarController {

    @FXML private GridPane toolbar;
    @FXML private Button settingsButton;
    @FXML private Button sortingButton;
    @FXML private Button lockButton;
    @FXML private Button gridButton;
    @FXML private Button fullscreenButton;
    @FXML private Button logsButton;
    private GridOverlay gridOverlay;
    private boolean toolbarLocked;
    private Animation currentAnimation;
    private StackPane sortingOverlay;

    public ToolbarController() {  this.toolbarLocked = Configuration.getInstance().lockToolbar();  }

    @FXML
    public void initialize() {
        ToolbarController.applyActiveStyle(this.lockButton, this.toolbarLocked);
        if(!this.toolbarLocked) {
            lockButton.setId("unlocked");
            this.hideToolbarAtStartup();
        }

        this.gridButton.setOnAction(event -> {
            gridOverlay.setVisible(!gridOverlay.isVisible());
            ToolbarController.applyActiveStyle(gridButton, gridOverlay.isVisible());
        });
        this.fullscreenButton.setOnAction(event -> {
            Window.getWM().toggleFullscreen();
            ToolbarController.applyActiveStyle(fullscreenButton, Window.getWM().isFullscreen());
        });
        this.lockButton.setOnAction(event -> {
            lockButton.setId((toolbarLocked ? "unlocked" : "locked"));
            toolbarLocked = !toolbarLocked;
            ToolbarController.applyActiveStyle(lockButton, toolbarLocked);
            if (!toolbarLocked)
                hideToolbar();
        });
        this.sortingButton.setOnAction(event -> {
            this.sortingOverlay.setVisible(!this.sortingOverlay.isVisible());
            ToolbarController.applyActiveStyle(this.sortingButton, this.sortingOverlay.isVisible());
        });

        this.settingsButton.setOnAction(event -> Window.getWM().openSettingsWindow());
        this.logsButton.setOnAction(event -> Window.getWM().openLogsWindows());
    }

    public void registerGridOverlay(GridOverlay grid) {
        this.gridOverlay = grid;
        if(this.gridOverlay.isVisible())
            this.gridButton.getStyleClass().add("active");
    }

    public void hideToolbarAtStartup(){
    }

    public void hideToolbar() {
        if (!toolbarLocked) {
            if (this.currentAnimation != null)
                this.currentAnimation.stop();
            Timeline timeline = new Timeline();
            KeyValue hidden = new KeyValue(toolbar.translateYProperty(), toolbar.getHeight(), Interpolator.EASE_BOTH);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(400), hidden);
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(event -> toolbar.setVisible(false));
            this.currentAnimation = timeline;

            Timeline empty = new Timeline();

            empty.getKeyFrames().add(new KeyFrame(Duration.millis((toolbar.getTranslateY() == 0.0) ? 200 : 0)));
            SequentialTransition sequence = new SequentialTransition(empty, timeline);
            this.currentAnimation = sequence;
            this.toolbar.getParent().requestFocus();
            sequence.play();
        }
    }

    public void showToolbar() {
        if(!toolbarLocked) {
            if(this.currentAnimation != null)
                this.currentAnimation.stop();
            toolbar.setVisible(true);
            Timeline timeline = new Timeline();
            KeyValue show = new KeyValue(toolbar.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame keyFrame  = new KeyFrame(Duration.millis(200), show);
            timeline.getKeyFrames().add(keyFrame);
            this.currentAnimation = timeline;
            timeline.play();
        }
    }

    private static void applyActiveStyle(Button p, boolean add) {
        if (add)
            p.getStyleClass().add("active");
        else
            p.getStyleClass().remove("active");
    }

    public void registerSortingOverlay(StackPane sortingOverlayContainer) {
        this.sortingOverlay = sortingOverlayContainer;
    }
}

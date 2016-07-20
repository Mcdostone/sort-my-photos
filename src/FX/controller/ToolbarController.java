package FX.controller;

import FX.Window;
import FX.view.GridOverlay;
import app.conf.Configuration;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;

/**
 * Controller for the toolbar.
 *
 * @author Mcdostone
 */
public class ToolbarController {

    @FXML private GridPane toolbarGrid;
    @FXML private Button settingsButton;
    @FXML private Button sortingButton;
    @FXML private Button lockButton;
    @FXML private Button gridButton;
    @FXML private Button fullscreenButton;
    @FXML private Button logsButton;
    @FXML private Button infosButton;

    private boolean toolbarLocked;
    private boolean infosVisible;
    private Animation currentAnimation;
    private Animation infosAnimation;

    private HashMap<String, Parent> binds;

    public ToolbarController() {
        this.toolbarLocked = Configuration.getInstance().lockToolbar();
        this.binds = new HashMap<>();
    }

    @FXML
    public void initialize() {
        this.settingsButton.setOnAction(  event ->  Window.getWM().openSettingsWindow()  );
        this.logsButton.setOnAction(  event ->  Window.getWM().openLogsWindows()  );
        this.fullscreenButton.setOnAction(  event ->  Window.getWM().toggleFullscreen()  );
        this.gridButton.setOnAction(event -> {
            Node grid = this.binds.get("grid");
            grid.setVisible(!grid.isVisible());
            ToolbarController.applyActiveStyle(gridButton, grid.isVisible());
        });
        this.infosButton.setOnAction(event -> {
            if(infosVisible)
                hideInfos();
            else
                showInfos();
            ToolbarController.applyActiveStyle(infosButton, this.infosVisible);
        });
        this.lockButton.setOnAction(event -> {
            lockButton.setId((toolbarLocked ? "unlocked" : "locked"));
            toolbarLocked = !toolbarLocked;
            ToolbarController.applyActiveStyle(lockButton, toolbarLocked);
            if (!toolbarLocked)
                hideToolbar();
        });
        this.sortingButton.setOnAction(event -> {
            Parent p = this.binds.get("sorting");
            p.setVisible(!p.isVisible());
            ToolbarController.applyActiveStyle(this.sortingButton, p.isVisible());
        });

        Window.getWM().getFullsreenProperty().addListener((observable, oldValue, newValue) -> ToolbarController.applyActiveStyle(fullscreenButton, newValue));
    }

    public void register(String componentName, Pane component) {
        this.binds.put(componentName, component);
        switch(componentName) {
            case "grid":
                ToolbarController.applyActiveStyle(this.gridButton, Configuration.getInstance().showGridAtStartup());
                break;
            case "infos":
                this.infosVisible = component.isVisible();
                ToolbarController.applyActiveStyle(this.infosButton, this.infosVisible);

                if(!this.infosVisible)
                    component.setTranslateX(component.getPrefWidth());
                break;
        }

    }

    private void hideInfos() {
        if (this.infosAnimation != null)
            this.infosAnimation.stop();
        Node infos = this.binds.get("infos");
        Timeline timeline = new Timeline();
        infosVisible = false;
        KeyValue hidden = new KeyValue(infos.translateXProperty(), infos.getBoundsInParent().getWidth(), Interpolator.EASE_BOTH);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(400), hidden);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> infos.setVisible(false));
        this.infosAnimation = timeline;

        timeline.play();
    }

    private void showInfos() {
        if (this.infosAnimation != null)
            this.infosAnimation.stop();
        Node infos = this.binds.get("infos");
        infos.setVisible(true);
        this.infosVisible = true;
        Timeline timeline = new Timeline();
        KeyValue hidden = new KeyValue(infos.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(200), hidden);
        timeline.getKeyFrames().add(keyFrame);
        this.infosAnimation = timeline;

        timeline.play();
    }

    public void hideToolbar() {
        Parent toolbar = this.toolbarGrid.getParent();
        if (!this.toolbarLocked) {
            if (this.currentAnimation != null)
                this.currentAnimation.stop();

            Timeline timeline = new Timeline();
            KeyValue hidden = new KeyValue(toolbar.translateYProperty(), toolbar.getBoundsInParent().getHeight(), Interpolator.EASE_BOTH);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(400), hidden);
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(event -> toolbar.setVisible(false));
            Timeline empty = new Timeline();
            empty.getKeyFrames().add(new KeyFrame(Duration.millis((toolbar.getTranslateY() == 0.0) ? 200 : 0)));
            SequentialTransition sequence = new SequentialTransition(empty, timeline);
            this.currentAnimation = sequence;

            sequence.play();
        }
    }

    public void showToolbar() {
        Parent toolbar = this.toolbarGrid.getParent();
        if(!this.toolbarLocked) {
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

}

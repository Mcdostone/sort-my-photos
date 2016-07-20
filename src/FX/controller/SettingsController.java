package FX.controller;

import app.conf.Configuration;
import app.model.MyLogger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

/**
 * Controller for the settings scene.
 *
 * @author Mcdostone
 */
public class SettingsController implements Observer {


    @FXML private Button changePath;
    @FXML private Label defaultPath;
    @FXML private CheckBox lockToolbar;
    @FXML private CheckBox enableGrid;
    @FXML private ColorPicker colorPicker;
    @FXML private TextField shortcutAccept;
    @FXML private TextField shortcutReject;
    @FXML private Button reset;
    private int limit = 1;

    public SettingsController() {  Configuration.getInstance().addObserver(this);  }

    @FXML public void initialize() {
        SettingsController.limitTextField(this.shortcutAccept, 1);
        SettingsController.limitTextField(this.shortcutReject, 1);
        this.changePath.setOnAction(t -> { changeDefaultPath();  });
        this.lockToolbar.setOnAction(t -> {
            Configuration.getInstance().setLockToolbar(lockToolbar.isSelected());
            MyLogger.getInstance().log(Level.CONFIG, "Lock toolbar: " + lockToolbar.isSelected());
        });
        this.enableGrid.setOnAction(t -> {
            Configuration.getInstance().setShowGrid(enableGrid.isSelected());
            MyLogger.getInstance().log(Level.CONFIG, "Enable grid: " + enableGrid.isSelected());
        });
        this.colorPicker.setOnAction(t -> {
            Configuration.getInstance().setColorGrid(colorPicker.getValue());
            MyLogger.getInstance().log(Level.CONFIG, "Color of grid: " + colorPicker.getValue());
        });
        this.shortcutAccept.textProperty().addListener(event ->
            Configuration.getInstance().setShortcutAccept(shortcutAccept.getText())
        );
        this.shortcutReject.textProperty().addListener(event ->
            Configuration.getInstance().setShortcutReject(shortcutReject.getText())
        );

        this.reset.setOnAction(t -> {
            Configuration.getInstance().reset();
            MyLogger.getInstance().log(Level.CONFIG, "Reset config");
        });

        this.reset.requestLayout();

        this.update(null, null);
    }

    /** Launch a DirecyoryChooser in order to choose the default path */
    private void changeDefaultPath() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose the default path");
        chooser.setInitialDirectory(new File(Configuration.getInstance().getDefaultPath()));
        File selectedFolder = chooser.showDialog(this.colorPicker.getScene().getWindow());
        if(selectedFolder != null && !selectedFolder.getAbsolutePath().equals(Configuration.getInstance().getDefaultPath())) {
            Configuration.getInstance().setDefaultPath(selectedFolder.getAbsolutePath());
            MyLogger.getInstance().log(Level.CONFIG, "Default path: " + selectedFolder.getAbsolutePath());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.defaultPath.setText(Configuration.getInstance().getDefaultPath());
        this.lockToolbar.setSelected(Configuration.getInstance().lockToolbar());
        this.enableGrid.setSelected(Configuration.getInstance().showGridAtStartup());
        this.colorPicker.setValue(Configuration.getInstance().getColorGrid());
        this.shortcutAccept.setText(Configuration.getInstance().getShortcutAccept());
        this.shortcutReject.setText(Configuration.getInstance().getShortcutReject());
    }

    private static void limitTextField(TextField t, int limit) {
        t.textProperty().addListener((observable, oldValue, newValue) -> {
            if(t.getText().length() > limit)
                t.setText(t.getText().substring(0, limit));
        });
    }

}
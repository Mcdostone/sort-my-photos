package FX.controller;

import app.conf.Configuration;
import app.model.MyLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
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
    @FXML private Button reset;

    public SettingsController() {  Configuration.getInstance().addObserver(this);  }

    @FXML public void initialize() {
        this.defaultPath.setText(Configuration.getInstance().getDefaultPath());
        this.enableGrid.setSelected(Configuration.getInstance().enableGridAtStartup());
        this.colorPicker.setValue(Configuration.getInstance().getColorGrid());
        this.lockToolbar.setSelected(Configuration.getInstance().lockToolbar());

        this.changePath.setOnAction(t -> { changeDefaultPath();  });
        this.lockToolbar.setOnAction(t -> {
            Configuration.getInstance().setLockToolbar(lockToolbar.isSelected());
            MyLogger.getInstance().log(Level.CONFIG, "Lock toolbar: " + lockToolbar.isSelected());
        });
        this.enableGrid.setOnAction(t -> {
            Configuration.getInstance().setEnableGrid(enableGrid.isSelected());
            MyLogger.getInstance().log(Level.CONFIG, "Enable grid: " + enableGrid.isSelected());
        });
        this.colorPicker.setOnAction(t -> {
            Configuration.getInstance().setColorGrid(colorPicker.getValue());
            MyLogger.getInstance().log(Level.CONFIG, "Color of grid: " + colorPicker.getValue());
        });
        this.reset.setOnAction(t -> {
            Configuration.getInstance().reset();
            MyLogger.getInstance().log(Level.CONFIG, "Reset config");
        });

        this.reset.requestLayout();
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
        this.enableGrid.setSelected(Configuration.getInstance().enableGridAtStartup());
        this.colorPicker.setValue(Configuration.getInstance().getColorGrid());
    }
}

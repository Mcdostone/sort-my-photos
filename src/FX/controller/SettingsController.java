package FX.controller;

import app.conf.Configuration;
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

/**
 * Controller for the settings scene.
 *
 * @author Mcdostone
 */
public class SettingsController implements Observer {

    @FXML private ColorPicker colorPicker;
    @FXML private CheckBox enableGrid;
    @FXML private Label defaultPath;
    @FXML private Button changePath;
    @FXML private Button save;
    @FXML private Button reset;

    public SettingsController() {
        Configuration.getInstance().addObserver(this);
    }

    @FXML public void initialize() {
        this.save.setOnAction(t -> {
            Stage stage = (Stage) save.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        this.reset.setOnAction(t -> {
            Configuration.getInstance().reset();
            Configuration.getInstance().addObserver(this);
            Configuration.getInstance().notifyObservers();
        });
        this.defaultPath.setText(Configuration.getInstance().getDefaultPath());
        this.enableGrid.setSelected(Configuration.getInstance().enableGridAtStartup());
        this.colorPicker.setValue(Configuration.getInstance().getColorGrid());

        this.changePath.setOnAction(t -> { changeDefaultPath();  });
        this.enableGrid.setOnAction(t -> {  Configuration.getInstance().setEnableGrid(enableGrid.isSelected());  });
        this.colorPicker.setOnAction(t -> {  Configuration.getInstance().setColorGrid(colorPicker.getValue());  });
    }

    /** Launch a DirecyoryChooser in order to choose the default path */
    private void changeDefaultPath() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose the default path");
        chooser.setInitialDirectory(new File(Configuration.getInstance().getDefaultPath()));
        File selectedFolder = chooser.showDialog(this.colorPicker.getScene().getWindow());
        if(selectedFolder != null)
            Configuration.getInstance().setDefaultPath(selectedFolder.getAbsolutePath());
    }

    @Override
    public void update(Observable o, Object arg) {
        this.defaultPath.setText(Configuration.getInstance().getDefaultPath());
        this.enableGrid.setSelected(Configuration.getInstance().enableGridAtStartup());
        this.colorPicker.setValue(Configuration.getInstance().getColorGrid());
    }
}

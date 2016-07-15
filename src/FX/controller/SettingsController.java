package FX.controller;

import app.conf.Configuration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller for the settings scene
 *
 * @author Mcdostone
 */
public class SettingsController {

    @FXML private ColorPicker colorPicker;
    @FXML private CheckBox enableGrid;
    @FXML private Button save;

    @FXML public void initialize() {
        this.save.setOnAction(t -> {
            Stage stage = (Stage) save.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        this.enableGrid.setSelected(Configuration.getInstance().enableGridAtStartup());
        this.colorPicker.setValue(Configuration.getInstance().getColorGrid());

        this.enableGrid.setOnAction(t -> {  Configuration.getInstance().setEnableGrid(enableGrid.isSelected());  });
        this.colorPicker.setOnAction(t -> {  Configuration.getInstance().setColorGrid(colorPicker.getValue());  });
    }
}

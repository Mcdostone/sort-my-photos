package FX.controller;

import app.conf.Configuration;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;

/**
 * Controller for the settings scene
 *
 * @author Mcdostone
 */
public class SettingsController {

    @FXML private ColorPicker colorPicker;

    @FXML public void initialize() {
        this.colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
                System.out.println(colorPicker.getValue());
                Configuration.COLOR_GRID = colorPicker.getValue();
            }
        });
    }
}

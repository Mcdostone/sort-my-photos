package FX.controller;

import app.conf.Configuration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the sorting overlay.
 *
 * @author Mcdostone
 */
public class SortingOverlayController {

    @FXML private Button acceptButton;
    @FXML private Button rejectButton;

    public SortingOverlayController() {

    }

    @FXML
    public void initialize() {
        this.acceptButton.setOnMousePressed(event -> {
            acceptButton.setText(Configuration.getInstance().getShortcutAccept());
            acceptButton.setId(null);
        });
        this.acceptButton.setOnMouseReleased(event -> {
            acceptButton.setText(null);
            acceptButton.setId("accept");
        });

        this.rejectButton.setOnMousePressed(event -> {
            rejectButton.setText(Configuration.getInstance().getShortcutReject());
            rejectButton.setId(null);
        });
        this.rejectButton.setOnMouseReleased(event -> {
            rejectButton.setText(null);
            rejectButton.setId("reject");
        });
    }

}

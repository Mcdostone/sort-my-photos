package FX.controller;

import app.conf.Configuration;
import app.model.SortingManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Observable;
import java.util.Observer;

/**
 * Controller for the sorting overlay.
 *
 * @author Mcdostone
 */
public class SortingOverlayController implements Observer {

    @FXML private Button acceptButton;
    @FXML private Button rejectButton;
    private SortingManager sortingManager;

    public SortingOverlayController() {
        Configuration.getInstance().addObserver(this);
    }

    @FXML
    public void initialize() {
        this.acceptButton.setOnMouseEntered(event -> {
            acceptButton.setText(Configuration.getInstance().getShortcutAccept());
            acceptButton.setId(null);
        });
        this.acceptButton.setOnMouseExited(event -> {
            acceptButton.setText(null);
            acceptButton.setId("accept");
        });
        this.acceptButton.setOnMouseReleased(event -> {  this.sortingManager.acceptMedia(); });

        this.rejectButton.setOnMouseExited(event -> {
            rejectButton.setText(null);
            rejectButton.setId("reject");
        });
        this.rejectButton.setOnMouseEntered(event -> {
            rejectButton.setText(Configuration.getInstance().getShortcutReject());
            rejectButton.setId(null);
        });
        this.rejectButton.setOnMouseReleased(event -> {  this.sortingManager.rejectMedia();  });
    }

    public void accept() {  this.sortingManager.acceptMedia();  }

    public void reject() {  this.sortingManager.rejectMedia();  }

    public void registerSortingManager(SortingManager sortingManager) {
        if(this.sortingManager == null)
            this.sortingManager = sortingManager;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(this.sortingManager != null) {
            this.sortingManager.setAcceptedDirectory(Configuration.getInstance().getAcceptedDirectory());
            this.sortingManager.setRejectedDirectory(Configuration.getInstance().getRejectDirectory());
        }
    }
}

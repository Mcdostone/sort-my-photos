package FX.controller;

import app.model.Media;
import app.model.MediaPlayer;
import app.util.DesktopRuntime;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

/**
 * Controller for the information pane.
 *
 * @author Mcdostone
 */
public class InfosOverlayController implements Observer {

    @FXML BorderPane infosBorderPane;
    @FXML Label filename;
    @FXML Label typeFile;
    @FXML Label fileSize;

    GridPane mediaProperties;
    private Media current;

    public InfosOverlayController() {
    }

    @FXML public void initialize() {
        this.filename.setOnMouseClicked(event -> DesktopRuntime.showFile(this.current.getPath())  );
    }

    @Override
    public void update(Observable o, Object arg) {
        MediaPlayer mediaPlayer = (MediaPlayer) o;
        this.current = mediaPlayer.current();

        this.filename.setText(Paths.get(this.current.getPath()).getFileName().toString());
        this.fileSize.setText(InfosOverlayController.humanReadableByteCount(new File(this.current.getPath()).length(), false));
        this.typeFile.setText(this.current.getMimetype());

        this.infosBorderPane.setBottom(InfosOverlayController.createMediaPropertiesGrid(this.current));
    }

    private static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    private static GridPane createMediaPropertiesGrid(Media m) {
        GridPane grid = new GridPane();
        ColumnConstraints col = new ColumnConstraints();
        RowConstraints row = new RowConstraints();
        row.setPrefHeight(30);
        row.setMinHeight(10);
        row.setPercentHeight(-1);
        col.setPercentWidth(50);
        grid.getColumnConstraints().addAll(col, col);

        int nbRows = 0;

        for(String propertyName: m.getProperties().keySet()) {
            Label property = new Label(propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1) + ":");
            Label value = new Label(m.getProperties().get(propertyName).toString());
            property.getStyleClass().add("label-settings");
            value.getStyleClass().add("label-infos");

            GridPane.setRowIndex(property, nbRows);
            GridPane.setColumnIndex(property, 0);
            GridPane.setRowIndex(value, nbRows);
            GridPane.setColumnIndex(value, 1);
            grid.getChildren().addAll(property, value);

            nbRows++;
        }

        for(int i = 0; i < nbRows; i++)
            grid.getRowConstraints().add(row);

        return grid;
    }

}

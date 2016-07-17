package FX.controller;

import FX.Window;
import app.conf.Configuration;
import app.model.MyLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Controller for the homepage of the app
 *
 * @author Mcdostone
 */
public class HomeController {

    @FXML private Button chooseFolder;
    @FXML private HBox dropZone;

    @FXML public void initialize() {
        this.chooseFolder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle("Choose medias");
                chooser.setInitialDirectory(new File(Configuration.getInstance().getDefaultPath()));
                File selectedFolder = chooser.showDialog(null);
                if(selectedFolder != null) {
                    ArrayList<File> tmp = new ArrayList<File>();
                    tmp.add(selectedFolder);
                    runMediaPlayer(tmp);
                }
            }
        });

        this.dropZone.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dropZone && event.getDragboard().hasFiles())
                    event.acceptTransferModes(TransferMode.COPY);
                event.consume();
            }
        });

        this.dropZone.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                List<File> paths = (ArrayList<File>) db.getContent(DataFormat.FILES);
                boolean success = false;
                if (paths != null) {
                    runMediaPlayer(paths);
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    private void runMediaPlayer(List<File> selectedFolders) {
        if(selectedFolders != null && !selectedFolders.isEmpty()) {
            MyLogger.getInstance().log(Level.INFO, "Directory: " + selectedFolders.toString());
            Window.getWM().startMediaPlayer(selectedFolders);
        }
    }
}

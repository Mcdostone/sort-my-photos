package FX.controller;

import FX.Window;
import app.conf.Configuration;
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

/**
 * Controller for the button to choose a folder to handle.
 *
 * @author Mcdostone
 */
public class HomeController {

    @FXML
    private Button chooseFolder;

    @FXML
    private HBox dropZone;

    @FXML
    public void initialize() {
        this.chooseFolder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle("Choose medias");
                chooser.setInitialDirectory(new File(Configuration.DEFAULT_PATH));
                File selectedFolder = chooser.showDialog(null);
                ArrayList<File> tmp = new ArrayList<File>();
                tmp.add(selectedFolder);
                runMediaPlayer(tmp);
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
                List<File> files = (ArrayList<File>) db.getContent(DataFormat.FILES);
                boolean success = false;
                if (files != null) {
                    for(File f: files)
                        System.out.println(f);

                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }

        });
    };


    private void runMediaPlayer(List<File> selectedFolders) {
        if(selectedFolders != null && !selectedFolders.isEmpty())
            Window.getWM().startMediaPlayer(selectedFolders);

    }



}

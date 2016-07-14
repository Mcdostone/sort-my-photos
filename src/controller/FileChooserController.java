package controller;

import app.conf.Configuration;
import app.view.swing.LogsWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by Yann on 14/07/2016.
 */
public class FileChooserController {

    @FXML
    private Button chooseFolder;

    public FileChooserController() {
        System.out.println("first");
    }

    @FXML
    public void initialize() {
        System.out.println("second");

        this.chooseFolder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle("Choose medias");
                chooser.setInitialDirectory(new File(Configuration.DEFAULT_PATH));
                File selectedFolder = chooser.showDialog(null);
                Stage stage = (Stage) chooseFolder.getScene().getWindow();
                stage.hide();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/starterView.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}

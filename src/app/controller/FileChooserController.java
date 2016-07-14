package app.controller;

import app.conf.Configuration;
import app.view.swing.LogsWindow;
import app.view.swing.Window;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.io.File;

/**
 * Controller for the start page button.
 * When the button is triggered, it displays a FileChooser.
 *
 * @author Mcdostone
 */
public class FileChooserController extends MouseAdapter {

    private Window w;

    /**
     * Unique constructor
     * @param w Window that will be updated when chooser is validated
     */
    public FileChooserController(Window w) {
        this.w = w;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(Configuration.DEFAULT_PATH));
        chooser.setDialogTitle("Choose ...");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ArrayList<File> list = new ArrayList<>();

        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            LogsWindow.getInstance().update("LOCATION: " + chooser.getSelectedFile() + "\n");

            list.add(chooser.getSelectedFile());
            this.w.launchMediaPlayer(list);
        } else
            System.out.println("No Selection ");
    }


}

package app.controller;

import app.conf.Configuration;
import app.view.swing.HomePanel;
import app.view.swing.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.io.File;

/**
 * Created by Gang du stud' on 14/06/2016.
 */
public class FileChooserController implements MouseListener {

    private HomePanel p;
    private Window w;

    public FileChooserController(HomePanel p, Window w) {
        this.p = p;
        this.w = w;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(Configuration.DEFAULT_PATH));
        chooser.setDialogTitle("Choose ...");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ArrayList<File> list = new ArrayList<>();

        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(p) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            list.add(chooser.getCurrentDirectory());
            this.w.launchMediaPlayer(list);
        } else
            System.out.println("No Selection ");
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

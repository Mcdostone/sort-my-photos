package app.controller;

import app.view.swing.SettingsWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Gang du stud' on 15/06/2016.
 */
public class ActionIconController extends MouseAdapter {

    private String action;

    public ActionIconController(String action) {
        super();
        this.action = action;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(this.action.toUpperCase()) {
            case "SETTINGS":
                SettingsWindow.createInstance();
                break;
        }


    }
}

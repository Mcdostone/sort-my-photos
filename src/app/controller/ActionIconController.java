package app.controller;

import app.view.swing.LogsWindow;
import app.view.swing.SettingsWindow;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller which displays the appropriated window, depending of the type of the icon.
 *
 * @author Mcdostone
 */
public class ActionIconController extends MouseAdapter {

    /** Type of action: SETTINGS/LOGS/... */
    private String action;
    private JPanel panel;

    public ActionIconController(String action, JPanel p) {
        super();
        this.action = action;
        this.panel = p;
    }

    @Override
    /** Create the dedicated window */
    public void mouseClicked(MouseEvent e) {
        switch(this.action.toUpperCase()) {
            case "SETTINGS":
                SettingsWindow.createInstance();
                break;
            case "LOGS":
                LogsWindow.createInstance().setVisible(true);
                break;
            case "SORT":
                this.panel.setVisible(!this.panel.isVisible());
                break;
        }


    }
}

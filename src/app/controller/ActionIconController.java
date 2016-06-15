package app.controller;

import app.view.LogsWindow;
import app.view.swing.SettingsWindow;

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

    public ActionIconController(String action) {
        super();
        this.action = action;
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
        }


    }
}

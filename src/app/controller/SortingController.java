package app.controller;

import app.conf.Configuration;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Yann on 18/06/2016.
 */
public class SortingController extends MouseAdapter {

    public static final String ACCEPT = "ACCEPT";
    public static final String REJECT = "REJECT";

    private JLabel l;
    private Icon backup;
    private String action;

    public SortingController(JLabel l, String action) {
        this.l = l;
        this.backup = this.l.getIcon();
        this.action = action;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        switch (this.action) {
            case SortingController.ACCEPT:
                this.l.setIcon(null);
                this.l.setText(app.conf.Configuration.ACCEPT_SHORCUT);
                break;
            case SortingController.REJECT:
                this.l.setIcon(null);
                this.l.setText(Configuration.REJECT_SHORTCUT);
                break;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.l.setText(null);
        this.l.setIcon(this.backup);
    }

}

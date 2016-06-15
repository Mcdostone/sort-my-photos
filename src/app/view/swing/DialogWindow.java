package app.view.swing;

import javax.swing.*;
import java.awt.*;

/**
 * A DialogWindow is a tiny window that brings some informations.
 * This Window is the main window of the app!
 *
 * @author Mcdostone
 */
public abstract class DialogWindow extends JFrame {

    protected DialogWindow(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(400 ,400));
        this.setLayout(new BorderLayout());
        this.pack();
        this.requestFocus();
    }

    public abstract void update(String data);
}

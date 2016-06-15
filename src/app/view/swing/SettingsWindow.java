package app.view.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

/**
 * Window which enables to modify settings of the app.
 *
 * @author Mcdostone
 */
public class SettingsWindow extends JFrame {

    /** Singleton */
    private static SettingsWindow window;

    private SettingsWindow() {
        super("Settings");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(400 ,400));
        this.setLayout(new BorderLayout());
        // First view
        this.pack();
        this.requestFocus();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                setVisible(false);
                dispose();
                SettingsWindow.window = null;
            }
        });
    }

    public static void createInstance() {
        if(SettingsWindow.window == null)
            SettingsWindow.window = new SettingsWindow();
        else
            SettingsWindow.window.requestFocus();
    }
}

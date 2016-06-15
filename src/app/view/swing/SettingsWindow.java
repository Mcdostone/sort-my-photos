package app.view.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Window which enables to modify settings of the app.
 *
 * @author Mcdostone
 */
public class SettingsWindow extends DialogWindow {

    private static SettingsWindow window;

    private SettingsWindow() {
        super("Settings");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                setVisible(false);
                dispose();
                SettingsWindow.window = null;
            }
        });

    }

    public static DialogWindow createInstance() {
        if(SettingsWindow.window == null)
            SettingsWindow.window = new SettingsWindow();
        else
            SettingsWindow.window.requestFocus();

        SettingsWindow.window.setVisible(true);
        return SettingsWindow.window;
    }

    @Override
    public void update(String data) {}
}

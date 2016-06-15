package app.view;

import app.view.swing.DialogWindow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Window which displays logs of the app.
 *
 * @author Mcdostone
 */
public class LogsWindow extends DialogWindow {

    private static LogsWindow window;

    private JTextArea textArea = new JTextArea();

    protected LogsWindow() {
        super("Logs");
        this.add(new JScrollPane(textArea));
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                setVisible(false);
                dispose();
            }
        });
    }

    public static DialogWindow createInstance() {
        if(LogsWindow.window == null)
            LogsWindow.window = new LogsWindow();

        return LogsWindow.window;
    }

    public void update(String data) {
        this.textArea.append(data + "\n");
        this.validate();
    }
}

package app.view.swing;

import app.util.UiKit;

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
        this.textArea.setEditable(false);
        this.textArea.setFocusable(false);
        this.textArea.setFont(UiKit.LOG_FONT);
        //this.textArea.setBackground(UiKit.BACKGROUND_TABBAR);
        //this.textArea.setForeground(UiKit.PRIMARY_COLOR);
        this.add(new JScrollPane(textArea));

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                setVisible(false);
                dispose();
            }
        });
    }

    public static DialogWindow getInstance() {
        if(LogsWindow.window == null)
            LogsWindow.window = new LogsWindow();

        return LogsWindow.window;
    }

    public void update(String data) {
        this.textArea.append(data + "\n");
        this.validate();
    }
}

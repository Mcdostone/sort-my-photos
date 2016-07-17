package FX.controller;

import app.model.MyLogger;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Controller for the Logs view
 *
 * @author Mcdostone
 */
public class LogsController implements Observer {

    @FXML TextArea logsArea;

    @FXML public void initialize() {
        MyLogger.getInstance().addObserver(this);
        MyLogger.getInstance().log(Level.INFO, "Welcome");
    }

    @Override
    public void update(Observable o, Object arg) {
        LogRecord log = (LogRecord)arg;
        this.logsArea.appendText(log.getLevel() + "\t" + log.getMessage() + "\n");
    }
}

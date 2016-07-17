package app.model;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.*;

/**
 * Logger for the app
 *
 * @author Mcdostone
 */
public class MyLogger extends Observable {

    private Logger logger;
    //private static final String filelog = "sort_my_photos_app.log";
    private static MyLogger myLogger;

    private MyLogger() throws IOException {
        this.logger = Logger.getGlobal();
        //FileHandler handler = new FileHandler(MyLogger.filelog, 10000, 10000, true);
        //handler.setFormatter(new SimpleFormatter());
        //this.logger.addHandler(handler);
        this.logger.setLevel(Level.CONFIG);
    }

    public void log(Level level, String msg) {
        String className = new Exception().getStackTrace()[1].getClassName();
        this.logger.logp(level, className, null, msg);

        this.setChanged();
        this.notifyObservers(new LogRecord(level, msg));
    }

    public static MyLogger getInstance() {
        if (MyLogger.myLogger == null)
            try {
                MyLogger.myLogger = new MyLogger();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return MyLogger.myLogger;
    }

}

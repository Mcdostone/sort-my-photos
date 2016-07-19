package app.conf;

import app.model.MyLogger;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.Observable;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Configuration of the app
 *
 * @author Mcdostone
 */
public class Configuration extends Observable {

    private static final String CONFIG_FILE = "config.properties";

    public String TITLE = "Sort my medias";
    public int WIDTH = 800;
    public int HEIGHT = 800;
    private String defaultPath = ".";
    public String SUPPORTED_MIME_TYPES = "image png tif jpg jpeg bmp";
    public String SETTINGS_ICON = "./assets/icons/settings.png";
    public String SORT_ICON = "./assets/icons/sort-2.png";
    public String LOGS_ICON = "./assets/icons/logs.png";
    public String ACCEPT_ICON = "./assets/icons/accept.png";
    public String REJECT_ICON = "./assets/icons/reject.png";
    private Color colorGrid = Color.ALICEBLUE;
    private boolean enableGridAtStartup = false;
    private boolean lockToolbar = true;
    private String shortcutAccept = "V";
    private String shortcutReject = "A";

    private static Configuration config;


    private Configuration() {}

    public static Configuration getInstance() {
        if(Configuration.config == null)
            Configuration.config = Configuration.load();
        return Configuration.config;
    }

    public void save() {
        Properties prop = new Properties();
        OutputStream output;
        try {
            output = new FileOutputStream(CONFIG_FILE);
            prop.setProperty("enableGrid", String.valueOf(this.enableGridAtStartup()));
            prop.setProperty("colorGrid", this.getColorGrid().toString());
            prop.setProperty("defaultPath", this.getDefaultPath());
            prop.setProperty("lockToolbar", String.valueOf(this.lockToolbar()));
            prop.setProperty("shortcutAccept", this.getShortcutAccept());
            prop.setProperty("shortcutReject", this.getShortcutReject());
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }



    public static Configuration load() {
        MyLogger.getInstance().log(Level.CONFIG, "Load: '" + Configuration.CONFIG_FILE + "'");
        Properties prop = new Properties();
        InputStream input;
        Configuration conf = new Configuration();

        try {
            input = new FileInputStream(Configuration.CONFIG_FILE);
            if(input != null) {
                prop.load(input);
                if(prop.containsKey("enableGrid"))
                    conf.setEnableGrid(Boolean.parseBoolean(prop.getProperty("enableGrid")));
                if(prop.containsKey("colorGrid"))
                    conf.setColorGrid(Color.web(prop.getProperty("colorGrid")));
                if(prop.containsKey("lockToolbar"))
                    conf.setLockToolbar(Boolean.parseBoolean(prop.getProperty("lockToolbar")));
                if(prop.containsKey("shortcutAccept"))
                    conf.setShortcutAccept(prop.getProperty("shortcutAccept"));
                if(prop.containsKey("shortcutReject"))
                conf.setShortcutReject(prop.getProperty("shortcutReject"));
                if(prop.containsKey("defaultPath"))
                    conf.setDefaultPath(prop.getProperty("defaultPath"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No config file!\n" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conf;
    }

    public void reset() {
        Configuration tmp = new Configuration();

        this.setDefaultPath(tmp.getDefaultPath());
        this.setColorGrid(tmp.getColorGrid());
        this.setEnableGrid(tmp.enableGridAtStartup());
        this.setLockToolbar(tmp.lockToolbar());
        this.setShortcutAccept(tmp.getShortcutAccept());
        this.setShortcutReject(tmp.getShortcutReject());

        this.setChanged();
        this.notifyObservers();
    }

    public void setEnableGrid(boolean enable) {
        this.enableGridAtStartup = enable;
    }

    public boolean enableGridAtStartup() {  return this.enableGridAtStartup;  }

    public Color getColorGrid() {
        return this.colorGrid;
    }

    public String getDefaultPath() {  return this.defaultPath;  }

    public void setLockToolbar(boolean lockToolbar) {  this.lockToolbar = lockToolbar;  }

    public boolean lockToolbar() {  return this.lockToolbar;  }

    public void setDefaultPath(String path) {
        this.defaultPath = path;
        this.clearChanged();
        this.setChanged();
        this.notifyObservers();
    }

    public void setColorGrid(Color c) {
        this.colorGrid = c;
        this.clearChanged();
        this.setChanged();
        this.notifyObservers();
    }


    public String getShortcutAccept() {
        return this.shortcutAccept;
    }

    public String getShortcutReject() {
        return this.shortcutReject;
    }

    public void setShortcutAccept(String s) {
        s = s.toUpperCase();
        if(s.length() != 0 && !s.equals(this.getShortcutReject()))
            this.shortcutAccept = s;
    }

    public void setShortcutReject(String s) {
        s = s.toUpperCase();
        if(s.length() != 0 && !s.equals(this.getShortcutAccept()))
            this.shortcutReject = s;
    }
}

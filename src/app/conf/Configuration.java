package app.conf;

import javafx.scene.paint.Color;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import java.io.*;
import java.util.Observable;
import java.util.Properties;

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
    private String DEFAULT_PATH = ".";
    public String SUPPORTED_MIME_TYPES = "image png tif jpg jpeg bmp";
    public String SETTINGS_ICON = "./assets/icons/settings.png";
    public String SORT_ICON = "./assets/icons/sort-2.png";
    public String LOGS_ICON = "./assets/icons/logs.png";
    public String ACCEPT_ICON = "./assets/icons/accept.png";
    public String REJECT_ICON = "./assets/icons/reject.png";
    private Color COLOR_GRID = Color.ALICEBLUE;
    private boolean enableGridAtStartup = true;
    public String ACCEPT_SHORCUT= "V";
    public String REJECT_SHORTCUT = "A";

    private static Configuration config;

    private Configuration() {}

    public static Configuration getInstance() {
        if(Configuration.config == null)
            Configuration.config = Configuration.load();

        return Configuration.config;
    }

    public void setDefaultPath(String path) {
        this.DEFAULT_PATH = path;
        this.setChanged();
        this.notifyObservers();
    }

    public String getDefaultPath() {
        return this.DEFAULT_PATH;
    }

    public void setColorGrid(Color c) {
        this.COLOR_GRID = c;
        this.setChanged();
        this.notifyObservers();
    }

    public void save() {
        Properties prop = new Properties();
        OutputStream output;
        try {
            output = new FileOutputStream(CONFIG_FILE);
            prop.setProperty("enableGrid", String.valueOf(this.enableGridAtStartup()));
            prop.setProperty("colorGrid", this.getColorGrid().toString());
            prop.setProperty("defaultPath", this.getDefaultPath());
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void reset() {
        Configuration.config = new Configuration();
    }

    public static Configuration load() {
        Properties prop = new Properties();
        InputStream input;
        Configuration conf = new Configuration();

        try {
            input = new FileInputStream(Configuration.CONFIG_FILE);
            if(input != null) {
                prop.load(input);
                conf.setEnableGrid(Boolean.parseBoolean(prop.getProperty("enableGrid")));
                conf.setColorGrid(Color.web(prop.getProperty("colorGrid")));
                if(prop.getProperty("defaultPath") != null)
                    conf.setDefaultPath(prop.getProperty("defaultPath"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No config file!\n" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conf;
    }

    public void setEnableGrid(boolean enable) {
        this.enableGridAtStartup = enable;
    }

    public boolean enableGridAtStartup() {  return this.enableGridAtStartup;  }

    public Color getColorGrid() {
        return this.COLOR_GRID;
    }
}

package app.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Enable to run the explorer file to show a given file.
 *
 * @author Mcdostone
 */
public class DesktopRuntime {

    public static void showFile(String path) {
        if(System.getProperty("os.name").toUpperCase().startsWith("WINDOWS")) {
            try {
                Runtime.getRuntime().exec("explorer.exe /select," + path);
            } catch (IOException e) {  e.printStackTrace();  }
        }
        else {
            try {
                Desktop.getDesktop().edit(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

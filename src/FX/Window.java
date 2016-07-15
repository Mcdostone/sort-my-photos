package FX;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of the software, the window
 *
 * @author Mcdostone
 */
public class Window extends Application {

    // Window Manager of this window !
    private static WindowManager wm;


    @Override
    public void start(Stage primaryStage) throws Exception{
        WindowManager wm = new WindowManager(primaryStage);
        wm.startHomePage();
        Window.wm = wm;
    }

    /** @return the WindowManager */
    public static WindowManager getWM() {
        return Window.wm;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

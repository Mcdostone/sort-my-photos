package FX; /**
 * Created by Yann on 13/07/2016.
 */

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Main class of the software
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

    public static WindowManager getWM() {
        return Window.wm;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

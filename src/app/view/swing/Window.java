package app.view.swing;

import javax.swing.*;
import app.conf.Configuration;
import app.model.MediaPlayer;

/**
 * Window of the app
 *
 * @author Mcdostone
 */
public class Window extends JFrame {

    private JPanel main;

    public Window() {
        super(Configuration.TITLE);
        this.init();
    }

    public Window(String title) {
        super(title);
        this.init();
    }

    private void init(){
        MediaPlayer m = new MediaPlayer();
        this.main = new MediaPlayerPanel(m);
        this.setContentPane(this.main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Window w = new Window();
    }
}




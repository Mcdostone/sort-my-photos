package app.view.swing;

import javax.swing.*;
import app.conf.Configuration;
import app.model.MediaPlayer;
import app.util.MediaPlayerFactory;

import java.util.List;
import java.io.File;

/**
 * Window of the app
 *
 * @author Mcdostone
 */
public class Window extends JFrame {

    private JPanel home;
    private JPanel mediaPlayer;

    public Window() {
        super(Configuration.TITLE);
        this.init();
    }

    public Window(String title) {
        super(title);
        this.init();
    }

    private void init(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.launchHome();
        this.pack();
        this.setVisible(true);
    }


    public void launchHome() {
        this.home = new HomePanel(this);
        this.setContentPane(this.home);
    }

    public void launchMediaPlayer(List<File> paths) {
        MediaPlayer m = MediaPlayerFactory.createMediaPlayer(paths);
        this.mediaPlayer = new MediaPlayerPanel(m);

        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.setContentPane(this.mediaPlayer);
    }

    public static void main(String[] args) {
        Window w = new Window();
    }
}




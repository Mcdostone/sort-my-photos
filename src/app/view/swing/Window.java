package app.view.swing;

import app.conf.Configuration;
import app.model.MediaPlayer;
import app.util.MediaPlayerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Window of the app
 *
 * @author Mcdostone
 */
public class Window extends JFrame {

    // List of all different panels
    private JPanel home;
    private JLayeredPane mediaPlayer;

    private final static int SIZE = 650;


    public Window() {
        super(Configuration.TITLE);
        this.init();
    }

    private void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(SIZE, SIZE));
        this.setLayout(new BorderLayout());
        // First view
        this.launchHome();
        this.pack();
        this.requestFocus();
        this.setVisible(true);
    }


    /** Enables to display the 'home' view of the app */
    public void launchHome() {
        this.home = new HomePanel(this);
        this.setContentPane(this.home);
    }

    /** Enables to display the 'media player' view in the app */
    public void launchMediaPlayer(List<File> paths) {
        MediaPlayer m = MediaPlayerFactory.createMediaPlayer(paths);
        this.mediaPlayer = new MediaPlayerPanel(m);

        this.getContentPane().setVisible(false);
        this.getContentPane().removeAll();
        this.setContentPane(this.mediaPlayer);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Window w = new Window();
    }
}




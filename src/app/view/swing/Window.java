package app.view.swing;

import app.conf.Configuration;
import app.model.MediaLoader;
import app.model.MediaPlayer;
import app.model.MediaPlayerFactory;

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
    private JPanel container;
    private JPanel start;
    private JLayeredPane mediaPlayerPanel;

    private final static int SIZE = 650;


    public Window() {
        super(Configuration.getInstance().TITLE);
        this.container = new JPanel();
        this.container.setLayout(new BorderLayout());
        this.init();
    }

    private void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(SIZE, SIZE));
        this.setLayout(new BorderLayout());
        // First view
        this.start();
        this.pack();
        this.requestFocus();
        this.setVisible(true);
    }


    /** Enables to display the 'home' view of the app */
    public void start() {
        this.start = new StarterPanel(this);
        this.container.add(this.start);
        this.setContentPane(this.container);
    }

    /** Enables to display the 'media player' view in the app */
    public void launchMediaPlayer(List<File> paths) {
        MediaPlayer m = MediaPlayerFactory.createMediaPlayer(paths);
        //MediaLoader loader = MediaLoader.getInstance();
        /*int capa = loader.capacity();
        capa = capa / 2;
        for(int i = -capa; i <= capa; i++)
            loader.add(m.get(i));*/


        this.mediaPlayerPanel = new MediaPlayerPanel(m);
        this.container.remove(this.start);
        this.container.add(this.mediaPlayerPanel);
        //this.container.add(new OverlaySorting());

        //this.mediaPlayerPanel.addKeyListener(new ShortcutController());

        this.setContentPane(this.container);
        this.repaint();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {  e.printStackTrace();  }

        Window w = new Window();
    }
}




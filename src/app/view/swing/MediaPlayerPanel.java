package app.view.swing;

import app.conf.Configuration;
import app.controller.MediaPlayerController;
import app.model.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Main panel which displays medias in the JFrame.
 *
 * @author Mcdostone
 */
public class MediaPlayerPanel extends JPanel implements Observer {

    private MediaPlayer m;
    private MediaPanel p;

    public MediaPlayerPanel(MediaPlayer m) {
        super();
        this.m = m;
        this.p = new MediaPanel();

        MediaPlayerController controller = new MediaPlayerController(m, p);
        this.setLayout(new GridLayout(1,1));
        this.add(this.p);

        this.setPreferredSize(new Dimension(Configuration.WIDTH, Configuration.HEIGHT));
        this.setBackground(Color.BLACK);

        this.addMouseListener(controller);
        this.addKeyListener(controller);
        this.m.addObserver(this);
        this.p.setMedia(this.m.next());
    }


    @Override
    public void update(Observable o, Object arg) {
        this.p.repaint();
    }
}

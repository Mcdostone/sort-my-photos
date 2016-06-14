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
public class MediaPlayerPanel extends JLayeredPane implements Observer {

    private MediaPlayer m;
    private MediaPanel p;

    public MediaPlayerPanel(MediaPlayer m) {
        super();
        this.m = m;
        this.p = new MediaPanel();
        MediaPlayerController controller = new MediaPlayerController(m, p);
        this.setPreferredSize(new Dimension(Configuration.WIDTH, Configuration.HEIGHT));
        this.addMouseListener(controller);
        this.addKeyListener(controller);
        this.m.addObserver(this);

        this.add(this.p, 0);
        this.add(new TabBarPanel(), 1);
    }


    @Override
    public void update(Observable o, Object arg) {
        this.p.repaint();
    }


    @Override
    public void doLayout() {
        for (Component comp : getComponents()) {
            comp.setBounds(0, 0, getWidth(), getHeight());
        }
    }

}

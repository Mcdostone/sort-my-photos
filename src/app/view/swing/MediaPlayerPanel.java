package app.view.swing;

import app.conf.Configuration;
import app.model.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Main panel which displays medias in the JFrame.
 *
 * @author Mcdostone
 */
public class MediaPlayerPanel extends JLayeredPane implements Observer, ComponentListener {

    private MediaPlayer m;
    private MediaPanel p;
    private OverlaySorting overlay;
    private TabBarPanel bar;

    public MediaPlayerPanel(MediaPlayer m) {
        super();
        this.m = m;
        this.p = new MediaPanel(m);
        this.bar = new TabBarPanel();
        this.overlay = new OverlaySorting();

        this.setPreferredSize(new Dimension(Configuration.WIDTH, Configuration.HEIGHT));
        this.m.addObserver(this);
        this.add(this.p, 0);
        this.add(this.overlay, 1);
        this.add(this.bar, 2);

        this.p.setMedia(this.m.firstMedia());

        this.doLayout();
    }


    @Override
    public void update(Observable o, Object arg) {
        this.p.repaint();
        this.overlay.repaint();
        this.bar.repaint();
        this.doLayout();
    }

    public int calculateHeightTabBar() {
        return this.getHeight() / 12;
    }


    @Override
    public void doLayout() {
        int heightBar = this.calculateHeightTabBar();
        this.p.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.overlay.setBounds(0, this.getHeight()/2 - heightBar/2, this.getWidth(), heightBar);
        this.bar.setBounds(0, this.getHeight() - heightBar, this.getWidth(), heightBar);

        this.moveToFront(this.overlay);
        this.moveToFront(this.bar);
        this.p.requestFocusInWindow();
    }

    @Override
    public void componentResized(ComponentEvent e) {  this.doLayout();  }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}

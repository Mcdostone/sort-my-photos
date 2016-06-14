package app.controller;


import app.model.MediaPlayer;
import app.view.swing.MediaPlayerPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Mcdostone
 */
public class MediaPlayerController implements MouseListener, KeyListener {

    private MediaPlayer m;
    private MediaPlayerPanel p;

    public MediaPlayerController(MediaPlayer m, MediaPlayerPanel p) {
        this.m = m;
        this.p = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.p.current(this.m.next());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

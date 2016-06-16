package app.controller;


import app.model.MediaPlayer;
import app.view.swing.MediaPanel;
import app.view.swing.MediaPlayerPanel;

import javax.swing.*;
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
    private MediaPanel p;

    public MediaPlayerController(MediaPlayer m, MediaPanel p) {
        this.m = m;
        this.p = p;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e))
            this.p.setMedia(this.m.next());
        if(SwingUtilities.isRightMouseButton(e))
            this.p.setMedia(this.m.previous());
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        switch( e.getKeyCode() ) {
            case KeyEvent.VK_LEFT:
                this.p.setMedia(this.m.previous());
                break;
            case KeyEvent.VK_RIGHT :
                this.p.setMedia(this.m.next());
                break;
        }
    }
}

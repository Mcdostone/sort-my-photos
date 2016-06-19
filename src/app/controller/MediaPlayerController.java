package app.controller;


import app.model.MediaPlayer;
import app.view.swing.MediaPanel;

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

    private MediaPlayer mediaPlayer;
    private MediaPanel mediaPanel;

    public MediaPlayerController(MediaPlayer mediaPlayer, MediaPanel mediaPanel) {
        this.mediaPlayer = mediaPlayer;
        this.mediaPanel = mediaPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e))
            this.mediaPanel.setMedia(this.mediaPlayer.next());
        if(SwingUtilities.isRightMouseButton(e))
            this.mediaPanel.setMedia(this.mediaPlayer.previous());
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
                this.mediaPanel.setMedia(this.mediaPlayer.previous());
                break;
            case KeyEvent.VK_RIGHT :
                this.mediaPanel.setMedia(this.mediaPlayer.next());
                break;
        }
    }
}

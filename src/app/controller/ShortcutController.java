package app.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by mcdostone on 16/06/16.
 */
public class ShortcutController implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch( e.getKeyCode() ) {
            case 192:
                System.out.println("fofo");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}

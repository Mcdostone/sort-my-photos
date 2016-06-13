package app.view.swing;

import javax.swing.*;
import app.conf.Configuration;

/**
 * Window of the app
 *
 * @author Mcdostone
 */
public class Window extends JFrame {

    private JPanel main;

    public Window() {
        super(Configuration.TITLE);
        this.init();
    }

    public Window(String title) {
        super(title);
        this.init();
    }

    private void init(){
        this.main = new MediaPanel();
        this.setContentPane(this.main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Window w = new Window();
    }
}




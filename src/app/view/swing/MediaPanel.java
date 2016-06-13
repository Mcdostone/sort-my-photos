package app.view.swing;

import javax.swing.*;
import app.conf.Configuration;
import java.awt.*;

/**
 * Main panel which displays medias in the JFrame.
 *
 * @author Mcdostone
 */
public class MediaPanel extends JPanel {

    public MediaPanel() {
        super();
        this.setPreferredSize(new Dimension(Configuration.WIDTH, Configuration.HEIGHT));
        this.setBackground(Color.BLACK);
    }
}

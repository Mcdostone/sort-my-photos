package app.view.swing;

import app.util.UiKit;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mcdostone on 16/06/16.
 */
public class OverlaySorting extends JPanel {

    public OverlaySorting() {
        super();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.RED);
        this.add(new JPanel(), BorderLayout.CENTER);
        this.add(UiKit.circleButton(), BorderLayout.EAST);
        this.add(UiKit.circleButton(), BorderLayout.WEST);
    }




}

package app.view.swing;

import app.util.UiKit;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mcdostone on 16/06/16.
 */
public class OverlaySorting extends JPanel {

    private JLabel accept;
    private JLabel reject;

    public OverlaySorting() {
        super();
        this.setBackground(new Color(0,0,0,0));
        this.accept = UiKit.acceptIcon();
        this.reject = UiKit.rejectIcon();

        this.add(this.accept);
        this.add(this.reject);
    }

    @Override
    public void doLayout() {
        int dx = 10;
        this.reject.setBounds(dx, 0, this.getHeight(), this.getHeight());
        this.accept.setBounds(this.getWidth() - this.getHeight() - dx, 0, this.getHeight(), this.getHeight());
    }

}

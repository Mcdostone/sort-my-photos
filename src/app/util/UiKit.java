package app.util;

import app.conf.Configuration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * UI kit to get a user friendly GUI
 *
 * @author Mcdostone
 */
public class UiKit {

    private final static Color SECONDARY_COLOR = Color.WHITE;
    private final static Color SECONDARY_COLOR_PASSIVE = new Color(255, 255, 255, 100);

    private final static int LENGTH_BORDER = 10;
    private final static int SIZE_BORDER = 2;

    private final static Font DEFAULT_FONT = new Font("Arial",Font.PLAIN, 18);


    public static JPanel panel() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.setBackground(Configuration.PRIMARY_COLOR);
        return p;
    }


    public static JPanel dragAndDropArea(int width, int height) {
        JPanel p = panel();
        p.setPreferredSize(new Dimension(width, height));
        p.setBorder(BorderFactory.createDashedBorder(UiKit.SECONDARY_COLOR_PASSIVE, SIZE_BORDER, LENGTH_BORDER, LENGTH_BORDER, false));
        p.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {  p.setBorder(BorderFactory.createDashedBorder(UiKit.SECONDARY_COLOR, SIZE_BORDER, LENGTH_BORDER, LENGTH_BORDER, false));  }

            @Override
            public void mouseExited(MouseEvent e) {  p.setBorder(BorderFactory.createDashedBorder(UiKit.SECONDARY_COLOR_PASSIVE, SIZE_BORDER, LENGTH_BORDER, LENGTH_BORDER, false));  }
        });

        return p;
    }


    public static JLabel label(String text) {
        JLabel l = new JLabel(text, JLabel.CENTER);
        l.setForeground(SECONDARY_COLOR);
        l.setFont(UiKit.DEFAULT_FONT);
        return l;
    }


    public static JLabel Cliquablelabel(String text) {
        JLabel l = label(text);
        l.setPreferredSize(new Dimension(170, 50));
        l.setMaximumSize(new Dimension(170, 50));
        l.setMinimumSize(new Dimension(170, 50));
        l.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, SIZE_BORDER));
        l.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                l.setForeground(Configuration.PRIMARY_COLOR);
                l.setOpaque(true);
                l.setBackground(SECONDARY_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                l.setOpaque(false);
                l.setForeground(SECONDARY_COLOR);

            }
        });

        return l;
    }
}

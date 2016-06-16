package app.util;

import app.conf.Configuration;
import app.controller.ActionIconController;
import com.sun.jmx.remote.security.JMXPluggableAuthenticator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * UI kit to get a user friendly GUI
 *
 * @author Mcdostone
 */
public class UiKit {

    public static Color PRIMARY_COLOR = new Color(34, 34, 34);
    public final static Color SECONDARY_COLOR = Color.WHITE;
    public final static Color SECONDARY_COLOR_PASSIVE = new Color(255, 255, 255, 100);
    public final static Color TRANSPARANT = new Color(0, 0, 0, 0);

    public final static Color COLOR_ICON_HOVER = new Color(10, 10, 10, 255);
    public final static Color BACKGROUND_TABBAR = new Color(0,0,0, 120);

    public final static int PADDING_ICON = 70;

    public final static int LENGTH_BORDER = 10;
    public final static int SIZE_BORDER = 2;
    public final static Border BORDER_ICON_DEFAULT =  BorderFactory.createMatteBorder(1, 0, 0, 0, UiKit.COLOR_ICON_HOVER);
    public final static Border BORDER_ICON =  BorderFactory.createMatteBorder(1, 0, 0, 1, UiKit.COLOR_ICON_HOVER);
    public final static Font DEFAULT_FONT = new Font("Arial",Font.PLAIN, 18);
    public final static Font LOG_FONT = new Font("consolas",Font.ITALIC, 14);

    public static JPanel panel() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.setBackground(UiKit.PRIMARY_COLOR);
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
        l.setMaximumSize(l.getPreferredSize());
        l.setMinimumSize(l.getPreferredSize());
        l.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR, SIZE_BORDER));
        l.addMouseListener(new CliquableLabelListener(l));
        return l;
    }

    public static JLabel CliquableIcon(String filename, int height) {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(height - PADDING_ICON, height - PADDING_ICON, Image.SCALE_DEFAULT));
        JLabel l = new JLabel(imageIcon, JLabel.CENTER);
        l.setPreferredSize(new Dimension(0, height));
        l.setMaximumSize(l.getPreferredSize());
        l.setMinimumSize(l.getPreferredSize());
        l.setOpaque(false);
        l.setBorder(UiKit.BORDER_ICON_DEFAULT);
        l.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l.setBackground(UiKit.TRANSPARANT);

        return l;
    }

    public static JLabel settingsIcon(int height) {
        JLabel l = UiKit.CliquableIcon(Configuration.SETTINGS_ICON, height);
        l.addMouseListener(new ActionIconController("SETTINGS"));
        return l;
    }


    public static JLabel sortIcon(int height) {
        JLabel l = UiKit.CliquableIcon(Configuration.SORT_ICON, height);
        l.addMouseListener(new ActionIconController("SORT"));
        return l;
    }

    public static JLabel LogsIcon(int height) {
        JLabel l = UiKit.CliquableIcon(Configuration.LOGS_ICON, height);

        l.addMouseListener(new ActionIconController("LOGS"));
        return l;
    }

    private static class CliquableLabelListener implements MouseListener {
        private JLabel l;

        public CliquableLabelListener(JLabel l) {  this.l = l;  };

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        public void mouseEntered(MouseEvent e) {
            l.setForeground(UiKit.PRIMARY_COLOR);
            l.setOpaque(true);
            l.setBackground(SECONDARY_COLOR);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            l.setOpaque(false);
            l.setForeground(SECONDARY_COLOR);

        }
    }

    public static JLabel circleButton() {
        JLabel l = new JLabel("kfkfkkjfkjfkjf");
        l.setSize(new Dimension(200, 200));
        l.setBackground(Color.blue);
        return l;
    }
}

package app.view.swing;

import app.util.UiKit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TabBar for accessing to all settings and actions.
 *
 * @author Mcdostone
 */
public class TabBarPanel extends JPanel {

    private int height = 100;
    private MediaPlayerPanel player;
    private JLabel settings;
    private JLabel sort;
    private JLabel logs;

    public TabBarPanel(MediaPlayerPanel p) {
        super();
        this.player = p;
        this.setPreferredSize(new Dimension(0, this.height));
        this.setOpaque(true);
        this.setBackground(UiKit.BACKGROUND_TABBAR);
        this.setLayout(new GridLayout(1, 3));
        this.initIcons();

    }

    private void initIcons() {
        this.settings = UiKit.settingsIcon((int) this.getPreferredSize().getHeight());
        this.sort = UiKit.sortIcon((int) this.getPreferredSize().getHeight());
        this.sort.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                player.switchSorting();
            }
        });

        this.logs = UiKit.LogsIcon((int) this.getPreferredSize().getHeight());

        this.settings.setBorder(UiKit.BORDER_ICON);
        this.sort.setBorder(UiKit.BORDER_ICON);

        this.add(this.settings);
        this.add(this.sort);
        this.add(this.logs);
    }



}

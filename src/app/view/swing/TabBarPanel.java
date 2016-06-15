package app.view.swing;

import app.util.UiKit;
import javax.swing.*;
import java.awt.*;

/**
 * TabBar for accessing to all settings and actions.
 *
 * @author Mcdostone
 */
public class TabBarPanel extends JPanel {

    private int height = 100;
    private JLabel settings;
    private JLabel sort;
    private JLabel logs;

    public TabBarPanel() {
        super();
        this.setPreferredSize(new Dimension(0, this.height));
        this.setOpaque(true);
        this.setBackground(UiKit.BACKGROUND_TABBAR);
        this.setLayout(new GridLayout(1, 3));
        this.initIcons();
    }

    private void initIcons() {
        this.settings = UiKit.settingsIcon((int) this.getPreferredSize().getHeight());
        this.sort = UiKit.sortIcon((int) this.getPreferredSize().getHeight());
        this.logs = UiKit.LogsIcon((int) this.getPreferredSize().getHeight());

        this.settings.setBorder(UiKit.BORDER_ICON);
        this.sort.setBorder(UiKit.BORDER_ICON);

        this.add(this.settings);
        this.add(this.sort);
        this.add(this.logs);
    }



}

package app.view.swing;

import app.conf.Configuration;
import app.controller.DragAndDropController;
import app.controller.FileChooserController;
import app.util.UiKit;
import com.sun.java.swing.plaf.windows.WindowsFileChooserUI;

import javax.swing.*;
import java.awt.*;

/**
 * Defines the first view of the app when you run it !
 * The first view enables to know all medias you want to sort.
 * That's why, you have the possibility to choose a directory thanks to the dedicated button
 * But you can also 'drag and drop' a folder.
 *
 * @author Mcdostone
 */
public class StarterPanel extends JPanel {

    public StarterPanel(Window w) {
        super();
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(Configuration.WIDTH, Configuration.HEIGHT));

        this.setBackground(UiKit.PRIMARY_COLOR);

        JLabel choose = UiKit.Cliquablelabel("Choose folder ...");
        choose.addMouseListener(new FileChooserController(w));
        this.setDropTarget(new DragAndDropController(w));
        JPanel drag = UiKit.dragAndDropArea(600, 600);
        drag.add(UiKit.label("Drag & drop a folder or  "));
        drag.add(choose);
        this.add(drag);
    }
}

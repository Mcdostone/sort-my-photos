package app.view.swing;

import app.controller.DragAndDropController;
import app.controller.FileChooserController;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 * Created by Gang du stud' on 14/06/2016.
 */
public class HomePanel extends JPanel {

    private JButton choose;
    private JFileChooser chooser;

    public HomePanel(Window w) {
        super();
        this.choose = new JButton("Choose folder");
        this.add(this.choose);
        this.choose.addActionListener(new FileChooserController(this, w));
        this.setDropTarget(new DragAndDropController(w));
    }

}

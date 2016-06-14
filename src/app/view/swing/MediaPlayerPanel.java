package app.view.swing;

import app.conf.Configuration;
import app.controller.MediaPlayerController;
import app.model.MediaPlayer;
import app.util.MediaFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Main panel which displays medias in the JFrame.
 *
 * @author Mcdostone
 */
public class MediaPlayerPanel extends JPanel implements Observer {

    private MediaPlayer m;
    private MediaPanel p;

    public MediaPlayerPanel(MediaPlayer m) {
        super();
        this.m = m;
        this.p = new MediaPanel();

        MediaPlayerController controller = new MediaPlayerController(m, p);
        this.setLayout(new GridLayout(1,1));
        this.add(this.p);

        this.setPreferredSize(new Dimension(Configuration.WIDTH, Configuration.HEIGHT));
        this.setBackground(Color.BLACK);

        this.initDragAndDrop();
        this.addMouseListener(controller);
        this.addKeyListener(controller);

        this.m.addObserver(this);
    }


    private void initDragAndDrop() {
        this.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {

                        if (file.isDirectory()) {
                            Files.walk(Paths.get(file.getAbsolutePath())).forEach(filePath -> {
                                if (Files.isRegularFile(filePath))
                                    m.addMedia(MediaFactory.create(filePath.toString()));
                            });
                        }
                        else
                        if (Files.isRegularFile(Paths.get(file.getAbsolutePath())))  m.addMedia(MediaFactory.create(file.getAbsolutePath()));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }

                p.setMedia(m.next());
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.p.repaint();
    }
}

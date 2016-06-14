package app.view.swing;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import app.conf.Configuration;
import app.controller.MediaPlayerController;
import app.model.Media;
import app.model.MediaPlayer;
import app.util.MediaFactory;

/**
 * Main panel which displays medias in the JFrame.
 *
 * @author Mcdostone
 */
public class MediaPlayerPanel extends JPanel implements Observer {

    private MediaPlayer m;
    private Media current;

    public MediaPlayerPanel(MediaPlayer m) {
        super();
        this.m = m;
        this.setPreferredSize(new Dimension(Configuration.WIDTH, Configuration.HEIGHT));
        this.setBackground(Color.BLACK);
        this.initDragAndDrop();
        this.m.addObserver(this);
        MediaPlayerController controller = new MediaPlayerController(m, this);
        this.addMouseListener(controller);
        this.addKeyListener(controller);
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

                current = m.next();
            }
        });
    }

    public void current(Media m) {  this.current = m;  }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            if(this.current != null)
                image = ImageIO.read(new File(this.current.getPath()));
        } catch (IOException e)  {
            e.printStackTrace();
        }
        if(image != null)  g.drawImage(image, 0, 0, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}

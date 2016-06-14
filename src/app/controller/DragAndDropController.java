package app.controller;

import app.view.swing.Window;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

/**
 * Created by Gang du stud' on 14/06/2016.
 */
public class DragAndDropController extends DropTarget {
    private Window w;

    public DragAndDropController(Window w) {  this.w = w;  }

    public synchronized void drop(DropTargetDropEvent evt) {
        try {
            evt.acceptDrop(DnDConstants.ACTION_COPY);
            List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    /*for (File file : droppedFiles) {

                        if (file.isDirectory()) {
                            Files.walk(Paths.get(file.getAbsolutePath())).forEach(filePath -> {
                                if (Files.isRegularFile(filePath))
                                    m.addMedia(MediaFactory.create(filePath.toString()));
                            });
                        }
                        else
                        if (Files.isRegularFile(Paths.get(file.getAbsolutePath())))  m.addMedia(MediaFactory.create(file.getAbsolutePath()));
                    }*/
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

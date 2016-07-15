package app.controller;

import app.view.swing.Window;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * the 'Drag and drop' FX.view.controller when you start the app.
 *
 * @author Mcdostone
 */
public class DragAndDropController extends DropTarget {
    // interact with the main Window when somethin is dropped */
    private Window w;

    /** Unique constructor */
    public DragAndDropController(Window w) {  this.w = w;  }

    @Override
    public synchronized void drop(DropTargetDropEvent evt) {
        evt.acceptDrop(DnDConstants.ACTION_COPY);
        try {
            List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            this.w.launchMediaPlayer(droppedFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

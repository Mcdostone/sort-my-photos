package app.view.swing;

import app.controller.MediaPlayerController;
import app.model.Media;
import app.model.MediaPlayer;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel which displays a given media
 *
 * @author Mcdostone
 */
public class MediaPanel extends JPanel {

    private Media current;

    public MediaPanel(MediaPlayer m) {
        super();
        this.setBackground(Color.darkGray);
        MediaPlayerController controller = new MediaPlayerController(m, this);
        this.setFocusable(true);
        this.addMouseListener(controller);
        this.addKeyListener(controller);
    }

    public void setMedia(Media m) {
        this.current = m;
        LogsWindow.getInstance().update("#DISPLAY\t" + this.current.toString());
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        //BufferedImage image = MediaLoader.getInstance().getImage(this.current);
        //System.out.println(this.current + "\n" + image + "\n");

        /*if(image != null) {
            Dimension scale = MediaPanel.getScaledDimension(new Dimension(image.getWidth(), image.getHeight()), this.getSize());
            Dimension pos = MediaPanel.getPosition(scale, this.getSize());
            g.drawImage(image, (int) pos.getWidth(), (int) pos.getHeight(), (int) scale.getWidth(), (int) scale.getHeight(), null);
        }*/
    }

    /** Thank you StackOverFlow */
    private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    private static Dimension getPosition(Dimension dim, Dimension boundary) {
        int dx = ((int) (boundary.getWidth() - dim.getWidth())) / 2;
        int dy = (int) (boundary.getHeight() - dim.getHeight()) / 2;

        return new Dimension(dx, dy);
    }
}

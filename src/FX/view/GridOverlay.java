package FX.view;

import app.conf.Configuration;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Grid Overlay
 *
 * @author Mcdostone
 */
public class GridOverlay extends StackPane {

    private Canvas canvas;


    public GridOverlay() {
        super();
        this.canvas = new Canvas();
        this.getChildren().add(this.canvas);
        this.canvas.toFront();
    }

    @Override
    protected void layoutChildren() {
        int w = (int) this.getWidth();
        int h = (int) this.getHeight();
        double spacingX = w /3.0;
        double spacingY = h /3.0;

        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, w, h);
        g.setLineWidth(1.0);
        g.setStroke(Configuration.COLOR_GRID);

        this.canvas.setLayoutX(0);
        this.canvas.setLayoutY(0);
        this.canvas.setWidth(w);
        this.canvas.setHeight(h);

        for (int x = (int) spacingX; x <= w - spacingX; x += spacingX)
            g.strokeLine(x + 0.5,0, x + 0.5, h);

        for (int y = (int) spacingY; y <= h - spacingY; y += spacingY)
            g.strokeLine(0,y + 0.5, w, y + 0.5);
    }
}

package FX.view;

import app.conf.Configuration;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

import java.util.Observable;
import java.util.Observer;

/**
 * Grid Overlay which improve visual sorting.
 *
 * @author Mcdostone
 */
public class GridOverlay extends StackPane implements Observer {

    /** Grid will be drawn on this canvas */
    private Canvas canvas;
    private Node over;

    /** Default constructor */
    public GridOverlay(boolean visible) {
        super();
        this.canvas = new Canvas();
        this.getChildren().add(this.canvas);
        this.setMouseTransparent(true);
        this.setVisible(visible);
    }

    public void setOverPane(Node p) {
        this.over = p;
        this.over.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            this.setPrefSize(newValue.getWidth(), newValue.getHeight());
            this.setLayoutX(newValue.getMinX());
            this.setLayoutY(newValue.getMinY());
        });
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
        g.setStroke(Configuration.getInstance().getColorGrid());

        this.canvas.setLayoutX(0);
        this.canvas.setLayoutY(0);
        this.canvas.setWidth(w);
        this.canvas.setHeight(h);

        for (int x = (int) spacingX; x <= w - spacingX; x += spacingX)
            g.strokeLine(x + 0.5,0, x + 0.5, h);

        for (int y = (int) spacingY; y <= h - spacingY; y += spacingY)
            g.strokeLine(0,y + 0.5, w, y + 0.5);
    }

    @Override
    public void update(Observable o, Object arg) {  this.layoutChildren();  }
}

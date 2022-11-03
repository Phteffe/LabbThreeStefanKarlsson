package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Shape {
    public Circle(double x, double y) {
        super(x, y);
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(getX()-10,getY()-10,20,20);
    }
}

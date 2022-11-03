package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Shape{
    public Rectangle(double x, double y) {
        super(x, y);
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(20,20,150,100);
    }
}

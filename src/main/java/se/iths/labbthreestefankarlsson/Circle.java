package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Shape {
    public Circle(double x, double y, Integer size) {
        super(x, y,size);
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(getxPos()-10,getyPos()-10,20,20);
    }
}

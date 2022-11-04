package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    public Circle(double x, double y, Integer size, Color color) {
        super(x, y,size,color);
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(this.getColor());
        context.fillOval(getxPos()-getSize()/2,getyPos()-getSize()/2,getSize(),getSize());
    }
}

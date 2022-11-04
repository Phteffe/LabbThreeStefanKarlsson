package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{
    public Rectangle(double x, double y, Integer size, Color color) {
        super(x,y,size,color);
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(this.getColor());
        context.fillRect(getxPos()-getSize()/2,getyPos()-getSize()/2,getSize()*2,getSize());
    }
}

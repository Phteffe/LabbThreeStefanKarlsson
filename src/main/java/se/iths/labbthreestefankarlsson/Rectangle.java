package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Shape{
    public Rectangle(double x, double y, Integer size) {
        super(x,y,size);
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(getxPos()-getSize()/2,getyPos()-getSize()/2,getSize()*2,getSize());
    }
}

package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    public Circle(double x, double y, Integer size, Color color) {
        super(x, y, size, color);
    }
    public Circle(Shape shape){
        super(shape);
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(this.getColor());
        context.setFill(this.getColor());
        context.fillOval(getxPos() - getSize() / 2, getyPos() - getSize() / 2, getSize(), getSize());
    }

    @Override
    public boolean isInsideShape(double x, double y) {
        double xPos = getxPos() - getSize()/2;
        double yPos = getyPos() - getSize()/2;

        return x >= xPos &&
                x <= xPos + getSize() &&
                y >= yPos &&
                y <= yPos + getSize();

    }

    @Override
    public Shape copyShape() {
        return new Circle(this);
    }

    @Override
    public String writeSvg() {
        String svgColorCode = "#" + getColor().toString().substring(2,10);
        return "<circle fill=\"" + svgColorCode +
                "\" r=\"" + getSize()/2  +
                "\" cx=\"" + (getxPos() + getSize()/2) +
                "\" cy=\"" + (getyPos() + getSize()/2) +
                "\" />";
    }

}

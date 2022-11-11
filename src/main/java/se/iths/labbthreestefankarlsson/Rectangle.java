package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    public Rectangle(double x, double y, Integer size, Color color) {
        super(x, y, size, color);
    }
    public Rectangle(Shape shape){
        super(shape);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(this.getColor());
        context.fillRect(getxPos() - getSize() / 2, getyPos() - getSize() / 2, getSize() * 2, getSize());
    }

    @Override
    public boolean isInsideShape(double x, double y) {
        double xPos = getxPos() - getSize()/2;
        double yPos = getyPos() - getSize()/2;

        return x >= xPos &&
                x <= xPos + getSize()*2 &&
                y >= yPos &&
                y <= yPos + getSize();
    }

    @Override
    public Shape copyShape() {
        return new Rectangle(this);
    }

    @Override
    public String writeSvg() {
        String svgColorCode = "#" + getColor().toString().substring(2,10);
        return "<rect fill=\"" + svgColorCode +
                "\" width=\"" + (getSize() * 2)  +
                "\" height=\"" + getSize()  +
                "\" x=\"" + getxPos() +
                "\" y=\"" + getyPos() +
                "\" />";
    }
}


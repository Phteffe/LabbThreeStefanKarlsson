package se.iths.labbthreestefankarlsson;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private final DoubleProperty xPos = new SimpleDoubleProperty();
    private final DoubleProperty yPos = new SimpleDoubleProperty();
    private Color color;
    private final DoubleProperty size = new SimpleDoubleProperty();

    public Shape(double x, double y, Integer size) {
        setxPos(x);
        setyPos(y);
        setSize(size);
    }
    public Shape (ShapeType shape) {

    }

    public double getxPos() {
        return xPos.get();
    }

    public DoubleProperty xPosProperty() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos.set(xPos);
    }

    public double getyPos() {
        return yPos.get();
    }

    public DoubleProperty yPosProperty() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos.set(yPos);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSize() {
        return size.get();
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(double size) {
        this.size.set(size);
    }

    public abstract void draw(GraphicsContext context);

    public static Shape createShape(ShapeType shapeType, double x, double y, Integer size) {
        return switch (shapeType) {
            case CIRCLE -> new Circle(x, y, size);
            case RECTANGLE -> new Rectangle(x,y,size);
        };
    }
}

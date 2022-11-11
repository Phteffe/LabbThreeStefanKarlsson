package se.iths.labbthreestefankarlsson;

import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private final DoubleProperty xPos = new SimpleDoubleProperty();
    private final DoubleProperty yPos = new SimpleDoubleProperty();
    private ObjectProperty<Color> color = new SimpleObjectProperty<>();
    private final DoubleProperty size = new SimpleDoubleProperty();

    public Shape(double x, double y, Integer size, Color color) {
        setxPos(x);
        setyPos(y);
        setSize(size);
        setColor(color);
    }

    public Shape(Shape shape) {
        setColor(shape.getColor());
        setSize(shape.getSize());
        setyPos(shape.getyPos());
        setxPos(shape.getxPos());
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
        return color.get();
    }

    public void setColor(Color color) {
        this.color.set(color);
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

//    public static Shape createShape(ShapeType shapeType, double x, double y, Integer size, Color color) {
//        return switch (shapeType) {
//            case CIRCLE -> new Circle(x, y, size, color);
//            case RECTANGLE -> new Rectangle(x, y, size, color);
//        };
//    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }
    public abstract boolean isInsideShape(double x, double y);
    public abstract Shape copyShape();
    public abstract String writeSvg();

}

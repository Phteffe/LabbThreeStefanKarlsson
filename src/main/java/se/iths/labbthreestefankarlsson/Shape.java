package se.iths.labbthreestefankarlsson;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private final double x;
    private final double y;

    private Color color;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
        color = Color.BLACK;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public abstract void draw(GraphicsContext context);

    public static Shape createShape(ShapeType shapeType, double x, double y){
        return switch (shapeType) {
            case CIRCLE -> new Circle(x,y);
            case RECTANGLE -> new Rectangle(x,y);
        };
    }
}

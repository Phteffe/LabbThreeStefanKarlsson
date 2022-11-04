package se.iths.labbthreestefankarlsson;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Model {
    ObjectProperty<ShapeType> currentShapeType = new SimpleObjectProperty<>(ShapeType.CIRCLE);
    ObservableList<ObservableShape> shapes;
    private final ObjectProperty<Integer> size;


    public Model() {
        this.shapes = FXCollections.observableArrayList(
                shape -> new Observable[]{
                        shape.xPosProperty(),
                        shape.yPosProperty(),
                        shape.sizeProperty()
                }
        );
        this.size = new SimpleObjectProperty<>(10);

    }

    public ShapeType getCurrentShapeType() {
        return currentShapeType.get();
    }

    public ObjectProperty<ShapeType> currentShapeTypeProperty() {
        return currentShapeType;
    }

    public ObservableList<? extends Shape> getShapes() {
        return shapes;
    }

    public Shape addShape(Shape shape) {
        var oShape = new ObservableShape(shape);
        shapes.add(oShape);
        return oShape;
    }
    public ObjectProperty<Integer> sizeProperty() {
        return size;
    }
    public Integer getSize(){
        return size.get();
    }


}

class ObservableShape extends Shape {
    Shape shape;
    ObjectProperty<Color> color = new SimpleObjectProperty<>();


    public ObservableShape(Shape shape) {
        super(shape.getxPos(), shape.getyPos(), (int) shape.getSize());
        this.shape = shape;
        color.set(shape.getColor());
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    @Override
    public Color getColor() {
        return color.get();
    }

    @Override
    public void setColor(Color color) {
        shape.setColor(color);
        this.color.set(color);
    }

    @Override
    public void draw(GraphicsContext context) {
        this.shape.draw(context);
    }

}

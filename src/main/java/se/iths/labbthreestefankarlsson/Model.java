package se.iths.labbthreestefankarlsson;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Model {
    ObjectProperty<ShapeType> currentShapeType = new SimpleObjectProperty<>(ShapeType.CIRCLE);
    ObservableList<ObservableShape> shapes = FXCollections.observableArrayList(param -> new Observable[]{param.colorProperty()});

    public ShapeType getCurrentShapeType() {
        return currentShapeType.get();
    }

    public ObjectProperty<ShapeType> currentShapeTypeProperty(){
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


}
class ObservableShape extends Shape{
    Shape shape;
    ObjectProperty<Color> color = new SimpleObjectProperty<>();


    public ObservableShape(Shape shape) {
        super(shape.getX(), shape.getY());
        this.shape = shape;
        color.set(shape.getColor());
    }
    public ObjectProperty<Color> colorProperty(){
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

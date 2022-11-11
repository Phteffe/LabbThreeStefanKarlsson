package se.iths.labbthreestefankarlsson;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;

public class Model {
    ObjectProperty<ShapeType> currentShapeType = new SimpleObjectProperty<>(ShapeType.CIRCLE);
    ObservableList<Shape> shapes;
    private final ObjectProperty<Integer> size;
    private final ObjectProperty<Color> color;

    public Deque<ObservableList<Shape>> undoShapeDeque;
    public ObservableList<Shape> selectedShapes;


    public Model() {
        this.shapes = FXCollections.observableArrayList(
                shape -> new Observable[]{
                        shape.xPosProperty(),
                        shape.yPosProperty(),
                        shape.sizeProperty(),
                        shape.colorProperty()
                }
        );
        this.size = new SimpleObjectProperty<>(50);
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.selectedShapes = FXCollections.observableArrayList();
        this.undoShapeDeque = new ArrayDeque<>();
    }

    public ObservableList<Shape> getTempShapeList() {
        ObservableList<Shape> tempList = FXCollections.observableArrayList();

        for (var shape : shapes) {
            tempList.add(shape.copyShape());
        }
        return tempList;
    }

    public void addToUndoDeque() {
        ObservableList<Shape> tempList = getTempShapeList();
        undoShapeDeque.addLast(tempList);
    }

    public void undo() {
        if (undoShapeDeque.isEmpty())
            return;

        shapes.clear();
        shapes.addAll(undoShapeDeque.removeLast());
    }

    public void deleteSelectedShape() {
        for (var shape : selectedShapes) {
            shapes.remove(shape);
        }
    }

    public void changeColorOfSelected() {
        for (var shape : selectedShapes) {
            shape.setColor(getColor());
        }
    }

    public void changeSizeOfSelected() {
        for (var shape : selectedShapes) {
            shape.setSize(getSize());
        }
    }


    public ObservableList<? extends Shape> getShapes() {
        return shapes;
    }


    public ObjectProperty<Integer> sizeProperty() {
        return size;
    }

    public Integer getSize() {
        return size.get();
    }

    public void draw(GraphicsContext context) {
        for (var shape : shapes) {
            shape.draw(context);
        }
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public Color getColor() {
        return color.get();
    }
}


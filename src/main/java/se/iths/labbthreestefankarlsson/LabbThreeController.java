package se.iths.labbthreestefankarlsson;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LabbThreeController {
    public Canvas canvas;
    public ChoiceBox<ShapeType> choiceBox;
    public Spinner<Integer> spinner;
    public GraphicsContext context;
    public ColorPicker colorPicker;

    Model model = new Model();
    ObservableList<ShapeType> shapeTypesList = FXCollections.observableArrayList(ShapeType.values());

    public void initialize() {
        choiceBox.setItems(shapeTypesList);
        choiceBox.valueProperty().bindBidirectional(model.currentShapeTypeProperty());
        model.getShapes().addListener(this::listChanged);

        SpinnerValueFactory<Integer> spinnerVal = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        spinner.setValueFactory(spinnerVal);
        spinnerVal.valueProperty().bindBidirectional(model.sizeProperty());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());

    }

    private void draw() {
        for (var shape:model.shapes) {
            shape.draw(context);
        }
    }
    public void init(Stage stage) {
        stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    final KeyCombination ctrlz = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
                    final KeyCombination ctrlShiftZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);

                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (ctrlz.match(keyEvent)) {
                            System.out.println("Undo");
                            keyEvent.consume();
                        } else if (ctrlShiftZ.match(keyEvent)) {
                            System.out.println("Redo");
                            keyEvent.consume();
                        }
                    }
                });
    }

    private void listChanged(Observable observable) {
        var context = canvas.getGraphicsContext2D();
        for (Shape s : model.getShapes()) {
            s.draw(context);
        }
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        Shape shape = Shape.createShape(model.getCurrentShapeType(), mouseEvent.getX(), mouseEvent.getY(), model.getSize(), model.getColor());
        model.addShape(shape);
        var context = canvas.getGraphicsContext2D();
        for (Shape s : model.getShapes()) {
            s.draw(context);
        }
    }


}


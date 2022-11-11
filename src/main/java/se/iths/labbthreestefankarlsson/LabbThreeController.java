package se.iths.labbthreestefankarlsson;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.iths.labbthreestefankarlsson.svg.SVGWriter;

public class LabbThreeController {
    public Canvas canvas;
    public Button rectangleButton;
    public Button circleButton;
    public Spinner<Integer> spinner;
    public GraphicsContext context;
    public ColorPicker colorPicker;
    public Model model;
    public CheckBox checkBox;
    public Button deleteButton;
    public Button undoButton;
    public Button changeColorButton;
    public Button changeSizeButton;
    public Button saveButton;

    ObservableList<Shape> shapeTypesList = FXCollections.observableArrayList();

    public void initialize() {
        model = new Model();
        context = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        renderCanvas();

//        model.getShapes().addListener(this::listChanged);

        SpinnerValueFactory<Integer> spinnerVal = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spinner.setValueFactory(spinnerVal);

        spinnerVal.valueProperty().bindBidirectional(model.sizeProperty());

        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
    }

    private void draw() {
        model.draw(context);
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

    private void selectShapes(Shape shape) {
        if (model.selectedShapes.contains(shape)) {
            model.selectedShapes.remove(shape);
            System.out.println("DE_SELECTED");
        } else {
            model.selectedShapes.add(shape);
            System.out.println("SELECTED");
        }
    }

    public void deleteMarkedShapes() {
        model.addToUndoDeque();
        model.deleteSelectedShape();
        renderCanvas();
        draw();
    }

    private void listChanged(Observable observable) {
        var context = canvas.getGraphicsContext2D();
        for (Shape s : model.getShapes()) {
            s.draw(context);
        }
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        drawOnClicked(mouseEvent);
        renderCanvas();
        draw();
    }

    private void drawOnClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        if (checkBox.isSelected()){
            for (var shape: model.shapes) {
                if (shape.isInsideShape(x,y)) {
                    selectShapes(shape);
                }
            }
        }
        shapeType(x,y);
    }

    public void renderCanvas() {
        context.setFill(Color.WHITE);
        context.fillRect(0,0,610,713);
    }
    public void shapeType(double x, double y) {
        if (rectangleButton.isFocused()) {
            addRectangleToObsList(x,y);
        } else if (circleButton.isFocused()) {
            addCircleToObsList(x,y);
        }
    }

    private void addCircleToObsList(double x, double y) {
        model.shapes.add(new Circle(x,y,model.getSize(),model.getColor()));
        shapeTypesList.add(new Circle(x,y, model.getSize(), model.getColor()));
    }

    private void addRectangleToObsList(double x, double y) {
        model.shapes.add(new Rectangle(x,y,model.getSize(),model.getColor()));
        shapeTypesList.add(new Rectangle(x,y, model.getSize(), model.getColor()));
    }

    public void changeColorOfSelected() {
        model.addToUndoDeque();
        model.changeColorOfSelected();
        renderCanvas();
        draw();
    }

    public void changeSizeOfSelected() {
        model.addToUndoDeque();
        model.changeSizeOfSelected();
        renderCanvas();
        draw();
    }
    public void undo() {
        model.undo();
        renderCanvas();
        draw();
    }

    public void saveFile() {
        SVGWriter svgFile = new SVGWriter();
        svgFile.saveToFile(model);
    }
}

